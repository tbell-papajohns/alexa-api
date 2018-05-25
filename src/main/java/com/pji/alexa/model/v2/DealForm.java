package com.pji.alexa.model.v2;

/**

PapaJohns
- heroItems: optional
- heroItemsToDisplay: optional
*/

import java.io.Serializable;
import java.util.List;

public class DealForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<DealForm> deals;

    private List<HeroForm> heroItems;

    private Integer heroItemsToDisplay;

	public List<DealForm> getDeals() {
		return deals;
	}

	public void setDeals(List<DealForm> deals) {
		this.deals = deals;
	}

	public List<HeroForm> getHeroItems() {
		return heroItems;
	}

	public void setHeroItems(List<HeroForm> heroItems) {
		this.heroItems = heroItems;
	}

	public Integer getHeroItemsToDisplay() {
		return heroItemsToDisplay;
	}

	public void setHeroItemsToDisplay(Integer heroItemsToDisplay) {
		this.heroItemsToDisplay = heroItemsToDisplay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
