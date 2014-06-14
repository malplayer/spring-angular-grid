package com.novation.eligibility.ui.web.model;

import java.util.List;

public class Price {

	private Integer id;
	private String name;
	private List<Tier> tiers;

	public Price() {
	}

	public Price(String name, List<Tier> tier) {
		super();
		this.name = name;
		this.tiers = tier;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tier) {
		this.tiers = tier;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", name=" + name + ", tiers=" + tiers + "]";
	}



}
