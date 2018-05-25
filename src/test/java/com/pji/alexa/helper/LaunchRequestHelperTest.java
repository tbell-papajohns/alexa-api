package com.pji.alexa.helper;

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
import org.mockito.runners.MockitoJUnitRunner;

import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.User;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.services.CardDetailsService;
import com.pji.alexa.services.CustomerInfoService;

import com.pji.alexa.util.Constants;
import com.pji.alexa.util.TokenValidator;
import com.pji.alexa.util.Util;

import io.jsonwebtoken.Claims;

@RunWith(MockitoJUnitRunner.class)
public class LaunchRequestHelperTest {
	
	@InjectMocks
	LaunchRequestHelper launchRequestHelper;
	
	@Mock
	Util util;
	
	@Mock
	CardDetailsService cardDetailsService;
	
	
	@Mock
	CustomerInfoService customerInfoService;
	
	@Mock
	TokenValidator tokenValidator;
	
	@Mock
	Claims claims;
	
	@Mock
	Session session;
	
	@Mock
	User user;
	
	@Mock
	LaunchRequest launchRequest;
	
	UserDataItems userDataItems;

	@Test
	public void testUpdateUserCredentialsFromJWT() throws Exception {
		
		userDataItems = getuserDataItems();
		
		when(session.getUser()).thenReturn(user);
		
		when(user.getAccessToken()).thenReturn("Token");
		
		when(tokenValidator.getClaims(session.getUser().getAccessToken())).thenReturn(claims);
		
		when((String)claims.get(util.getProperty(Constants.PJI_OAUTH_JWT_BODY_CUSTOMERID))).thenReturn("Return:-pji.oauth.jwt.body.customerid");
		
		when((String)claims.get(util.getProperty(Constants.PJI_OAUTH_JWT_BODY_PJTOKEN))).thenReturn("Return:-pji.oauth.jwt.body.pjtoken");
		
		launchRequestHelper.updateUserCredentialsFromJWT(session, userDataItems);
		
		when(user.getAccessToken()).thenReturn(null);
		launchRequestHelper.updateUserCredentialsFromJWT(session, userDataItems);
		
	}

	@Test
	public void testPopulateInitalDataForUser() throws Exception {
		userDataItems = getuserDataItems();
		List<String> list = new ArrayList<>();
		list.add("value1");
		list.add("value2");
		list.add("value3");
		list.add("value4");
		list.add("value5");
		
		
		Item item = new Item();
		item.setId(123);
		item.setItemId("909");
		item.setUtterences(null);
		List<Item> itm = new ArrayList<>();
		itm.add(item);
		
		when(customerInfoService.getCustomerLocations(userDataItems.getCustomerId(),userDataItems.getCustomerToken())).thenReturn(itm);
		
		when(cardDetailsService.getCustomerCardDetails(userDataItems.getCustomerId(),userDataItems.getCustomerToken())).thenReturn(itm);
		
		launchRequestHelper.populateInitalDataForUser(session, userDataItems);
	}

	@SuppressWarnings("unused")
	@Test
	public void testGetSpeechletForAccountLinking() {
		SpeechletResponse speechletResponse= new SpeechletResponse();
	
		when(util.getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_LINK_ACCOUNT)).thenReturn("speech");
		
		speechletResponse = launchRequestHelper.getSpeechletForAccountLinking();
		
		PlainTextOutputSpeech result = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
		
		Assert.assertEquals("speech", result.getText());
	}
	private UserDataItems getuserDataItems()
	{	Map<String,Boolean> addressList = new HashMap<>();
		addressList.put("hello", true);
		addressList.put("welcome", false);
		Item it = new Item();
		it.setId(123);
		it.setItemId("909");
		it.setUtterences(null);
		List<Item> itm = new ArrayList<>();
		itm.add(it);
		
		UserDataItems userDataItems = new UserDataItems();
		userDataItems.setCustomerId("customerId");
		userDataItems.setCustomerToken("customerToken");
		userDataItems.setDeliveryStoreId("storeId");
		userDataItems.setOrderType("favorite");
		userDataItems.setTerritoryId("TerritoryId");
		
		
		return userDataItems;
	}
}
