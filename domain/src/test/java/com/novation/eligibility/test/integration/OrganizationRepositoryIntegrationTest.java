package com.novation.eligibility.test.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Organization;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.test.unit.OrganizationTest;

public class OrganizationRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Inject
	OrganizationRepository repo;
	
	@Test
	public void testOrganizationRepository() {
		Organization o = OrganizationTest.create();
		
		repo.save(o);
		
		String id = o.getId();
		
		assertThat(id, notNullValue());
		
		Organization o2 = repo.findOne(id);
		
		assertThat(o2, notNullValue());
		assertThat(o.getWebSite(), equalTo(o2.getWebSite()));
		assertThat(o.getDescription(), equalTo(o2.getDescription()));		
	}
	
	@Test
	public void testCreateOrganization() {
		OrganizationTest unit = new OrganizationTest(callback);
		unit.testCreateOrganization();
	}
}
