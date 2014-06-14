package com.novation.eligibility.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ClassOfTrade extends BaseEntity {

	protected String name;
	
	protected String description;
	
	@ManyToMany
	@JoinTable(name = "CLASS_OF_TRADE_ELIGIBLE_TIERS",
		joinColumns = @JoinColumn(name = "class_of_trade_id"),
		inverseJoinColumns = @JoinColumn(name = "eligible_tier_id"))
	protected List<Tier> tiers = new ArrayList<Tier>();
	
	@ManyToMany
	@JoinTable(name = "CLASS_OF_TRADE_BUYING_COMPANY",
		joinColumns = @JoinColumn(name = "class_of_trade_id"),
		inverseJoinColumns = @JoinColumn(name = "buying_company_id"))
	protected List<BuyingCompany> alliances = new ArrayList<BuyingCompany>();
	
	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tiers) {
		this.tiers = tiers;
	}
	
	public void addTier(Tier tier) {
		tiers.add(tier);
	}

	public List<BuyingCompany> getAlliances() {
		return alliances;
	}

	public void setAlliances(List<BuyingCompany> alliances) {
		this.alliances = alliances;
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
