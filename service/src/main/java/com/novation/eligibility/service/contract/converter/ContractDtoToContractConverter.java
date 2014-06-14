package com.novation.eligibility.service.contract.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.Contract;
import com.novation.eligibility.domain.repo.MemberAllianceRepository;
import com.novation.eligibility.domain.repo.ProgramRepository;
import com.novation.eligibility.domain.repo.TierRepository;
import com.novation.eligibility.domain.repo.VendorRepository;
import com.novation.eligibility.dto.dtos.ContractDto;

@Component
public class ContractDtoToContractConverter implements Converter<ContractDto, Contract> {

	@Inject
	protected TierRepository tierRepo;
	
	@Inject
	protected VendorRepository vendorRepo;
	
	@Inject
	protected ProgramRepository programRepo;
	
	@Inject
	protected MemberAllianceRepository memberAllianceRepo;
	
	@Override
	public Contract convert(ContractDto s) {
		return convert(s, new Contract());
	}
	
	public Contract convert(ContractDto s, Contract t) {
		if (t == null) {
			t = new Contract();
		}
		
		t.setName(s.getName());
		t.setEffectiveDate(s.getEffectiveDate());
		
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		if (s.getExpirationDate() != null) t.setExpirationDate(s.getExpirationDate());
		
		for (String id : s.getTierIds()) {
			t.addTier(tierRepo.findOne(id));
		}
		
		for (String id : s.getVendorIds()) {
			t.addPossibleDistributor(vendorRepo.findOne(id));
		}
		
		t.setSupplier(vendorRepo.findOne(s.getSupplierVendorId()));
		
		for (String id : s.getProgramIds()) {
			t.addProgram(programRepo.findOne(id));
		}
		
		for (String id : s.getMemberAllianceIds()) {
			t.addExclusiveForAlliance(memberAllianceRepo.findOne(id));
		}
		
		return t;
	}
}
