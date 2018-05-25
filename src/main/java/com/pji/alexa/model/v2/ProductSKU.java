package com.pji.alexa.model.v2;

/**

PapaJohns
- baseIngredientSizeId: optional
- baseIngredientTypeId: optional
- customizationId: optional
- productTypeId: optional
- sku: required
*/

import java.io.Serializable;

public class ProductSKU implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long baseIngredientSizeId;

	private Long baseIngredientTypeId;

	private Long customizationId;

	private Long productTypeId;

	private String sku;

	public Long getBaseIngredientSizeId() {
		return baseIngredientSizeId;
	}

	public void setBaseIngredientSizeId(Long baseIngredientSizeId) {
		this.baseIngredientSizeId = baseIngredientSizeId;
	}

	public Long getBaseIngredientTypeId() {
		return baseIngredientTypeId;
	}

	public void setBaseIngredientTypeId(Long baseIngredientTypeId) {
		this.baseIngredientTypeId = baseIngredientTypeId;
	}

	public Long getCustomizationId() {
		return customizationId;
	}

	public void setCustomizationId(Long customizationId) {
		this.customizationId = customizationId;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}
