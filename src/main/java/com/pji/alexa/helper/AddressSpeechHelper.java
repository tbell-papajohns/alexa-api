package com.pji.alexa.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;

@Component
public class AddressSpeechHelper extends SpeechHelper{

	/**
	 * This method returns the street address prompt
	 * @param customerId
	 * @param customerToken
	 * @param locationId
	 * @return 
	 */
	public Map<String, String> getPromptForStreetAddress(UserDataItems userdata, String locationId, String responseType, int addressListSize) throws Exception{
		String speechPrompt=null;
		String speechResponseForCard=null;
		String streetAddress = null;
		Map<String, String> promptForStreetAddressMap	=	new HashMap<>();
		if(StringUtils.isNotEmpty(locationId)) {
			for(Item address : userdata.getAddressList()) {
					if(locationId.equals(address.getItemId())){
						streetAddress = address.getItemName();
						break;
					}			
				}
		}
		if(StringUtils.isEmpty(responseType)) {
			//First Time
			speechPrompt = getUtil().getVerbiage(Constants.VERBAGE_ADDRESS_LISTING_FIRST_TIME, streetAddress, getUtil().convertNumberStringToNumberNames(String.valueOf(addressListSize)));
			speechResponseForCard = getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ADDRESS_LISTING_FIRST_TIME, streetAddress, getUtil().convertNumberStringToNumberNames(String.valueOf(addressListSize)));
		}else if (Constants.USER_RESPONSE_TYPES_INVALID.equalsIgnoreCase(responseType)) {
			//Invalid response
			speechPrompt = getUtil().getVerbiage(Constants.VERBAGE_ADDRESS_LISTING_AFTER_INVALID_RESPONSE, streetAddress);
			speechResponseForCard = getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ADDRESS_LISTING_AFTER_INVALID_RESPONSE, streetAddress);
		}else if(Constants.USER_RESPONSE_TYPES_NO.equalsIgnoreCase(responseType)) {
			//a no response
			speechPrompt = getUtil().getVerbiage(Constants.VERBAGE_ADDRESS_LISTING_AFTER_NO_RESPONSE, streetAddress);
			speechResponseForCard = getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ADDRESS_LISTING_AFTER_NO_RESPONSE, streetAddress);
		}else if(Constants.USER_RESPONSE_TYPES_LAST_NO.equalsIgnoreCase(responseType)) {
			//a no response
			speechPrompt = getUtil().getVerbiage(Constants.VERBAGE_ADDRESS_LISTING_WHEN_NO_ADDRESS_EXIST);
			speechResponseForCard = getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_ADDRESS_LISTING_WHEN_NO_ADDRESS_EXIST);
		}
		promptForStreetAddressMap.put("SpeechPrompt", speechPrompt);
		promptForStreetAddressMap.put("SpeechResponseForCard", speechResponseForCard);
		return promptForStreetAddressMap;
	}
	
	/**
	 * This method gets the next value which is not pitched from the data list of, the next address is listed based on the priority as well
	 * the customer
	 * 
	 * @param dataMap
	 * @return
	 */
	public String getNextDataItem(List<Item> addressList) {
		Item nextAddress = null;
		int addressIndex= 0;
		String nextItemId= null;
		for (Item address : addressList) {
			addressIndex ++;
			if (!address.isUttered() && address.isPrimary()) {
				nextAddress= address;
				break;
			}
		}		
		if(nextAddress == null) {
			addressIndex= 0;
			for (Item address : addressList) {
				addressIndex ++;
				if (!address.isUttered()) {
					nextAddress= address;
					break;
				}
			}
		}
		if(nextAddress != null) {			
			nextAddress.setUttered(true);
			addressList.set(addressIndex-1, nextAddress);		
			nextItemId=nextAddress.getItemId();
		}
		return nextItemId;
	}
}
