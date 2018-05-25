package com.pji.alexa.intents;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class ClearIntentTest {
	
	@InjectMocks
	ClearIntent clearIntent;
	
	@Mock
	IntentRequest intentRequest;
	
	@Mock
	Session session;
	
	@Mock
	BaseIntent baseIntent;
	
	@Spy
	UserDataItems userdata;
	
	@Mock
	Util util;
	
	@Mock
	Transformer transformer;
	
	AlexaOrder alexaOrder;

	@org.junit.Before
	public void Before() throws Exception {
		
			MockitoAnnotations.initMocks(this);
			baseIntent.setUtil(util);
			baseIntent.setTransformer(transformer);
		
			userdata = new UserDataItems();
			userdata = getuserDataItems();

	}
	
	@SuppressWarnings("unused")
	@Test
	public void testClearCart() throws Exception {
		SpeechletResponse speechletResponse = new SpeechletResponse();

		when(baseIntent.getUserDataFromSession()).thenReturn(userdata);
		when(baseIntent.getSession()).thenReturn(session);
		when(session.getAttribute("nextstep") instanceof String).thenReturn(true);
		when(baseIntent.getTransformer()).thenReturn(transformer);
	
		when((UserDataItems) baseIntent.getSession().getAttribute(Constants.SESSION_USER_DATA)).thenReturn(userdata);
		
	
		when(baseIntent.getUtil()).thenReturn(util);
		when(baseIntent.getUtil().getVerbiage(Constants.VERBAGE_CLEAR_CART)).thenReturn("clearCartSpeech");
		
	
		BaseIntent Base = new BaseIntent();
		BaseIntent spy = Mockito.spy(Base);
		Mockito.doNothing().when(spy).setSessionAttribute(Constants.SESSION_USER_DATA, userdata);
	
	
		when(baseIntent.getUtil().getVerbiage(Constants.VERBAGE_CLEAR_CART_NO_ITEMS)).thenReturn("speech");

		when(baseIntent.getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST, "clearCartSpeech")).thenReturn("speech");
		
		when(baseIntent.getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST, "clearCartSpeech")).thenReturn("speech");
		
		when(baseIntent.getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST, "clearCartSpeech")).thenReturn("speech");
		
		Mockito.doNothing().when(spy).setSessionAttribute(Constants.SESSION_ORDER_OBJECT, alexaOrder);
		
		Mockito.doNothing().when(spy).setSessionAttribute(Constants.INTERACTION_NEXT_STEP, Constants.INTERACTION_STEP_ORDER_TYPE);
		
		SpeechletResponse speechlet = clearIntent.clearCart(intentRequest, session);
		userdata.setOrderType("recentOrder");
		SpeechletResponse speechletWhenrecentOrder = clearIntent.clearCart(intentRequest, session);
		userdata.setOrderType("favorite");
		SpeechletResponse speechletWhenfavorite = clearIntent.clearCart(intentRequest, session);
	}
	
	@SuppressWarnings("unused")
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
