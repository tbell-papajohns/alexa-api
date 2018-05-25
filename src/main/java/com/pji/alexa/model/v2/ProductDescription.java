package com.pji.alexa.model.v2;

public class ProductDescription implements Comparable<ProductDescription>{
	private String productName;
	private Double productCost;
	private Integer productQuantity;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProductDescription) {
			ProductDescription productDescription= (ProductDescription)obj;
			return super.equals(obj) && this.productCost == productDescription.getProductCost();			
		}
		return false;
	}	
    @Override
	public int compareTo(ProductDescription productDescription) {
    	return Double.compare(this.getProductCost(), productDescription.getProductCost());
	}    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Double getProductCost() {
		return productCost;
	}

	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}
	
}