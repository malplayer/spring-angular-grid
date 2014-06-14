package com.novation.eligibility.ui.web.controller.registration;

import javax.validation.constraints.Size;

import org.apache.bval.constraints.Email;
import org.springframework.validation.Errors;

public class Registration {

	@Size(min = 1, max = 128)
	protected String username;

	@Size(min = 4, max = 128)
	protected String password;

	protected String password2;

	@Email
	protected String primaryEmail;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String email) {
		this.primaryEmail = email;
	}

	public Errors validateGlobally(Errors errs) {
		password = password == null ? "" : password.trim();
		password2 = password2 == null ? "" : password2.trim();

		if (!password.equals(password2)) {
			errs.reject("passwords.differ");
		}

		return errs;
	}
}
