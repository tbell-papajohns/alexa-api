package com.pji.alexa.model.v2;

/**

PapaJohns
- categoryId: optional
- categoryName: optional
*/

import java.io.Serializable;

public class PaymentCategoryForm implements Serializable {

    public Integer categoryId;

    public String categoryName;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
