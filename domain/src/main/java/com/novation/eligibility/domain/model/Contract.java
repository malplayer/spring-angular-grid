package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Contract extends BaseEntity {

	protected String name;
	
	protected String description;
	
	@NotNull
	@Column(nullable = false)	
	protected Date effectiveDate;
	
	protected Date expirationDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
	protected List<Tier> tiers = new ArrayList<Tier>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "possibleDistributionContracts")
	protected List<Vendor> possibleDistributors = new ArrayList<Vendor>();
	
	@ManyToOne(optional = false)
	protected Vendor supplier;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
	protected List<Program> programs = new ArrayList<Program>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CONTRACT_MEMBER_ALLIANCES",
		joinColumns = @JoinColumn(name = "contract_id"),
		inverseJoinColumns = @JoinColumn(name = "member_alliance_id"))
	protected List<MemberAlliance> exclusiveForAlliances = new ArrayList<MemberAlliance>();
	
	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tiers) {
		this.tiers = tiers;
	}
	
	public void addTier(Tier tier) {
		tiers.add(tier);
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public List<Vendor> getPossibleDistributors() {
		return possibleDistributors;
	}

	public void setPossibleDistributors(List<Vendor> possibleDistributors) {
		this.possibleDistributors = possibleDistributors;
	}
	
	public void addPossibleDistributor(Vendor possibleDistributor) {
		this.possibleDistributors.add(possibleDistributor);
	}

	public Vendor getSupplier() {
		return supplier;
	}

	public void setSupplier(Vendor supplier) {
		this.supplier = supplier;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}
	
	public void addProgram(Program program) {
		this.programs.add(program);
	}

	public List<MemberAlliance> getExclusiveForAlliances() {
		return exclusiveForAlliances;
	}

	public void setExclusiveForAlliances(List<MemberAlliance> exclusiveForAlliances) {
		this.exclusiveForAlliances = exclusiveForAlliances;
	}
	
	public void addExclusiveForAlliance(MemberAlliance exclusiveForAlliance) {
		this.exclusiveForAlliances.add(exclusiveForAlliance);
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
