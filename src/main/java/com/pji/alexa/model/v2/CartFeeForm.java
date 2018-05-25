package com.pji.alexa.model.v2;

/**

PapaJohns
- amount: optional
- description: optional
- feeTypeId: optional
- tax: optional
*/

import java.io.Serializable;

public class CartFeeForm implements Serializable {

    public Double amount;

    public String description;

    public Integer feeTypeId;

    public Double tax;

}
