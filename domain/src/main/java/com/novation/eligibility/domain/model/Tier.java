package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tier extends BaseEntity {

	protected String name;
	
	protected String description;
	
	protected String levelDescription;
	
	@ManyToOne(optional = false)
	protected Contract contract;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tier")
	protected List<ContractTierSelection> selections = new ArrayList<ContractTierSelection>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "tiers")
	protected List<ClassOfTrade> eligibleClassOfTrades = new ArrayList<ClassOfTrade>();
	
	@ManyToMany
	@JoinTable(name = "TIER_PROGRAMS",
		joinColumns = @JoinColumn(name = "tier_id"),
		inverseJoinColumns = @JoinColumn(name = "program_id"))
	protected List<Program> programs = new ArrayList<Program>();
	

	public List<ClassOfTrade> getEligibleClassOfTrades() {
		return eligibleClassOfTrades;
	}

	public void setEligibleClassOfTrades(List<ClassOfTrade> eligibleClassOfTrades) {
		this.eligibleClassOfTrades = eligibleClassOfTrades;
	}
	
	public void addEligibleClassOfTrade(ClassOfTrade eligibleCOT) {
		eligibleClassOfTrades.add(eligibleCOT);
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
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

	public String getLevelDescription() {
		return levelDescription;
	}

	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<ContractTierSelection> getSelections() {
		return selections;
	}

	public void setSelections(List<ContractTierSelection> selections) {
		this.selections = selections;
	}

}
