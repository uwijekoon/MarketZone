package com.ci6225.marketzone.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.service.UserService;

@Component
public class FormValidation {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	public void registerValidate(User user, Errors errors, BindingResult bindingResult) {
		
		if(user.getUserType() != 0 && user.getUserType()==2) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopName", "NotEmpty.userForm.shopName", "ShopName field is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.userForm.description", "Description field is required.");
		}
		
		if(user.getConfirmPassword() != "" && !(user.getPassword().equals(user.getConfirmPassword()))){
			errors.rejectValue("confirmPassword", "Notmatch.userForm.password", "Confirm password does not match with password");
		}
		if(user.getUserType() == 0 || !(user.getUserType() == 1 || user.getUserType() == 2)){
			errors.rejectValue("userType", "Notmatch.userForm.userType", "Please select user type.");
		}
		
		if(!bindingResult.hasErrors()){
			if(userService.findByUserCode(user.getUserCode()) != null) {
				errors.rejectValue("userCode", "exists.userForm.userCode", "UserName already exists!");
			}

			if(userService.findByUserEmail(user.getEmail()) != null) {
				errors.rejectValue("email", "exists.userForm.email", "Email already existes!");
			}
		}
	}
	
	public void loginValidate(User user, Errors errors) {
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", "required.userCode", "Username is required.");
	}

}
