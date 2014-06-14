package com.novation.eligibility.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novation.eligibility.rest.controller.exception.UnknownResourceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Default controller that exists to return a proper REST response for unmapped
 * requests.
 */
@Controller
@RequestMapping("/api/**")
public class DefaultController {

	@RequestMapping("/**")
	public void unmappedRequest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		throw new UnknownResourceException("There is no resource for path "
				+ uri);
	}
}
