package com.novation.eligibility.dto.dtos;

public enum SelectionType {

	REQUESTED("Requested"),
	COUNTERED("Countered"),
	ACCEPTED("Accepted");
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	private SelectionType(String value) {
		this.value = value;
	}
}
