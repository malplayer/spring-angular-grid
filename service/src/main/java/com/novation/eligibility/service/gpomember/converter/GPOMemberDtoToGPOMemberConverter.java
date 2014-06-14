package com.novation.eligibility.service.gpomember.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.GPOMember;
import com.novation.eligibility.domain.repo.ContractExecutionRepository;
import com.novation.eligibility.domain.repo.BuyingCompanyRepository;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.dto.dtos.GPOMemberDto;

@Component
public class GPOMemberDtoToGPOMemberConverter implements Converter<GPOMemberDto, GPOMember> {

	@Inject
	OrganizationRepository orgRepo;
	
	@Inject
	ContractExecutionRepository contractExecutionRepo;
	
	@Inject
	BuyingCompanyRepository buyingCompanyRepo;
	
	@Override
	public GPOMember convert(GPOMemberDto s) {
		return convert(s, new GPOMember());
	}
	
	public GPOMember convert(GPOMemberDto s, GPOMember t) {
		if (t == null) {
			t = new GPOMember();
		}
		
		t.setMemberNumber(s.getMemberNumber());
		t.setOrganization(orgRepo.findOne(s.getOrganizationId()));
		
		for (String id : s.getPrimaryContractExecutionIds()) {
			t.addPrimaryContractExecution(contractExecutionRepo.findOne(id));
		}
		
		for (String id : s.getBuyingCompanyIds()) {
			t.addMembership(buyingCompanyRepo.findOne(id));
		}
		
		for (String id : s.getContractExecutionIds()) {
			t.addContractExecution(contractExecutionRepo.findOne(id));
		}
		
		return t;
	}

}
