package com.novation.eligibility.dto.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.novation.eligibility.dto.general.DataTransferObject;

public class EligibilityRequestDto extends DataTransferObject  {

	private static final long serialVersionUID = 1L;

	private Collection<String> contractTierSelectionIds = new ArrayList<String>();
	private String gpoMemberId;
	private String vendorId; // preferredDistributor
	
	public String getGpoMemberId() {
		return gpoMemberId;
	}
	public void setGpoMemberId(String gpoMemberId) {
		this.gpoMemberId = gpoMemberId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public Collection<String> getContractTierSelectionIds() {
		return contractTierSelectionIds;
	}
	public void setContractTierSelectionIds(
			Collection<String> contractTierSelectionIds) {
		this.contractTierSelectionIds = contractTierSelectionIds;
	}
	
}
