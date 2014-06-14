package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class VendorDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String vendorId;
	private String organizationId;
	private Collection<String> contractExecutionIds = new ArrayList<String>(); // contractExecutions
	private Collection<String> possibleDistributionContractIds = new ArrayList<String>();
	private Collection<String> contractIds = new ArrayList<String>();
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Collection<String> getContractExecutionIds() {
		return contractExecutionIds;
	}
	public void setContractExecutionIds(
			Collection<String> contractExecutionIds) {
		this.contractExecutionIds = contractExecutionIds;
	}
	public void addContractExecutionId(String id) {
		this.contractExecutionIds.add(id);
	}
	public Collection<String> getPossibleDistributionContractIds() {
		return possibleDistributionContractIds;
	}
	public void setPossibleDistributionContractIds(
			Collection<String> possibleDistributionContractIds) {
		this.possibleDistributionContractIds = possibleDistributionContractIds;
	}
	public void addPossibleDistributionContractId(String id) {
		this.possibleDistributionContractIds.add(id);
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
