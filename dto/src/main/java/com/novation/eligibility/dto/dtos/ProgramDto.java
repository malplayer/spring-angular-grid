package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ProgramDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String description;
	private String notes;
	private Collection<String> tierIds = new ArrayList<String>();
	private String contractId;
	private Collection<String> contractTierSelectionIds = new ArrayList<String>(); // selections	
	
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	
}
