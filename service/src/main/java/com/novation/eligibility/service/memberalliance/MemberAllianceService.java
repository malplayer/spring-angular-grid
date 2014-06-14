package com.novation.eligibility.service.memberalliance;

import com.novation.eligibility.dto.dtos.MemberAllianceDto;
import com.novation.eligibility.service.response.Response;

public interface MemberAllianceService {

	Response<MemberAllianceDto> findMemberAllianceByName(String name);
	
	Response<MemberAllianceCreationResult> createMemberAlliance(MemberAllianceDto memberAlliance);
	
}
