package com.pji.alexa.model.v2;

import java.util.List;
import java.util.Map;

public class UserDataItems {
	
	private List<Item> addressList;
	private List<Item> favoriteList;
	private List<Item> paymentList;
	private List<Item> pastOrderList;
	private String orderType;
	private String deliveryStoreId;
	private String territoryId;
	private String customerId;
	private String customerToken;
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDeliveryStoreId() {
		return deliveryStoreId;
	}

	public void setDeliveryStoreId(String deliveryStoreId) {
		this.deliveryStoreId = deliveryStoreId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}
	public String getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(String territoryId) {
		this.territoryId = territoryId;
	}
	public List<Item> getFavoriteList() {
		return favoriteList;
	}
	public void setFavoriteList(List<Item> favoriteList) {
		this.favoriteList = favoriteList;
	}
	public List<Item> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<Item> paymentList) {
		this.paymentList = paymentList;
	}

	public List<Item> getPastOrderList() {
		return pastOrderList;
	}

	public void setPastOrderList(List<Item> pastOrderList) {
		this.pastOrderList = pastOrderList;
	}

	public List<Item> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Item> addressList) {
		this.addressList = addressList;
	}
}
