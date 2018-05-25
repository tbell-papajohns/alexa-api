package com.pji.alexa.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.exceptions.StoreException;
import com.pji.alexa.model.v2.GeoLocationForm;
import com.pji.alexa.model.v2.StoreForm;
import com.pji.alexa.model.v2.StoreInfo;
import com.pji.alexa.util.Constants;

@Service
public class StoreService extends BaseService {

	public StoreService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}
	
	/**
	 * This method returns entire Store Information
	 * @param customerId
	 * @param token
	 * @return
	 */
	public StoreInfo getStoreDetails(String customerId, String token, String postalCode, String city, String street, String searchType, String roomType, String locationType) throws Exception{
		this.setTokenValue(token);
		this.setUrl(createURL());
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_GET)));
		try {
			return (StoreInfo) sendHttpRequest(StoreInfo.class, searchType, locationType, street, city, postalCode, roomType);
		}catch(Exception e) {
			e.printStackTrace();
			throw new StoreException("Store not available");
		}
	}
	
	/**
	 * 
	 * @param customerId
	 * @param customerToken
	 * @param addressId
	 * @return
	 */
	public StoreForm getDeliveryDetailsAsPerGeoLocation(String customerId, String customerToken, String addressId, GeoLocationForm geoLocationFormData) throws Exception{		
		StoreForm storeForm = null;
		StoreInfo storeInfo = getStoreDetails(customerId, customerToken, geoLocationFormData.getPostalCode(), geoLocationFormData.getCity(), geoLocationFormData.getAddress1(), Constants.ALEXA_ORDER_TYPE, Constants.ALEXA_DELIVERY_ROOM_TYPE, Constants.ALEXA_DELIVERY_TYPE);
		for(StoreForm deliveryStoreForm : storeInfo.getData().getDeliveryStores()) {
			if(deliveryStoreForm.getStoreId() != null && deliveryStoreForm.getTerritoryId() != null) {
					storeForm= deliveryStoreForm;
					break;
			}
		}
		return storeForm;
	}
	
	
	public String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_STORE_SEARCH_URL);
	}
}
