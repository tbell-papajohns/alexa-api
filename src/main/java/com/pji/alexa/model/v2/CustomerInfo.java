package com.pji.alexa.model.v2;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class CustomerInfo {
	private CustomerForm data;

	public CustomerForm getData() {
		return data;
	}

	public void setData(CustomerForm data) {
		this.data = data;
	}	
}
