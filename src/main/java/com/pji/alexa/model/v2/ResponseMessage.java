package com.pji.alexa.model.v2;

/**

PapaJohns
- code: optional, Allowable Values: ["BALANCE", "EARNED", "BONUS", "EXPIRED", "REDEEMED", "PENDING", "CANCELLED", "RETURNED"]
- description: optional
*/

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
