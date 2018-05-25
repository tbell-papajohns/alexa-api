package com.pji.alexa.model.v2;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class CustomerFavouriteDetails {

	private CustomerFavoriteForm data;

	public CustomerFavoriteForm getData() {
		return data;
	}
	public void setData(CustomerFavoriteForm data) {
		this.data = data;
	}
}
