package com.pji.alexa.model.v2;

/**

PapaJohns
- englishPromoCode: optional
- promoHeaderId: optional
*/

import java.io.Serializable;

public class PromoForm implements Serializable {

    public String englishPromoCode;

    public Long promoHeaderId;

	public String getEnglishPromoCode() {
		return englishPromoCode;
	}

	public void setEnglishPromoCode(String englishPromoCode) {
		this.englishPromoCode = englishPromoCode;
	}

	public Long getPromoHeaderId() {
		return promoHeaderId;
	}

	public void setPromoHeaderId(Long promoHeaderId) {
		this.promoHeaderId = promoHeaderId;
	}

}
