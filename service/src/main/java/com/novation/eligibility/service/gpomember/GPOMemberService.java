package com.novation.eligibility.service.gpomember;

import com.novation.eligibility.dto.dtos.GPOMemberDto;
import com.novation.eligibility.service.response.Response;

public interface GPOMemberService {

	Response<GPOMemberDto> findGPOMemberByMemberNumber(String memberNumber);
	
	Response<GPOMemberCreationResult> createGPOMember(GPOMemberDto dto);
}
