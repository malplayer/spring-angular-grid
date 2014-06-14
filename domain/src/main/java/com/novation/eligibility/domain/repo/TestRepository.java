package com.novation.eligibility.domain.repo;

import com.novation.eligibility.domain.model.TestTable;


public interface TestRepository extends CustomRepository<TestTable,Integer>{
	TestTable findById(int id);
}
