package com.ci6225.marketzone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<CartItem> itemList = new ArrayList<>();

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}
	
	 public void addItemsToCart(Product product, int quantity){
		 CartItem item = new CartItem();
		 item.setProduct(product);
		 item.setQuantity(quantity);
		 itemList.add(item);
	 }
	 
	 public void removeItem(int index){
		 itemList.remove(index);
	 }
	 
	 public void updateQuantity(int index, int quantity){
		 itemList.get(index).setQuantity(quantity);
	 }
		 
	 public double getSubTotal(){
		 double subTotal = 0;
		 for(CartItem item : itemList){
			 subTotal += item.getQuantity() * item.getProduct().getUnitPrice();
		 }
		 return subTotal;
	 }
	 
	 public double getAdminFee(){
		 return getSubTotal() * 0.01;
	 }
	 
	 public double getTotal(){
		 return getSubTotal() + getAdminFee();
	 }
}
