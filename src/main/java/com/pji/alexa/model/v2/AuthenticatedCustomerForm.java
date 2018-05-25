package com.pji.alexa.model.v2;

/**

PapaJohns
- customer: optional
- customerToken: optional
- passwordResetToken: optional
*/

import java.io.Serializable;

public class AuthenticatedCustomerForm implements Serializable {

    public CustomerForm customer;

    public String customerToken;

    public String passwordResetToken;

	public CustomerForm getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerForm customer) {
		this.customer = customer;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

}
