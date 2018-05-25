package com.pji.alexa.model.v2;

/**

PapaJohns
- cartState: optional
- orderDate: optional
- orderNumber: optional
- statusMessages: optional
*/

import java.io.Serializable;
import java.util.List;

public class PastOrderForm implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartStateForm cartState;

    private String orderDate;

    private Long orderNumber;

    private List<ResponseMessage> statusMessages;

	public CartStateForm getCartState() {
		return cartState;
	}
	public void setCartState(CartStateForm cartState) {
		this.cartState = cartState;
	}
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<ResponseMessage> getStatusMessages() {
		return statusMessages;
	}

	public void setStatusMessages(List<ResponseMessage> statusMessages) {
		this.statusMessages = statusMessages;
	}

}
