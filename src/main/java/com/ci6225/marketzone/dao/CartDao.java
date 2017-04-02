package com.ci6225.marketzone.dao;

import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.Cart;

@Repository("cartDao")
public class CartDao extends AbstractDao<Integer, Cart>{

	public Cart findById(int id) {
        return getByKey(id);
    }
	
	public void saveCart(Cart cart) {
        persist(cart);
    }
    
}
