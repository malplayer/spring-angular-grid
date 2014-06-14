package com.novation.eligibility.service.test.integration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.novation.eligibility.service.test.unit.AbstractUnitTest;

/**
 * Convenient parent test class.
 */
@ContextConfiguration("classpath*:META-INF/spring/*.xml")
public abstract class AbstractServiceIntegrationTest extends
		AbstractTransactionalTestNGSpringContextTests {

	public static String uuid() {
		return AbstractUnitTest.uuid();
	}

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceUnit
	protected EntityManagerFactory emf;

	@PersistenceContext
	protected EntityManager em;

	/**
	 * Subclasses should override & call this method first, then perform any
	 * additional set up logic.
	 */
	@BeforeClass
	public void beforeClass() {
		Assert.assertNotNull(emf);
	}

	/**
	 * Subclasses should override & call this method first, then perform any
	 * additional set up logic.
	 */
	@BeforeMethod
	public void beforeMethod() {
		Assert.assertNotNull(em);
	}

	/**
	 * Subclasses should override, perform any additional tear down logic, then
	 * call this method last.
	 */
	@AfterMethod
	public void afterMethod() {
	}

	/**
	 * Subclasses should override, perform any additional tear down logic, then
	 * call this method last.
	 */
	@AfterClass
	public void afterClass() {
	}
}
