package com.pji.alexa.model.v2;

/**

PapaJohns
- amount: optional
- billingAddress: optional
- cardId: optional
- cardNumber: optional
- cvv: optional
- expirationMonth: optional
- expirationYear: optional
- nameOnCard: optional
- paymentTypeId: optional
- storeCardAfterAuthOn: optional
- tip: optional
- updateStoredCard: optional
- vantivLowValueToken: optional
- vantivOrderId: optional
- visaCheckoutCallId: optional
*/

import java.io.Serializable;

public class CreditCardPaymentForm implements Serializable {

    public Double amount;

    public BillingAddressForm billingAddress;

    public Integer cardId;

    public String cardNumber;

    public String cvv;

    public String expirationMonth;

    public String expirationYear;

    public String nameOnCard;

    public Integer paymentTypeId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public BillingAddressForm getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddressForm billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

}
