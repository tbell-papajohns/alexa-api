package com.pji.alexa.model.v2;

/**

PapaJohns
- code: optional, Allowable Values: ["BALANCE", "EARNED", "BONUS", "EXPIRED", "REDEEMED", "PENDING", "CANCELLED", "RETURNED"]
- storeMessageId: optional
- storeMessageType: optional, Allowable Values: ["PAYMENT", "DELIVERY", "INFO"]
*/

import java.io.Serializable;

public class StoreMessageForm implements Serializable {

    public String code;

    public Long storeMessageId;

    public String storeMessageType;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getStoreMessageId() {
		return storeMessageId;
	}

	public void setStoreMessageId(Long storeMessageId) {
		this.storeMessageId = storeMessageId;
	}

	public String getStoreMessageType() {
		return storeMessageType;
	}

	public void setStoreMessageType(String storeMessageType) {
		this.storeMessageType = storeMessageType;
	}

}
