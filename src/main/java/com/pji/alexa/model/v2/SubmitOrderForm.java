package com.pji.alexa.model.v2;

/**

PapaJohns
- cartState: required
- createAccountWithOrder: optional
- customer: required
- ipAddress: required
- overrideDuplicateOrderCheck: optional
- paymentSummary: required
- registrationSignInForm: optional
- selectedPaymentCategories: required
*/

import java.io.Serializable;
import java.util.List;

public class SubmitOrderForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartStateForm cartState;

    private CustomerForm customer;

    private String ipAddress;

    private PaymentSummaryForm paymentSummary;

    private List<PaymentCategoryForm> selectedPaymentCategories;

	public CartStateForm getCartState() {
		return cartState;
	}

	public void setCartState(CartStateForm cartState) {
		this.cartState = cartState;
	}

	public CustomerForm getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerForm customer) {
		this.customer = customer;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public PaymentSummaryForm getPaymentSummary() {
		return paymentSummary;
	}

	public void setPaymentSummary(PaymentSummaryForm paymentSummary) {
		this.paymentSummary = paymentSummary;
	}

	public List<PaymentCategoryForm> getSelectedPaymentCategories() {
		return selectedPaymentCategories;
	}

	public void setSelectedPaymentCategories(List<PaymentCategoryForm> selectedPaymentCategories) {
		this.selectedPaymentCategories = selectedPaymentCategories;
	}

}
