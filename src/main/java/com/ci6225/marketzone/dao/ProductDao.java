package com.ci6225.marketzone.dao;

import java.util.List;

import org.hibernate.Criteria;
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
        return (List<Product>) criteria.list();
    }
    
}
