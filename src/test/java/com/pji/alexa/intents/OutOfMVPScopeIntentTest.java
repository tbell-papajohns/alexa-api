package com.pji.alexa.intents;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import com.pji.alexa.handler.RequestHandler;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.Item;
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
public class OutOfMVPScopeIntentTest {
	private static final Logger logger = LoggerFactory.getLogger(OutOfMVPScopeIntentTest.class);  
	
	@InjectMocks
	OutOfMVPScopeIntent outOfMVPScopeIntent;

	@Mock
	BaseIntent baseIntent;

	@Mock
	Util util;
	@Mock
	Transformer transformer;
	@Mock
	AlexaOrder alexaOrder;
	@Mock
	Constants Constants;
	//@Autowired
@Mock
Session session;
	/**
	 * This method is invoked first when the test case run
	 */
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		outOfMVPScopeIntent.setUtil(util);
		
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
	public void testCreateOutOfMVPResponse() {
		try	{
			
			SpeechletResponse speechletResponse = new SpeechletResponse();
			UserDataItems userDataItems=new UserDataItems();
			userDataItems=getuserDataItems();
			
			when(outOfMVPScopeIntent.getUtil().getVerbiage(Constants.VERBAGE_ORDER_OUTOFMVP)).thenReturn("MVP Test is Success");
		
			session.setAttribute(Constants.SESSION_USER_DATA, Constants.SESSION_USER_DATA);
			
			HashMap<String, Object> value=new HashMap<String,Object>();
					
			when( outOfMVPScopeIntent.getSession().getAttribute(Constants.SESSION_USER_DATA)).thenReturn(userDataItems);
			
			when(outOfMVPScopeIntent.getUserDataFromSession()).thenReturn(userDataItems);
		
			speechletResponse = outOfMVPScopeIntent.createOutOfMVPResponse(session);
			PlainTextOutputSpeech outs = (PlainTextOutputSpeech) speechletResponse.getOutputSpeech();
			assertEquals("MVP Test is Success", outs.getText());}
		catch(Exception exception){
			logger.info(exception.getMessage());}

	}

	private UserDataItems getuserDataItems()
	{	Map<String,Boolean> addressList = new HashMap<>();
		addressList.put("hello", true);
		Item it = new Item();
		it.setId(123);
		it.setItemId("909");
		it.setUtterences(null);
		List<Item> itm = new ArrayList<>();
		itm.add(it);
		
		UserDataItems userDataItems = new UserDataItems();
		//userDataItems.setAddressList(addressList);
		userDataItems.setCustomerId("2323");
		userDataItems.setCustomerToken("34343");
		userDataItems.setDeliveryStoreId("78787");
		//userDataItems.setFavoriteList(addressList);
		//userDataItems.setItemList(itm);
		userDataItems.setOrderType("both");
		//userDataItems.setPastOrderNumber("3535");
		userDataItems.setTerritoryId("98978");
		
		return userDataItems;
	}
}
