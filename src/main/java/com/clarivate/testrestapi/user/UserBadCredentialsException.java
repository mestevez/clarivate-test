package com.clarivate.testrestapi.user;

public class UserBadCredentialsException extends RuntimeException {
	public UserBadCredentialsException() {
		super("Incorrect user or password");
	}
}
