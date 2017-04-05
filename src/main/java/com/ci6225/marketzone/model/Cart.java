package com.ci6225.marketzone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<OrderItem> itemList = new ArrayList<>();

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
	
	 public void addItemsToCart(Product product, int quantity){
		 OrderItem item = new OrderItem();
		 item.setProduct(product);
		 item.setQuantity(quantity);
		 item.setAmount(quantity * product.getUnitPrice()); 
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
		 for(OrderItem item : itemList){
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
