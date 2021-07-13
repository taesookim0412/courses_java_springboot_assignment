package com.taesookim.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.taesookim.project.models.User;
import com.taesookim.project.services.UserService;

@Component
public class UserValidator implements Validator {
	private final UserService userService;
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		if (userService.findEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Unique");
		}
		
	}

}
