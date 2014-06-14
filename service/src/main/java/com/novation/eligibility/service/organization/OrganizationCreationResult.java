package com.novation.eligibility.service.organization;

import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class OrganizationCreationResult extends DataTransferObject {

	public enum Cause {
		BAD_USERNAME, USERNAME_EXISTS, EMAIL_EXISTS, NAME_EXISTS, REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
	}

	public Cause cause;
	public OrganizationDto organization;
	
	public Cause getCause() {
		return cause;
	}
	
	public OrganizationCreationResult setCause(Cause reason) {
		this.cause = reason;
		return this;
	}
	
	public OrganizationDto getOrganization() {
		return organization;
	}
	
	public OrganizationCreationResult setOrganization(OrganizationDto organization) {
		this.organization = organization;
		return this;
	}
}
