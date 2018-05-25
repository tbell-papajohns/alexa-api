package com.pji.alexa.model.v2;

/**

PapaJohns
- paymentCategory: optional
- paymentMethods: optional
*/

import java.io.Serializable;
import java.util.List;

public class StorePaymentForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PaymentCategoryForm paymentCategory;

    private List<PaymentMethodForm> paymentMethods;

	public PaymentCategoryForm getPaymentCategory() {
		return paymentCategory;
	}

	public void setPaymentCategory(PaymentCategoryForm paymentCategory) {
		this.paymentCategory = paymentCategory;
	}

	public List<PaymentMethodForm> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethodForm> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

}
