package com.clarivate.testrestapi.security;

import com.clarivate.testrestapi.security.token.jwt.AuthTokenValidatorJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends OncePerRequestFilter {

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException {
		try {
			// Extract JWT token from request
			AuthTokenValidatorJWT authTokenValidator = new AuthTokenValidatorJWT(request);

			if (authTokenValidator.isValid()) {
				Authentication auth = authTokenValidator.getAuth();

				SecurityContextHolder.getContext().setAuthentication(auth);
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}
}
