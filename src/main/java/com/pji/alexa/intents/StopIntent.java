package com.pji.alexa.intents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.pji.alexa.util.Constants;

/**
 * This class includes the interaction flow logic for Stop Intent
 * 
 * @author bharath
 *
 */
@Component
public class StopIntent extends BaseIntent{
	
	private static final Logger logger = LoggerFactory.getLogger(StopIntent.class);  
	
	public SpeechletResponse createStopResponse(Intent intent) {
		logger.info("Stoping the skill");
		String speech 			= this.getUtil().getVerbiage(Constants.VERBAGE_ORDER_STOP);
		String speechResponseForCard	=	getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ORDER_STOP);
		boolean shouldSessionEnd = true;
	    SpeechletResponse response = getSpeechletResponseWithoutAnyDirective(speech, intent, shouldSessionEnd, getUtil().getVerbiage(Constants.THANKYOU_CARD_TITLE), speechResponseForCard);
	    //response.setCard(getSimpleCard(getUtil().getVerbiage(Constants.THANKYOU_CARD_TITLE),speech));
	    return response;  
    }

}
