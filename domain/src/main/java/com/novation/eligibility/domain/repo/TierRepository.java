package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.Tier;


public interface TierRepository extends CustomRepository<Tier, String> {
	
	Tier findByName(String name);
}
