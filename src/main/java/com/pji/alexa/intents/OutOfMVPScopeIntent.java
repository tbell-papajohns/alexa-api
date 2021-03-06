package com.pji.alexa.intents;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;

@Component
public class OutOfMVPScopeIntent extends BaseIntent{
	

	private static final Logger logger = LoggerFactory.getLogger(HelpIntent.class);  
	
	public SpeechletResponse createOutOfMVPResponse(Session session) throws InstantiationException, IllegalAccessException {
		logger.info("Building help response");
		SpeechletResponse speechletResponse= null;
		Reprompt reprompt = new Reprompt();
		SimpleCard card = new SimpleCard();
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		UserDataItems userdata = this.getUserDataFromSession();
		if(this.getSessionAttributeStringType(Constants.INTERACTION_NEXT_STEP).equals(Constants.INTERACTION_STEP_ADDRESS)) {
			List<Item> addressList = userdata.getAddressList();
			for(Item address : addressList) {
				address.setUttered(false);
			}
			userdata.setAddressList(addressList);
		}
		session.setAttribute(Constants.SESSION_USER_DATA, userdata);
		String speech = getUtil().getVerbiage(Constants.VERBAGE_ORDER_OUTOFMVP);
		plainTextOutputSpeech.setText(speech);
		card.setTitle(getUtil().getVerbiage(Constants.HELP_CONTENT_CARD_TITLE));
		card.setContent(getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_OUTOFMVP));
		speechletResponse= SpeechletResponse.newAskResponse(plainTextOutputSpeech, reprompt, card);
		speechletResponse.setShouldEndSession(false);
		//speechletResponse.setCard(getSimpleCard(getUtil().getVerbiage(Constants.HELP_CONTENT_CARD_TITLE), plainTextOutputSpeech.getText()));
	    return speechletResponse;  
    }
}
