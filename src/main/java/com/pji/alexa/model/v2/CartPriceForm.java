package com.pji.alexa.model.v2;

/**

PapaJohns
- beverageSurcharge: optional
- beverageTotal: optional
- discountTotal: optional
- feeList: optional
- feeTaxTotal: optional
- feeTotal: optional
- foodSurcharge: optional
- foodTotal: optional
- grandTotal: optional
- productTotal: optional
- taxList: optional
- taxTotal: optional
- tip: optional
*/

import java.io.Serializable;
import java.util.List;

public class CartPriceForm implements Serializable {

    private Double beverageSurcharge;

    private Double beverageTotal;

    private Double discountTotal;

    private List<CartFeeForm> feeList;

    private Double feeTaxTotal;

    private Double feeTotal;

    private Double foodSurcharge;

    private Double foodTotal;

    private Double grandTotal;

    private Double productTotal;

    private List<CartTaxForm> taxList;

    private Double taxTotal;

    private Double tip;

	public Double getBeverageSurcharge() {
		return beverageSurcharge;
	}

	public void setBeverageSurcharge(Double beverageSurcharge) {
		this.beverageSurcharge = beverageSurcharge;
	}

	public Double getBeverageTotal() {
		return beverageTotal;
	}

	public void setBeverageTotal(Double beverageTotal) {
		this.beverageTotal = beverageTotal;
	}

	public Double getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(Double discountTotal) {
		this.discountTotal = discountTotal;
	}

	public List<CartFeeForm> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<CartFeeForm> feeList) {
		this.feeList = feeList;
	}

	public Double getFeeTaxTotal() {
		return feeTaxTotal;
	}

	public void setFeeTaxTotal(Double feeTaxTotal) {
		this.feeTaxTotal = feeTaxTotal;
	}

	public Double getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(Double feeTotal) {
		this.feeTotal = feeTotal;
	}

	public Double getFoodSurcharge() {
		return foodSurcharge;
	}

	public void setFoodSurcharge(Double foodSurcharge) {
		this.foodSurcharge = foodSurcharge;
	}

	public Double getFoodTotal() {
		return foodTotal;
	}

	public void setFoodTotal(Double foodTotal) {
		this.foodTotal = foodTotal;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Double getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}

	public List<CartTaxForm> getTaxList() {
		return taxList;
	}

	public void setTaxList(List<CartTaxForm> taxList) {
		this.taxList = taxList;
	}

	public Double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public Double getTip() {
		return tip;
	}

	public void setTip(Double tip) {
		this.tip = tip;
	}
}
