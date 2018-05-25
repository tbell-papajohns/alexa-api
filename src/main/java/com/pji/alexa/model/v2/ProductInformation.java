package com.pji.alexa.model.v2;

/**

PapaJohns
- allowHalfToppingFlag: optional
- allowedSauces: optional
- allowedToppings: optional
- calories: optional
- code: optional, Allowable Values: ["BALANCE", "EARNED", "BONUS", "EXPIRED", "REDEEMED", "PENDING", "CANCELLED", "RETURNED"]
- complimentarySides: optional
- defaultToppings: optional
- description: optional
- disclaimerText: optional
- displayPrice: optional
- distinguishingName: optional
- hideCartEditLink: optional
- hideQuantitySelector: optional
- image: optional
- instructionList: optional
- name: optional
- numToppingsAllowed: optional
- numToppingsFree: optional
- numToppingsRemovable: optional
- numToppingsReplaced: optional
- papaSize: optional
- productSKU: optional
- servingLabel: optional
- shortDescription: optional
- showProductDescriptions: optional
- surcharge: optional
- title: optional
- toppingTiers: optional
- webImage: optional
*/

import java.io.Serializable;
import java.util.List;

public class ProductInformation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean allowHalfToppingFlag;

    private List<Sauce> allowedSauces;

    private List<AllowedTopping> allowedToppings;

    private Integer calories;

    private String code;

    private List<ComplimentarySide> complimentarySides;

    private List<Long> defaultToppings;

    private String description;

    private String disclaimerText;

    private String displayPrice;

    private String distinguishingName;

    private Boolean hideCartEditLink;

    private Boolean hideQuantitySelector;

    private String image;

    private List<InstructionGroupInformation> instructionList;

    private String name;

    private Integer numToppingsAllowed;

    private Integer numToppingsFree;

    private Integer numToppingsRemovable;

    private Integer numToppingsReplaced;

    private PapaSize papaSize;

    private ProductSKU productSKU;

    private String servingLabel;

    private String shortDescription;

    private Boolean showProductDescriptions;

    private Double surcharge;

    private String title;

    private List<ToppingTier> toppingTiers;

    private String webImage;

	public Boolean getAllowHalfToppingFlag() {
		return allowHalfToppingFlag;
	}

	public void setAllowHalfToppingFlag(Boolean allowHalfToppingFlag) {
		this.allowHalfToppingFlag = allowHalfToppingFlag;
	}

	public List<Sauce> getAllowedSauces() {
		return allowedSauces;
	}

	public void setAllowedSauces(List<Sauce> allowedSauces) {
		this.allowedSauces = allowedSauces;
	}

	public List<AllowedTopping> getAllowedToppings() {
		return allowedToppings;
	}

	public void setAllowedToppings(List<AllowedTopping> allowedToppings) {
		this.allowedToppings = allowedToppings;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ComplimentarySide> getComplimentarySides() {
		return complimentarySides;
	}

	public void setComplimentarySides(List<ComplimentarySide> complimentarySides) {
		this.complimentarySides = complimentarySides;
	}

	public List<Long> getDefaultToppings() {
		return defaultToppings;
	}

	public void setDefaultToppings(List<Long> defaultToppings) {
		this.defaultToppings = defaultToppings;
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

	public String getDistinguishingName() {
		return distinguishingName;
	}

	public void setDistinguishingName(String distinguishingName) {
		this.distinguishingName = distinguishingName;
	}

	public Boolean getHideCartEditLink() {
		return hideCartEditLink;
	}

	public void setHideCartEditLink(Boolean hideCartEditLink) {
		this.hideCartEditLink = hideCartEditLink;
	}

	public Boolean getHideQuantitySelector() {
		return hideQuantitySelector;
	}

	public void setHideQuantitySelector(Boolean hideQuantitySelector) {
		this.hideQuantitySelector = hideQuantitySelector;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<InstructionGroupInformation> getInstructionList() {
		return instructionList;
	}

	public void setInstructionList(List<InstructionGroupInformation> instructionList) {
		this.instructionList = instructionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumToppingsAllowed() {
		return numToppingsAllowed;
	}

	public void setNumToppingsAllowed(Integer numToppingsAllowed) {
		this.numToppingsAllowed = numToppingsAllowed;
	}

	public Integer getNumToppingsFree() {
		return numToppingsFree;
	}

	public void setNumToppingsFree(Integer numToppingsFree) {
		this.numToppingsFree = numToppingsFree;
	}

	public Integer getNumToppingsRemovable() {
		return numToppingsRemovable;
	}

	public void setNumToppingsRemovable(Integer numToppingsRemovable) {
		this.numToppingsRemovable = numToppingsRemovable;
	}

	public Integer getNumToppingsReplaced() {
		return numToppingsReplaced;
	}

	public void setNumToppingsReplaced(Integer numToppingsReplaced) {
		this.numToppingsReplaced = numToppingsReplaced;
	}

	public PapaSize getPapaSize() {
		return papaSize;
	}

	public void setPapaSize(PapaSize papaSize) {
		this.papaSize = papaSize;
	}

	public ProductSKU getProductSKU() {
		return productSKU;
	}

	public void setProductSKU(ProductSKU productSKU) {
		this.productSKU = productSKU;
	}

	public String getServingLabel() {
		return servingLabel;
	}

	public void setServingLabel(String servingLabel) {
		this.servingLabel = servingLabel;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Boolean getShowProductDescriptions() {
		return showProductDescriptions;
	}

	public void setShowProductDescriptions(Boolean showProductDescriptions) {
		this.showProductDescriptions = showProductDescriptions;
	}

	public Double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ToppingTier> getToppingTiers() {
		return toppingTiers;
	}

	public void setToppingTiers(List<ToppingTier> toppingTiers) {
		this.toppingTiers = toppingTiers;
	}

	public String getWebImage() {
		return webImage;
	}

	public void setWebImage(String webImage) {
		this.webImage = webImage;
	}
}
