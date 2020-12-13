package com.clarivate.testrestapi.security.token;

public interface AuthTokenGenerator {

	/**
	 *
	 * @return User session token
	 */
	String getToken();
}
