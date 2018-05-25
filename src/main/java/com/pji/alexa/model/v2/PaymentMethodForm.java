package com.pji.alexa.model.v2;

/**

PapaJohns
- payMethodId: optional
- payMethodName: optional
- paymentFee: optional
*/

import java.io.Serializable;

public class PaymentMethodForm implements Serializable {

    public Integer payMethodId;

    public String payMethodName;

    public Double paymentFee;

	public Integer getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(Integer payMethodId) {
		this.payMethodId = payMethodId;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public Double getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(Double paymentFee) {
		this.paymentFee = paymentFee;
	}

}
