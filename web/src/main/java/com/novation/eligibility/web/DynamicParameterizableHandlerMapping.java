package com.novation.eligibility.web;

import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;

public class DynamicParameterizableHandlerMapping extends
		AbstractUrlHandlerMapping {

	public static final String DEFAULT_URL_PATH = "/*";

	protected Controller controller = new DynamicParameterizableViewController();

	protected String urlPath = DEFAULT_URL_PATH;

	public DynamicParameterizableHandlerMapping() {
		registerHandler();
	}

	protected void registerHandler() {
		registerHandler(getUrlPath(), getController());
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
}
