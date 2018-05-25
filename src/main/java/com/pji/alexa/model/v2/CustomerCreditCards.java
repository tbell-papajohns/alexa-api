package com.pji.alexa.model.v2;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class CustomerCreditCards {
	private List<CustomerCreditCardForm> data;

	public List<CustomerCreditCardForm> getData() {
		return data;
	}

	public void setData(List<CustomerCreditCardForm> data) {
		this.data = data;
	}	
}
