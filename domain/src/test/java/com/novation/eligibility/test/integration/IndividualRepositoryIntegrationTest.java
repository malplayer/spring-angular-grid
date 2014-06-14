package com.novation.eligibility.test.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.domain.repo.IndividualRepository;
import com.novation.eligibility.test.unit.IndividualTest;

public class IndividualRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Inject
	IndividualRepository repo;
	
	@Test
	public void testIndividualRepository() {
		
		Individual i = IndividualTest.create();
		
		repo.save(i);
		
		String id = i.getId();
		
		assertThat(id, notNullValue());
		
		Individual i2 = repo.findOne(id);
		
		assertThat(i2, notNullValue());
		assertThat(i.getFirstName(), equalTo(i2.getFirstName()));
		assertThat(i.getMiddleName(), equalTo(i2.getMiddleName()));
		assertThat(i.getLastName(), equalTo(i2.getLastName()));
		assertThat(i.getDescription(), equalTo(i2.getDescription()));
	}
	
	@Test
	public void testCreateIndividual() {
		IndividualTest unit = new IndividualTest(callback);
		unit.testCreateIndividual();
	}
	
	@Test
	public void testIndividualNames() {
		IndividualTest unit = new IndividualTest(callback);
		unit.testIndividualNames();
	}

	@Test
	public void testIndividualPhones() {
		IndividualTest unit = new IndividualTest(callback);
		unit.testIndividualPhones();
	}
}
