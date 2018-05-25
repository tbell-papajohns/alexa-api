package com.pji.alexa.model.v2;

/**

PapaJohns
- canAddToppingsFlag: optional
- numToppingsAllowed: optional
- numToppingsRemovable: optional
- numToppingsReplaced: optional
- productSKU: optional
*/

import java.io.Serializable;

public class ItemChoice implements Serializable {

    public Boolean canAddToppingsFlag;

    public Integer numToppingsAllowed;

    public Integer numToppingsRemovable;

    public Integer numToppingsReplaced;

    public ProductSKU productSKU;

}
