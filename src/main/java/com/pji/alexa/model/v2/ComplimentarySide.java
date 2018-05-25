package com.pji.alexa.model.v2;

/**

PapaJohns
- complimentarySideId: optional
- defaultProduct: optional
- productChoices: optional
*/

import java.io.Serializable;
import java.util.List;

public class ComplimentarySide implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer complimentarySideId;

    private ProductSKU defaultProduct;

    private List<ProductSKU> productChoices;

	public Integer getComplimentarySideId() {
		return complimentarySideId;
	}

	public void setComplimentarySideId(Integer complimentarySideId) {
		this.complimentarySideId = complimentarySideId;
	}

	public ProductSKU getDefaultProduct() {
		return defaultProduct;
	}

	public void setDefaultProduct(ProductSKU defaultProduct) {
		this.defaultProduct = defaultProduct;
	}

	public List<ProductSKU> getProductChoices() {
		return productChoices;
	}

	public void setProductChoices(List<ProductSKU> productChoices) {
		this.productChoices = productChoices;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
