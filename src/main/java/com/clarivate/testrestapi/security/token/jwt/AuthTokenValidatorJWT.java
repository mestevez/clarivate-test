package com.clarivate.testrestapi.security.token.jwt;

import com.clarivate.testrestapi.security.token.AuthTokenValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Validation is performed using the JWT library, which handle authentication tokens in memory.
 */
public class AuthTokenValidatorJWT implements AuthTokenValidator {

	private final HttpServletRequest request;
	private final JWTProperties properties;

	public AuthTokenValidatorJWT(HttpServletRequest request) {
		this(request, new JWTProperties());
	}

	public AuthTokenValidatorJWT(HttpServletRequest request, JWTProperties properties) {
		this.request = request;
		this.properties = properties;
	}

	/**
	 * Verify if the http request contains a valid authentication token
	 * In case of the authentication was not valid, this method will emmit a RuntimeException.
	 *
	 * @return true - if the http request contains a valid token. false - otherwise.
	 */
	@Override
	public Authentication getAuth() {
		String authenticationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String jwtToken = authenticationHeader.replace(properties.getPrefix(), "");

		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(properties.getSecretKey().getBytes()).build()
				.parseClaimsJws(jwtToken)
				.getBody();

		return new UsernamePasswordAuthenticationToken(
				claims.getSubject(),
				null,
				null
		);
	}

	/**
	 * Verify if the http request contains a valid authentication token
	 * In case of the authentication was not valid, this method will emmit a RuntimeException.
	 *
	 * @return true - if the http request contains a valid token. false - otherwise.
	 */
	@Override
	public boolean isValid() {
		String authenticationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		return authenticationHeader != null && authenticationHeader.startsWith(properties.getPrefix());
	}
}
