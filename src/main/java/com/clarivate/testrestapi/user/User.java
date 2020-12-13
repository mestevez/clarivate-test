package com.clarivate.testrestapi.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	private @Id
	@GeneratedValue
	Long userId;

	private String username;
	private String password;

	public User() {
	}

	/**
	 * Custom constructor when need to create a new instance, but don't have and userId yet.
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

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
	public Long getUserId() {
		return userId;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("User {userId=%s, username=%s, password=%s}", userId, username, password);
	}
}
