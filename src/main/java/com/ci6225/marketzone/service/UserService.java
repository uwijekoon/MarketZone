package com.ci6225.marketzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.dao.UserDao;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.util.PasswordUtil;

@Service("userService")
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User findById(int id) {
		return userDao.findById(id);
	}
	
	public boolean userRegister(User user) throws Exception {
		byte[] salt = PasswordUtil.getSalt();
        String passwordEnc = PasswordUtil.getSecurePassword(user.getPassword(), salt);
        user.setPassword(passwordEnc);
        user.setSalt(salt);
        userDao.saveUser(user);
		return true;
	}
	
	public User findByUserCode(String code) {
		return userDao.findByUserCode(code);
	}
	
	public User userLogin(String userCode, String password) {
		
		User user = userDao.findByUserCode(userCode);
		String userPass = PasswordUtil.getSecurePassword(password, user.getSalt());
		
		if(user.getPassword().equals(userPass)) {
			return user;
		}
		return null;
	}
	
}
