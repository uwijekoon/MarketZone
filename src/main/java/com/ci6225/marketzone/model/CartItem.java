package com.ci6225.marketzone.model;

import javax.validation.constraints.Min;

public class CartItem {
	private Product product;
	
	@Min(1)
	private int quantity = 1;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		public double getAmount(){
			return quantity * product.getUnitPrice();
		}
	
}
