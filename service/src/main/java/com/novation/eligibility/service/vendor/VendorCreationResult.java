package com.novation.eligibility.service.vendor;

import com.novation.eligibility.dto.dtos.VendorDto;
import com.novation.eligibility.dto.general.DataTransferObject;

@SuppressWarnings("serial")
public class VendorCreationResult extends DataTransferObject {

	public enum Cause {
		NAME_EXISTS, REPO_FAILURE, UNHANDLED_EXCEPTION, DOMAIN_EXCEPTION, MARSHALLING_EXCEPTION
	}

	public Cause cause;
	public VendorDto vendor;
	
	public Cause getCause() {
		return cause;
	}
	
	public VendorCreationResult setCause(Cause reason) {
		this.cause = reason;
		return this;
	}
	
	public VendorDto getVendor() {
		return vendor;
	}
	
	public VendorCreationResult setVendor(VendorDto vendor) {
		this.vendor = vendor;
		return this;
	}
}
