package com.pji.alexa.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pji.alexa.model.v2.AddCartItem;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.ProductDescription;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.services.CartService;
import com.pji.alexa.services.CustomerInfoService;
import com.pji.alexa.services.OrderReadyEstimateService;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;

@Component
public class OrderSpeechHelper {

	@Autowired
	private Util util;


	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderReadyEstimateService orderReadyEstimateService;
	
	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}	
	
	/**
	 * This method returns the prompt for listing the favorites
	 * @param customerId
	 * @param customerToken
	 * @return
	 */
	public Map<String, String> getPromptForOrderDetails(List<Item> itemlList, String itemId, String orderType) throws Exception{
		StringBuilder orderDetails = new StringBuilder();
		StringBuilder orderDetailsForCard = new StringBuilder();
		StringBuilder orderTypeDetails = new StringBuilder();
		Map<String, String> promptForOrderConfirmationMap	=	new HashMap<>();
		for(Item item: itemlList) {
			if(item.getItemId().equalsIgnoreCase(itemId)) {
				List<ProductDescription> productDescriptionList= item.getItemDescription();
				StringBuilder commaSeparatedProductList= new StringBuilder();
				int ctr=0;
				for(ProductDescription productDescription:productDescriptionList) {
					ctr++;
					if(ctr == productDescriptionList.size() &&  productDescriptionList.size() != 1) {
						commaSeparatedProductList.append(Constants.VERBAGE_FAVORITE_RECENT_PRODUCT_ITEM_AND + " ");
					}
					commaSeparatedProductList.append(productDescription.getProductQuantity());
					commaSeparatedProductList.append(" ");
					commaSeparatedProductList.append(productDescription.getProductName());
					if(ctr == productDescriptionList.size()) {
						commaSeparatedProductList.append(". ");
					}else {
						commaSeparatedProductList.append(", ");
					}			
				}
				if(orderType.equalsIgnoreCase(Constants.ORDER_TYPE_FAVORITE)) {
					orderTypeDetails.append(orderType);
					orderTypeDetails.append("- The ");
					orderTypeDetails.append(item.getItemName());
				}else if(orderType.equalsIgnoreCase(Constants.ORDER_TYPE_RECENT)) {
					orderTypeDetails.append(Constants.INTERACTION_STEP_RECENT_ORDER);
				}
				orderTypeDetails.append(", ");
				orderDetails.append(this.getUtil().getVerbiage(Constants.VERBAGE_ORDER_CONFIRM_ITEMS_DESC,orderTypeDetails.toString(),commaSeparatedProductList.toString()));
				orderDetailsForCard.append(this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_CONFIRM_ITEMS_DESC,orderTypeDetails.toString(),commaSeparatedProductList.toString()));//need to update the verbiage
				break;
			}
		}
		promptForOrderConfirmationMap.put("SpeechPrompt", orderDetails.toString());
		promptForOrderConfirmationMap.put("SpeechResponseForCard", orderDetailsForCard.toString());
		return promptForOrderConfirmationMap;
	}
	
	/**
	 * This method searches the utterence to get the cardid
	 * @param utteredString
	 * @param userdata
	 * @param alexaOrder
	 * @throws Exception
	 */
		public boolean searchAndUpdateCardDetails(String utteredString, UserDataItems userdata, AlexaOrder alexaOrder) throws Exception{
			boolean cardDetailsUpdated= false;
			List<Item> cardDetailsList= userdata.getPaymentList();
			String itemName= null;
			String itemId= null;
			itemId	=	this.getUtil().findItem(utteredString, cardDetailsList);
			if(StringUtils.isNotEmpty(itemId)) {
				for(Item paymentItem: cardDetailsList) {
					if(paymentItem.getItemId()	==	itemId) {
						itemName = 	paymentItem.getItemName();
						cardDetailsUpdated= true;
						break;
					}
				}
			}
			if(cardDetailsUpdated) {
				if(Constants.ALEXA_PAYMENT_TYPE_CASH.equalsIgnoreCase(itemName)) {
					alexaOrder.setPaymentType(Constants.ALEXA_PAYMENT_TYPE_CASH);
				}else {
					alexaOrder.setPaymentType(Constants.ALEXA_PAYMENT_TYPE_CARD);
					alexaOrder.setCardId(itemId);
				}
			}
			return cardDetailsUpdated;
		}	
	
	/**
	 * This method returns total price and asks user for confirmation
	 * @param customerId
	 * @param storeId
	 * @param customerToken
	 * @param favoriteId
	 * @param deliveryTerritoryId
	 * @return
	 */
		public Map<String, String> getPromptForCartConfirmation(String customerId, String storeId, String customerToken, String favoriteId, String pastOrderNumber, int deliveryTerritoryId, String paymentType) throws Exception{
			String speechPrompt=null;
			String speechResponseForCard	=	null;
			Map<String, String>	promptCartConfirmationMap	=	new HashMap<String, String>();
			AddCartItem addCartItem = cartService.getCartItem(customerId, storeId, customerToken, favoriteId, pastOrderNumber, deliveryTerritoryId);
			Double grandTotal = cartService.getGrandTotal(addCartItem, customerToken);
			
			if(!Constants.ALEXA_PAYMENT_TYPE_CASH.equalsIgnoreCase(paymentType)) {
				speechPrompt = util.getVerbiage(Constants.VERBAGE_GRAND_TOTAL, grandTotal);
				speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_GRAND_TOTAL, grandTotal);
			}
			else {
				speechPrompt = util.getVerbiage(Constants.VERBAGE_GRAND_TOTAL_IN_CASH, grandTotal);
				speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_GRAND_TOTAL_IN_CASH, grandTotal);// need to update the verbiage
			}
			promptCartConfirmationMap.put("SpeechPrompt", speechPrompt);
			promptCartConfirmationMap.put("SpeechResponseForCard", speechResponseForCard);
			return promptCartConfirmationMap;
		}
	
	/**
	 * This methods order success message along with order number
	 * @param customerId
	 * @param customerToken
	 * @param storeId
	 * @param cardId
	 * @param favoriteId
	 * @param addressId
	 * @param deliveryTerritoryId
	 * @return
	 */
		public Map<String, String> getPromptForOrderSubmission(String orderNumber, String subId) {
			String speechPrompt=null;
			String speechResponseForCard=null;
			Map<String, String> promptOrderSubmissionMap	=	new HashMap<>();
			Map<String, String> promptOrderReadyEstimateMap	=	new HashMap<>();
			promptOrderReadyEstimateMap	=	getPromptForOrderReadyEstimate(orderNumber, subId);
			speechPrompt = util.getVerbiage(Constants.VERBAGE_ORDER_SUCCESS, promptOrderReadyEstimateMap.get("OrderReadyEstimateSpeech"));
			speechResponseForCard=	util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_SUCCESS, promptOrderReadyEstimateMap.get("OrderReadyEstimateSpeechForCard"));//need to change the verbiage
			promptOrderSubmissionMap.put("SpeechPrompt", speechPrompt);
			promptOrderSubmissionMap.put("SpeechResponseForCard", speechResponseForCard);
			return promptOrderSubmissionMap;
		}
		
		public Map<String, String> getPromptForOrderReadyEstimate(String orderNumber, String subId) {
			String orderReadyEstimateSpeech = 	"";
			String orderReadyEstimateSpeechForCard	=	"";
			Map<String, String> promptOrderReadyEstimateMap	=	new HashMap<>();
			try {
				String orderReadyEstimate = orderReadyEstimateService.getOrderReadyEstimate(orderNumber, subId);
				orderReadyEstimateSpeech = util.getVerbiage(Constants.VERBAGE_ORDER_READY_ESTIMATE, orderReadyEstimate);
				orderReadyEstimateSpeechForCard	=	util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_READY_ESTIMATE, orderReadyEstimate);// need to change the verbiage
				promptOrderReadyEstimateMap.put("OrderReadyEstimateSpeech", orderReadyEstimateSpeech);
				promptOrderReadyEstimateMap.put("OrderReadyEstimateSpeechForCard", orderReadyEstimateSpeechForCard);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return promptOrderReadyEstimateMap;
		}
	/**
	 * This method returns the card number prompt
	 * @param customerId
	 * @param customerToken
	 * @param cardId
	 * @return
	 */
	public Map<String, String> getPromptForPaymentDetails(List<Item> itemList, boolean firstTimePrompt) {
		String speechPrompt=null;
		String speechResponseForCard=null;
		Map<String, String>  promptForPaymentMap	=	new HashMap<>();
		/**
		 * When there is no card available.
		 */
		if(itemList.size() ==1) {
			speechPrompt = util.getVerbiage(Constants.VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONLY_CASH);
			speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONLY_CASH); //need to update the verbiage
		}
		/**
		 * When there is only one card
		 */
		if(itemList.size() ==2) {
			speechPrompt = util.getVerbiage(Constants.VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONE_CARD,itemList.get(0).getItemName());
			speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONE_CARD,itemList.get(0).getItemName()); //need to update the verbiage
		}
		/**
		 * When there are more than one card
		 */
		if(itemList.size() > 2) {
			StringBuilder cardDetailsSpeech = new StringBuilder();
			StringBuilder cardPositions	=new StringBuilder();

			for(int i=0;i< itemList.size()-1; i++) {
				Item cardItem = itemList.get(i);
				String [] utterences = cardItem.getUtterences();
				cardDetailsSpeech.append(utterences[0]);
				cardDetailsSpeech.append(Constants.VERBAGE_CARDS_LISTING_SUPPORTING_TEXTS_ONE_THAT_ENDS_WITH);
				cardDetailsSpeech.append(utterences[1]);				
				cardPositions.append(utterences[0]);
				cardPositions.append(Constants.VERBAGE_CARDS_LISTING_SUPPORTING_TEXTS_ONE);
				if(i < itemList.size()-2) {
					cardDetailsSpeech.append(", ");
					cardPositions.append(", ");					
				}
			}
			speechPrompt = util.getVerbiage(Constants.VERBAGE_PAYMENT_LISTING_FIRST_TIME_MORE_CARD,cardDetailsSpeech.toString(),itemList.size()-1, cardPositions.toString());
			speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME_MORE_CARD,cardDetailsSpeech.toString(),itemList.size()-1, cardPositions.toString()); //need to update the verbiage
		}		
		if(firstTimePrompt) {
			speechPrompt = util.getVerbiage(Constants.VERBAGE_PAYMENT_LISTING_FIRST_TIME,speechPrompt);
			speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME,speechResponseForCard); //need to update the verbiage
		}else {
			//prefixing with error response with card details for reprompting
			speechPrompt = util.getVerbiage(Constants.VERBAGE_PAYMENT_LISTING_AFTER_INVALID_RESPONSE,speechPrompt);
			speechResponseForCard = util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_PAYMENT_LISTING_AFTER_INVALID_RESPONSE,speechResponseForCard); //need to update the verbiage
		}
		promptForPaymentMap.put("SpeechPrompt", speechPrompt);
		promptForPaymentMap.put("SpeechResponseForCard", speechResponseForCard);
		return promptForPaymentMap;
	}		
	public String getCardPromptForOrderSubmission(String orderNumber, String subId) {
		String speechPrompt=null;
		speechPrompt = util.getVerbiage(Constants.VERBAGE_ORDER_SUCCESS_FOR_CARD, orderNumber, getPromptForOrderReadyEstimate(orderNumber, subId));
		return speechPrompt;
	}
}
