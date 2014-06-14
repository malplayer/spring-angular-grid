package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Program extends BaseEntity {

	protected String name;
	
	protected String description;
	
	@NotNull
	@Column(nullable = false)
	protected ProgramType programType;
	
	protected String notes;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "programs")
	protected List<Tier> tiers = new ArrayList<Tier>();
	
	@ManyToOne(optional = false)
	protected Contract contract;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
	protected List<ContractTierSelection> selections = new ArrayList<ContractTierSelection>();
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tiers) {
		this.tiers = tiers;
	}

	public void addTier(Tier tier) {
		tiers.add(tier);
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

	public ProgramType getProgramType() {
		return programType;
	}

	public void setProgramType(ProgramType programType) {
		this.programType = programType;
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
