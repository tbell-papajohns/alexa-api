package com.pji.alexa.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pji.alexa.model.v2.GeoLocationForm;
import com.pji.alexa.model.v2.StoreForm;
import com.pji.alexa.model.v2.StoreInfo;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;

@SpringBootTest
public class StoreServiceTest {
	@Spy
	@InjectMocks
	StoreService storeService;
	
	@Mock
	RestTemplateBuilder restTemplateBuilder;
	
	@Mock
	Constants Constants;

	@Mock
	Util util;
	
	@Mock
	BaseService baseService;
	
	@Mock
	Environment environment;
	
	@Spy
	RestTemplate restTemplate;
	
	@Mock
	ResponseEntity response;
	
	@Mock
	org.springframework.http.HttpEntity<String> httpEntity;
	
	@Mock
	GeoLocationForm geoLocationFormData;
	
	@Mock
	StoreInfo storeInfo;
	
	@MockBean
	Class<String> returnType;
	
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
		//storeService=spy(new StoreService(restTemplateBuilder));
		//baseService=spy(new BaseService(restTemplateBuilder));
		storeService.setUtil(util);
		
		
	}

	@Test
	public void testStoreService() {
		RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
		StoreService StoreService=new StoreService(restTemplateBuilder);
	}
	
	@Test
	public void testGetStoreDetails() {
		try {
		Mockito.doNothing().when(storeService).setTokenValue("token");;
		//storeService.setBaseUrl("www.h3.com");
		String baseURL="www.hmn.com";
			
	
		when(storeService.getBaseUrl()).thenReturn(new String("dffd"));
		when(storeService.getUtil()).thenReturn(util);
		
		when(environment.getProperty("pji.endpoint.store.search.url")).thenReturn("/api/v2/stores?searchType={searchType}&locationType={locationType}&street={street}&city={city}&postalCode={postalCode}&roomType={roomType}");
		
	
		when(storeService.getUtil().getProperty("pji.endpoint.store.search.url")).thenReturn("welcome");
		
		when(storeService.createURL()).thenReturn("www.hmn1.com");
		StoreInfo storeInfo=new StoreInfo();	
	
		Map<String, String> map=new HashMap<String,String>();
		map.put("first", "first");
	
		doReturn(map).when(storeService).getHeaders();
		
		doReturn(map).when(storeService).sendHttpRequest(StoreInfo.class, "55678", "newyork","usa" , "nexon", "3435", "valid");
		
		doReturn(HttpMethod.POST).when(storeService).getHttpMethod();
		
		Object[]uriParams=new Object[1];
		
		doReturn(response).when(restTemplate).exchange("www.papajhons.com", HttpMethod.POST,httpEntity,returnType ,uriParams);;
	
		storeInfo = storeService.getStoreDetails("55678", "454dgfgfdgfg", "44567", "Newyork", "Landmark", "Store", "larg", "home");
		assertNull(storeInfo);
		
		//StoreForm StoreForm=storeService.getDeliveryDetailsAsPerGeoLocation("45567","4354656", "578", geoLocationFormData);

		
		}
		
	 catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	}
	
	/*public void testGetDeliveryDetailsAsPerGeoLocation() {
		
		doReturn(map).when(storeService).getStoreDetails(customerId, customerToken, geoLocationFormData.getPostalCode(), geoLocationFormData.getCity(), geoLocationFormData.getAddress1(), Constants.ALEXA_ORDER_TYPE, Constants.ALEXA_DELIVERY_ROOM_TYPE, Constants.ALEXA_DELIVERY_TYPE)
	}*/
}
