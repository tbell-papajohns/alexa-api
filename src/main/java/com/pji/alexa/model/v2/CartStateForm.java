package com.pji.alexa.model.v2;

/**

PapaJohns
- deals: optional
- deliveryTerritoryId: optional
- discountPromos: optional
- grandTotal: optional
- optOutPromoIds: optional
- orderReadyTime: optional
- orderType: optional
- products: required
- statusMessages: optional
- statuses: optional
- storeId: optional
- trackingPromos: optional
*/

import java.io.Serializable;
import java.util.List;

public class CartStateForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CartDealForm> deals;

    private Integer deliveryTerritoryId;

    private List<CartPromoForm> discountPromos;

    private Double grandTotal;

    private List<Long> optOutPromoIds;

    private String orderReadyTime;

    private String orderType;

    private List<CartProductForm> products;

    private List<ResponseMessage> statusMessages;

    private List<String> statuses;

    private Integer storeId;

    private List<CartPromoForm> trackingPromos;

	public List<CartDealForm> getDeals() {
		return deals;
	}

	public void setDeals(List<CartDealForm> deals) {
		this.deals = deals;
	}

	public Integer getDeliveryTerritoryId() {
		return deliveryTerritoryId;
	}

	public void setDeliveryTerritoryId(Integer deliveryTerritoryId) {
		this.deliveryTerritoryId = deliveryTerritoryId;
	}

	public List<CartPromoForm> getDiscountPromos() {
		return discountPromos;
	}

	public void setDiscountPromos(List<CartPromoForm> discountPromos) {
		this.discountPromos = discountPromos;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public List<Long> getOptOutPromoIds() {
		return optOutPromoIds;
	}

	public void setOptOutPromoIds(List<Long> optOutPromoIds) {
		this.optOutPromoIds = optOutPromoIds;
	}

	public String getOrderReadyTime() {
		return orderReadyTime;
	}

	public void setOrderReadyTime(String orderReadyTime) {
		this.orderReadyTime = orderReadyTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public List<CartProductForm> getProducts() {
		return products;
	}

	public void setProducts(List<CartProductForm> products) {
		this.products = products;
	}

	public List<ResponseMessage> getStatusMessages() {
		return statusMessages;
	}

	public void setStatusMessages(List<ResponseMessage> statusMessages) {
		this.statusMessages = statusMessages;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public List<CartPromoForm> getTrackingPromos() {
		return trackingPromos;
	}

	public void setTrackingPromos(List<CartPromoForm> trackingPromos) {
		this.trackingPromos = trackingPromos;
	}
}
