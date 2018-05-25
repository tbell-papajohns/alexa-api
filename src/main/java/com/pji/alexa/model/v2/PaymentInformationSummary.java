package com.pji.alexa.model.v2;

/**

PapaJohns
- cashPaymentAmount: optional
- checkPaymentAmount: optional
- creditCardPaymentInformation: optional
- eCheckPaymentInformation: optional
- giftCardPaymentInformation: optional
- mobilePaymentInformation: optional
- nonIntegratedCreditCardPayment: optional
- payPalPaymentInformation: optional
*/

import java.io.Serializable;
import java.util.List;

public class PaymentInformationSummary implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double cashPaymentAmount;

    private List<CreditCardPaymentInformation> creditCardPaymentInformation;

	public Double getCashPaymentAmount() {
		return cashPaymentAmount;
	}

	public void setCashPaymentAmount(Double cashPaymentAmount) {
		this.cashPaymentAmount = cashPaymentAmount;
	}

	public List<CreditCardPaymentInformation> getCreditCardPaymentInformation() {
		return creditCardPaymentInformation;
	}

	public void setCreditCardPaymentInformation(List<CreditCardPaymentInformation> creditCardPaymentInformation) {
		this.creditCardPaymentInformation = creditCardPaymentInformation;
	}

}
