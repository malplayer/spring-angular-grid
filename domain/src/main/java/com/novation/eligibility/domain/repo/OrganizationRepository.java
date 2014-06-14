package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.Organization;


public interface OrganizationRepository extends CustomRepository<Organization, String> {
	
	Organization findByUsername(String username);
	
	Organization findByName(String name);
	
	Organization findByDuns(String duns);
}
