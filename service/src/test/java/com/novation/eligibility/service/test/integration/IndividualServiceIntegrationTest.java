package com.novation.eligibility.service.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.inject.Inject;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.testng.annotations.Test;

import com.novation.eligibility.dto.dtos.IndividualDto;
import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.domain.repo.IndividualRepository;
import com.novation.eligibility.service.individual.IndividualService;
import com.novation.eligibility.service.individual.MinimalIndividualCreationResult;
import com.novation.eligibility.service.party.ChangePasswordResult;
import com.novation.eligibility.support.security.hash.Hasher;
import com.novation.eligibility.service.response.Response;
import com.novation.eligibility.service.response.Status;

public class IndividualServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Inject
	IndividualRepository repo;

	@Inject
	IndividualService svc;

	protected static Md5PasswordEncoder MD5 = new Md5PasswordEncoder();

	@Test
	public void testFindAdmin() {
		String username = "admin";

		Response<IndividualDto> response = svc.findIndividualByUsername(username);
		assertThat(response.getStatus(), equalTo(Status.SUCCESS));

		IndividualDto dto = response.getPayload();
		assertThat(dto, is(not(nullValue())));
		assertThat(dto.getUsername(), equalTo(username));
	}

	@Test
	public void testFindByUsername() {
		String username = uuid();
		Individual u = new Individual(username);
		repo.save(u);

		em.flush();

		Response<IndividualDto> r = svc.findIndividualByUsername(username);

		assertNotNull(r);

		IndividualDto dto = null;
		assertNotNull(dto = r.getPayload());

		assertEquals(u.getUsername(), dto.getUsername());
		assertEquals(u.getPrimaryEmail(), dto.getPrimaryEmail());
	}

	@Test
	public void testCreateIndividualMinimallyWithSalt() {

		String username = uuid();
		String salt = Hasher.createSalt();
		String passwordHash = MD5.encodePassword(uuid(), salt);
		String email = uuid() + "@domain.com";

		Response<MinimalIndividualCreationResult> result = svc
				.createIndividualMinimally(username, passwordHash, email, salt);
		assertThat(result.getStatus(), equalTo(Status.SUCCESS));
		IndividualDto dto = result.getPayload().getIndividual();
		assertThat(dto.getId(), notNullValue());
		assertThat(dto.getUsername(), equalTo(username));
		assertThat(dto.getPrimaryEmail(), equalTo(email));
		assertThat(dto.getSalt(), equalTo(salt));
	}

	@Test
	public void testCreateIndividualMinimallyWithoutSalt() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		String email = uuid() + "@domain.com";

		Response<MinimalIndividualCreationResult> result = svc
				.createIndividualMinimally(username, passwordHash, email);
		assertThat(result.getStatus(), equalTo(Status.SUCCESS));
		IndividualDto dto = result.getPayload().getIndividual();
		assertThat(dto.getId(), notNullValue());
		assertThat(dto.getUsername(), equalTo(username));
		assertThat(dto.getPrimaryEmail(), equalTo(email));
		assertThat(dto.getSalt(), nullValue());
	}
	
	@Test
	public void testChangeIndividualPasswordByUsername() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		Individual u = new Individual(username);
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
	public void testChangeIndividualPasswordByUsernameWithWrongCurrentPassword() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		Individual u = new Individual(username);
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
	public void testChangeIndividualPasswordById() {

		String username = uuid();
		String passwordHash = MD5.encodePassword(uuid(), null);
		Individual u = new Individual(username);
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
