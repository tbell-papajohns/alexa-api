package com.pji.alexa.helper;

import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pji.alexa.model.v2.AddCartItem;
import com.pji.alexa.services.CartService;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;


@RunWith(MockitoJUnitRunner.class)
public class OrderSpeechHelperTest {
	@InjectMocks
	OrderSpeechHelper orderSpeechHelper;
	
	@Mock
	CartService cartService;
	
	@Mock
	Util util;
	
	
	
	@Test
	public void testGetPromptForCartConfirmation() throws Exception {
		
		AddCartItem addCartItem	=	new AddCartItem();
		
		when(cartService.getCartItem("customerId", "storeId", "customerToken", "favoriteId", "pastOrderNumber", 1)).thenReturn(addCartItem);
		
		when(cartService.getGrandTotal(addCartItem, "customerToken")).thenReturn(0.002);
		
		when(util.getVerbiage(Matchers.anyString(), Matchers.any(Double.class))).thenReturn("Success");
		
		
		
		
		Map<String, String> testMap	=	orderSpeechHelper.getPromptForCartConfirmation("customerId", "storeId", "customerToken",
												"favoriteId", "pastOrderNumber", 800, "verbage.grand.total.in.cash");
		String value	=	testMap.get("SpeechPrompt");
		Assert.assertEquals("Success", value);
	}

}
