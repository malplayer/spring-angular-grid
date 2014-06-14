package com.novation.eligibility.domain.repo;

import java.io.Serializable;

import com.novation.eligibility.domain.repository.springdata.Repository;


public interface CustomRepository<T, ID extends Serializable> extends
		Repository<T, ID> {
}
