package com.pji.alexa.model.v2;

/**

PapaJohns
- papaSizeSKU: optional
- price: optional
*/

import java.io.Serializable;

public class PapaSize implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductSKU papaSizeSKU;

	private Double price;

	public ProductSKU getPapaSizeSKU() {
		return papaSizeSKU;
	}

	public void setPapaSizeSKU(ProductSKU papaSizeSKU) {
		this.papaSizeSKU = papaSizeSKU;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
