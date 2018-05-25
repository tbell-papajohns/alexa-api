package com.pji.alexa.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.LinkAccountCard;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.services.CardDetailsService;
import com.pji.alexa.services.CustomerInfoService;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.TokenValidator;
import com.pji.alexa.util.Util;

import io.jsonwebtoken.Claims;

@Component
public class LaunchRequestHelper {
    private static final Logger logger = LoggerFactory.getLogger(LaunchRequestHelper.class);    
	
	@Autowired
	private Util util;
	
	@Autowired
	private CardDetailsService cardDetailsService;
	
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private TokenValidator tokenValidator;
	
	private static final String WELCOME_CARD_TITLE="Welcome";
	

	/**
	 * This method updates the customerid and pji-token
	 * @param session
	 * @param userDataItems
	 * @throws Exception
	 */
	public void updateUserCredentialsFromJWT(Session session, UserDataItems userDataItems) throws Exception {
		if (session.getUser() != null && session.getUser().getAccessToken() != null) {
			logger.debug("Extracting credentials from JWT token");
			Claims claims = tokenValidator.getClaims(session.getUser().getAccessToken());
			userDataItems.setCustomerId((String)claims.get(util.getProperty(Constants.PJI_OAUTH_JWT_BODY_CUSTOMERID)));
			userDataItems.setCustomerToken((String)claims.get(util.getProperty(Constants.PJI_OAUTH_JWT_BODY_PJTOKEN)));
		} else {
			logger.debug("Invalid or Missing Token");
			userDataItems.setCustomerId("66288853");
			userDataItems.setCustomerToken("Zjk4N2JmZWItZGU3Zi00NTFmLTliOTItODc5NTdmZGY1YWVhSWtaYXQ1WGhaMQ==");
		}
	}
	/**
	 * 
	 * @param launchRequest
	 * @param session
	 * @return
	 * @throws SpeechletException
	 */
	public SpeechletResponse getLaunchResponse(LaunchRequest launchRequest, Session session, UserDataItems userDataItems) throws SpeechletException {		
		SpeechletResponse speechletResponse= null;
		Reprompt reprompt = new Reprompt();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		SimpleCard card	=	new SimpleCard();
		String speech="";
		String speechResponseForCard	=	"";
		card.setTitle(WELCOME_CARD_TITLE);
		if(userDataItems.getAddressList().size() > 0) {
			String address = getFirstAddressDetails(userDataItems, session);
			logger.debug("Adding the address in Response " + address);
			String firstAddressSpeech	=	util.getVerbiage(Constants.VERBAGE_ADDRESS_LISTING_FIRST_TIME, address);
			String firstAddressSpeechForCard	=	util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_ADDRESS_LISTING_FIRST_TIME, address);
			speech=util.getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_DEFAULT,firstAddressSpeech);
			speechResponseForCard	=	util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_DEFAULT,firstAddressSpeechForCard);
			plainTextOutputSpeech.setText(speech);
			reprompt.setOutputSpeech(plainTextOutputSpeech);
			card.setContent(speechResponseForCard);
			speechletResponse= SpeechletResponse.newAskResponse(plainTextOutputSpeech, reprompt, card);
			speechletResponse.setShouldEndSession(false);
		}else {
			speech=util.getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_DEFAULT_NO_ADDRESS) + ". You dont have any address configured yet, Please visit www.papajohns.com to update your delivery address";
			plainTextOutputSpeech.setText(speech);			
			speechResponseForCard=	util.getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_DEFAULT_NO_ADDRESS) + ". You don't have any address configured yet, Please visit www.papajohns.com to update your delivery address";
			card.setContent(speechResponseForCard);
			speechletResponse= SpeechletResponse.newTellResponse(plainTextOutputSpeech, card);
		}
		logger.debug("Welcome Speech " + speech);	
		return speechletResponse;
	}

	/**
	 * This method populates initial data for the user.
	 * @param session
	 * @param userDataItems
	 */
	public void populateInitalDataForUser(Session session,UserDataItems userDataItems) throws Exception{
		List<Item> addressList = customerInfoService.getCustomerLocations(userDataItems.getCustomerId(),userDataItems.getCustomerToken());
		List<Item> cardsList = cardDetailsService.getCustomerCardDetails(userDataItems.getCustomerId(),userDataItems.getCustomerToken());
		userDataItems.setPaymentList(cardsList);
		userDataItems.setAddressList(addressList);
		session.setAttribute(Constants.SESSION_USER_DATA, userDataItems);
	}	
	/**
	 * This method returns the response for Account linking if account is not linked
	 * @return
	 */
	public SpeechletResponse getSpeechletForAccountLinking() {
		logger.info("Account linking card");
		SpeechletResponse speechletResponse= null;
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		String speech="";
		speech=util.getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_LINK_ACCOUNT);
		plainTextOutputSpeech.setText(speech);
		speechletResponse= SpeechletResponse.newTellResponse(plainTextOutputSpeech);
		speechletResponse.setCard(new LinkAccountCard());
		return speechletResponse;
	}
	/**
	 * This method returns the first address.
	 * @param userDataItems
	 * @return
	 */
	private String getFirstAddressDetails(UserDataItems userDataItems, Session session) {
		String streetAddress = null;
		String addressId = null;
		boolean isPrimaryPresent = false;
		List<Item> addressList = userDataItems.getAddressList();
		for(Item address : addressList) {
			if (address.isPrimary()) {
				addressId = address.getItemId();
				address.setUttered(true);
				streetAddress = address.getItemName();
				userDataItems.setAddressList(addressList);
				isPrimaryPresent = true;
				break;
			}
		}
		while(!isPrimaryPresent) {
			for (Item address : addressList) {
				addressId = address.getItemId();
				address.setUttered(true);
				streetAddress = address.getItemName();
				userDataItems.setAddressList(addressList);
				break;
			}
		}
		session.setAttribute(Constants.SESSION_USER_DATA, userDataItems);
		session.setAttribute(Constants.SESSION_ORDER_ITEM, addressId);
		return streetAddress;
	}

	public CardDetailsService getCardDetailsService() {
		return cardDetailsService;
	}

	public void setCardDetailsService(CardDetailsService cardDetailsService) {
		this.cardDetailsService = cardDetailsService;
	}
}
