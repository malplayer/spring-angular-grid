package com.novation.eligibility.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ContractTierExecutionEvent extends BaseEntity {

	@NotNull
	@Column(nullable = false)
	protected ContractExecutionEventType eventType;
	
	@NotNull
	@Column(nullable = false)
	protected Date whenOccurred;
	
	@ManyToOne(optional = false)	
	protected Party initiator;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ContractExecution_id")
	protected ContractExecution owner;
	
	public Date getWhenOccurred() {
		return whenOccurred;
	}

	public void setWhenPerformed(Date whenOccurred) {
		this.whenOccurred = whenOccurred;
	}

	public Party getInitiator() {
		return initiator;
	}

	public void setInitiator(Party initiator) {
		this.initiator = initiator;
	}
	
	public ContractExecutionEventType getEventType() {
		return eventType;
	}

	public void setEventType(ContractExecutionEventType eventType) {
		this.eventType = eventType;
	}

	public void setWhenOccurred(Date whenOccurred) {
		this.whenOccurred = whenOccurred;
	}

	public ContractExecution getOwner() {
		return owner;
	}

	public void setOwner(ContractExecution owner) {
		this.owner = owner;
	}

}
