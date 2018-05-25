package com.pji.alexa.intents;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.pji.alexa.services.CustomerInfoService;
import com.pji.alexa.services.FavouriteService;
import com.pji.alexa.services.OrderService;
import com.pji.alexa.services.PastOrderService;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class BaseIntentTest {
	
	
	@Spy
	@InjectMocks
	BaseIntent baseIntent;
	
	@Mock
	FavouriteService favouriteService;
	
	@Mock
	CustomerInfoService customerInfoService;
	
	@Mock
	OrderService orderService;
	
	@Mock
	Util util;
	
	@Mock
	Transformer transformer;	

	@Mock
	FavouriteService favoriteService;
	
	@Mock
	PastOrderService pastOrderService;
	
	@MockBean
	Intent intent;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void testCreateErrorResponse() {
		SpeechletResponse speechletResponse = new SpeechletResponse();
		when(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_NO_OTHER_FUNCTIONALITY)).thenReturn("verbage.error.response");
		
		speechletResponse = baseIntent.createErrorResponse();
		PlainTextOutputSpeech response = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
		Assert.assertEquals("verbage.error.response", response.getText());
	}

	@Test
	public void testCreateExceptionResponse() {
		SpeechletResponse speechletResponse = new SpeechletResponse();
		when(util.getVerbiage(Constants.VERBAGE_ERROR_RESPONSE_FOR_API_EXCEPTIONS)).thenReturn("verbage.api.exception.response");
		
		speechletResponse = baseIntent.createExceptionResponse();
		PlainTextOutputSpeech response = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
		Assert.assertEquals("verbage.api.exception.response", response.getText());
		
	}
	
	

}
