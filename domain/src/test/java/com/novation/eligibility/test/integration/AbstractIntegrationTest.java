package com.novation.eligibility.test.integration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import com.novation.eligibility.test.unit.AbstractUnitTest;

@ContextConfiguration("classpath*:META-INF/spring/*.xml")
public abstract class AbstractIntegrationTest extends
		AbstractTransactionalTestNGSpringContextTests {

	public static Md5PasswordEncoder MD5 = new Md5PasswordEncoder();

	public static String md5(String s) {
		return md5(s, null);
	}

	public static String md5(String pwd, String salt) {
		return MD5.encodePassword(pwd, salt);
	}

	public static String uuid() {
		return AbstractUnitTest.uuid();
	}

	public class Callback implements AbstractUnitTest.Callback {

		@Override
		public void onEntitiesCreated(Object... entities) {
			for (Object entity : entities) {
				em.persist(entity);
			}
		}

		@Override
		public void onEntitiesChanged(Object... entities) {
			em.flush();
		}

		@Override
		public void onUnitTestDone() {
		}
	}

	protected Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	protected EntityManager em;

	protected Callback callback = new Callback();
}
