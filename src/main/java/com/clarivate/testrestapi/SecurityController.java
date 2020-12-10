package com.clarivate.testrestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SecurityController {

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity login(
		@RequestBody Login loginForm
	) {

		if (loginForm.getUsername() == null || loginForm.getPassword() == null) {
			// TODO: Throw specific Exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			// TODO: Verify password via JPA
			if (loginForm.getPassword().equals("secret")) {
				UUID token = UUID.randomUUID();
				// TODO: Validate username and password and assign it to the user
				System.out.println(String.format("!!! USERNAME: %s", loginForm.getUsername()));
				return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(token);
			} else {
				// TODO: Throw specific Exception
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		}
	}
}
