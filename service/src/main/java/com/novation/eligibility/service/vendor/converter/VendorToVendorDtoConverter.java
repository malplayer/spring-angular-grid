package com.novation.eligibility.service.vendor.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.Contract;
import com.novation.eligibility.domain.model.ContractExecution;
import com.novation.eligibility.domain.model.Vendor;
import com.novation.eligibility.dto.dtos.VendorDto;

public class VendorToVendorDtoConverter implements Converter<Vendor, VendorDto> {
	
	@Override
	public VendorDto convert(Vendor s) {
		return convert(s, new VendorDto());
	}

	public VendorDto convert(Vendor s, VendorDto t) {
		if (t == null) {
			t = new VendorDto();
		}
		
		t.setId(s.getId());
		t.setName(s.getName());
		t.setVendorId(s.getVendorId());
		t.setOrganizationId(s.getOrganization().getId());

		for (ContractExecution ce : s.getContractExecutions()) {
			t.addContractExecutionId(ce.getId());
		}
		
		for (Contract c : s.getPossibleDistributionContracts()) {
			t.addPossibleDistributionContractId(c.getId());
		}
		
		for (Contract c : s.getContracts()) {
			t.addContractId(c.getId());
		}

		return t;
	}
}
