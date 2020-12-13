package com.clarivate.testrestapi.security.token;

import org.springframework.security.core.Authentication;

/**
 * Extract the authentication token from the request and verify if its valid.
 */
public interface AuthTokenValidator {

	/**
	 * Gets a springboot authentication instance from the http request.
	 *
	 * @return Instance of springboot authentication
	 */
	Authentication getAuth();

	/**
	 * Verify if the http request contains a valid authentication token
	 *
	 * @return true - if the http request contains a valid token. false - otherwise.
	 */
	boolean isValid();
}
