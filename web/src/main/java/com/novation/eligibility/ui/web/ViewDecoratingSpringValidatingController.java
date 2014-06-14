package com.novation.eligibility.ui.web;

import javax.inject.Inject;
import javax.validation.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import com.novation.eligibility.support.AbstractLoggable;

public abstract class ViewDecoratingSpringValidatingController extends AbstractLoggable {

	public static final String DEFAULT_VIEW_PREFIX = "/view";
	public static final String DEFAULT_VIEW_SUFFIX = "";

	protected String viewPrefix = DEFAULT_VIEW_PREFIX;
	protected String viewSuffix = DEFAULT_VIEW_SUFFIX;
	protected SpringValidatorAdapter validator;

	public ViewDecoratingSpringValidatingController() {
	}

	public ViewDecoratingSpringValidatingController(String viewPrefix) {
		this(viewPrefix, null);
	}

	public ViewDecoratingSpringValidatingController(String viewPrefix,
			String viewSuffix) {
		setViewPrefix(viewPrefix);
		setViewSuffix(viewSuffix);
	}

	@Inject
	public void setValidator(Validator validator) {
		this.validator = (validator instanceof SpringValidatorAdapter) ? (SpringValidatorAdapter) validator
				: new SpringValidatorAdapter(validator);
	}

	public SpringValidatorAdapter getValidator() {
		return validator;
	}

	protected void validate(Object target, Errors errors, Object... hints) {
		validator.validate(target, errors, hints);
	}

	protected String decorateViewName(String viewName) {
		return decorateViewName(viewName, false);
	}

	protected String decorateViewName(String viewName, boolean redirect) {
		String s = viewPrefix + viewName + viewSuffix;
		return redirect ? redirect(s) : s;
	}

	protected String redirect(String url) {
		return "redirect:" + url;
	}

	public String getViewPrefix() {
		return viewPrefix;
	}

	public void setViewPrefix(String viewPrefix) {
		this.viewPrefix = viewPrefix == null || viewPrefix.trim().length() == 0 ? ""
				: viewPrefix;
	}

	public String getViewSuffix() {
		return viewSuffix;
	}

	public void setViewSuffix(String viewSuffix) {
		this.viewSuffix = viewSuffix == null || viewSuffix.trim().length() == 0 ? ""
				: viewSuffix;
	}
}
