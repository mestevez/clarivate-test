package com.clarivate.testrestapi.user;

public class User {
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
		return String.format("username: %s, password: %s", username, password);
	}
}
