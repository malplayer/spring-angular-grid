package com.novation.eligibility.service.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.inject.Inject;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Organization;
import com.novation.eligibility.domain.repo.IndividualRepository;
import com.novation.eligibility.domain.repo.OrganizationRepository;
import com.novation.eligibility.dto.dtos.OrganizationDto;
import com.novation.eligibility.service.organization.OrganizationCreationResult;
import com.novation.eligibility.service.organization.OrganizationService;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class OrganizationServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	OrganizationRepository repo;
	
	@Inject
	IndividualRepository indRepo;
	
	@Inject
	protected OrganizationService svc;
	
	protected static Md5PasswordEncoder MD5 = new Md5PasswordEncoder();
	
	@Test
	public void testCreateOrganization() {
		OrganizationDto m = new OrganizationDto();
		m.setName(uuid());
		m.setDescription(uuid());
		
		Response<OrganizationCreationResult> response = svc.createOrganization(m);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));
		
		OrganizationDto dto = response.getPayload().getOrganization();
		assertThat(dto, is(not(nullValue())));
		assertThat(dto.getName(), equalTo(m.getName()));
		assertThat(dto.getDescription(), equalTo(m.getDescription()));
	}
	
	@Test
	public void testFindByName() {
		// HACK ALERT
		// This operation is required for DataNucleus until the seed database includes all tables. (Matthew is investigating a fix)
		indRepo.findOne("");

		String name = uuid();
		Organization m = new Organization();
		m.setName(name);
		repo.save(m);

		em.flush();
		
		Response<OrganizationDto> r = svc.findOrganizationByName(name);
		assertNotNull(r);
		
		OrganizationDto dto = null;
		assertNotNull(dto = r.getPayload());
		
		assertEquals(m.getName(), dto.getName());
	}
	
	@Test
	public void testChangeOrganizationPasswordByUsername() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		Organization u = new Organization(username);
		u.setPasswordHash(passwordHash);
		repo.save(u);

		em.flush();

		String newHash = MD5.encodePassword(uuid(), null);
		Response<ChangePasswordResult> result = svc
				.changePasswordByUsername(username, passwordHash, newHash);

		assertThat(result.getStatus(), equalTo(Status.SUCCESS));
		assertThat(result.getPayload(), equalTo(ChangePasswordResult.SUCCESS));
	}

	@Test
	public void testChangeOrganizationPasswordByUsernameWithWrongCurrentPassword() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		Organization u = new Organization(username);
		u.setPasswordHash(passwordHash);
		repo.save(u);

		em.flush();

		String newHash = MD5.encodePassword(uuid(), null);
		Response<ChangePasswordResult> result = svc
				.changePasswordByUsername(username,
						newHash /* forces failure */, newHash);

		assertThat(result.getStatus(), equalTo(Status.FAILURE));
		assertThat(result.getPayload(), equalTo(ChangePasswordResult.FAILURE));
	}

	@Test
	public void testChangeOrganizationPasswordById() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		Organization u = new Organization(username);
		u.setPasswordHash(passwordHash);
		repo.save(u);

		em.flush();

		String newHash = MD5.encodePassword(uuid(), null);
		Response<ChangePasswordResult> result = svc.changePasswordById(
				u.getId(), passwordHash, newHash);

		assertThat(result.getStatus(), equalTo(Status.SUCCESS));
		assertThat(result.getPayload(), equalTo(ChangePasswordResult.SUCCESS));
	}

}
