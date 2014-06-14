package com.novation.eligibility.domain.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Individual extends Party {

	protected String firstName;

	protected String lastName;
	
	protected String middleName;

	public Individual() {}
	
	public Individual(@NotNull String username) {
        setUsername(username);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String givenName) {
		this.firstName = givenName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String surname) {
		this.lastName = surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

}
