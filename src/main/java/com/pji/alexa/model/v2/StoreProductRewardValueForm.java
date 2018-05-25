package com.pji.alexa.model.v2;

/**

PapaJohns
- productTypeId: optional
- promo: optional
- rewardValue: optional
- storeProductRewardValueId: optional
*/

import java.io.Serializable;

public class StoreProductRewardValueForm implements Serializable {

    public Long productTypeId;

    public PromoForm promo;

    public Integer rewardValue;

    public Long storeProductRewardValueId;

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public PromoForm getPromo() {
		return promo;
	}

	public void setPromo(PromoForm promo) {
		this.promo = promo;
	}

	public Integer getRewardValue() {
		return rewardValue;
	}

	public void setRewardValue(Integer rewardValue) {
		this.rewardValue = rewardValue;
	}

	public Long getStoreProductRewardValueId() {
		return storeProductRewardValueId;
	}

	public void setStoreProductRewardValueId(Long storeProductRewardValueId) {
		this.storeProductRewardValueId = storeProductRewardValueId;
	}

}
