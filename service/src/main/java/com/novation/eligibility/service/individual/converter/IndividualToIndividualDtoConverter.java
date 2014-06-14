package com.novation.eligibility.service.individual.converter;

import org.springframework.core.convert.converter.Converter;

import com.novation.eligibility.domain.model.ContractTierExecutionEvent;
import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.dto.dtos.IndividualDto;

public class IndividualToIndividualDtoConverter implements Converter<Individual, IndividualDto> {

	@Override
	public IndividualDto convert(Individual s) {
		return convert(s, new IndividualDto());
	}
	
	public IndividualDto convert(Individual s, IndividualDto t) {
		if (t == null) {
			t = new IndividualDto();
		}
		
		t.setId(s.getId());
		if (s.getFirstName() != null) t.setFirstName(s.getFirstName());
		if (s.getMiddleName() != null) t.setMiddleName(s.getMiddleName());
		if (s.getLastName() != null) t.setLastName(s.getLastName());
		if (s.getPasswordHash() != null) t.setPasswordHash(s.getPasswordHash());
		if (s.getSalt() != null) t.setSalt(s.getSalt());
		if (s.getPrimaryEmail() != null) t.setPrimaryEmail(s.getPrimaryEmail());
		if (s.getUsername() != null) t.setUsername(s.getUsername());
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		
		for (ContractTierExecutionEvent e : s.getEvents()) {
			t.addContractTierExecutionEventId(e.getId());
		}
		return t;
	}

}
