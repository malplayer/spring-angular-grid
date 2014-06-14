package com.novation.eligibility.service.gpomember.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.ContractExecution;
import com.novation.eligibility.domain.model.BuyingCompany;
import com.novation.eligibility.domain.model.GPOMember;
import com.novation.eligibility.dto.dtos.GPOMemberDto;

public class GPOMemberToGPOMemberDtoConverter implements
		Converter<GPOMember, GPOMemberDto> {

	@Override
	public GPOMemberDto convert(GPOMember s) {
		return convert(s, new GPOMemberDto());
	}
	
	public GPOMemberDto convert(GPOMember s, GPOMemberDto t) {
		if (t == null) {
			t = new GPOMemberDto();
		}
		
		t.setId(s.getId());
		t.setMemberNumber(s.getMemberNumber());
		t.setOrganizationId(s.getOrganization().getId());
		
		for (BuyingCompany m : s.getMemberships()) {
			t.addBuyingCompanyId(m.getId());
		}
		
		for (ContractExecution c : s.getPrimaryContractExecutions()) {
			t.addPrimaryContractExecutionId(c.getId());
		}
		
		for (ContractExecution c : s.getContractExecutions()) {
			t.addContractExecutionId(c.getId());
		}
		
		return t;
	}

}
