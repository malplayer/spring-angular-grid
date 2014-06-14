package com.novation.eligibility.service.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUser extends User {

	private static final long serialVersionUID = 1L;
	public String email;
	public String id;
	public String salt;

	public AppUser(String id, String username, String password, String salt,
			Collection<? extends GrantedAuthority> authorities, String email) {

		super(username, password, authorities);

		this.salt = salt;
		this.email = email;
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
