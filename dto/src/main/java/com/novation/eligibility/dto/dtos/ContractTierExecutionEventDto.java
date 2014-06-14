package com.novation.eligibility.dto.dtos;

import java.util.Date;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ContractTierExecutionEventDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private ContractExecutionEventType eventType;
	private Date whenOccurred;
	private String partyId; // initiator
	private String contractExecutionId; // owner
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ContractExecutionEventType getEventType() {
		return eventType;
	}
	public void setEventType(ContractExecutionEventType eventType) {
		this.eventType = eventType;
	}
	public Date getWhenOccurred() {
		return whenOccurred;
	}
	public void setWhenOccurred(Date whenOccurred) {
		this.whenOccurred = whenOccurred;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getContractExecutionId() {
		return contractExecutionId;
	}
	public void setContractExecutionId(String contractExecutionId) {
		this.contractExecutionId = contractExecutionId;
	}
}
