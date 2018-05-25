package com.pji.alexa.model.v2;

import java.io.Serializable;
import java.util.List;

public class StoreSearchResultForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<StoreForm> carryoutStores;

    private List<StoreForm> deliveryStores;

    private List<GeoLocationForm> geocodeResults;

    private GeoLocationForm searchLocation;

    private Boolean storeFound;

    private Boolean zipCodeSearch;

	public List<StoreForm> getCarryoutStores() {
		return carryoutStores;
	}

	public void setCarryoutStores(List<StoreForm> carryoutStores) {
		this.carryoutStores = carryoutStores;
	}

	public List<StoreForm> getDeliveryStores() {
		return deliveryStores;
	}

	public void setDeliveryStores(List<StoreForm> deliveryStores) {
		this.deliveryStores = deliveryStores;
	}

	public List<GeoLocationForm> getGeocodeResults() {
		return geocodeResults;
	}

	public void setGeocodeResults(List<GeoLocationForm> geocodeResults) {
		this.geocodeResults = geocodeResults;
	}

	public GeoLocationForm getSearchLocation() {
		return searchLocation;
	}

	public void setSearchLocation(GeoLocationForm searchLocation) {
		this.searchLocation = searchLocation;
	}

	public Boolean getStoreFound() {
		return storeFound;
	}

	public void setStoreFound(Boolean storeFound) {
		this.storeFound = storeFound;
	}

	public Boolean getZipCodeSearch() {
		return zipCodeSearch;
	}

	public void setZipCodeSearch(Boolean zipCodeSearch) {
		this.zipCodeSearch = zipCodeSearch;
	}
    
}
