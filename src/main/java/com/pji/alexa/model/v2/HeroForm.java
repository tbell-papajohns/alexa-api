package com.pji.alexa.model.v2;

/**

PapaJohns
- deal: optional
- dealForm: optional
- image: optional
- itemCode: optional
- linkURL: optional
- productInformation: optional
- text: optional
- textLineThree: optional
- textLineTwo: optional
- topper: optional
- type: optional, Allowable Values: ["PERCENTAGE", "AMOUNT", "DELIVERY_FEE", "CUSTOMER_POINTS"]
*/

import java.io.Serializable;

public class HeroForm implements Serializable {

    public DealInformation deal;

    public DealForm dealForm;

    public String image;

    public String itemCode;

    public String linkURL;

    public ProductInformation productInformation;

    public String text;

    public String textLineThree;

    public String textLineTwo;

    public String topper;

    public String type;

}
