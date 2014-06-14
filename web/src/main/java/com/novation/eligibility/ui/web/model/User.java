package com.novation.eligibility.ui.web.model;

public class User {
	public static final String MEMBER_ROLE = "role_member";
	public static final String SUPPLIER_ROLE = "role_supplier";
	public static final String ADMIN_ROLE = "role_admin";
	public static final String MEMBER_PROXY_ROLE = "role_memberproxy";

	
	private String userName;
	private String role;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", role=" + role + "]";
	}
	
	

	
}
