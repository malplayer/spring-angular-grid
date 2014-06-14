package com.novation.eligibility.service.gpomember;

import com.novation.eligibility.dto.dtos.GPOMemberDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class GPOMemberCreationResult extends DataTransferObject {

	public enum Cause {
		MEMBERNUMBER_EXISTS, REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
	}

	public Cause cause;
	public GPOMemberDto gpoMember;
	
	public Cause getCause() {
		return cause;
	}
	
	public GPOMemberCreationResult setCause(Cause reason) {
		this.cause = reason;
		return this;
	}
	
	public GPOMemberDto getGPOMember() {
		return gpoMember;
	}
	
	public GPOMemberCreationResult setGPOMember(GPOMemberDto gpoMember) {
		this.gpoMember = gpoMember;
		return this;
	}
}
