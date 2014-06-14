package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class GPOMember extends BaseEntity {

	@NotNull
	@Column(nullable = false)
	protected String memberNumber;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "gpoMember")
	protected List<BuyingCompany> memberships = new ArrayList<BuyingCompany>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "requestingMember")
	protected List<ContractExecution> primaryContractExecutions = new ArrayList<ContractExecution>();

	@OneToOne(optional = false)
	protected Organization organization;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "gpoMembers")
	protected List<ContractExecution> contractExecutions = new ArrayList<ContractExecution>(); 
	
	public List<BuyingCompany> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<BuyingCompany> memberships) {
		this.memberships = memberships;
	}
	
	public void addMembership(BuyingCompany membership) {
		memberships.add(membership);
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public List<ContractExecution> getPrimaryContractExecutions() {
		return primaryContractExecutions;
	}

	public void setPrimaryContractExecutions(List<ContractExecution> contractExecutions) {
		this.primaryContractExecutions = contractExecutions;
	}

	public void addPrimaryContractExecution(ContractExecution contractExecution) {
		primaryContractExecutions.add(contractExecution);
	}
	
	public List<ContractExecution> getContractExecutions() {
		return contractExecutions;
	}

	public void setContractExecutions(List<ContractExecution> contractExecutions) {
		this.contractExecutions = contractExecutions;
	}

	public void addContractExecution(ContractExecution contractExecution) {
		this.contractExecutions.add(contractExecution);
	}

}
