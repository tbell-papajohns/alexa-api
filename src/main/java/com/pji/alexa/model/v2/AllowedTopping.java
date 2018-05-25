package com.pji.alexa.model.v2;

/**

PapaJohns
- maxQuantity: optional
- toppingId: optional
*/

import java.io.Serializable;

public class AllowedTopping implements Serializable {

    public Integer maxQuantity;

    public Long toppingId;

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Long getToppingId() {
		return toppingId;
	}

	public void setToppingId(Long toppingId) {
		this.toppingId = toppingId;
	}

}
