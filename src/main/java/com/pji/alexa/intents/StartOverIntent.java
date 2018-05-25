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
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;

@Component
public class StartOverIntent extends BaseIntent{
	
	private static final Logger logger = LoggerFactory.getLogger(StartOverIntent.class);
	
	public SpeechletResponse startOver(IntentRequest intentRequest, Session session) throws Exception {
		logger.debug("Starting over the order");
		SpeechletResponse speechletResponse= null;
		Reprompt reprompt = new Reprompt();
		SimpleCard card = new SimpleCard();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();

		String clearCartSpeech = getUtil().getVerbiage(Constants.VERBAGE_CLEAR_CART);
		
		UserDataItems userdata = this.getUserDataFromSession();
		List<Item> addressList = userdata.getAddressList();
		for(Item address : addressList) {
			address.setUttered(false);
		}
		userdata.setAddressList(addressList);
		this.setSessionAttribute(Constants.SESSION_USER_DATA, userdata);
		logger.debug("clearing the session");
		this.getSession().removeAttribute(Constants.SESSION_ORDER_OBJECT);
		this.setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ADDRESS);
		logger.debug("Intent after starting over: " + getTransformer().objectToString(intentRequest));
		logger.debug("Session after starting over: " + getTransformer().objectToString(session));

		String speech = getUtil().getVerbiage(Constants.VERBAGE_START_OVER, clearCartSpeech);
		plainTextOutputSpeech.setText(speech);
		reprompt.setOutputSpeech(plainTextOutputSpeech);
		card.setTitle(getUtil().getVerbiage(Constants.THANKYOU_CARD_TITLE));
		card.setContent(getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_START_OVER, clearCartSpeech));
		speechletResponse = SpeechletResponse.newAskResponse(plainTextOutputSpeech, reprompt, card);//890
		//speechletResponse.setCard(getSimpleCard(getUtil().getVerbiage(Constants.THANKYOU_CARD_TITLE),speech));
		return speechletResponse;
	}
}