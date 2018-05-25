package com.pji.alexa.model.v2;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class CustomerFavourites {

	private List<CustomerFavoriteForm> data;

	public List<CustomerFavoriteForm> getData() {
		return data;
	}
	public void setData(List<CustomerFavoriteForm> data) {
		this.data = data;
	}
}
