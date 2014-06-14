package com.novation.eligibility.service.contracttierselection.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.ContractTierSelection;
import com.novation.eligibility.dto.dtos.ContractTierSelectionDto;
import com.novation.eligibility.dto.dtos.SelectionType;

public class ContractTierSelectionToContractTierSelectionDtoConverter implements
	Converter<ContractTierSelection, ContractTierSelectionDto> {

	@Override
	public ContractTierSelectionDto convert(ContractTierSelection s) {
		return convert(s, new ContractTierSelectionDto());
	}
	
	public ContractTierSelectionDto convert(ContractTierSelection s, ContractTierSelectionDto t) {
		if (t == null) {
			t = new ContractTierSelectionDto();
		}
		
		t.setId(s.getId());
		t.setSelectionType(SelectionType.valueOf(s.getSelectionType().name()));
		t.setContractExecutionId(s.getRequest().getId());
		if (s.getProgram() == null) t.setProgramId(s.getProgram().getId());
		if (s.getTier() == null) t.setTierId(s.getTier().getId());
		return t;
	}

}
