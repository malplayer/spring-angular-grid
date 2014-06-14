package com.novation.eligibility.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.support.RequestContextUtils;

public class DynamicParameterizableViewController extends
		ParameterizableViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView(getViewName(request),
				RequestContextUtils.getInputFlashMap(request));
	}

	protected String getViewName(HttpServletRequest request) {
		if (request == null) {
			return getViewName();
		}
		return request.getPathInfo();
	}
}
