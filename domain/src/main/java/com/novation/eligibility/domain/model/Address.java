package com.novation.eligibility.domain.model;

import javax.persistence.Entity;

@Entity
public final class Address extends BaseEntity {

	public Address() {
	}

	public Address(String line1, String line2, String city, String state,
			String zipCode) {

		setLine1(line1);
		setLine2(line2);
		setCity(city);
		setState(state);
		setPostalCode(zipCode);
	}

	protected String line1;
	protected String line2;
	protected String city;
	protected String state;
	protected String postalCode;

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String zipCode) {
		this.postalCode = zipCode;
	}

}
