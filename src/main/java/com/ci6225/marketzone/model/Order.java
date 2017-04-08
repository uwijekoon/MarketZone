/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ci6225.marketzone.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.ci6225.marketzone.util.Constants;

/**
 *
 * @author Ureka
 */
@Entity
@Table(name="ORDER_DETAIL")
public class Order {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="total_amount")
    private double subTotal;
    
    @NotNull
	@Size(min=3, max=10, message="First Name must between 3 and 10 characters")
    @Column(name="first_name")
    private String firstName;
    
    @NotNull
	@Size(min=3, max=10, message="Last Name must between 3 and 10 characters")
    @Column(name="last_name")
    private String lastName;
    
    @NotEmpty
	@Email
    @Column(name="email")
    private String email;
    
    @Column(name="telephone")
    private String telephone;
    
	@NotNull
	@Size(min=3, max=100, message="Address1 must between 3 and 100 characters")
    @Column(name="address1")
    private String address1;
    
    @Column(name="address2")
    private String address2;
    
    @NotNull
	@Size(min=3, max=20, message="City must between 3 and 20 characters")
    @Column(name="city")
    private String city;
    
    @NotNull
	@Size(min=3, max=10, message="First name must between 3 and 10 characters")
    @Column(name="postal_code")
    private String postalCode;
    
    
    @Column(name="country")
    private String country;
    
    @Column(name="payment_method")
    private int paymentMethod;
    
    @Column(name="comments")
    private String comments;
    
    @Column(name="order_date")
    private Date orderDate;
    
    @Column(name="status")
    private int status = 1;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade={CascadeType.ALL})
    private List<OrderItem> orderItems = new ArrayList<>();
    
    @ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	public double getAdminFee(){
		return subTotal * 0.01;
	}
	
	public double getTotal(){
		return getSubTotal() + getAdminFee();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getAddress(){
		String address = address1;
		if(address2 != null){
			address += ", "+address2;
		}
		address += city + ", "+ country + "," + postalCode;
		return address;
	}
	
	public String getOrderStatusStr(){
		String statusStr = "";
		switch(status){
		case Constants.ORDER_STATUS_CONFIRMED : 
			statusStr = Constants.ORDER_STATUS_CONFIRMEDSTR;
			break;
		default:	
			statusStr = "";
		}
		return statusStr;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;

        return order.getId() == this.id;
	}

}
