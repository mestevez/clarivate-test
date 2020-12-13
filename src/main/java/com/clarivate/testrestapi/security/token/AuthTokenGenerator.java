package com.clarivate.testrestapi.security.token;

public abstract class AuthTokenGenerator {
	private final String token;

	/**
	 *
	 * @param token User session token
	 */
	protected AuthTokenGenerator(String token) {
		this.token = token;
	}

	/**
	 *
	 * @return User session token
	 */
	public String getToken() {
		return token;
	}
}
