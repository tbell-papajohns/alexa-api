package com.pji.alexa.model.v2;

/**

PapaJohns
- altPricingText: optional
- availableAllDay: optional
- carryoutOnlyFlag: optional
- dealCode: optional
- dealId: optional
- dealItems: optional
- deliveryOnlyFlag: optional
- description: optional
- disclaimerText: optional
- displayPrice: optional
- image: optional
- itemProperties: optional
- limitDays: optional
- limitEndTime: optional
- limitStartTime: optional
- minOrderForDiscount: optional
- pricingMethodCode: optional, Allowable Values: ["STANDARD_PRICE_EDEAL", "BUY_ONE_GET_ONE_FREE", "BUY_ONE_GET_ONE_FOR_DOLLAR_AMOUNT_OFF", "BUY_ONE_GET_ONE_FOR_PERCENTAGE_OFF", "BUY_ITEMS_GET_ANOTHER_ITEM_AT_SPECIAL_PRICE", "BUY_ITEMS_GET_ANOTHER_ITEM_AT_DOLLAR_AMOUNT_OFF", "BUY_ITEMS_GET_ANOTHER_ITEM_AT_PERCENTAGE_OFF", "DOLLAR_AMOUNT_OFF_ITEM", "PERCENTAGE_OFF_ITEM", "DOLLAR_AMOUNT_OFF_ORDER", "PERCENTAGE_OFF_ORDER", "FREE_TOPPINGS", "TOPPING_FIXED_PRICE", "DOLLAR_OFF_TOPPING", "PERCENT_OFF_TOPPING", "DELIVERY_FEE_AMOUNT_OFF_ORDER", "FREE_CUSTOMER_POINTS", "REGULAR_MENU_PRICE", "PAPA_REWARDS_TRACKING", "TRACKING"]
- promotionCode: optional
- sortOrder: optional
- title: optional
- useAltText: optional
- webImage: optional
*/

import java.io.Serializable;
import java.util.List;

public class DealInformation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String altPricingText;

    private Boolean availableAllDay;

    private Boolean carryoutOnlyFlag;

    private String dealCode;

    private Long dealId;

    private List<DealItem> dealItems;

    private Boolean deliveryOnlyFlag;

    private String description;

    private String disclaimerText;

    private String displayPrice;

    private String image;

    private List<ItemProperty> itemProperties;

    private List<String> limitDays;

    private String limitEndTime;

    private String limitStartTime;

    private Double minOrderForDiscount;

    private String pricingMethodCode;

    private String promotionCode;

    private Integer sortOrder;

    private String title;

    private Boolean useAltText;

    private String webImage;

	public String getAltPricingText() {
		return altPricingText;
	}

	public void setAltPricingText(String altPricingText) {
		this.altPricingText = altPricingText;
	}

	public Boolean getAvailableAllDay() {
		return availableAllDay;
	}

	public void setAvailableAllDay(Boolean availableAllDay) {
		this.availableAllDay = availableAllDay;
	}

	public Boolean getCarryoutOnlyFlag() {
		return carryoutOnlyFlag;
	}

	public void setCarryoutOnlyFlag(Boolean carryoutOnlyFlag) {
		this.carryoutOnlyFlag = carryoutOnlyFlag;
	}

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public List<DealItem> getDealItems() {
		return dealItems;
	}

	public void setDealItems(List<DealItem> dealItems) {
		this.dealItems = dealItems;
	}

	public Boolean getDeliveryOnlyFlag() {
		return deliveryOnlyFlag;
	}

	public void setDeliveryOnlyFlag(Boolean deliveryOnlyFlag) {
		this.deliveryOnlyFlag = deliveryOnlyFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisclaimerText() {
		return disclaimerText;
	}

	public void setDisclaimerText(String disclaimerText) {
		this.disclaimerText = disclaimerText;
	}

	public String getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ItemProperty> getItemProperties() {
		return itemProperties;
	}

	public void setItemProperties(List<ItemProperty> itemProperties) {
		this.itemProperties = itemProperties;
	}

	public List<String> getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(List<String> limitDays) {
		this.limitDays = limitDays;
	}

	public String getLimitEndTime() {
		return limitEndTime;
	}

	public void setLimitEndTime(String limitEndTime) {
		this.limitEndTime = limitEndTime;
	}

	public String getLimitStartTime() {
		return limitStartTime;
	}

	public void setLimitStartTime(String limitStartTime) {
		this.limitStartTime = limitStartTime;
	}

	public Double getMinOrderForDiscount() {
		return minOrderForDiscount;
	}

	public void setMinOrderForDiscount(Double minOrderForDiscount) {
		this.minOrderForDiscount = minOrderForDiscount;
	}

	public String getPricingMethodCode() {
		return pricingMethodCode;
	}

	public void setPricingMethodCode(String pricingMethodCode) {
		this.pricingMethodCode = pricingMethodCode;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getUseAltText() {
		return useAltText;
	}

	public void setUseAltText(Boolean useAltText) {
		this.useAltText = useAltText;
	}

	public String getWebImage() {
		return webImage;
	}

	public void setWebImage(String webImage) {
		this.webImage = webImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
