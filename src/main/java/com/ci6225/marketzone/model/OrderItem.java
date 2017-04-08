/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ci6225.marketzone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ci6225.marketzone.util.Constants;

/**
 *
 * @author Ureka
 */
@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="quantity")
    private int quantity;
	@Column(name="amount")
    private double amount;
	@Column(name="status")
    private double status = 1;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
    private Product product;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable = false, referencedColumnName="id")
	Order order;
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getUnitPrice(){
		return amount / quantity;
	}
	
	public String getStatusStr(){
		if(status == Constants.ORDER_STATUS_CONFIRMED){
			return Constants.ORDER_STATUS_CONFIRMEDSTR;
		}
		return "";
	}

}
