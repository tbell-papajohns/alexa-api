package com.pji.alexa.helper;

import org.springframework.beans.factory.annotation.Autowired;

import com.pji.alexa.services.CustomerInfoService;
import com.pji.alexa.util.Util;

public class SpeechHelper {
	
	@Autowired
	private Util util;
	
	@Autowired
	private CustomerInfoService customerInfoService;

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public CustomerInfoService getCustomerInfoService() {
		return customerInfoService;
	}

	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}
}
