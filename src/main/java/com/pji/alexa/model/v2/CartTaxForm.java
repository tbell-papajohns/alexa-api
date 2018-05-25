package com.pji.alexa.model.v2;

/**

PapaJohns
- amount: optional
- taxType: optional
- taxableAmount: optional
*/

import java.io.Serializable;

public class CartTaxForm implements Serializable {

    public Double amount;

    public String taxType;

    public Double taxableAmount;

}
