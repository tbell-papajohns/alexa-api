package com.pji.alexa.model.v2;

import java.util.List;

public class CartResponse {
	
	private CartResponseForm data;
	private List<ResponseMessage> messages;

	public CartResponseForm getData() {
		return data;
	}

	public void setData(CartResponseForm data) {
		this.data = data;
	}

	public List<ResponseMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ResponseMessage> messages) {
		this.messages = messages;
	}

}
