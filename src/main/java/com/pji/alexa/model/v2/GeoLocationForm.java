package com.pji.alexa.model.v2;

/**

PapaJohns
- address1: required
- address2: optional
- address3: optional
- address4: optional
- aptCode: optional, Allowable Values: ["APT", "STE", "FLR", "NON"]
- campusAddress: optional
- city: optional
- county: optional
- geocodeQuality: optional
- latitude: optional
- locationType: required
- longitude: optional
- postalCode: required
- territoryId: optional
*/

import java.io.Serializable;

public class GeoLocationForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String address1;

    private String address2;

    private String address3;

    private String address4;

    private String aptCode;

    private CampusAddressForm campusAddress;

    private String city;

    private String county;

    private String geocodeQuality;

    private Double latitude;

    private LocationType locationType;

    private Double longitude;

    private String postalCode;

    private Integer territoryId;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public CampusAddressForm getCampusAddress() {
		return campusAddress;
	}

	public void setCampusAddress(CampusAddressForm campusAddress) {
		this.campusAddress = campusAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getGeocodeQuality() {
		return geocodeQuality;
	}

	public void setGeocodeQuality(String geocodeQuality) {
		this.geocodeQuality = geocodeQuality;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

}
