package com.novation.eligibility.service.vendor.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.Vendor;
import com.novation.eligibility.domain.repo.ContractRepository;
import com.novation.eligibility.domain.repo.ContractExecutionRepository;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.dto.dtos.VendorDto;

@Component
public class VendorDtoToVendorConverter implements Converter<VendorDto, Vendor> {
	
	@Inject
	protected OrganizationRepository orgRepo;
	
	@Inject
	protected ContractExecutionRepository contractExecutionRepo;
	
	@Inject
	protected ContractRepository contractRepo;
	
	@Override
	public Vendor convert(VendorDto s) {
		return convert(s, new Vendor());
	}
	
	public Vendor convert(VendorDto s, Vendor t) {
		if (t == null) {
			t = new Vendor();
		}
		
		t.setName(s.getName());
		t.setVendorId(s.getVendorId());
		t.setOrganization(orgRepo.findOne(s.getOrganizationId()));		
		
		for (String id : s.getContractExecutionIds()) {
			t.addContractExecution(contractExecutionRepo.findOne(id));
		}
		
		for (String id : s.getPossibleDistributionContractIds()) {
			t.addPossibleDistributionContract(contractRepo.findOne(id));
		}
		
		for (String id : s.getContractIds()) {
			t.addContract(contractRepo.findOne(id));
		}
		
		return t;
	}

}
