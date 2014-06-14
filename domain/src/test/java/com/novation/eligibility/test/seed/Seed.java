package com.novation.eligibility.test.seed;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.novation.eligibility.domain.model.Individual;
import com.novation.eligibility.domain.model.Party;
import com.novation.eligibility.domain.repo.IndividualRepository;

/**
 * Seeds the empty database with reference data.
 * <p/>
 * Make sure the spring profiles <code>dev</code>, <code>seed</code>, and
 * profile names for the JPA implementation and database vendors are active via
 * the system property <code>spring.profiles.active</code>! This needs to be
 * done because they can't be hardcoded in an @{@link ActiveProfiles}
 * annotation; they differ depending on the build.
 */
@ContextConfiguration("classpath*:META-INF/spring/*.xml")
public class Seed extends AbstractTransactionalTestNGSpringContextTests {

	protected Logger log = LoggerFactory.getLogger(getClass());

	// @Inject
	// protected XmlUserRepository xmlUserRepo;

	@Inject
	protected IndividualRepository individualRepo;

	@Test
	@Rollback(false)
	public void seed() {
		// order matters!
		seedUsers();
	}

	private void seedUsers() {
		individualRepo.deleteAll();

		Individual admin = new Individual(Party.ADMIN_USERNAME);
		// MD5 hash of "admin"
		admin.setPasswordHash("21232f297a57a5a743894a0e4a801fc3");

		for (Individual user : new Individual[] { admin }) {
			individualRepo.save(user);
		}
	}
}
