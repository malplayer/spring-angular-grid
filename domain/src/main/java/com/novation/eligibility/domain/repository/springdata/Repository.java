package com.novation.eligibility.domain.repository.springdata;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Interface that extends Spring Data's {@link PagingAndSortingRepository},
 * providing a central location for additional query methods not provided by
 * Spring Data.
 * 
 * @param <T>
 *            The type of the entity for which this is a repository.
 * @param <ID>
 *            The type of the persistent identity of <code>T</code>.
 */
@NoRepositoryBean
public interface Repository<T, ID extends Serializable> extends
		PagingAndSortingRepository<T, ID> {
}
