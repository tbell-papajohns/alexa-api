package com.pji.alexa.model.v2;

/**

PapaJohns
- toppings: optional
*/

import java.io.Serializable;
import java.util.List;

public class CartSectionForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> toppings;
	public List<Long> getToppings() {
		return toppings;
	}
	public void setToppings(List<Long> toppings) {
		this.toppings = toppings;
	}
}
