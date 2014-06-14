package com.novation.eligibility.service.memberalliance;

import com.novation.eligibility.dto.dtos.MemberAllianceDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class MemberAllianceCreationResult extends DataTransferObject {

	public enum Cause {
		NAME_EXISTS, REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
	}

	public Cause cause;
	public MemberAllianceDto memberAlliance;
	
	public Cause getCause() {
		return cause;
	}
	
	public MemberAllianceCreationResult setCause(Cause reason) {
		this.cause = reason;
		return this;
	}
	
	public MemberAllianceDto getMemberAlliance() {
		return memberAlliance;
	}
	
	public MemberAllianceCreationResult setMemberAlliance(MemberAllianceDto memberAlliance) {
		this.memberAlliance = memberAlliance;
		return this;
	}
}
