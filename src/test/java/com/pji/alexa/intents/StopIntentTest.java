package com.pji.alexa.intents;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;

/**
 * This class in an Test class which would cater to test case for Stop Intent
 * @author Punit
 *
 */
@SpringBootTest
public class StopIntentTest {

	private static final Logger logger = LoggerFactory.getLogger(OutOfMVPScopeIntentTest.class);  
	@InjectMocks
	StopIntent stopIntent;

	@Mock
	BaseIntent baseIntent;

	@Mock
	Util util;
	@MockBean
	Intent intent;

	/**
	 * This method is invoked first when the test case run
	 */
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		baseIntent.setUtil(util);
		
	}
	/**
	 * This method is invoked  when the close run test
	 */
	@After
	public void after() {
	}

	/**
	 * This method is invoked when the stop intent test run
	 */
	@Test
	public void testCreateStopResponse() {
		try	{
			SpeechletResponse speechletResponse = new SpeechletResponse();
			when(stopIntent.getUtil().getVerbiage(Constants.VERBAGE_ORDER_STOP)).thenReturn("Stop Intent Test is Success");
			when(baseIntent.getSpeechletResponseWithoutAnyDirective("",intent,false,"","")).thenReturn(speechletResponse);
			speechletResponse = stopIntent.createStopResponse(intent);
			PlainTextOutputSpeech outs = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
			assertEquals("Stop Intent Test is Success", outs.getText());}
		catch(Exception exception){
			logger.info(exception.getMessage());}

	}


}
