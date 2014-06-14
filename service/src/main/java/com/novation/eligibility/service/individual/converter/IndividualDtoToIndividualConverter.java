package com.novation.eligibility.service.individual.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.domain.repo.ContractTierExecutionEventRepository;
import com.novation.eligibility.dto.dtos.IndividualDto;

@Component
public class IndividualDtoToIndividualConverter implements Converter<IndividualDto, Individual> {

	@Inject
	ContractTierExecutionEventRepository repo;
	
	@Override
	public Individual convert(IndividualDto s) {
		return convert(s, new Individual());
	}
	
	public Individual convert(IndividualDto s, Individual t) {
		if (t == null) {
			t = new Individual();
		}

		if (s.getFirstName() != null) t.setFirstName(s.getFirstName());
		if (s.getMiddleName() != null) t.setMiddleName(s.getMiddleName());
		if (s.getLastName() != null) t.setLastName(s.getLastName());
		if (s.getDescription() != null) t.setDescription(s.getDescription());
		if (s.getPrimaryEmail() != null) t.setPrimaryEmail(s.getPrimaryEmail());
		if (s.getUsername() != null) t.setUsername(s.getUsername());
		if (s.getPasswordHash() != null) t.setPasswordHash(s.getPasswordHash());
		if (s.getSalt() != null) t.setSalt(s.getSalt());
		
		for (String id : s.getContractTierExecutionEventIds()) {
			t.addEvent(repo.findOne(id));
		}

		return t;
	}

}
