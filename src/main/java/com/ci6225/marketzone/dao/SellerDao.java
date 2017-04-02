package com.ci6225.marketzone.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.Seller;
import com.ci6225.marketzone.model.Seller1;
import com.ci6225.marketzone.model.User;

@Repository("sellerDao")
public class SellerDao extends AbstractDao<Integer, Seller>{

	public Seller findById(int id) {
        return getByKey(id);
    }
	
	public void saveSeller(Seller seller) {
        persist(seller);
    }

	@SuppressWarnings("unchecked")
    public List<User> findAllSellers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }
    
}