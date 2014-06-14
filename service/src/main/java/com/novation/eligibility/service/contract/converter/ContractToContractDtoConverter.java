package com.novation.eligibility.service.contract.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.Contract;
import com.novation.eligibility.domain.model.MemberAlliance;
import com.novation.eligibility.domain.model.Program;
import com.novation.eligibility.domain.model.Tier;
import com.novation.eligibility.domain.model.Vendor;
import com.novation.eligibility.dto.dtos.ContractDto;

public class ContractToContractDtoConverter implements Converter<Contract, ContractDto> {

	@Override
	public ContractDto convert(Contract s) {
		return convert(s, new ContractDto());
	}
	
	public ContractDto convert(Contract s, ContractDto t) {
		if (t == null) {
			t = new ContractDto();
		}
		
		t.setId(s.getId());
		t.setName(s.getName());
		t.setEffectiveDate(s.getEffectiveDate());
		
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		if (s.getExpirationDate() != null) t.setExpirationDate(s.getExpirationDate());

		for (Tier tier : s.getTiers()) {
			t.addTierId(tier.getId());
		}
		
		for (Vendor v : s.getPossibleDistributors()) {
			t.addVendorId(v.getId());
		}
		
		t.setSupplierVendorId(s.getSupplier().getId());
		
		for (Program p : s.getPrograms()) {
			t.addProgramId(p.getId());
		}
		
		for (MemberAlliance m : s.getExclusiveForAlliances()) {
			t.addMemberAllianceId(m.getId());
		}
		
		return t;
	}
}
