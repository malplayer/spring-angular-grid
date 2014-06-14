package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.MemberAlliance;


public interface MemberAllianceRepository extends CustomRepository<MemberAlliance, String> {

	MemberAlliance findByName(String name);
}
