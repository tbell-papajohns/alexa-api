package com.pji.alexa.model.v2;

public class AlexaOrder {

	private String orderType;
	private String addressId;
	private String favoriteId;
	private String pastOrderNumber;
	private boolean orderTypeConfirmed;
	private boolean orderConfirmed;	
	private boolean cartConfirmed;
	private String cardId;
	private String storeId;
	private int deliveryTerritoryId;
	private String paymentType;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public boolean isOrderTypeConfirmed() {
		return orderTypeConfirmed;
	}
	public void setOrderTypeConfirmed(boolean orderTypeConfirmed) {
		this.orderTypeConfirmed = orderTypeConfirmed;
	}
	public boolean isOrderConfirmed() {
		return orderConfirmed;
	}
	public void setOrderConfirmed(boolean orderConfirmed) {
		this.orderConfirmed = orderConfirmed;
	}
	public boolean isCartConfirmed() {
		return cartConfirmed;
	}
	public void setCartConfirmed(boolean cartConfirmed) {
		this.cartConfirmed = cartConfirmed;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	public String getPastOrderNumber() {
		return pastOrderNumber;
	}
	public void setPastOrderNumber(String pastOrderNumber) {
		this.pastOrderNumber = pastOrderNumber;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getDeliveryTerritoryId() {
		return deliveryTerritoryId;
	}
	public void setDeliveryTerritoryId(int deliveryTerritoryId) {
		this.deliveryTerritoryId = deliveryTerritoryId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
