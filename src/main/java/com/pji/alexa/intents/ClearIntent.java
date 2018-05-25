package com.pji.alexa.intents;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;

@Component
public class ClearIntent extends BaseIntent{
	
	private static final Logger logger = LoggerFactory.getLogger(ClearIntent.class);
	
	public SpeechletResponse clearCart(IntentRequest intentRequest, Session session) throws Exception {
		logger.debug("Clearing the cart");
		SpeechletResponse speechletResponse= null;
		Reprompt reprompt = new Reprompt();
		SimpleCard card = new SimpleCard();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		UserDataItems userdata = this.getUserDataFromSession();
		String speech = "";
		String speechResponseForCard = "";
		String clearCartSpeech = getUtil().getVerbiage(Constants.VERBAGE_CLEAR_CART);
		
		logger.debug("clearing the session");
	
		if(this.getSessionAttributeStringType(Constants.INTERACTION_NEXT_STEP).equals(Constants.INTERACTION_STEP_ADDRESS)) {
			List<Item> addressList = userdata.getAddressList();
			for(Item address : addressList) {
				address.setUttered(false);
			}
			userdata.setAddressList(addressList);
			this.setSessionAttribute(Constants.SESSION_USER_DATA, userdata);
			speech = getUtil().getVerbiage(Constants.VERBAGE_CLEAR_CART_NO_ITEMS);
			speechResponseForCard	=	getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_CLEAR_CART_NO_ITEMS);
		}else {
			if(userdata.getOrderType().equalsIgnoreCase(Constants.ORDER_TYPE_BOTH)) {
				speech = getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST, clearCartSpeech);
				speechResponseForCard	=	getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST, clearCartSpeech);
			}else if(userdata.getOrderType().equalsIgnoreCase(Constants.ORDER_TYPE_RECENT)) {
				speech = getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST, clearCartSpeech);
				speechResponseForCard	=	getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST, clearCartSpeech);
			}else if(userdata.getOrderType().equalsIgnoreCase(Constants.ORDER_TYPE_FAVORITE)) {
				speech = getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST, clearCartSpeech);
				speechResponseForCard	=	getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST, clearCartSpeech);
			}		
			AlexaOrder alexaOrder = this.getAlexaOrderObjectFromSession();
			alexaOrder.setOrderType(null);
			alexaOrder.setFavoriteId(null);
			alexaOrder.setPastOrderNumber(null);
			alexaOrder.setOrderTypeConfirmed(false);
			alexaOrder.setOrderConfirmed(false);
			alexaOrder.setCartConfirmed(false);
			alexaOrder.setCardId(null);
			alexaOrder.setPaymentType(null);
			this.setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);

			this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ORDER_TYPE);
			logger.debug("Intent after clearing cart: " + getTransformer().objectToString(intentRequest));
			logger.debug("Session after clearing cart: " + getTransformer().objectToString(session));
		}
		plainTextOutputSpeech.setText(speech);
		reprompt.setOutputSpeech(plainTextOutputSpeech);
		card.setTitle(getUtil().getVerbiage(Constants.CLEAR_CART_CARD_TITLE));
		card.setContent(speechResponseForCard);
		speechletResponse = SpeechletResponse.newAskResponse(plainTextOutputSpeech, reprompt, card);
		//speechletResponse.setCard(getSimpleCard(Constants.CLEAR_CART_CARD_TITLE, speech));
		return speechletResponse;
	}
}