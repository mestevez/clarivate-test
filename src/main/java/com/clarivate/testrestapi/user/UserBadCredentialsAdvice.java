package com.clarivate.testrestapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserBadCredentialsAdvice {
	@ResponseBody
	@ExceptionHandler(UserBadCredentialsException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	String userBadCredentialsHandler(UserBadCredentialsException ex) {
		return ex.getMessage();
	}
}
