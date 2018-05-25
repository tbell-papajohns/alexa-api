package com.pji.alexa.model.v2;

/**

PapaJohns
- allowHalfToppingFlag: optional
- allowedToppings: optional
- defaultItem: optional
- itemChoiceList: optional
- numToppingsDiscount: optional
- numToppingsFree: optional
- productConfigurationId: optional
- sortOrder: optional
*/

import java.io.Serializable;
import java.util.List;

public class DealItem implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean allowHalfToppingFlag;

    private List<AllowedTopping> allowedToppings;

    private ProductSKU defaultItem;

    private List<ItemChoice> itemChoiceList;

    private Integer numToppingsDiscount;

    private Integer numToppingsFree;

    private Integer productConfigurationId;

    private Integer sortOrder;

	public Boolean getAllowHalfToppingFlag() {
		return allowHalfToppingFlag;
	}

	public void setAllowHalfToppingFlag(Boolean allowHalfToppingFlag) {
		this.allowHalfToppingFlag = allowHalfToppingFlag;
	}

	public List<AllowedTopping> getAllowedToppings() {
		return allowedToppings;
	}

	public void setAllowedToppings(List<AllowedTopping> allowedToppings) {
		this.allowedToppings = allowedToppings;
	}

	public ProductSKU getDefaultItem() {
		return defaultItem;
	}

	public void setDefaultItem(ProductSKU defaultItem) {
		this.defaultItem = defaultItem;
	}

	public List<ItemChoice> getItemChoiceList() {
		return itemChoiceList;
	}

	public void setItemChoiceList(List<ItemChoice> itemChoiceList) {
		this.itemChoiceList = itemChoiceList;
	}

	public Integer getNumToppingsDiscount() {
		return numToppingsDiscount;
	}

	public void setNumToppingsDiscount(Integer numToppingsDiscount) {
		this.numToppingsDiscount = numToppingsDiscount;
	}

	public Integer getNumToppingsFree() {
		return numToppingsFree;
	}

	public void setNumToppingsFree(Integer numToppingsFree) {
		this.numToppingsFree = numToppingsFree;
	}

	public Integer getProductConfigurationId() {
		return productConfigurationId;
	}

	public void setProductConfigurationId(Integer productConfigurationId) {
		this.productConfigurationId = productConfigurationId;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
}
