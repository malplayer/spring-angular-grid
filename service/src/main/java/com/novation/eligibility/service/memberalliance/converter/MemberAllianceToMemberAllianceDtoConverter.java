package com.novation.eligibility.service.memberalliance.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.Contract;
import com.novation.eligibility.domain.model.MemberAlliance;
import com.novation.eligibility.domain.model.BuyingCompany;
import com.novation.eligibility.dto.dtos.MemberAllianceDto;

public class MemberAllianceToMemberAllianceDtoConverter implements Converter<MemberAlliance, MemberAllianceDto> {

	@Override
	public MemberAllianceDto convert(MemberAlliance s) {
		return convert(s, new MemberAllianceDto());
	}
	
	public MemberAllianceDto convert(MemberAlliance s, MemberAllianceDto t) {
		if (t == null) {
			t = new MemberAllianceDto();
		}
		
		t.setId(s.getId());
		t.setName(s.getName());
		t.setDescription(s.getDescription());
		
		for (Contract c : s.getExclusiveContracts()) {
			t.addContractId(c.getId());
		}
		
		for (BuyingCompany m : s.getMemberships()) {
			t.addBuyingCompanyId(m.getId());
		}
		
		return t;
	}

}
