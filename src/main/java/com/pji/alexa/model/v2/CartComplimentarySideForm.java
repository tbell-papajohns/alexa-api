package com.pji.alexa.model.v2;

/**

PapaJohns
password reset token
- id: optional
- sku: optional
*/

import java.io.Serializable;

public class CartComplimentarySideForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String sku;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}
