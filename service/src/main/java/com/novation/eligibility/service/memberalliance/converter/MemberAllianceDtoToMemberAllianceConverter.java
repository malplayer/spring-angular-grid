package com.novation.eligibility.service.memberalliance.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.MemberAlliance;
import com.novation.eligibility.domain.repo.ContractRepository;
import com.novation.eligibility.domain.repo.BuyingCompanyRepository;
import com.novation.eligibility.dto.dtos.MemberAllianceDto;

@Component
public class MemberAllianceDtoToMemberAllianceConverter implements Converter<MemberAllianceDto, MemberAlliance> {

	@Inject
	ContractRepository contractRepo;
	
	@Inject
	BuyingCompanyRepository buyingCompanyRepo;
	
	@Override
	public MemberAlliance convert(MemberAllianceDto s) {
		return convert(s, new MemberAlliance());
	}

	public MemberAlliance convert(MemberAllianceDto s, MemberAlliance t) {
		if (t == null) {
			t = new MemberAlliance();
		}
		
		t.setName(s.getName());
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		
		for (String id : s.getContractIds()) {
			t.addExclusiveContract(contractRepo.findOne(id));
		}
		
		for (String id : s.getBuyingCompanyIds()) {
			t.addMembership(buyingCompanyRepo.findOne(id));
		}
		
		return t;
	}
}
