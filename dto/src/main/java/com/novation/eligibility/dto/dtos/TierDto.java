package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class TierDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String levelDescription;
	private String contractId;
	private Collection<String> contractTierSelectionIds = new ArrayList<String>(); // selections
	private Collection<String> classOfTradeIds = new ArrayList<String>(); // eligibleClassOfTrades
	private Collection<String> programIds = new ArrayList<String>(); // programs
	
	public String getLevelDescription() {
		return levelDescription;
	}
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Collection<String> getClassOfTradeIds() {
		return classOfTradeIds;
	}
	public void setClassOfTradeIds(Collection<String> classOfTradeIds) {
		this.classOfTradeIds = classOfTradeIds;
	}
	public void addClassOfTradeId(String id) {
		classOfTradeIds.add(id);
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

}
