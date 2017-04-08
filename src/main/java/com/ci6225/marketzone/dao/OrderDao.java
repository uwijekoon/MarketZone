package com.ci6225.marketzone.dao;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.Order;
import com.ci6225.marketzone.model.OrderItem;
import com.ci6225.marketzone.model.Seller;
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
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        return (List<Order>) criteria.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Order> findOrders(Seller seller) {
		Query query = getSession().createQuery("select o from Order as o inner join o.orderItems as oi inner join oi.product as p "
				+ "where p.seller.id = :id order by o.id desc");
		query.setParameter("id", seller.getId());
		List<Order> orderList = query.list();
		Set<Order> depdupeCustomers = new LinkedHashSet<>(orderList);
		orderList.clear();
		orderList.addAll(depdupeCustomers);
		return orderList;
    }
	
	@SuppressWarnings("unchecked")
	public Order getOrderRequest(int orderId, int sellerId) {
		Query query = getSession().createQuery("select distinct o from Order as o inner join o.orderItems as oi inner join oi.product as p "
				+ "inner join p.seller as s where o.id = :id and s.id=:sellerId");
		query.setParameter("id", orderId);
		query.setParameter("sellerId", sellerId);
		List<Order> list = (List<Order>) query.list();
		List<OrderItem> itemList = list.get(0).getOrderItems();
		for (Iterator<OrderItem> iterator = itemList.iterator(); iterator.hasNext();) {
			OrderItem orderItem = (OrderItem) iterator.next();
			if(orderItem.getProduct().getSeller().getId() != sellerId){
				iterator.remove();
			}
		}
		return list.get(0);
    }
}
