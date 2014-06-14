package com.novation.eligibility.domain.repo;

import java.util.Date;

import com.novation.eligibility.domain.model.Contract;


public interface ContractRepository extends CustomRepository<Contract, String> {
	
	Contract findByName(String name);
	
	Contract findByEffectiveDate(Date effectiveDate);
}
