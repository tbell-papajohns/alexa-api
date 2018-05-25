package com.pji.alexa.model.v2;

/**

PapaJohns
- carryoutStoreId: optional
- currentFlag: optional
- deliveryRemarks: optional
- deliveryStoreId: optional
- email: optional
- geoLocation: required
- locationId: optional
- locationName: optional
- phone: optional
- primaryFlag: optional
*/

import java.io.Serializable;

public class CustomerLocationForm implements Serializable {

    public Integer carryoutStoreId;

    public Boolean currentFlag;

    public String deliveryRemarks;

    public Integer deliveryStoreId;

    public String email;

    public GeoLocationForm geoLocation;

    public Long locationId;

    public String locationName;

    public String phone;

    public Boolean primaryFlag;

	public Integer getCarryoutStoreId() {
		return carryoutStoreId;
	}

	public void setCarryoutStoreId(Integer carryoutStoreId) {
		this.carryoutStoreId = carryoutStoreId;
	}

	public Boolean getCurrentFlag() {
		return currentFlag;
	}

	public void setCurrentFlag(Boolean currentFlag) {
		this.currentFlag = currentFlag;
	}

	public String getDeliveryRemarks() {
		return deliveryRemarks;
	}

	public void setDeliveryRemarks(String deliveryRemarks) {
		this.deliveryRemarks = deliveryRemarks;
	}

	public Integer getDeliveryStoreId() {
		return deliveryStoreId;
	}

	public void setDeliveryStoreId(Integer deliveryStoreId) {
		this.deliveryStoreId = deliveryStoreId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GeoLocationForm getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocationForm geoLocation) {
		this.geoLocation = geoLocation;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getPrimaryFlag() {
		return primaryFlag;
	}

	public void setPrimaryFlag(Boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

}
