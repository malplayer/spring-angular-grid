package com.novation.eligibility.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ContractTierSelection extends BaseEntity {

	@NotNull
	@Column(nullable = false)	
	protected SelectionType selectionType;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ContractExecution_id")
	protected ContractExecution request;
	
	@ManyToOne(optional = true)
	protected Tier tier;
	
	@ManyToOne(optional = true)
	protected Program program;
	
	public SelectionType getSelectionType() {
		return selectionType;
	}

	public void setSelectionType(SelectionType selectionType) {
		this.selectionType = selectionType;
	}

	public ContractExecution getRequest() {
		return request;
	}

	public void setRequest(ContractExecution request) {
		this.request = request;
	}

	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

}
