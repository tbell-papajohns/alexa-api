package com.pji.alexa.handler;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.pji.alexa.exceptions.StoreException;
import com.pji.alexa.helper.LaunchRequestHelper;
import com.pji.alexa.intents.CancelIntent;
import com.pji.alexa.intents.ClearIntent;
import com.pji.alexa.intents.HelpIntent;
import com.pji.alexa.intents.OrderIntent;
import com.pji.alexa.intents.OutOfMVPScopeIntent;
import com.pji.alexa.intents.StartOverIntent;
import com.pji.alexa.intents.StopIntent;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;

/**
 * This class in an handler class which would cater to all events generated from the alexa device
 * @author anubh
 *
 */
@Component
public class RequestHandler implements Speechlet {
	
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);    

    @Autowired
    private Util util;
    
    @Autowired
    private LaunchRequestHelper launchRequestHelper;
    
    @Autowired
    private OrderIntent orderIntent;
    
    @Autowired
    private StartOverIntent startOverIntent;
    
    @Autowired
    private ClearIntent clearIntent;
    
    @Autowired
    private HelpIntent helpIntent;
    
    @Autowired
    private CancelIntent cancelIntent;
    
    @Autowired
    private StopIntent stopIntent;
    
    @Autowired
    private OutOfMVPScopeIntent outOfMVPIntent;
    
    @Autowired
    private Transformer transformer;
    
    
	public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {		
		
		logger.debug("Intent Request : " + transformer.objectToString(intentRequest));
		logger.debug("Session : " + transformer.objectToString(session));

		Intent intent = intentRequest.getIntent();
        String requestIntentName = (intent != null) ? intent.getName() : null;
        SpeechletResponse speechletResponse = null;
        if(session.getAttribute(Constants.SESSION_USER_DATA) == null) {
    		logger.debug("Initial Data not available, populating it now");
    		UserDataItems userDataItems = new UserDataItems();
    		try {
				populateInitialData(session, userDataItems);
			} catch (Exception e) {
				logger.error("Exception while populating user data "+e.toString());
				speechletResponse = orderIntent.createExceptionResponse();
				return speechletResponse;
			}
        }
        
	    try {
	        if (this.orderIntent.getIntentName().equals(requestIntentName)) {       	   
	        	this.orderIntent.setSession(session);	            
	            orderIntent.updateSlotValues(intentRequest.getIntent().getSlots(),intentRequest.getIntent(),session);	                    
				speechletResponse = orderIntent.getPromptForSlotCollection(intentRequest);
	        }else if(util.getProperty(Constants.INTENT_NAME_START_OVER).equals(requestIntentName)) {
	        	this.startOverIntent.setSession(session);
	        	speechletResponse = startOverIntent.startOver(intentRequest, session);
	        }else if(util.getProperty(Constants.INTENT_NAME_CLEAR).equals(requestIntentName)){
	        	this.clearIntent.setSession(session);
	        	speechletResponse = clearIntent.clearCart(intentRequest, session);
	        }else if(util.getProperty(Constants.INTENT_NAME_HELP).equals(requestIntentName)) {
	        	this.helpIntent.setSession(session);
	        	speechletResponse = helpIntent.createHelpResponse(session);
	        }else if(util.getProperty(Constants.INTENT_NAME_CANCEL).equals(requestIntentName)) {
	        	speechletResponse = cancelIntent.createCancelResponse(intentRequest.getIntent());
	        }else if(util.getProperty(Constants.INTENT_NAME_STOP).equals(requestIntentName)) {
	        	speechletResponse = stopIntent.createStopResponse(intentRequest.getIntent());
	        }else if(util.getProperty(Constants.INTENT_NAME_OUTOFMVP).equals(requestIntentName)) {
	        	this.outOfMVPIntent.setSession(session);
	        	speechletResponse = outOfMVPIntent.createOutOfMVPResponse(session);
	        }else {
	        	speechletResponse=orderIntent.createErrorResponse();
	        }
	    }
	    catch (StoreException e) {
        	e.printStackTrace();
			speechletResponse = orderIntent.createExceptionResponseForStoreUnavailable();
		} 
        catch (Exception e) {
        	e.printStackTrace();
        	logger.error("Exception while getting speechlet response "+e);
			speechletResponse = orderIntent.createExceptionResponse();
		}  
		logger.debug("Response : " + transformer.objectToString(speechletResponse));
		logger.debug("Session : " + transformer.objectToString(session));
		
		return speechletResponse;
	}
	 /**
	  * This method is invoked when the skill is launched
	  */
	public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
		logger.debug("Launch Request1 : " + transformer.objectToString(launchRequest));
		UserDataItems userDataItems = new UserDataItems();
		SpeechletResponse speechletResponse =null;
		try {
			populateInitialData(session, userDataItems);
			speechletResponse = launchRequestHelper.getLaunchResponse(launchRequest, session, userDataItems);
		} catch (Exception e) {
			logger.error(e.getClass().getCanonicalName());
			speechletResponse = launchRequestHelper.getSpeechletForAccountLinking();
		} 
		logger.debug("Response : " + transformer.objectToString(speechletResponse));
		logger.debug("Session : " + transformer.objectToString(session));
		return speechletResponse;
	}

	public void onSessionEnded(SessionEndedRequest sessionEndRequest, Session session) throws SpeechletException {
		logger.debug("Session End Request : "+transformer.objectToString(sessionEndRequest));
	}
	public void onSessionStarted(SessionStartedRequest sessionStartRequest, Session session) throws SpeechletException {
		logger.debug("Session Start Request");
	}
	
	public void populateInitialData(Session session, UserDataItems userDataItems) throws Exception {
		launchRequestHelper.updateUserCredentialsFromJWT(session,userDataItems);
		if(StringUtils.isNotEmpty(userDataItems.getCustomerId()) && StringUtils.isNotEmpty(userDataItems.getCustomerToken())) {
			launchRequestHelper.populateInitalDataForUser(session,userDataItems);
			session.setAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ADDRESS);
		}
	}
	@PostConstruct
	public void setIntentNames() {
		this.orderIntent.setIntentName(util.getProperty(Constants.INTENT_NAME_ORDER));
	}
}
