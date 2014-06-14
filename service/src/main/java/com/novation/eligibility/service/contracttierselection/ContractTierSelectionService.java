package com.novation.eligibility.service.contracttierselection;

import com.novation.eligibility.dto.dtos.ContractTierSelectionDto;
import com.novation.eligibility.service.response.Response;

public interface ContractTierSelectionService {
	
	Response<ContractTierSelectionCreationResult> createContractTierSelection(ContractTierSelectionDto contractTierSelection);
}
