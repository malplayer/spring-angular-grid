package com.novation.eligibility.service.eligibility;

import com.novation.eligibility.dto.dtos.ContractExecutionDto;
import com.novation.eligibility.dto.dtos.EligibilityRequestDto;
import com.novation.eligibility.service.response.Response;

public interface EligibilityService {

	Response<ContractExecutionDto> requestEligibility(EligibilityRequestDto request);
	
	Response<ContractExecutionDto> queryStatus(String contractExecutionId);
}
