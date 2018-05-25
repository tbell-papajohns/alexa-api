package com.pji.alexa.intents;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;

/**
 * This class in an Test class which would cater to test case for out of MVP scope
 * @author Punit
 *
 */
@SpringBootTest
public class HelpIntentTest {

	private static final Logger logger = LoggerFactory.getLogger(OutOfMVPScopeIntentTest.class);
	@InjectMocks
	HelpIntent helpIntent;

	@Mock
	BaseIntent baseIntent;

	@Mock
	Util util;
	@Mock
	Session session;

	@Mock
	Transformer transformer;

	@Mock
	AlexaOrder alexaOrder;

	/**
	 * This method is invoked first when the test case run
	 */
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		baseIntent.setUtil(util);

	}

	/**
	 * This method is invoked when the close run test
	 */
	@After
	public void after() {
	}

	/**
	 * This method is invoked when call help 
	 */
	@Test
	public void testCreateHelpResponse() {
		try {
			PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
			UserDataItems userDataItems = new UserDataItems();

			Reprompt reprompt = new Reprompt();
			SpeechletResponse speechletResponse = new SpeechletResponse();
			when(helpIntent.getUtil().getVerbiage(Constants.VERBAGE_ORDER_HELP)).thenReturn("Help Intent Test is Success");

			when(baseIntent.getSession()).thenReturn(session);

			when(baseIntent.getSession()).thenReturn(session);
			when(session.getAttribute("test")).thenReturn(true);
			when(baseIntent.getTransformer()).thenReturn(transformer);
			when((UserDataItems) baseIntent.getSession().getAttribute(Constants.SESSION_USER_DATA))
					.thenReturn(userDataItems);

			when((AlexaOrder) baseIntent.getSession().getAttribute("sessionOrderObject")).thenReturn(alexaOrder);
			when(baseIntent.getUserDataFromSession()).thenReturn(userDataItems);

			speechletResponse = helpIntent.createHelpResponse(session);
			PlainTextOutputSpeech outs = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
			assertEquals("Help Intent Test is Success", outs.getText());
		} catch (Exception exception) {
			logger.info(exception.getMessage());
		}

	}

}
