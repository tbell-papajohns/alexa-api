package com.pji.alexa.model.v2;

/**

PapaJohns
- deal: optional
- dealForm: optional
- description: optional
- displayPrice: optional
- id: optional
- image: optional
- sku: optional
- sortOrder: optional
*/

import java.io.Serializable;

public class CartUpsellForm implements Serializable {

    public DealInformation deal;

    public DealForm dealForm;

    public String description;

    public String displayPrice;

    public Long id;

    public String image;

    public String sku;

    public Integer sortOrder;

}
