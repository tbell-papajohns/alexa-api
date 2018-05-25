package com.pji.alexa.model.v2;

/**

PapaJohns
- defaultOrderReadyTime: optional
- minOrderAmount: optional
- points: optional
- price: optional
- state: optional
- upsells: optional
*/

import java.io.Serializable;
import java.util.List;

public class CartResponseForm implements Serializable {

    private String defaultOrderReadyTime;

    private Double minOrderAmount;

    private CartPointsForm points;

    private CartPriceForm price;

    private CartStateForm state;

    private List<CartUpsellForm> upsells;

	public String getDefaultOrderReadyTime() {
		return defaultOrderReadyTime;
	}

	public void setDefaultOrderReadyTime(String defaultOrderReadyTime) {
		this.defaultOrderReadyTime = defaultOrderReadyTime;
	}

	public Double getMinOrderAmount() {
		return minOrderAmount;
	}

	public void setMinOrderAmount(Double minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}

	public CartPointsForm getPoints() {
		return points;
	}

	public void setPoints(CartPointsForm points) {
		this.points = points;
	}

	public CartPriceForm getPrice() {
		return price;
	}

	public void setPrice(CartPriceForm price) {
		this.price = price;
	}

	public CartStateForm getState() {
		return state;
	}

	public void setState(CartStateForm state) {
		this.state = state;
	}

	public List<CartUpsellForm> getUpsells() {
		return upsells;
	}

	public void setUpsells(List<CartUpsellForm> upsells) {
		this.upsells = upsells;
	}
}
