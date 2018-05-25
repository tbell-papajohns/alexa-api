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
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;

@SpringBootTest
public class CancelIntentTest {

	private static final Logger logger = LoggerFactory.getLogger(OutOfMVPScopeIntentTest.class);  
	@InjectMocks
	CancelIntent cancelIntent;

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
	 * This method is invoked when the out of mvp scope test run
	 */
	@Test
	public void testCreateCancelResponse() {
		try	{
			SpeechletResponse speechletResponse = new SpeechletResponse();
			when(cancelIntent.getUtil().getVerbiage(Constants.VERBAGE_ORDER_CANCEL)).thenReturn("MVP Test is Success");
			when(baseIntent.getSpeechletResponseWithoutAnyDirective("", intent,false,"","")).thenReturn(speechletResponse);
			speechletResponse = cancelIntent.createCancelResponse(intent);
			PlainTextOutputSpeech outs = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
			assertEquals("MVP Test is Success", outs.getText());}
		catch(Exception exception){
			logger.info(exception.getMessage());}

	}


}
