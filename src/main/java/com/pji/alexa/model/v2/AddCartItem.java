package com.pji.alexa.model.v2;

public class AddCartItem {
	private CartProductForm product;

	private CartStateForm state;

	public CartProductForm getProduct() {
		return product;
	}

	public void setProduct(CartProductForm product) {
		this.product = product;
	}

	public CartStateForm getState() {
		return state;
	}

	public void setState(CartStateForm state) {
		this.state = state;
	}
}
