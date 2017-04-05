package com.ci6225.marketzone.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.Order;
import com.ci6225.marketzone.model.User;

@Repository("orderDao")
public class OrderDao extends AbstractDao<Integer, Order>{

	public Order findById(int id) {
        return getByKey(id);
    }
	
	public void saveCart(Order order) {
        persist(order);
    }
    
	@SuppressWarnings("unchecked")
	public List<Order> findOrders(User user) {
        Criteria criteria = createEntityCriteria();
        criteria.setFetchMode("lineItems", FetchMode.SELECT);
        criteria.add(Restrictions.eq("user", user));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Order>) criteria.list();
    }
}
