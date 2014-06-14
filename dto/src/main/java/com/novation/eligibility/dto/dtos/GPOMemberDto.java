package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class GPOMemberDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String organizationId;
	private String memberNumber;
	private Collection<String> buyingCompanyIds = new ArrayList<String>();
	private Collection<String> primaryContractExecutionIds = new ArrayList<String>();
	private Collection<String> contractExecutionIds = new ArrayList<String>();
	
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
	public Collection<String> getContractExecutionIds() {
		return contractExecutionIds;
	}
	public void setContractExecutionIds(
			Collection<String> contractExecutionIds) {
		this.contractExecutionIds = contractExecutionIds;
	}
	public void addContractExecutionId(String id) {
		contractExecutionIds.add(id);
	}
	public Collection<String> getPrimaryContractExecutionIds() {
		return primaryContractExecutionIds;
	}
	public void setPrimaryContractExecutionIds(
			Collection<String> primaryContractExecutionIds) {
		this.primaryContractExecutionIds = primaryContractExecutionIds;
	}
	public void addPrimaryContractExecutionId(String id) {
		this.primaryContractExecutionIds.add(id);
	}
}
