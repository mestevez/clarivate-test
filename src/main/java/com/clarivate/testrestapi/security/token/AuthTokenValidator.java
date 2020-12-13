package com.clarivate.testrestapi.security.token;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Extract the authentication token from the request and verify if its valid.
 */
public abstract class AuthTokenValidator {
	private final HttpServletRequest request;

	/**
	 * @param request Http request the token is extracted from
	 */
	protected AuthTokenValidator(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Gets a springboot authentication instance from the http request.
	 *
	 * @return Instance of springboot authentication
	 */
	public abstract Authentication getAuth();

	/**
	 * @return Http request the token is extracted from
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * Verify if the http request contains a valid authentication token
	 *
	 * @return true - if the http request contains a valid token. false - otherwise.
	 */
	public abstract boolean isValid();
}
