package com.clarivate.testrestapi.user;

import com.clarivate.testrestapi.security.UserCredentials;
import com.clarivate.testrestapi.security.token.jwt.AuthTokenGeneratorJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity login(@RequestBody UserCredentials credentials) {
		if (credentials.getUsername() == null || credentials.getPassword() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			User user = repository.findByUsername(credentials.getUsername())
					.orElseThrow(() -> new UserBadCredentialsException());

			if (credentials.getPassword().equals(user.getPassword())) {
				String token = new AuthTokenGeneratorJWT(credentials.getUsername()).getToken();

				return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(token);
			} else {
				throw new UserBadCredentialsException();
			}
		}
	}
}
