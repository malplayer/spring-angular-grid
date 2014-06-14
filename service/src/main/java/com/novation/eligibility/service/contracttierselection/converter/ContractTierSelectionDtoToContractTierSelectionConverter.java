package com.novation.eligibility.service.contracttierselection.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.ContractTierSelection;
import com.novation.eligibility.domain.model.SelectionType;
import com.novation.eligibility.domain.repo.ContractExecutionRepository;
import com.novation.eligibility.domain.repo.ProgramRepository;
import com.novation.eligibility.domain.repo.TierRepository;
import com.novation.eligibility.dto.dtos.ContractTierSelectionDto;

@Component
public class ContractTierSelectionDtoToContractTierSelectionConverter 
	implements Converter<ContractTierSelectionDto, ContractTierSelection> {

	@Inject
	protected ContractExecutionRepository contractExecutionRepo;
	
	@Inject
	protected ProgramRepository programRepo;
	
	@Inject TierRepository tierRepo;
	
	@Override
	public ContractTierSelection convert(ContractTierSelectionDto s) {
		return convert(s, new ContractTierSelection());
	}
	
	public ContractTierSelection convert(ContractTierSelectionDto s, ContractTierSelection t) {
		if (t == null) {
			t = new ContractTierSelection();
		}
		
		t.setSelectionType(SelectionType.valueOf(s.getSelectionType().toString()));
		t.setRequest(contractExecutionRepo.findOne(s.getContractExecutionId()));
		if (s.getProgramId() != null) t.setProgram(programRepo.findOne(s.getProgramId()));
		if (s.getTierId() != null) t.setTier(tierRepo.findOne(s.getTierId()));
		
		return t;
	}

}
