package com.pji.alexa.model.v2;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class StoreInfo {
	private StoreSearchResultForm data;

	public StoreSearchResultForm getData() {
		return data;
	}

	public void setData(StoreSearchResultForm data) {
		this.data = data;
	}	
}
