package com.novation.eligibility.ui.services;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityService {

	@Inject
	protected AuthenticationManager authenticationManager;

	public boolean tryLogUserIn(String username, String password,
			HttpServletRequest request) {
		try {
			return logUserIn(username, password, request);
		} catch (Exception x) {
			return false;
		}
	}

	public boolean logUserIn(String username, String password,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authentication = authenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return authentication.isAuthenticated();
	}
}
