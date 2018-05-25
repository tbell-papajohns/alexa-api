package com.pji.alexa.model.v2;

/**

PapaJohns
- description: optional
- discountFactor: optional
- dollarAmount: optional
- favoriteId: optional
- promoCode: optional
- status: optional
- statusMessage: optional
- type: optional, Allowable Values: ["PERCENTAGE", "AMOUNT", "DELIVERY_FEE", "CUSTOMER_POINTS"]
*/

import java.io.Serializable;

public class CartPromoForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String description;

    private Double discountFactor;

    private Double dollarAmount;

    private Long favoriteId;

    private String promoCode;

    private String status;

    private ResponseMessage statusMessage;

    private String type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDiscountFactor() {
		return discountFactor;
	}

	public void setDiscountFactor(Double discountFactor) {
		this.discountFactor = discountFactor;
	}

	public Double getDollarAmount() {
		return dollarAmount;
	}

	public void setDollarAmount(Double dollarAmount) {
		this.dollarAmount = dollarAmount;
	}

	public Long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
