package com.clarivate.testrestapi;

import com.clarivate.testrestapi.security.token.jwt.AuthTokenGeneratorJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity login(@RequestBody Login loginForm) {

		if (loginForm.getUsername() == null || loginForm.getPassword() == null) {
			// TODO: Throw specific Exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			// TODO: Validate username and password
			//  It is currently using hardcoded password "secret" for JUNIT test implementation
			if (loginForm.getPassword().equals("secret")) {
				String token = new AuthTokenGeneratorJWT(loginForm.getUsername()).getToken();

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
