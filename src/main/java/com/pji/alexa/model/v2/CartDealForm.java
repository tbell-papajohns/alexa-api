package com.pji.alexa.model.v2;

/**

PapaJohns
- dealId: optional
- displayPrice: optional
- favoriteId: optional
- ignoreMinAmount: optional
- products: optional
- promoCode: optional
- quantity: optional
- shopcartItemId: optional
- status: optional
- statusMessage: optional
- title: optional
*/

import java.io.Serializable;
import java.util.List;

public class CartDealForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long dealId;

    private String displayPrice;

    private Long favoriteId;

    private Boolean ignoreMinAmount;

    private List<CartProductForm> products;

    private String promoCode;

    private Integer quantity;

    private String shopcartItemId;

    private String status;

    private ResponseMessage statusMessage;

    private String title;

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public String getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}

	public Long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Boolean getIgnoreMinAmount() {
		return ignoreMinAmount;
	}

	public void setIgnoreMinAmount(Boolean ignoreMinAmount) {
		this.ignoreMinAmount = ignoreMinAmount;
	}

	public List<CartProductForm> getProducts() {
		return products;
	}

	public void setProducts(List<CartProductForm> products) {
		this.products = products;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getShopcartItemId() {
		return shopcartItemId;
	}

	public void setShopcartItemId(String shopcartItemId) {
		this.shopcartItemId = shopcartItemId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ResponseMessage getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(ResponseMessage statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
