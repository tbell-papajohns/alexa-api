package com.pji.alexa.model.v2;

/**

PapaJohns
- allowMultipleGiftCards: optional
- allowPaoWhenClosedFlag: optional
- allowPayShare: optional
- allowPlanAheadOrderFlag: optional
- altPhoneNumber: optional
- availabilityMessages: optional
- distance: optional
- guestOrderCheckout: optional, Allowable Values: ["ALLOW", "DENY", "ALLOW_ONLY_FOR_CREDIT_OR_GIFT_CARDS"]
- lastUpdateTimeStamp: optional
- maxOrderAmt: optional
- minCarryoutAmt: optional
- minDeliveryAmt: optional
- phoneNumber: optional
- storeHours: optional
- storeId: optional
- storeLocation: optional
- storeMessages: optional
- storeOpen: optional
- storePayments: optional
- storeProductRewardValues: optional
- storeStatus: optional
- territoryId: optional
- timeZone: optional, Allowable Values: ["EST", "CST", "MST", "PST", "AKST", "HST", "AST", "NST"]
- trackCarryoutOrders: optional
- trackDeliveryOrders: optional
- useDaylightTimeFlag: optional
*/

import java.io.Serializable;
import java.util.List;

public class StoreForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Boolean allowMultipleGiftCards;

    private Boolean allowPaoWhenClosedFlag;

    private Boolean allowPayShare;

    private Boolean allowPlanAheadOrderFlag;

    private String altPhoneNumber;

    private List<String> availabilityMessages;

    private Double distance;

    private String guestOrderCheckout;

    private String lastUpdateTimeStamp;

    private Double maxOrderAmt;

    private Double minCarryoutAmt;

    private Double minDeliveryAmt;

    private String phoneNumber;

    private List<StoreHourForm> storeHours;

    private Integer storeId;

    private GeoLocationForm storeLocation;

    private List<StoreMessageForm> storeMessages;

    private Boolean storeOpen;

    private List<StorePaymentForm> storePayments;

    private List<StoreProductRewardValueForm> storeProductRewardValues;

    private StoreStatusForm storeStatus;

    private Integer territoryId;

    private String timeZone;

    private Boolean trackCarryoutOrders;

    private Boolean trackDeliveryOrders;

    private Boolean useDaylightTimeFlag;

	public Boolean getAllowMultipleGiftCards() {
		return allowMultipleGiftCards;
	}

	public void setAllowMultipleGiftCards(Boolean allowMultipleGiftCards) {
		this.allowMultipleGiftCards = allowMultipleGiftCards;
	}

	public Boolean getAllowPaoWhenClosedFlag() {
		return allowPaoWhenClosedFlag;
	}

	public void setAllowPaoWhenClosedFlag(Boolean allowPaoWhenClosedFlag) {
		this.allowPaoWhenClosedFlag = allowPaoWhenClosedFlag;
	}

	public Boolean getAllowPayShare() {
		return allowPayShare;
	}

	public void setAllowPayShare(Boolean allowPayShare) {
		this.allowPayShare = allowPayShare;
	}

	public Boolean getAllowPlanAheadOrderFlag() {
		return allowPlanAheadOrderFlag;
	}

	public void setAllowPlanAheadOrderFlag(Boolean allowPlanAheadOrderFlag) {
		this.allowPlanAheadOrderFlag = allowPlanAheadOrderFlag;
	}

	public String getAltPhoneNumber() {
		return altPhoneNumber;
	}

	public void setAltPhoneNumber(String altPhoneNumber) {
		this.altPhoneNumber = altPhoneNumber;
	}

	public List<String> getAvailabilityMessages() {
		return availabilityMessages;
	}

	public void setAvailabilityMessages(List<String> availabilityMessages) {
		this.availabilityMessages = availabilityMessages;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getGuestOrderCheckout() {
		return guestOrderCheckout;
	}

	public void setGuestOrderCheckout(String guestOrderCheckout) {
		this.guestOrderCheckout = guestOrderCheckout;
	}

	public String getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(String lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	public Double getMaxOrderAmt() {
		return maxOrderAmt;
	}

	public void setMaxOrderAmt(Double maxOrderAmt) {
		this.maxOrderAmt = maxOrderAmt;
	}

	public Double getMinCarryoutAmt() {
		return minCarryoutAmt;
	}

	public void setMinCarryoutAmt(Double minCarryoutAmt) {
		this.minCarryoutAmt = minCarryoutAmt;
	}

	public Double getMinDeliveryAmt() {
		return minDeliveryAmt;
	}

	public void setMinDeliveryAmt(Double minDeliveryAmt) {
		this.minDeliveryAmt = minDeliveryAmt;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<StoreHourForm> getStoreHours() {
		return storeHours;
	}

	public void setStoreHours(List<StoreHourForm> storeHours) {
		this.storeHours = storeHours;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public GeoLocationForm getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(GeoLocationForm storeLocation) {
		this.storeLocation = storeLocation;
	}

	public List<StoreMessageForm> getStoreMessages() {
		return storeMessages;
	}

	public void setStoreMessages(List<StoreMessageForm> storeMessages) {
		this.storeMessages = storeMessages;
	}

	public Boolean getStoreOpen() {
		return storeOpen;
	}

	public void setStoreOpen(Boolean storeOpen) {
		this.storeOpen = storeOpen;
	}

	public List<StorePaymentForm> getStorePayments() {
		return storePayments;
	}

	public void setStorePayments(List<StorePaymentForm> storePayments) {
		this.storePayments = storePayments;
	}

	public List<StoreProductRewardValueForm> getStoreProductRewardValues() {
		return storeProductRewardValues;
	}

	public void setStoreProductRewardValues(List<StoreProductRewardValueForm> storeProductRewardValues) {
		this.storeProductRewardValues = storeProductRewardValues;
	}

	public StoreStatusForm getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(StoreStatusForm storeStatus) {
		this.storeStatus = storeStatus;
	}

	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Boolean getTrackCarryoutOrders() {
		return trackCarryoutOrders;
	}

	public void setTrackCarryoutOrders(Boolean trackCarryoutOrders) {
		this.trackCarryoutOrders = trackCarryoutOrders;
	}

	public Boolean getTrackDeliveryOrders() {
		return trackDeliveryOrders;
	}

	public void setTrackDeliveryOrders(Boolean trackDeliveryOrders) {
		this.trackDeliveryOrders = trackDeliveryOrders;
	}

	public Boolean getUseDaylightTimeFlag() {
		return useDaylightTimeFlag;
	}

	public void setUseDaylightTimeFlag(Boolean useDaylightTimeFlag) {
		this.useDaylightTimeFlag = useDaylightTimeFlag;
	}

}
