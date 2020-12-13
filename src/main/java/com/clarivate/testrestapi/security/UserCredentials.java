package com.clarivate.testrestapi.security;

public class UserCredentials {
	private String username;
	private String password;

	/**
	 *
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("UserCredentials {username=%s, password=%s}", username, password);
	}
}
