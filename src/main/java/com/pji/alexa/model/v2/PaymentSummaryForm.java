package com.pji.alexa.model.v2;

/**

PapaJohns
- cashPaymentAmount: optional
- checkPaymentAmount: optional
- creditCardPayment: optional
- eCheckPayment: optional
- giftCardPayment: optional
- mobilePayment: optional
- nonIntegratedCreditCardPayment: optional
- orderPaymentIds: optional
*/

import java.io.Serializable;
import java.util.List;

public class PaymentSummaryForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double cashPaymentAmount;

    private List<CreditCardPaymentForm> creditCardPayment;

	public Double getCashPaymentAmount() {
		return cashPaymentAmount;
	}

	public void setCashPaymentAmount(Double cashPaymentAmount) {
		this.cashPaymentAmount = cashPaymentAmount;
	}

	public List<CreditCardPaymentForm> getCreditCardPayment() {
		return creditCardPayment;
	}

	public void setCreditCardPayment(List<CreditCardPaymentForm> creditCardPayment) {
		this.creditCardPayment = creditCardPayment;
	}


}
