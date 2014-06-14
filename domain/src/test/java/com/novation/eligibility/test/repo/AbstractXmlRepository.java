package com.novation.eligibility.test.repo;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

@Service
public abstract class AbstractXmlRepository {

	protected abstract void doInit() throws Exception;

	protected abstract Class<?>[] getXmlTypes();

	protected boolean initialized = false;
	protected Resource resource;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected String xml;
	protected XStream xstream;

	protected AbstractXmlRepository(Resource resource) {
		setXmlSource(resource);
	}

	public void setXmlSource(Resource resource) {
		this.resource = resource;
	}

	@PostConstruct
	protected void onPostConstruct() {
		Assert.notNull(resource, "xmlSource cannot be null");
	}

	protected XStream initXStream() {
		XStream x = new XStream(new PureJavaReflectionProvider());
		x.processAnnotations(getXmlTypes());
		return x;
	}

	synchronized public void init() {
		if (initialized) {
			return;
		}
		log.info("reading seed data from xml");

		xstream = initXStream();

		try {

			doInit();

			initialized = true;
		} catch (Exception e) {
			throw new RuntimeException(
					"Problems loading xml file containing seed data: Exception message = "
							+ e.getMessage());
		}

		initialized = true;
		log.info("done reading seed data from xml");
	}

	public String getXml() {
		return xml;
	}
}
