package com.novation.eligibility.test.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Organization;

public class OrganizationTest extends AbstractUnitTest {

	public static Organization create() {
		Organization o = new Organization();
		
		o.setName(uuid());
		o.setDuns(uuid());
		o.setWebSite(uuid());
		o.setDescription(uuid());
		
		assertThat(o.getName(), notNullValue());
		assertThat(o.getDuns(), notNullValue());
		assertThat(o.getWebSite(), notNullValue());
		assertThat(o.getDescription(), notNullValue());

		return o;
	}
	
	public OrganizationTest() {
	}
	
	public OrganizationTest(Callback callback) {
		this.callback = callback;
	}
	
	@Test
	public void testCreateOrganization() {
		Organization o = create();
		callback.onEntitiesCreated(o);
	}
	
}
