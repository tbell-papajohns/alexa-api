package com.pji.alexa.intents;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.slu.entityresolution.StatusCode;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.pji.alexa.helper.OrderSpeechHelper;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;

/**
 * This class includes the interaction flow logic
 * 
 * @author anubh
 *
 */
@Component
public class OrderIntent extends BaseIntent {

    private static final Logger logger = LoggerFactory.getLogger(OrderIntent.class);    
	
	@Autowired
	private OrderSpeechHelper orderIntentHelper;
	
	private String slotNameOrderType;

	private String slotNameOrderName;

	private String slotNameAddressConfirmation;

	private String slotNameOrderConfirmation;

	private String slotNameCartConfirmation;
	
	private String slotNameOrderTypeConfirmation;
	
	private String slotNamePaymentMethod;
	/**
	 * This method updates the slot values
	 * 
	 * @param slots
	 * @param session
	 */
	@PostConstruct
	public void setSlotNamesForIntent() {
		this.setSlotNameOrderType(this.getUtil().getProperty(Constants.SLOT_NAME_ORDER_TYPE));
		this.setSlotNameOrderName(this.getUtil().getProperty(Constants.SLOT_NAME_ORDER_NAME));
		this.setSlotNameAddressConfirmation(this.getUtil().getProperty(Constants.SLOT_NAME_ADDRESS_CONFIRMATION));
		this.setSlotNameCartConfirmation(this.getUtil().getProperty(Constants.SLOT_NAME_CART_CONFIRMATION));
		this.setSlotNameOrderConfirmation(this.getUtil().getProperty(Constants.SLOT_NAME_ORDER_CONFIRMATION));
		this.setSlotNameOrderTypeConfirmation(this.getUtil().getProperty(Constants.SLOT_NAME_ORDER_TYPE_CONFIRMATION));
		this.setSlotNamePaymentMethod(this.getUtil().getProperty(Constants.SLOT_NAME_PAYMENT_METHOD));
	}
	/**
	 * This method would be called everytime for slot collection, the Slots would be
	 * called on in the same sequence as mentioned below
	 * 
	 * @param intentRequest
	 * @param session
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public SpeechletResponse getPromptForSlotCollection(IntentRequest intentRequest) throws Exception {
		SpeechletResponse speechletResponse = null;
		String nextStep= getSessionAttributeStringType(Constants.INTERACTION_NEXT_STEP);
		if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getOrderType()) && nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_ORDER_TYPE)) {
			speechletResponse= getPromptForOrderType(intentRequest);
		}
		if (nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_ADDRESS)) {
			speechletResponse= getPromptForAddress(intentRequest);
		}
		if (nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_FAVORITE)) {
			speechletResponse= getPromptForFavorite(intentRequest);
		}
		if (nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_ORDER_CONFIRM) || nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_RECENT_ORDER)) {
			speechletResponse = getPromptForOrderConfirmation(intentRequest);
		}
		if (nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_CARD)) {
			speechletResponse = getSpeechletResponseForPayment(intentRequest);
		}
		if (nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_CART_CONFIRM)) {
			speechletResponse = getPromptForCartConfirmation(intentRequest);			
		}
		if (nextStep.equalsIgnoreCase(Constants.INTERACTION_STEP_ORDER)) {
			speechletResponse = getPromptForOrderSubmission(intentRequest);						
		}
		return speechletResponse;
	}
	/**
	 * This method updates the slot values
	 * @param slots
	 * @param orderIntent
	 * @param session
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public void updateSlotValues(Map<String, Slot> slots, Intent orderIntent, Session session) throws Exception {
		logger.debug("Validating Slot Values");
		UserDataItems userdata = this.getUserDataFromSession();
		if (validateSlotValue(slots, this.slotNameAddressConfirmation)) {
			updateSlotValueForAddress(userdata);
		}
		if (validateSlotValue(slots, this.slotNameOrderTypeConfirmation)) {
			updateSlotValueForOrderTypeConfirmation(userdata, slots);
		}
		if (validateSlotValue(slots, this.slotNameOrderType)) {
			updateSlotValueForOrderType(userdata, slots);
		}
		if (validateSlotValue(slots, this.slotNameOrderName)) {
			updateSlotValueForOrderName(userdata, slots);
		}
		if (validateSlotValue(slots, this.slotNameOrderConfirmation)) {
			updateSlotValueForOrderConfirmation(slots);
		}
		if (validateSlotValue(slots, this.slotNamePaymentMethod)) {
			updateSlotValueForPaymentMethod(userdata, slots);
		}
		if (validateSlotValue(slots, this.slotNameCartConfirmation)) {
			updateSlotValueForCartConfirmation(slots);
		}
		logger.debug("Next Step: {}", this.getSessionAttributeStringType(Constants.INTERACTION_NEXT_STEP));
	}	
	/**
	 * 
	 * @param userdata
	 * @param addressId
	 */
	private void updateUserDataWithFavoritesAndRecentOrderDetails(UserDataItems userdata) throws Exception{
		logger.debug("Updating user data for addressid : {}", this.getSessionAttributeStringType(Constants.SESSION_ORDER_ITEM));
		this.getCustomerInfoService().updateCustomerStoreIdAndTerritoryId(this.getSessionAttributeStringType(Constants.SESSION_ORDER_ITEM), userdata);	
		List<Item> favoriteList= this.getFavouriteService().getFavoriteItems(userdata.getCustomerId(), userdata.getDeliveryStoreId(), userdata.getCustomerToken());
		List<Item> pastOrderList= this.getPastOrderService().getPastOrderDetails(userdata.getCustomerId(), userdata.getDeliveryStoreId(), userdata.getCustomerToken());
		
		String orderType = null;
		if(!favoriteList.isEmpty() && !pastOrderList.isEmpty()) {
			// when both favorite and recent exist
			orderType = Constants.ORDER_TYPE_BOTH;
		}else if(!favoriteList.isEmpty() && pastOrderList.isEmpty()){
			// when only favorites exist
			orderType = Constants.ORDER_TYPE_FAVORITE;
		}else if(favoriteList.isEmpty() && !pastOrderList.isEmpty()){
			// when only recent order exist
			orderType = Constants.ORDER_TYPE_RECENT;
		}
		userdata.setOrderType(orderType);
		userdata.setPastOrderList(pastOrderList);
		userdata.setFavoriteList(favoriteList);
		this.setSessionAttribute(Constants.SESSION_USER_DATA, userdata);
		logger.debug("Current Order type is {}", orderType);
	}
	/**
	 * This method updates the ordertype slot value to session
	 * @param userdata
	 * @param slots
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	private void updateSlotValueForOrderType(UserDataItems userdata, Map<String, Slot> slots) throws Exception{
		if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getOrderType())) {
			AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
			if(slots.get(this.slotNameOrderType).getResolutions() != null) {
				String orderType= slots.get(this.slotNameOrderType).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName();
				alexaOrder.setOrderType(orderType);					
				if(StringUtils.isEmpty(alexaOrder.getAddressId())){
					this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ADDRESS);
				}else {
					alexaOrder.setOrderTypeConfirmed(true);
					if(Constants.ORDER_TYPE_FAVORITE.equalsIgnoreCase(orderType)) {
						this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_FAVORITE);						
					}else if (Constants.ORDER_TYPE_RECENT.equalsIgnoreCase(orderType)) {
						this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_RECENT_ORDER);
					}
				}
				this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
			}
		}
	}
	
	private SpeechletResponse getPromptForOrderType(IntentRequest intentRequest) throws Exception {
		UserDataItems userdata = this.getUserDataFromSession();
		SpeechletResponse speechletResponse = null;
		logger.info("Creating prompt for order type");
		// Checking if Order type is captured
		String orderType = userdata.getOrderType();
		Map<String, String> promptForOrderTypeMap = this.getOrderTypeIntentHelper().getPromptForOrderType(userdata);
		if(StringUtils.isEmpty(orderType)) {
			// when both favorites and past order do not exist
			speechletResponse = getSpeechletResponseWithoutAnyDirective(this.getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_DONT_EXIST), intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.ALEXA_CARD_ORDERTYPE_PROMPT_TITLE), this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_DONT_EXIST));
		}else{
			if(intentRequest.getIntent().getSlot(this.slotNameOrderTypeConfirmation).getResolutions() !=null && intentRequest.getIntent().getSlot(this.slotNameOrderTypeConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_NO)) {
				// when user says no to the order type
				speechletResponse = getSpeechletResponseWithoutAnyDirective(this.getUtil().getVerbiage(Constants.VERBAGE_ORDER_TYPE_NOT_CONFIRMED), intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.ALEXA_CARD_ORDERTYPE_PROMPT_TITLE), this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_TYPE_NOT_CONFIRMED));
			}else if(orderType.equals(Constants.ORDER_TYPE_BOTH)) {
				// when both favorites and past order exist
				speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderType,promptForOrderTypeMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.ALEXA_CARD_ORDERTYPE_PROMPT_TITLE), promptForOrderTypeMap.get("SpeechResponseForCard"));
			}else if(orderType.equals(Constants.ORDER_TYPE_RECENT)) {
				// when only past order exist
				speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderTypeConfirmation,promptForOrderTypeMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.ALEXA_CARD_ORDERTYPE_PROMPT_TITLE), promptForOrderTypeMap.get("SpeechResponseForCard"));
			}else if(orderType.equals(Constants.ORDER_TYPE_FAVORITE)) {
				// when only favorites exist
				speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderTypeConfirmation,promptForOrderTypeMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.ALEXA_CARD_ORDERTYPE_PROMPT_TITLE), promptForOrderTypeMap.get("SpeechResponseForCard"));
			}		
		}
		return speechletResponse;
	}
	
	/**
	 * This method updates the favorite id in order object if its a correct favorite name
	 * @param userdata
	 * @param slots
	 * @throws Exception
	 */
	private void updateSlotValueForOrderName(UserDataItems userdata, Map<String, Slot> slots) throws Exception{
		AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
		if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getFavoriteId())) {
			String slotValue= null;
			String favoriteId= null;
			if(this.getAlexaOrderObjectFromSession().getOrderType().equalsIgnoreCase(Constants.ORDER_TYPE_FAVORITE)) {
				if(slots.get(this.slotNameOrderName).getResolutions() != null && slots.get(this.slotNameOrderName).getResolutions().getResolutionAtIndex(0).getStatus().getCode().equals(StatusCode.ER_SUCCESS_MATCH)) {
					slotValue= slots.get(this.slotNameOrderName).getResolutions().getResolutionAtIndex(0).getValueWrappers().get(0).getValue().getName();
				}else {
					slotValue= slots.get(this.slotNameOrderName).getValue();
				}
				List<Item> favoriteList = userdata.getFavoriteList();
				favoriteId= this.getUtil().findItem(slotValue, favoriteList);
			}
			if(StringUtils.isEmpty(favoriteId)) {
				this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_FAVORITE);
			}else {
				alexaOrder.setFavoriteId(favoriteId);
				this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
				this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ORDER_CONFIRM);
			}
		}
	}
	private void updateSlotValueForAddress(UserDataItems userdata) throws Exception {
		if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getAddressId())) {
			/**
			 * Would only come here when slot value is yes
			 */
			updateUserDataWithFavoritesAndRecentOrderDetails(userdata);	
			AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
			alexaOrder.setAddressId(this.getSessionAttributeStringType(Constants.SESSION_ORDER_ITEM));
			alexaOrder.setStoreId(userdata.getDeliveryStoreId());
			alexaOrder.setDeliveryTerritoryId(Integer.parseInt(userdata.getTerritoryId()));
			this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);				
			if(StringUtils.isNotEmpty(this.getAlexaOrderObjectFromSession().getOrderType())) {
				if (this.getAlexaOrderObjectFromSession().getOrderType().equalsIgnoreCase(Constants.ORDER_TYPE_FAVORITE)) {
					this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_FAVORITE);
				} else {
					this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ORDER_CONFIRM);
				}
			}else {
				this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ORDER_TYPE);
			}
		}
	}
		
	private SpeechletResponse getPromptForAddress(IntentRequest intentRequest) throws Exception {
		logger.info("Creating prompt for Address Elicitation");

		UserDataItems userdata = this.getUserDataFromSession();
		SpeechletResponse speechletResponse = null;

		if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getAddressId())) {
			String currentItemValue = null;
			List<Item> addressList = userdata.getAddressList();
			String userResponseType=null;
			if(intentRequest.getIntent().getSlot(this.slotNameAddressConfirmation).getResolutions() == null) {
				//First time ellicitation/continue
				currentItemValue = this.getStreetAddressSpeechHelper().getNextDataItem(addressList);
				if(StringUtils.isEmpty(currentItemValue)) {
					userResponseType=Constants.USER_RESPONSE_TYPES_LAST_NO;
				}
			}else if (intentRequest.getIntent().getSlot(this.slotNameAddressConfirmation).getResolutions() != null &&  
					intentRequest.getIntent().getSlot(this.slotNameAddressConfirmation).getResolutions().getResolutionAtIndex(0).getStatus().getCode().equals(StatusCode.ER_SUCCESS_NO_MATCH)) {
				//Invalid value
				userResponseType=Constants.USER_RESPONSE_TYPES_INVALID;
			}else if(intentRequest.getIntent().getSlot(this.slotNameAddressConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_NO)) {
				//a No response from user
				currentItemValue = this.getStreetAddressSpeechHelper().getNextDataItem(addressList);
				if(StringUtils.isNotEmpty(currentItemValue)) {
					userResponseType=Constants.USER_RESPONSE_TYPES_NO;
				}else {
					userResponseType=Constants.USER_RESPONSE_TYPES_LAST_NO;
				}
			}
			this.setSessionAttribute(Constants.SESSION_ORDER_ITEM, currentItemValue);
			Map<String, String> promptForStreetAddressMap= getStreetAddressSpeechHelper().getPromptForStreetAddress(userdata, currentItemValue,userResponseType, userdata.getAddressList().size());
			
			if(StringUtils.isNotEmpty(userResponseType) && userResponseType.equalsIgnoreCase(Constants.USER_RESPONSE_TYPES_LAST_NO)) {
				speechletResponse= getSpeechletResponseWithoutAnyDirective(promptForStreetAddressMap.get("SpeechPrompt"), intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.ADDRESS_PROMPT_CARD_TITLE), promptForStreetAddressMap.get("SpeechResponseForCard"));
			}else {
				speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameAddressConfirmation, promptForStreetAddressMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.ADDRESS_PROMPT_CARD_TITLE), promptForStreetAddressMap.get("SpeechResponseForCard"));
			}
			userdata.setAddressList(addressList);
			this.setSessionAttribute(Constants.SESSION_USER_DATA, userdata);
		}
		return speechletResponse;
	}
		
	/**
	 * This method creates the prompt for favorite
	 * @param intentRequest
	 * @return
	 * @throws Exception
	 */
	private SpeechletResponse getPromptForFavorite(IntentRequest intentRequest) throws Exception{
		logger.info("Creating prompt for Fovorite Ellicitation");
		UserDataItems userdata = this.getUserDataFromSession();
		SpeechletResponse speechletResponse 	= 	null;
		String speech	=	"";
		String speechResponseForCard	=	"";
		List<Item> favoriteList= userdata.getFavoriteList();
		if(!favoriteList.isEmpty()) {
			boolean invalidResponse= false;			
			if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getFavoriteId()) && 
					StringUtils.isNotEmpty(intentRequest.getIntent().getSlot(this.slotNameOrderName).getValue())) {
				//When user uttered a responded with a favorite name which is not validates
				invalidResponse= true;
			}
			Map<String, String> promptForFavoriteMap = this.getFavoriteSpeechHelper().getPromptForListingFavourites(favoriteList, invalidResponse);
			speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderName, promptForFavoriteMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.FAVOURITE_PROMPT_CARD_TITLE), promptForFavoriteMap.get("SpeechResponseForCard"));
		}else if (!userdata.getPastOrderList().isEmpty()) {
			//you dont have favorite configured, would you like to order your recent
			speech= this.getUtil().getVerbiage("verbage.welcome.message.lastorder.exist","");
			speechResponseForCard = this.getUtil().getVerbiage("card.verbage.welcome.message.lastorder.exist","");
			speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderTypeConfirmation,speech,intentRequest.getIntent(), getUtil().getVerbiage(Constants.PAST_ORDER_PROMPT_CARD_TITLE), speechResponseForCard);
		}else {
			//you dont have favorite or recent configured yet
			speech= this.getUtil().getVerbiage("verbage.welcome.message.both.favorite.and.lastorder.not.exist","");
			speechResponseForCard = this.getUtil().getVerbiage("card.verbage.welcome.message.both.favorite.and.lastorder.not.exist","");
			speechletResponse=getSpeechletResponseWithoutAnyDirective(speech, intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.FAVOURITE_PAST_NO_EXIST_PROMPT_CARD_TITLE), speechResponseForCard);
		}
		return speechletResponse;
	}
	
	/**
	 * This method updates the payment details in session object
	 * @param userdata
	 * @param slots
	 * @throws Exception
	 */
	private void updateSlotValueForPaymentMethod(UserDataItems userdata, Map<String, Slot> slots) throws Exception{
		/**
		 * Would only come here when slot value is yes
		 */
		if (StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getPaymentType())) {
			AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
			String slotValue= null;
			if(slots.get(this.slotNamePaymentMethod).getResolutions() != null && 
					slots.get(this.slotNamePaymentMethod).getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().equals(StatusCode.ER_SUCCESS_MATCH)) {
				//if utterence matches with the one mapped then use this one
				slotValue= slots.get(this.slotNamePaymentMethod).getResolutions().getResolutionsPerAuthority().get(0).getValueWrappers().get(0).getValue().getName();
			}else {
				//consider the one which is not mapped
				slotValue= slots.get(slotNamePaymentMethod).getValue().toLowerCase();
			}			
			if(orderIntentHelper.searchAndUpdateCardDetails(slotValue, userdata, alexaOrder)) {
				this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
				this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_CART_CONFIRM);
			}
		}
	}
	
	private void updateSlotValueForOrderTypeConfirmation(UserDataItems userdata, Map<String, Slot> slots) throws Exception{
		/**
		 * Would only come here when slot value is yes
		 */
		AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
		if (!this.getAlexaOrderObjectFromSession().isOrderTypeConfirmed() && slots.get(this.slotNameOrderTypeConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_YES)) {				
			alexaOrder.setOrderTypeConfirmed(Constants.BOOLEAN_TRUE);
			alexaOrder.setOrderType(userdata.getOrderType());
			if(StringUtils.isEmpty(alexaOrder.getAddressId())){
				this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ADDRESS);
			}else {
				if(Constants.ORDER_TYPE_FAVORITE.equalsIgnoreCase(userdata.getOrderType())) {
					this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_FAVORITE);						
				}else if(Constants.ORDER_TYPE_RECENT.equalsIgnoreCase(userdata.getOrderType())) {					
					alexaOrder.setPastOrderNumber(userdata.getPastOrderList().get(0).getItemId());
					this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_RECENT_ORDER);
				}
			}
			this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
		}
	}
	
	private void updateSlotValueForOrderConfirmation(Map<String, Slot> slots) throws Exception{
		/**
		 * Would only come here when slot value is yes
		 */
		if (!this.getAlexaOrderObjectFromSession().isOrderConfirmed() && slots.get(this.slotNameOrderConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_YES)) {
			AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
			alexaOrder.setOrderConfirmed(Constants.BOOLEAN_TRUE);
			this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
			this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_CARD);
		}
	}
	
	private SpeechletResponse getPromptForOrderConfirmation(IntentRequest intentRequest) throws Exception {
		logger.info("Creating prompt for Order Confirmation");
		UserDataItems userdata = this.getUserDataFromSession();
		SpeechletResponse speechletResponse 	= null;
		AlexaOrder alexaOrder= this.getAlexaOrderObjectFromSession();
		Map<String, String>	promptForOrderConfirmationMap	=	new HashMap<>();
		if (!this.getAlexaOrderObjectFromSession().isOrderConfirmed()) {
			if(intentRequest.getIntent().getSlot(this.slotNameOrderConfirmation).getResolutions() != null && intentRequest.getIntent().getSlot(this.slotNameOrderConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_NO)) {
				String response = this.getUtil().getVerbiage(Constants.VERBAGE_ORDER_NOT_CONFIRMED);
				String responseForCard = this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_NOT_CONFIRMED);
				speechletResponse = getSpeechletResponseWithoutAnyDirective(response, intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.ALEXA_CARD_ORDERTYPE_PROMPT_TITLE), responseForCard);
			}else {
				String orderType= alexaOrder.getOrderType();
				List<Item> favoriteList = userdata.getFavoriteList();
				List<Item> pastOrderList =userdata.getPastOrderList();
				if (orderType.equals(Constants.ORDER_TYPE_FAVORITE)) {
					String favoriteId= alexaOrder.getFavoriteId();
					promptForOrderConfirmationMap= orderIntentHelper.getPromptForOrderDetails(favoriteList,favoriteId,orderType);
					speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderConfirmation,promptForOrderConfirmationMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.FAV_DETAILS_CARD_TITLE), promptForOrderConfirmationMap.get("SpeechResponseForCard"));
				} else if (orderType.equals(Constants.ORDER_TYPE_RECENT)) {
					String pastOrderNumber= pastOrderList.get(0).getItemId();
					promptForOrderConfirmationMap= orderIntentHelper.getPromptForOrderDetails(pastOrderList,pastOrderNumber,orderType);
					speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameOrderConfirmation,promptForOrderConfirmationMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.PAST_ORDER_PROMPT_CARD_TITLE), promptForOrderConfirmationMap.get("SpeechResponseForCard"));
				}
			}
		}
		return speechletResponse;
	}
	
	/**
	 * This method creates the prompt for payment options
	 * @param intentRequest
	 * @return
	 * @throws Exception
	 */
	private SpeechletResponse getSpeechletResponseForPayment(IntentRequest intentRequest) throws Exception {
		logger.info("Creating prompt for Card Elliciation");
		UserDataItems userdata = this.getUserDataFromSession();
		SpeechletResponse speechletResponse = null;
		boolean firstTimePrompt= true;
		Collections.sort(userdata.getPaymentList());
	
		if(intentRequest.getIntent().getSlot(this.slotNamePaymentMethod).getResolutions() != null 
				&& intentRequest.getIntent().getSlot(this.slotNamePaymentMethod).getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().equals(StatusCode.ER_SUCCESS_MATCH)
				&& intentRequest.getIntent().getSlot(this.slotNamePaymentMethod).getResolutions().getResolutionsPerAuthority().get(0).getValueWrappers().get(0).getValue().getName().equalsIgnoreCase("no")) {
			//user said no to payment options specified
			speechletResponse= getSpeechletResponseWithoutAnyDirective(this.getUtil().getVerbiage(Constants.VERBAGE_PAYMENT_NO_SELECTED_OPTION), intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.CREDIT_CARD_PROMPT_CARD_TITLE), this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_PAYMENT_NO_SELECTED_OPTION));//need to update verbiage
		}else {
			if(StringUtils.isNotEmpty(intentRequest.getIntent().getSlot(this.slotNamePaymentMethod).getValue()) && StringUtils.isEmpty(this.getAlexaOrderObjectFromSession().getPaymentType())) {
				//There is a slot value which is not valid since it is not updated
				firstTimePrompt= false;
			}
			Map<String, String>  promptForPaymentMap	= orderIntentHelper.getPromptForPaymentDetails(userdata.getPaymentList(), firstTimePrompt);
			speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNamePaymentMethod, promptForPaymentMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.CREDIT_CARD_PROMPT_CARD_TITLE), promptForPaymentMap.get("SpeechResponseForCard"));
		}
		return speechletResponse;
	}

	
	private void updateSlotValueForCartConfirmation(Map<String, Slot> slots) throws InstantiationException, IllegalAccessException, Exception{
		/**
		 * Would only come here when slot value is yes
		 */
		if (!this.getAlexaOrderObjectFromSession().isCartConfirmed() && slots.get(this.slotNameCartConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_YES)) {

			AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
			alexaOrder.setCartConfirmed(Constants.BOOLEAN_TRUE);
			this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
			this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ORDER);
		}
	}
	
	private SpeechletResponse getPromptForCartConfirmation(IntentRequest intentRequest) throws Exception{
		UserDataItems userdata = this.getUserDataFromSession();
		SpeechletResponse speechletResponse = null;
		String responseForCard	=	null;
		/**
		 * if slot value is null, then we are pitching for first time otherwise if slot
		 * value is no then subsequent times
		 */					
		if (!this.getAlexaOrderObjectFromSession().isCartConfirmed()) {
			logger.info("Creating prompt for Cart Elliciation");

			if(intentRequest.getIntent().getSlot(this.slotNameCartConfirmation).getResolutions() != null && 
					(intentRequest.getIntent().getSlot(this.slotNameCartConfirmation).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_NO))) {
				String response = this.getUtil().getVerbiage(Constants.VERBAGE_CART_NOT_CONFIRMED);
				responseForCard = this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_CART_NOT_CONFIRMED);//need to confirm the verbiage, verbiage changed
				speechletResponse = getSpeechletResponseWithoutAnyDirective(response, intentRequest.getIntent(), false, getUtil().getVerbiage(Constants.CART_PROMPT_CARD_TITLE), responseForCard);
			}else {
					/**
				 * if slot value is null, then we are pitching for first time otherwise if slot
				 * value is no then subsequent times
				 */
				String storeId = this.getAlexaOrderObjectFromSession().getStoreId();
				String addressId = this.getAlexaOrderObjectFromSession().getAddressId();
				String favoriteId = this.getAlexaOrderObjectFromSession().getFavoriteId();
				String pastOrderNumber = this.getAlexaOrderObjectFromSession().getPastOrderNumber();
				String paymentType	=	this.getAlexaOrderObjectFromSession().getPaymentType();
				int deliveryTerritoryId = this.getCustomerInfoService().getDeliveryTerritoryId(userdata.getCustomerId(),userdata.getCustomerToken(), addressId);
				this.getAlexaOrderObjectFromSession().setDeliveryTerritoryId(deliveryTerritoryId);
				Map<String, String> promptCartConfirmationMap	= orderIntentHelper.getPromptForCartConfirmation(userdata.getCustomerId(), storeId,	userdata.getCustomerToken(), favoriteId, pastOrderNumber, deliveryTerritoryId, paymentType);
				
				speechletResponse = getSpeechResponseWithElicitSlotDirective(this.slotNameCartConfirmation, promptCartConfirmationMap.get("SpeechPrompt"),intentRequest.getIntent(), getUtil().getVerbiage(Constants.CART_PROMPT_CARD_TITLE), promptCartConfirmationMap.get("SpeechResponseForCard"));
			}
		}
		return speechletResponse;
	}
	
	private SpeechletResponse getPromptForOrderSubmission(IntentRequest intentRequest) throws Exception{
		SpeechletResponse speechletResponse = null;	
		if (checkAllDataCollected()) {
			logger.info("Creating prompt for Order Placement, delegating the request");
			String customerId = this.getUserDataFromSession().getCustomerId();
			String customerToken = this.getUserDataFromSession().getCustomerToken();
			Map<String, String> orderResponseMap = this.getOrderService().createAndSubmitOrder(this.getAlexaOrderObjectFromSession(),customerId, customerToken);
			Map<String, String> promptOrderSubmissionMap = orderIntentHelper.getPromptForOrderSubmission(orderResponseMap.get(Constants.ORDER_NUMBER),orderResponseMap.get(Constants.SUBSCRIPTION_ID));
			speechletResponse = getSpeechletResponseWithoutAnyDirective(promptOrderSubmissionMap.get("SpeechPrompt"), intentRequest.getIntent(), true, getUtil().getVerbiage(Constants.ORDER_PROMPT_CARD_TITLE), promptOrderSubmissionMap.get("SpeechResponseForCard"));
		}	
		return speechletResponse;
	}
	/**
	 * This method validates the slot value
	 * 
	 * @param slots
	 * @param slotName
	 * @return
	 */
	private boolean validateSlotValue(Map<String, Slot> slots, String slotName) {

		boolean validSlot = true;
		if(slotName.equalsIgnoreCase(this.slotNameOrderConfirmation) || slotName.equalsIgnoreCase(this.slotNameCartConfirmation) || slotName.equalsIgnoreCase(this.slotNameOrderType)) {
			if(slots.get(slotName).getResolutions() == null || slots.get(slotName).getResolutions().getResolutionAtIndex(0).getStatus().getCode().equals(StatusCode.ER_SUCCESS_NO_MATCH)) {
				validSlot= false;
			}			
		}
		if(slotName.equalsIgnoreCase(this.slotNameAddressConfirmation) || slotName.equalsIgnoreCase(this.slotNameOrderTypeConfirmation)){
			if(slots.get(slotName).getResolutions() == null || slots.get(slotName).getResolutions().getResolutionAtIndex(0).getStatus().getCode().equals(StatusCode.ER_SUCCESS_NO_MATCH) || slots.get(slotName).getResolutions().getResolutionAtIndex(0).getValueWrapperAtIndex(0).getValue().getName().equalsIgnoreCase(Constants.SLOT_VALUE_NO)) {
				validSlot= false;
			}
		}
		if(slotName.equalsIgnoreCase(this.slotNameOrderName) || slotName.equalsIgnoreCase(this.slotNamePaymentMethod)) {
			if(slots.get(slotName).getValue() == null) {
				validSlot= false;
			}
		}
		return validSlot;
	}

	/**
	 * This method checks if all slot values are collected and are present in the
	 * session
	 * 
	 * @param session
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public boolean checkAllDataCollected() throws InstantiationException, IllegalAccessException {
		boolean allDataCollected = false;
		AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
		if (StringUtils.isNotEmpty(alexaOrder.getAddressId()) && (StringUtils.isNotEmpty(alexaOrder.getCardId()) || StringUtils.isNotEmpty(alexaOrder.getPaymentType()))//ORDERS-430
				&& (StringUtils.isNotEmpty(alexaOrder.getFavoriteId()) || alexaOrder.getOrderType().equalsIgnoreCase("recentOrder")) && alexaOrder.isOrderConfirmed()
				&& alexaOrder.isCartConfirmed()) {
			allDataCollected = true;
		}
		return allDataCollected;
	}

	public String getSlotNameOrderType() {
		return slotNameOrderType;
	}

	public void setSlotNameOrderType(String slotNameOrderType) {
		this.slotNameOrderType = slotNameOrderType;
	}

	public String getSlotNameOrderName() {
		return slotNameOrderName;
	}

	public void setSlotNameOrderName(String slotNameOrderName) {
		this.slotNameOrderName = slotNameOrderName;
	}

	public OrderSpeechHelper getOrderIntentHelper() {
		return orderIntentHelper;
	}

	public void setOrderIntentHelper(OrderSpeechHelper orderIntentHelper) {
		this.orderIntentHelper = orderIntentHelper;
	}

	public String getSlotNameAddressConfirmation() {
		return slotNameAddressConfirmation;
	}

	public void setSlotNameAddressConfirmation(String slotNameAddressConfirmation) {
		this.slotNameAddressConfirmation = slotNameAddressConfirmation;
	}

	public String getSlotNameOrderConfirmation() {
		return slotNameOrderConfirmation;
	}

	public void setSlotNameOrderConfirmation(String slotNameOrderConfirmation) {
		this.slotNameOrderConfirmation = slotNameOrderConfirmation;
	}

	public String getSlotNameCartConfirmation() {
		return slotNameCartConfirmation;
	}

	public void setSlotNameCartConfirmation(String slotNameCartConfirmation) {
		this.slotNameCartConfirmation = slotNameCartConfirmation;
	}
	
	public String getSlotNameOrderTypeConfirmation() {
		return slotNameOrderTypeConfirmation;
	}

	public void setSlotNameOrderTypeConfirmation(String slotNameOrderTypeConfirmation) {
		this.slotNameOrderTypeConfirmation = slotNameOrderTypeConfirmation;
	}
	
	public String getSlotNamePaymentMethod() {
		return slotNamePaymentMethod;
	}

	public void setSlotNamePaymentMethod(String slotNamePaymentMethod) {
		this.slotNamePaymentMethod = slotNamePaymentMethod;
	}
}
