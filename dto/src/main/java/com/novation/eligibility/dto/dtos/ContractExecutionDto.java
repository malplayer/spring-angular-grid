package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ContractExecutionDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private Date whenStarted;
	private Collection<String> contractTierExecutionEventIds = new ArrayList<String>(); // events
	private String gpoMemberId; // requestingMember
	private String vendorId; // selectedDistributor
	private Collection<String> contractTierSelectionIds = new ArrayList<String>(); // selections
	private Collection<String> gpoMemberIds = new ArrayList<String>(); 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getWhenStarted() {
		return whenStarted;
	}
	public void setWhenStarted(Date whenStarted) {
		this.whenStarted = whenStarted;
	}
	public Collection<String> getContractTierExecutionEventIds() {
		return contractTierExecutionEventIds;
	}
	public void setContractTierExecutionEventIds(
			Collection<String> contractTierExecutionEventIds) {
		this.contractTierExecutionEventIds = contractTierExecutionEventIds;
	}
	public void addContractTierExecutionEventId(String id) {
		contractTierExecutionEventIds.add(id);
	}
	public String getGpoMemberId() {
		return gpoMemberId;
	}
	public void setGpoMemberId(String gpoMemberId) {
		this.gpoMemberId = gpoMemberId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public Collection<String> getContractTierSelectionIds() {
		return contractTierSelectionIds;
	}
	public void setContractTierSelectionIds(
			Collection<String> contractTierSelectionIds) {
		this.contractTierSelectionIds = contractTierSelectionIds;
	}
	public void addContractTierSelectionId(String id) {
		contractTierSelectionIds.add(id);
	}
	public Collection<String> getGpoMemberIds() {
		return gpoMemberIds;
	}
	public void setGpoMemberIds(Collection<String> gpoMemberIds) {
		this.gpoMemberIds = gpoMemberIds;
	}
	public void addGpoMemberId(String id) {
		this.gpoMemberIds.add(id);
	}
}
