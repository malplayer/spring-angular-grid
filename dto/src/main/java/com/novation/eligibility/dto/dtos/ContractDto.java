package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ContractDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String description;
	private Date effectiveDate;
	private Date expirationDate;
	private Collection<String> tierIds = new ArrayList<String>();
	private Collection<String> vendorIds = new ArrayList<String>(); // possibleDistributors
	private String supplierVendorId;
	private Collection<String> programIds = new ArrayList<String>();
	private Collection<String> memberAllianceIds = new ArrayList<String>();
	
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
	public Collection<String> getVendorIds() {
		return vendorIds;
	}
	public void setVendorIds(Collection<String> vendorIds) {
		this.vendorIds = vendorIds;
	}
	public void addVendorId(String id) {
		vendorIds.add(id);
	}
	public String getSupplierVendorId() {
		return supplierVendorId;
	}
	public void setSupplierVendorId(String supplierVendorId) {
		this.supplierVendorId = supplierVendorId;
	}
	public Collection<String> getProgramIds() {
		return programIds;
	}
	public void setProgramIds(Collection<String> programIds) {
		this.programIds = programIds;
	}
	public void addProgramId(String id) {
		programIds.add(id);
	}
	public Collection<String> getMemberAllianceIds() {
		return memberAllianceIds;
	}
	public void setMemberAllianceIds(Collection<String> memberAllianceIds) {
		this.memberAllianceIds = memberAllianceIds;
	}
	public void addMemberAllianceId(String id) {
		memberAllianceIds.add(id);
	}
	
}
