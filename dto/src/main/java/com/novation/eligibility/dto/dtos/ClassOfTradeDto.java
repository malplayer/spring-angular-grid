package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ClassOfTradeDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private Collection<String> tierIds = new ArrayList<String>();
	private Collection<String> buyingCompanyIds = new ArrayList<String>();
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Collection<String> getTierIds() {
		return tierIds;
	}
	public void setTierIds(Collection<String> tierIds) {
		this.tierIds = tierIds;
	}
	public void addTierId(String id) {
		tierIds.add(id);
	}
	public Collection<String> getBuyingCompanyIds() {
		return buyingCompanyIds;
	}
	public void setBuyingCompanyIds(
			Collection<String> buyingCompanyIds) {
		this.buyingCompanyIds = buyingCompanyIds;
	}
	public void addBuyingCompanyId(String id) {
		buyingCompanyIds.add(id);
	}
	
}
