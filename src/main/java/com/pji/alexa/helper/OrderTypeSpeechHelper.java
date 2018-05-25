package com.pji.alexa.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pji.alexa.model.v2.UserDataItems;
import com.pji.alexa.util.Constants;

@Component
public class OrderTypeSpeechHelper extends SpeechHelper {

	/**
	 * This method returns a Map with Order type as key and respective speech as value
	 * @param userdata
	 * @return orderType
	 */
	public Map<String, String> getPromptForOrderType(UserDataItems userdata) {
		String speechPrompt="";
		String speechResponseForCard="";
		Map<String, String> promptForOrderTypeMap	=	new HashMap<>();
		String orderType = userdata.getOrderType();
		String discountSpeech= null;
		discountSpeech= getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_DISCOUNT, getUtil().getVerbiage(Constants.VERBAGE_DISCOUNT_PERCENT));
		String discountSpeechForCard= getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_DISCOUNT, getUtil().getVerbiage(Constants.VERBAGE_DISCOUNT_PERCENT));
		if(orderType.equals(Constants.ORDER_TYPE_BOTH)) {
			// when both favorite and recent exist
			speechPrompt= getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST, discountSpeech);
			speechResponseForCard= getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST, discountSpeechForCard);
		}else if(orderType.equals(Constants.ORDER_TYPE_RECENT)) {
			// when only recent order exist
			speechPrompt = getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST, discountSpeech);
			speechResponseForCard = getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST, discountSpeechForCard);
		}else if(orderType.equals(Constants.ORDER_TYPE_FAVORITE)) {
			// when only favorites exist
			speechPrompt = getUtil().getVerbiage(Constants.VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST, discountSpeech);	
			speechResponseForCard = getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST, discountSpeechForCard);
		}
		promptForOrderTypeMap.put("SpeechPrompt", speechPrompt);
		promptForOrderTypeMap.put("SpeechResponseForCard", speechResponseForCard);
		return promptForOrderTypeMap;
	}
}
