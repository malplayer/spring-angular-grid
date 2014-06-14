package com.novation.eligibility.service.contracttierselection;

import com.novation.eligibility.dto.dtos.ContractTierSelectionDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class ContractTierSelectionCreationResult extends DataTransferObject {

	public enum Cause {
		REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
	}

	public Cause cause;
	public ContractTierSelectionDto contractTierSelection;
	
	public Cause getCause() {
		return cause;
	}
	public void setCause(Cause cause) {
		this.cause = cause;
	}
	public ContractTierSelectionDto getContractTierSelection() {
		return contractTierSelection;
	}
	public void setContractTierSelection(
			ContractTierSelectionDto contractTierSelection) {
		this.contractTierSelection = contractTierSelection;
	}	
}
