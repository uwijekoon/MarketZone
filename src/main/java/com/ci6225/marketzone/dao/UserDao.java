package com.ci6225.marketzone.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ci6225.marketzone.model.User;

@Repository("userDao")
public class UserDao extends AbstractDao<Integer, User>{

	public User findById(int id) {
        return getByKey(id);
    }
	
	public User findByUserCode(String code) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userCode", code));
        return (User) criteria.uniqueResult();
    }
	
	public void saveUser(User user) {
        persist(user);
    }

	@SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }
	
	public User findByUserEmail(String email) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (User) criteria.uniqueResult();
    }
    
}
