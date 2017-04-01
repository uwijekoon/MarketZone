package com.ci6225.marketzone.service;

import org.springframework.stereotype.Service;

import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.util.PasswordUtil;

@Service
public class AuthService {

	public boolean userRegister(User user) throws Exception {
		byte[] salt = PasswordUtil.getSalt();
        String passwordEnc = PasswordUtil.getSecurePassword(user.getPassword(), salt);
        
        
		return false;
	}
	
	
}
