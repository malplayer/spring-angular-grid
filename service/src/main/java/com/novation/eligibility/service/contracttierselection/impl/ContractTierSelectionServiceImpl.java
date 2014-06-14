package com.novation.eligibility.service.contracttierselection.impl;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novation.eligibility.domain.model.ContractTierSelection;
import com.novation.eligibility.domain.repo.ContractTierSelectionRepository;
import com.novation.eligibility.dto.dtos.ContractTierSelectionDto;
import com.novation.eligibility.service.AbstractService;
import com.novation.eligibility.service.contracttierselection.ContractTierSelectionCreationResult;
import com.novation.eligibility.service.contracttierselection.ContractTierSelectionService;
import com.novation.eligibility.service.contracttierselection.converter.ContractTierSelectionToContractTierSelectionDtoConverter;
import com.novation.eligibility.service.response.Response;

@Service
@Transactional(readOnly = true)
public class ContractTierSelectionServiceImpl extends AbstractService implements ContractTierSelectionService {

	@Inject
	protected ContractTierSelectionRepository repo;
	
	@Inject
	protected Converter<ContractTierSelectionDto, ContractTierSelection> ctsWriteConverter;
	protected Converter<ContractTierSelection, ContractTierSelectionDto> ctsReadConverter = new ContractTierSelectionToContractTierSelectionDtoConverter();
	
	@Transactional(readOnly = false)
	@Override
	public Response<ContractTierSelectionCreationResult> createContractTierSelection(
			ContractTierSelectionDto contractTierSelection) {
		// TODO Auto-generated method stub
		return null;
	}

}
