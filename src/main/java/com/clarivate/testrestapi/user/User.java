package com.clarivate.testrestapi.user;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

	/**
	 * user unique identifier
	 */
	private @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userId;

	/**
	 * User name, user for authentication
	 */
	private String username;

	/**
	 * Authentication password
	 */
	private String password;

	/**
	 * Used internally by spring boot
	 */
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
