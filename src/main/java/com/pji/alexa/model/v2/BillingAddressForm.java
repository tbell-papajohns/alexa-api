package com.pji.alexa.model.v2;

/**

PapaJohns
- address1: optional
- city: optional
- phone: optional
- postalCode: optional
- sameAsOrderAddress: optional
- state: optional
*/

import java.io.Serializable;

public class BillingAddressForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String address1;

    private String city;

    private String phone;

    private String postalCode;

    private Boolean sameAsOrderAddress;

    private String state;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Boolean getSameAsOrderAddress() {
		return sameAsOrderAddress;
	}

	public void setSameAsOrderAddress(Boolean sameAsOrderAddress) {
		this.sameAsOrderAddress = sameAsOrderAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
