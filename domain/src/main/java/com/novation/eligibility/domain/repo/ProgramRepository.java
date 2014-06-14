package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.Program;


public interface ProgramRepository extends CustomRepository<Program, String> {
	
	Program findByName(String name);
}
