package com.gitlab.esenbogagnu.urmsweb.validator;

import com.gitlab.esenbogagnu.urmsweb.domain.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserDto userDto = (UserDto) target;

		if(!userDto.getPassword().equals(userDto.getMatchingPassword())) {
			errors.rejectValue("password", "{error.passwords_not_match}");
		}
	}
}
