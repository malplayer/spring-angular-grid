package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class MemberAllianceDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private Collection<String> buyingCompanyIds = new ArrayList<String>();
	private Collection<String> contractIds = new ArrayList<String>();
	
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
	public Collection<String> getContractIds() {
		return contractIds;
	}
	public void setContractIds(Collection<String> contractIds) {
		this.contractIds = contractIds;
	}
	public void addContractId(String id) {
		contractIds.add(id);
	}
	
}
