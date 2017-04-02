package com.ci6225.marketzone.dao;

import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.Order;

@Repository("orderDao")
public class OrderDao extends AbstractDao<Integer, Order>{

	public Order findById(int id) {
        return getByKey(id);
    }
	
	public void saveCart(Order cart) {
        persist(cart);
    }
    
}
