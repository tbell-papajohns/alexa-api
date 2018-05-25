package com.pji.alexa.model.v2;

/**

PapaJohns
- amount: optional
- billingAddress: optional
- cardNumber: optional
- mcveTtid: optional
- nameOnCard: optional
- orderPaymentId: optional
- paymentTypeId: optional
*/

import java.io.Serializable;

public class CreditCardPaymentInformation implements Serializable {

    public Double amount;

    public BillingAddress billingAddress;

    public String cardNumber;

    public Long mcveTtid;

    public String nameOnCard;

    public Long orderPaymentId;

    public Integer paymentTypeId;

}
