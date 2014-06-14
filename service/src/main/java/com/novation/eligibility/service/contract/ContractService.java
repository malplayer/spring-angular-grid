package com.novation.eligibility.service.contract;

import java.util.Date;

import com.novation.eligibility.dto.dtos.ContractDto;
import com.novation.eligibility.service.response.Response;

public interface ContractService {

	Response<ContractDto> findContractByName(String name);
	
	Response<ContractDto> findContractByEffectiveDate(Date effectiveDate);
	
	Response<ContractCreationResult> createContract(ContractDto dto);
	
}
