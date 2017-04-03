package com.ci6225.marketzone.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.User;

@Component
public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Product name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitPrice", "required.unitPrice", "Unit Price is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "required.quantity", "Quantity is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "Description is required.");
		
		if(product.getQuantity() < 0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.userForm.shopName", "ShopName field is required.");
		}
		
		
		/*if(user.getUserType() != 0 && user.getUserType()==2) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopName", "NotEmpty.userForm.shopName", "ShopName field is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.userForm.description", "Description field is required.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","required.confirmPassword", "Confirm Password is required.");

		

		if(user.getConfirmPassword() != "" && !(user.getPassword().equals(user.getConfirmPassword()))){
			errors.rejectValue("confirmPassword", "Notmatch.userForm.password", "Confirm password does not match with password");
		}
		if(user.getUserType() != 0 && !(user.getUserType() == 1 || user.getUserType() == 2)){
			errors.rejectValue("userType", "Notmatch.userForm.userType", "Please select user type.");
		}*/
		
	}

}
