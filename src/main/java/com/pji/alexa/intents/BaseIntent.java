package com.pji.alexa.intents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.dialog.directives.DelegateDirective;
import com.amazon.speech.speechlet.dialog.directives.DialogIntent;
import com.amazon.speech.speechlet.dialog.directives.ElicitSlotDirective;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.pji.alexa.helper.FavoriteSpeechHelper;
import com.pji.alexa.helper.OrderTypeSpeechHelper;
import com.pji.alexa.helper.AddressSpeechHelper;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.services.CustomerInfoService;
import com.pji.alexa.services.FavouriteService;
import com.pji.alexa.services.OrderService;
import com.pji.alexa.services.PastOrderService;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;

@Component
public class BaseIntent {

	@Autowired
	private FavouriteService favouriteService;
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private Util util;
	
	@Autowired
	private Transformer transformer;	

	@Autowired
	private FavouriteService favoriteService;
	
	@Autowired
	private PastOrderService pastOrderService;
	
	@Autowired
	private FavoriteSpeechHelper favoriteSpeechHelper;
	
	@Autowired
	private OrderTypeSpeechHelper orderTypeIntentHelper;
	
	@Autowired
	private AddressSpeechHelper streetAddressSpeechHelper;
	
	protected String orderSessionObject=Constants.SESSION_ORDER_OBJECT;
	
	private String intentName;
	private String customerToken;
	private String customerId;
	
	private Session session;
	
	public AlexaOrder getAlexaOrderObjectFromSession() throws InstantiationException, IllegalAccessException {
		AlexaOrder alexaOrder=null;
		//getting the value from session if exist otherwise creating new
		if(this.getSession().getAttribute(orderSessionObject) != null) {
			/**
			 * Get the session value convert the map into json string and then convert the string to object
			 * 
			 */
			if(this.getSession().getAttribute(orderSessionObject) instanceof Map) {
				Map map = (HashMap)this.getSession().getAttribute(orderSessionObject);
				alexaOrder = (AlexaOrder)transformer.convertSessionDataObjectToDomainObject(map, AlexaOrder.class);
			}else if (this.getSession().getAttribute(orderSessionObject) instanceof AlexaOrder){
				alexaOrder= (AlexaOrder)this.getSession().getAttribute(orderSessionObject);
			}
		}else {
			alexaOrder= new AlexaOrder();
		}
		return alexaOrder;
	}
	
	public UserDataItems getUserDataFromSession() throws InstantiationException, IllegalAccessException {
		UserDataItems userDataItems = null;
		if(this.getSession().getAttribute(Constants.SESSION_USER_DATA) instanceof UserDataItems) {
			userDataItems= (UserDataItems) this.getSession().getAttribute(Constants.SESSION_USER_DATA);
		}else {
			Map<String,Object> map = (HashMap<String, Object>)this.getSession().getAttribute(Constants.SESSION_USER_DATA);           				
			userDataItems= (UserDataItems) this.getTransformer().convertSessionDataObjectToDomainObject(map, UserDataItems.class);
		}
		return userDataItems;
	}
	public void setAlexaOrderObjectInSession(AlexaOrder alexaOrder) {
		this.setSessionAttribute(this.orderSessionObject, alexaOrder);
	}
	
	public void setAlexaOrderObjectToSession(AlexaOrder alexaOrder) {
		this.getSession().setAttribute(orderSessionObject, alexaOrder);

	}
	public String getSessionAttributeStringType(String key) {
		String value="";
		if(this.getSession().getAttribute(key) instanceof String) {
			value= (String) this.getSession().getAttribute(key);
		}
		return value;
	}
	public void setSessionAttribute(String key, Object value) {
		this.getSession().setAttribute(key, value);
	}
	/**
	 * This method creates the response for ellicitation of a slot, the slot name should already be defined in the interaction model
	 * @param slotNameToElicit
	 * @param speech
	 * @param intent
	 * @return
	 */
    public SpeechletResponse getSpeechResponseWithElicitSlotDirective(String slotNameToElicit, String speech, Intent intent, String title, String contentForCard) {
	    ElicitSlotDirective elicitSlotDirective = new ElicitSlotDirective();
	    SimpleCard card = new SimpleCard();
	    elicitSlotDirective.setSlotToElicit(slotNameToElicit);
	    List<Directive> directives = new ArrayList<>();
	    directives.add(elicitSlotDirective);                	
	    DialogIntent dialogIntent = new DialogIntent(intent);	
	    elicitSlotDirective.setUpdatedIntent(dialogIntent);
	    // Create SpeechletResponse
	    SpeechletResponse response = new SpeechletResponse();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(speech);		
		response.setReprompt(new Reprompt());
	    response.setOutputSpeech(plainTextOutputSpeech);
	    response.setDirectives(directives);
	    response.setNullableShouldEndSession(false);
	    card.setTitle(title);
		card.setContent(contentForCard);
		response.setCard(card);
	    return response;  
	}

    /**
     * This method creates a response to delegate the control to alexa interaction model, here it is delegated after all slots are filled
     * @param intent
     * @return
     */
    public SpeechletResponse getSpeechResponseWithDelegateDirective(Intent intent) {
    	DelegateDirective delegateDirective = new DelegateDirective();
	    List<Directive> directives = new ArrayList<>();
	    directives.add(delegateDirective);                	
	    DialogIntent dialogIntent = new DialogIntent(intent);	
	    delegateDirective.setUpdatedIntent(dialogIntent);
	    SpeechletResponse response = new SpeechletResponse();
	    response.setDirectives(directives);
	    response.setNullableShouldEndSession(false);
	    return response;  
	}
    
    /**
     * This method creates a response without any directive
     * @param speech
     * @param intent
     * @return
     */
    public SpeechletResponse getSpeechletResponseWithoutAnyDirective(String speech, Intent intent, boolean shouldSessionEnd, String title, String contentForCard) {
	    SpeechletResponse response = new SpeechletResponse();
	    SimpleCard card = new SimpleCard();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(speech);
		response.setReprompt(new Reprompt());
	    response.setOutputSpeech(plainTextOutputSpeech);
	    response.setNullableShouldEndSession(shouldSessionEnd);
	    card.setTitle(title);
		card.setContent(contentForCard);
		response.setCard(card);
	    return response;  
	}
    /**
     * This method creates a response for MVP ticket Orders-440
     * @param speech
     * @param shouldSessionEnd
     * @return
     */
	  public SpeechletResponse getSpeechletResponseForMVP(String speech,boolean shouldSessionEnd) {
	    SpeechletResponse response = new SpeechletResponse();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(speech);
		response.setReprompt(new Reprompt());
	    response.setOutputSpeech(plainTextOutputSpeech);
	    response.setNullableShouldEndSession(shouldSessionEnd);
	    return response;  
	}
	
    public SpeechletResponse createErrorResponse() {
	    SpeechletResponse response = new SpeechletResponse();
	    SimpleCard card = new SimpleCard();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_NO_OTHER_FUNCTIONALITY));
		card.setTitle("Oops!");
		card.setContent(util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_ERROR_RESPONSE_FOR_NO_OTHER_FUNCTIONALITY));
	    response= SpeechletResponse.newAskResponse(plainTextOutputSpeech, new Reprompt(), card);
	    response.setNullableShouldEndSession(false);
	    return response;  
    }
    
    /**
     * This method returns en error response if Store API is down or there is a bad response
     * @return
     */
    public SpeechletResponse createExceptionResponseForStoreUnavailable() {
	    SpeechletResponse response = new SpeechletResponse();
    	PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_STORE_EXCEPTIONS));
		response.setReprompt(new Reprompt());
	    response.setOutputSpeech(plainTextOutputSpeech);
	    SimpleCard card = new SimpleCard();
		card.setTitle("Oops!");
		card.setContent(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_STORE_EXCEPTIONS));
	    response= SpeechletResponse.newAskResponse(plainTextOutputSpeech, new Reprompt(), card);
	    response.setNullableShouldEndSession(false);
	    return response; 
    }
    /**
     * This method creates an error response if any API is down
     * @return
     */
    public SpeechletResponse createExceptionResponse() {
	    SpeechletResponse response = new SpeechletResponse();
	    SimpleCard card = new SimpleCard();
    	PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
    	plainTextOutputSpeech.setText(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_API_EXCEPTIONS));
    	card.setTitle("Oops!");
		card.setContent(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_API_EXCEPTIONS));
		response= SpeechletResponse.newAskResponse(plainTextOutputSpeech, new Reprompt(), card);
	    return response;
    }
	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public FavouriteService getFavouriteService() {
		return favouriteService;
	}

	public void setFavouriteService(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	public CustomerInfoService getCustomerInfoService() {
		return customerInfoService;
	}

	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public Transformer getTransformer() {
		return transformer;
	}

	public void setTransformer(Transformer transformer) {
		this.transformer = transformer;
	}

	public FavouriteService getFavoriteService() {
		return favoriteService;
	}

	public void setFavoriteService(FavouriteService favoriteService) {
		this.favoriteService = favoriteService;
	}

	public PastOrderService getPastOrderService() {
		return pastOrderService;
	}

	public void setPastOrderService(PastOrderService pastOrderService) {
		this.pastOrderService = pastOrderService;
	}

	public FavoriteSpeechHelper getFavoriteSpeechHelper() {
		return favoriteSpeechHelper;
	}

	public void setFavoriteSpeechHelper(FavoriteSpeechHelper favoriteSpeechHelper) {
		this.favoriteSpeechHelper = favoriteSpeechHelper;
	}

	public OrderTypeSpeechHelper getOrderTypeIntentHelper() {
		return orderTypeIntentHelper;
	}

	public void setOrderTypeIntentHelper(OrderTypeSpeechHelper orderTypeIntentHelper) {
		this.orderTypeIntentHelper = orderTypeIntentHelper;
	}

	public AddressSpeechHelper getStreetAddressSpeechHelper() {
		return streetAddressSpeechHelper;
	}

	public void setStreetAddressSpeechHelper(AddressSpeechHelper streetAddressSpeechHelper) {
		this.streetAddressSpeechHelper = streetAddressSpeechHelper;
	}
}
