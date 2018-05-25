package com.pji.alexa.model.v2;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class PastOrders {
	private List<PastOrderForm> data;

	public List<PastOrderForm> getData() {
		return data;
	}

	public void setData(List<PastOrderForm> data) {
		this.data = data;
	}	
}
