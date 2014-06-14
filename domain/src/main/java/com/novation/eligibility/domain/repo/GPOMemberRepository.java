package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.GPOMember;


public interface GPOMemberRepository extends CustomRepository<GPOMember, String> {

	GPOMember findByMemberNumber(String memberNumber);
}
