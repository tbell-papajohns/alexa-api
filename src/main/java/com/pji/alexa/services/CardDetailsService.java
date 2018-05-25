package com.pji.alexa.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.CreditCardPaymentForm;
import com.pji.alexa.model.v2.CustomerCreditCardForm;
import com.pji.alexa.model.v2.CustomerCreditCards;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.util.Constants;
 
@Service
public class CardDetailsService extends BaseService {

	public CardDetailsService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}

	/**This methods returns all the details of Customer Credit Cards 
	 * @param customerId
	 * @param token
	 * @return
	 */
	public CustomerCreditCards getCardDetails(String customerId, String token) throws Exception{
		this.setTokenValue(token);
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_GET)));
		this.setUrl(createURL());
		return (CustomerCreditCards)sendHttpRequest(CustomerCreditCards.class,customerId);		
	}
	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_CUSTOMER_CARD_LIST_URL);
	}	
	/**
	 * This method builds the payment details of the customer- cards and cash options
	 * @param customerId
	 * @param token
	 * @return
	 */
	public List<Item> getCustomerCardDetails(String customerId, String token) throws Exception{
		List<Item> cardList = new ArrayList<>();
		CustomerCreditCards customerCreditCards = getCardDetails(customerId,token);
		List<CustomerCreditCardForm> customerCardFormList = customerCreditCards.getData();
		Item item;
		int itemCount=0;
		for(int i =0; i< customerCardFormList.size(); i++) {
			CustomerCreditCardForm customerCreditCardForm = customerCardFormList.get(i);
			String cardNumber= customerCreditCardForm.getCardNumber();
			String[] cardArray = cardNumber.split("-");
			cardNumber = cardArray[3];		
			String cardNumberOrdinal= getUtil().convertNumberStringToNumberNames(cardNumber);
			String cardPositionOrdinal = getUtil().getOrdinal(i+1);
			List<String> utterenceList = new LinkedList<>();
			utterenceList.add(cardPositionOrdinal.trim().toLowerCase()); //first, second etc
			utterenceList.add(cardNumberOrdinal.trim().toLowerCase());// card number like 'one two three four'
			utterenceList.add(cardNumber.trim().toLowerCase());// card number like '1234'			
			/**
			 * This would support 'last' utterence with the last card
			 */
		    if(i== customerCardFormList.size()-1) {
		    	utterenceList.add(Constants.SLOT_VALUE_LAST);
		    }
		    /**
		     * This would support 'card' utterence, if there is only one card
		     */
		    if(customerCardFormList.size() ==1) {
		    	utterenceList.add(Constants.ALEXA_PAYMENT_TYPE_CARD);
		    }
		    item = new Item(i, customerCreditCardForm.getCardId().toString(), cardNumberOrdinal, null, utterenceList.toArray(new String[utterenceList.size()]), false, false);
		    cardList.add(item);
		    itemCount++;
		}
	    String[] cashUtterence = {Constants.ALEXA_PAYMENT_TYPE_CASH};
	    item = new Item(itemCount, Constants.ALEXA_PAYMENT_TYPE_CASH, Constants.ALEXA_PAYMENT_TYPE_CASH,null, cashUtterence, false, false);
		cardList.add(item);
		return cardList;		
	}
	/**
	 * This method returns the CreditCardPayment Form, used for building SubmitOrderForm
	 * @param customerId
	 * @param token
	 * @param cardId
	 * @return
	 */
	public CreditCardPaymentForm getCustomerCreditCard(String customerId, String token, String cardId) throws Exception{
		CustomerCreditCards customerCreditCards = getCardDetails(customerId, token);
		CreditCardPaymentForm creditCardPaymentForm = new CreditCardPaymentForm();
		for(CustomerCreditCardForm customerCreditCardForm : customerCreditCards.getData()) {
			if(customerCreditCardForm.getCardId().toString().equals(cardId)) {
				creditCardPaymentForm.setCardId(customerCreditCardForm.getCardId());
				creditCardPaymentForm.setPaymentTypeId(customerCreditCardForm.getPaymentTypeId());
				break;
			}
		}
		return creditCardPaymentForm;
	}
}
