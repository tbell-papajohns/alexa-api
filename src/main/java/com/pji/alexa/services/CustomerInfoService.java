package com.pji.alexa.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.CustomerInfo;
import com.pji.alexa.model.v2.CustomerLocationForm;
import com.pji.alexa.model.v2.GeoLocationForm;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.StoreForm;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;
 
@Service
public class CustomerInfoService extends BaseService {
 
	public CustomerInfoService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}

	/**
	 * This method returns entire Customer Information
	 * @param customerId
	 * @param token
	 * @return
	 */
	public CustomerInfo getCustomerDetails(String customerId, String token) throws Exception{
		this.setTokenValue(token);
		this.setUrl(createURL());
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_GET)));
		return (CustomerInfo) sendHttpRequest(CustomerInfo.class,customerId);
	}
	
	/**
	 * This method returns List of Customer location Ids
	 * @param customerId
	 * @param token
	 * @return
	 */
	public List<Item> getCustomerLocations(String customerId, String token) throws Exception{
		List<Item> addressList = new ArrayList<>();
		CustomerInfo customerInfo = getCustomerDetails(customerId,token);
		List<CustomerLocationForm> customerLocationForms = customerInfo.getData().getCustomerLocations();
		Item item;
		for(int i=0; i < customerLocationForms.size(); i++){
			CustomerLocationForm customerLocationForm = customerLocationForms.get(i);
			String locationId = customerLocationForm.getLocationId().toString();
			boolean isPrimary = customerLocationForm.getPrimaryFlag();
			item = new Item(i+1, locationId, customerLocationForm.getGeoLocation().getAddress1(), null, null, false, isPrimary);
			addressList.add(item);
		}
		return addressList;
	}
	
	/**
	 * This method updates the Delivery Store Id,
	 * @param customerId
	 * @param token
	 * @param locationId
	 * @return
	 */
	public void updateCustomerStoreIdAndTerritoryId(String locationId, UserDataItems userdata) throws Exception{
		String storeId = null;
		String territoryId= null;
		GeoLocationForm geoLocationForm = null;
		CustomerInfo customerInfo = getCustomerDetails(userdata.getCustomerId(),userdata.getCustomerToken());
		for(CustomerLocationForm customerLocationForm : customerInfo.getData().getCustomerLocations()) {
			if (customerLocationForm.getLocationId().toString().equals(locationId)) {
				geoLocationForm= customerLocationForm.getGeoLocation();
				if(customerLocationForm.getDeliveryStoreId() != null) {
					storeId = customerLocationForm.getDeliveryStoreId().toString();
					territoryId = customerLocationForm.getGeoLocation().getTerritoryId().toString();
				}
				break;
			}
		}
		if(StringUtils.isEmpty(storeId)) {			
			StoreForm storeFormData = getStoreService().getDeliveryDetailsAsPerGeoLocation(userdata.getCustomerId(),userdata.getCustomerToken(),locationId, geoLocationForm);
			storeId= storeFormData.getStoreId().toString();
			territoryId= storeFormData.getTerritoryId().toString();
		}
		userdata.setDeliveryStoreId(storeId);
		userdata.setTerritoryId(territoryId);
	}
	
	/**
	 * This method returns Street address
	 * @param customerId
	 * @param token
	 * @param locationId
	 * @return
	 */
	public String getStreetAddress(String customerId, String token, String locationId) throws Exception{
		String streetAddress = "";
		CustomerInfo customerInfo = getCustomerDetails(customerId,token);
		for(CustomerLocationForm customerLocationForm : customerInfo.getData().getCustomerLocations()) {
			if (customerLocationForm.getLocationId().toString().equals(locationId)) {
				streetAddress = customerLocationForm.getGeoLocation().getAddress1();
				break;
			}
		}
		return streetAddress;
	}
	
	/**
	 * This method returns Delivery Territory Id
	 * @param customerId
	 * @param token
	 * @param locationId
	 * @return
	 */
	public Integer getDeliveryTerritoryId(String customerId, String token, String locationId) throws Exception{
		int deliveryTerritoryId = 0;
		CustomerInfo customerInfo = getCustomerDetails(customerId,token);
		for(CustomerLocationForm customerLocationForm : customerInfo.getData().getCustomerLocations()) {
			if (customerLocationForm.getLocationId().toString().equals(locationId)) {
				deliveryTerritoryId = customerLocationForm.getGeoLocation().getTerritoryId();
				break;
			}
		}
		return deliveryTerritoryId;
	}
	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_CUSTOMER_DETAILS);
	}
}