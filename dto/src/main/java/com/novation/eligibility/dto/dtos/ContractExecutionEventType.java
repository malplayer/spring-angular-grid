package com.novation.eligibility.dto.dtos;

public enum ContractExecutionEventType {

	MEMBER_INITIAL_REQUEST("Member Initial Request"),
	SUPPLIER_APPROVAL("Supplier Approval"),
	SUPPLIER_APPROVAL_REQUEST("Supplier Approval Request"),
	SUPPLIER_TIER_CHANGE("Supplier Tier Change"),
	MEMBER_ACCEPTANCE_NEW_TIER("Member Acceptance New Tier"),
	DISTRIBUTOR_CONFIRMATION_REQUEST("Distributor Confirmation Request"),
	SUPPLIER_DENY_REQUEST("Supplier Deny Request"),
	SUPPLIER_BULK_REQUEST("Supplier Bulk Request"),
	HARD_COPY_INITIAL_REQUEST("Hard Copy Initial Request");
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	private ContractExecutionEventType(String value) {
		this.value = value;
	}

}
