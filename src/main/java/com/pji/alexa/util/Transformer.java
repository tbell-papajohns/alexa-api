package com.pji.alexa.util;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class Transformer {
	
	@Autowired
	Gson gson;
	
	/**
	 * This method converts the given object into a string
	 * @param obj
	 * @return
	 */
	public String objectToString(Object obj) {
		String request = gson.toJson(obj);
		return request;
	}
	
	/**
	 * This method converts the given string into a object of given type
	 * @param response
	 * @param type
	 * @return
	 */
	public Object stringToObject(String response, Class<?> type) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(response).getAsJsonObject();
		return gson.fromJson(jsonObject, type);
	}
	/**
	 * This method converts session object into a domain object
	 * @param data
	 * @param type
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object convertSessionDataObjectToDomainObject(Map<String,Object> data, Class<?> type) throws InstantiationException, IllegalAccessException {
		JSONObject json = new JSONObject(data);
		return this.stringToObject(json.toString(), type);		
	}

}