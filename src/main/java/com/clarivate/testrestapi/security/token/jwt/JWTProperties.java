package com.clarivate.testrestapi.security.token.jwt;

import java.io.IOException;
import java.util.Properties;

public class JWTProperties {
	private final String header;
	private final String prefix;
	private final String secretKey;
	private final int expires;

	public JWTProperties() {
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("jwt.properties"));

			this.header = props.getProperty("jwt.header");
			this.prefix = String.format("%s ", props.getProperty("jwt.prefix"));
			this.secretKey = props.getProperty("jwt.secretKey");
			this.expires = Integer.parseInt(props.getProperty("jwt.expires"));
		} catch (IOException e) {
			// TODO: Throw an specific AuthTokenValidatorJWTConfigurationException
			throw new RuntimeException(e);
		}
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public int getExpires() {
		return expires;
	}
}
