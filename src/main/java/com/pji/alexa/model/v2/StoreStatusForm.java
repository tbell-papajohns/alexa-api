package com.pji.alexa.model.v2;

/**

PapaJohns
- description: optional
- storeStatusCode: optional, Allowable Values: ["ACTIVE", "NOT_AVAILABLE", "TEMP_UNAVAILABLE", "CARRYOUT_ONLY", "DELIVERY_ONLY"]
- storeStatusId: optional
- storeStatusReasonId: optional
- storeStatusReasonMessage: optional
*/

import java.io.Serializable;

public class StoreStatusForm implements Serializable {

    public String description;

    public String storeStatusCode;

    public Integer storeStatusId;

    public Integer storeStatusReasonId;

    public String storeStatusReasonMessage;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStoreStatusCode() {
		return storeStatusCode;
	}

	public void setStoreStatusCode(String storeStatusCode) {
		this.storeStatusCode = storeStatusCode;
	}

	public Integer getStoreStatusId() {
		return storeStatusId;
	}

	public void setStoreStatusId(Integer storeStatusId) {
		this.storeStatusId = storeStatusId;
	}

	public Integer getStoreStatusReasonId() {
		return storeStatusReasonId;
	}

	public void setStoreStatusReasonId(Integer storeStatusReasonId) {
		this.storeStatusReasonId = storeStatusReasonId;
	}

	public String getStoreStatusReasonMessage() {
		return storeStatusReasonMessage;
	}

	public void setStoreStatusReasonMessage(String storeStatusReasonMessage) {
		this.storeStatusReasonMessage = storeStatusReasonMessage;
	}

}
