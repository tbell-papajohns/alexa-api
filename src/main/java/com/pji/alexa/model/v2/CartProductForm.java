package com.pji.alexa.model.v2;

/**

PapaJohns
- displayPrice: optional
- favoriteId: optional
- image: optional
- instructions: optional
- papaSize: optional
- papaSized: optional
- productConfigurationId: optional
- quantity: required
- sauceId: optional
- sectionOne: optional
- sectionTwo: optional
- sectionWhole: optional
- shopcartItemId: optional
- sides: optional
- sku: required
- status: optional
- statusMessage: optional
- title: optional
*/

import java.io.Serializable;
import java.util.List;

public class CartProductForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String displayPrice;

    private Long favoriteId;

    private String image;

    private List<CartProductInstructionForm> instructions;

    private PapaSize papaSize;

    private Boolean papaSized;

    private Integer productConfigurationId;

    private Integer quantity;

    private Long sauceId;

    private CartSectionForm sectionOne;

    private CartSectionForm sectionTwo;

    private CartSectionForm sectionWhole;

    private String shopcartItemId;

    private List<CartComplimentarySideForm> sides;

    private String sku;

    private String status;

    private ResponseMessage statusMessage;

    private String title;

	public String getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}

	public Long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<CartProductInstructionForm> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<CartProductInstructionForm> instructions) {
		this.instructions = instructions;
	}

	public PapaSize getPapaSize() {
		return papaSize;
	}

	public void setPapaSize(PapaSize papaSize) {
		this.papaSize = papaSize;
	}

	public Boolean getPapaSized() {
		return papaSized;
	}

	public void setPapaSized(Boolean papaSized) {
		this.papaSized = papaSized;
	}

	public Integer getProductConfigurationId() {
		return productConfigurationId;
	}

	public void setProductConfigurationId(Integer productConfigurationId) {
		this.productConfigurationId = productConfigurationId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getSauceId() {
		return sauceId;
	}

	public void setSauceId(Long sauceId) {
		this.sauceId = sauceId;
	}

	public CartSectionForm getSectionOne() {
		return sectionOne;
	}

	public void setSectionOne(CartSectionForm sectionOne) {
		this.sectionOne = sectionOne;
	}

	public CartSectionForm getSectionTwo() {
		return sectionTwo;
	}

	public void setSectionTwo(CartSectionForm sectionTwo) {
		this.sectionTwo = sectionTwo;
	}

	public CartSectionForm getSectionWhole() {
		return sectionWhole;
	}

	public void setSectionWhole(CartSectionForm sectionWhole) {
		this.sectionWhole = sectionWhole;
	}

	public String getShopcartItemId() {
		return shopcartItemId;
	}

	public void setShopcartItemId(String shopcartItemId) {
		this.shopcartItemId = shopcartItemId;
	}

	public List<CartComplimentarySideForm> getSides() {
		return sides;
	}

	public void setSides(List<CartComplimentarySideForm> sides) {
		this.sides = sides;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ResponseMessage getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(ResponseMessage statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
