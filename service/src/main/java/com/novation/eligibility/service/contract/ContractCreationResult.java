package com.novation.eligibility.service.contract;

import com.novation.eligibility.dto.dtos.ContractDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class ContractCreationResult extends DataTransferObject {

	public enum Cause {
		NAME_EXISTS, REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
	}

	public Cause cause;
	public ContractDto contract;
	
	public Cause getCause() {
		return cause;
	}
	
	public ContractCreationResult setCause(Cause reason) {
		this.cause = reason;
		return this;
	}
	
	public ContractDto getContract() {
		return contract;
	}
	
	public ContractCreationResult setContract(ContractDto contract) {
		this.contract = contract;
		return this;
	}
}
