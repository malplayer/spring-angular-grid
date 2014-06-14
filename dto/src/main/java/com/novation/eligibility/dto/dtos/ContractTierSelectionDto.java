package com.novation.eligibility.dto.dtos;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ContractTierSelectionDto extends DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private SelectionType selectionType;
	private String contractExecutionId; // request
	private String tierId;
	private String programId;

	public SelectionType getSelectionType() {
		return selectionType;
	}
	public void setSelectionType(SelectionType selectionType) {
		this.selectionType = selectionType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractExecutionId() {
		return contractExecutionId;
	}
	public void setContractExecutionId(String contractExecutionId) {
		this.contractExecutionId = contractExecutionId;
	}
	public String getTierId() {
		return tierId;
	}
	public void setTierId(String tierId) {
		this.tierId = tierId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
}
