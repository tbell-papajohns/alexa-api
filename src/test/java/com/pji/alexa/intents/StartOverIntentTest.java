package com.pji.alexa.intents;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class StartOverIntentTest {
	
	@InjectMocks
	StartOverIntent startOverIntent;
	
	@Mock
	Util util;
	
	@Mock
	Transformer transformer;
	
	@Mock
	BaseIntent baseIntent;
	
	@Mock
	IntentRequest intentRequest;
	
	@Mock
	Session session;
	
	@Mock
	OutputSpeech outputSpeech;
	
	@Spy
	UserDataItems userdata;
	
	SpeechletResponse speechletResponse;
	
	AlexaOrder alexaOrder;
	
	@org.junit.Before
	public void Before() throws Exception {
		
			MockitoAnnotations.initMocks(this);
			baseIntent.setUtil(util);
			baseIntent.setTransformer(transformer);
		
			userdata = new UserDataItems();
			userdata = getuserDataItems();

	}
	
	@Test
	public void testStartOver() throws Exception {
		
		when(baseIntent.getUserDataFromSession()).thenReturn(userdata);
		when(baseIntent.getSession()).thenReturn(session);
		when(session.getAttribute("vadla")).thenReturn(true);
		when(baseIntent.getTransformer()).thenReturn(transformer);
		
		
		when(startOverIntent.getUtil().getVerbiage(Constants.VERBAGE_CLEAR_CART)).thenReturn("clearCartSpeech");
		
		when((UserDataItems) baseIntent.getSession().getAttribute(Constants.SESSION_USER_DATA)).thenReturn(userdata);
	
		when(baseIntent.getAlexaOrderObjectFromSession()).thenReturn(alexaOrder);
		
		when(startOverIntent.getUtil().getVerbiage("verbage.start.over","clearCartSpeech")).thenReturn("verbage.start.over");
	
		BaseIntent Base = new BaseIntent();
		BaseIntent spy = Mockito.spy(Base);
		Mockito.doNothing().when(spy).setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
	
		Mockito.doNothing().when(spy).setSessionAttribute(Constants.SESSION_ORDER_OBJECT, Constants.INTERACTION_STEP_ORDER_TYPE);
		
		when(startOverIntent.getTransformer().objectToString(intentRequest)).thenReturn("Intent after starting over");
		when(startOverIntent.getTransformer().objectToString(session)).thenReturn("Session after starting over");
		
		when(startOverIntent.getUtil().getVerbiage("verbage.welcome.message.both.favorite.and.lastorder.exist", "verbage.start.over")).thenReturn("verbage.welcome.message.both.favorite.and.lastorder.exist");
		
		when(startOverIntent.getUtil().getVerbiage("verbage.welcome.message.lastorder.exist", "verbage.start.over")).thenReturn("verbage.welcome.message.lastorder.exist");
		
		when(startOverIntent.getUtil().getVerbiage("verbage.welcome.message.favorite.exist", "verbage.start.over")).thenReturn("verbage.welcome.message.favorite.exist");

		speechletResponse = new SpeechletResponse();

		speechletResponse = startOverIntent.startOver(intentRequest, session);
		PlainTextOutputSpeech outs = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
		Assert.assertEquals("verbage.start.over", outs.getText());
		
	}
	
	private UserDataItems getuserDataItems()
	{	Map<String,Boolean> addressList = new HashMap<>();
		addressList.put("hello", true);
		Item it = new Item();
		it.setId(123);
		it.setItemId("909");
		it.setUtterences(null);
		it.setUttered(false);
		List<Item> itm = new ArrayList<>();
		itm.add(it);
		
		UserDataItems userDataItems = new UserDataItems();
		userDataItems.setAddressList(itm);
		userDataItems.setCustomerId("2323");
		userDataItems.setCustomerToken("34343");
		userDataItems.setDeliveryStoreId("78787");
		userDataItems.setOrderType("both");
		userDataItems.setTerritoryId("98978");
		
		return userDataItems;
	}
}
