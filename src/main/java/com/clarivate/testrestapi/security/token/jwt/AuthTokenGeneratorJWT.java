package com.clarivate.testrestapi.security.token.jwt;

import com.clarivate.testrestapi.security.token.AuthTokenGenerator;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class AuthTokenGeneratorJWT extends AuthTokenGenerator {

	/**
	 * @param username User name is used as subject of the generated token
	 */
	public AuthTokenGeneratorJWT(String username) {
		this(username, new JWTProperties());
	}

	/**
	 * @param username User name is used as subject of the generated token
	 * @param properties JWT properties, which contains secretKey, expiration time, etc.
	 */
	public AuthTokenGeneratorJWT(String username, JWTProperties properties) {
		super(_generateJWTToken(username, properties.getPrefix(), properties.getSecretKey(), properties.getExpires()));
	}

	/**
	 * Generates a JWT token applying HS256 signature algorithm, which involves SHA-256 encryption.
	 *
	 * @param username User name is used as subject of the generated token
	 * @param prefix Prefix for identifying auth token param in request
	 * @param secretKey Token secret key encription
	 * @param expirationTime Expiration time of the token. In milliseconds.
	 * @return String representation of  JWT token
	 */
	private static String _generateJWTToken(
			String username,
			String prefix,
			String secretKey,
			int expirationTime
	) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = secretKey.getBytes();
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder jwtBuilder = Jwts
				.builder()
				.setId("clarivateJWT")
				.setSubject(username)

				.setIssuedAt(new Date(System.currentTimeMillis()))

				// Apply expiration time: 10 minutes
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))

				// Apply signature method
				.signWith(signingKey, signatureAlgorithm);

		// Get JWT generated token
		String token = jwtBuilder.compact();

		// Add token prefix
		return String.format("%s%s", prefix, token);
	}
}
