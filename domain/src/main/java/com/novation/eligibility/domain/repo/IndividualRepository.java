package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.Individual;


public interface IndividualRepository extends CustomRepository<Individual, String> {
	
	Individual findByUsername(String username);
}
