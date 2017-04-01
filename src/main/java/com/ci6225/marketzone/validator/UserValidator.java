package com.ci6225.marketzone.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ci6225.marketzone.model.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userType", "NotEmpty.userForm.userType", "Please select the user typre.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", "NotEmpty.userForm.userCode", "User Name field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName", "First Name field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm.lastName", "Last Name field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.userForm.phone", "Phone field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email", "Email field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userForm.country", "Please select the Country.");
		
		if(user.getUserType() != null && "2".equals(user.getUserType())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopName", "NotEmpty.userForm.shopName", "ShopName field is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.userForm.description", "Description field is required.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","required.confirmPassword", "Confirm Password is required.");

		

		if(user.getConfirmPassword() != null && !(user.getPassword().equals(user.getConfirmPassword()))){
			errors.rejectValue("confirmPassword", "Notmatch.userForm.password", "Confirm password does not match with password");
		}
		if(user.getUserType() != null && !("1".equals(user.getUserType()) || "2".equals(user.getUserType()))){
			errors.rejectValue("userType", "Notmatch.userForm.userType", "Please select user type.");
		}
		
	}

}