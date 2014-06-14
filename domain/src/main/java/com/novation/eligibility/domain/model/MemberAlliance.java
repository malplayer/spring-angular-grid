package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class MemberAlliance extends BaseEntity {

	protected String name;
	
	protected String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memberAlliance")
	protected List<BuyingCompany> memberships = new ArrayList<BuyingCompany>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "exclusiveForAlliances")
	protected List<Contract> exclusiveContracts = new ArrayList<Contract>();
	
	
	public List<BuyingCompany> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<BuyingCompany> memberships) {
		this.memberships = memberships;
	}

	public void addMembership(BuyingCompany membership) {
		memberships.add(membership);
	}

	public List<Contract> getExclusiveContracts() {
		return exclusiveContracts;
	}

	public void setExclusiveContracts(List<Contract> exclusiveContracts) {
		this.exclusiveContracts = exclusiveContracts;
	}

	public void addExclusiveContract(Contract contract) {
		exclusiveContracts.add(contract);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
