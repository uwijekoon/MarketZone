package com.ci6225.marketzone.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.Product;


@Repository("productDao")
public class ProductDao extends AbstractDao<Integer, Product>{

	public Product findById(int id) {
        return getByKey(id);
    }
	
	public void saveProduct(Product product) {
        persist(product);
    }
	
	@SuppressWarnings("unchecked")
    public List<Product> findAvailableProducts() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deleted", 'F'));
        return (List<Product>) criteria.list();
    }
	
	@SuppressWarnings("unchecked")
    public List<Product> findProducts(int sellerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deleted", 'F'));
        criteria.add(Restrictions.eq("seller_id", sellerId));
        return (List<Product>) criteria.list();
    }
    
}
