package com.novation.eligibility.hibernate.hbm2ddl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@SuppressWarnings("deprecation")
public abstract class SpringLcemfbHibernateTool {

	public static final String RESOURCES_ARG = "--spring-resources";
	public static final String RESOURCES_ARG_ASSIGN_OP = "=";
	public static final String RESOURCES_ARG_WITH_ASSIGN = RESOURCES_ARG
			+ RESOURCES_ARG_ASSIGN_OP;
	public static final int RESOURCES_ARG_MIN_LENGTH = RESOURCES_ARG_WITH_ASSIGN
			.length() + RESOURCES_ARG_ASSIGN_OP.length() + 1;
	public static final String DEFAULT_RESOURCES = "classpath*:META-INF/spring/*.xml";

	protected static String[] splitResourceLocations(
			String delimitedResourceLocations, String... delimiters) {
		return delimitedResourceLocations.split(delimiters == null
				|| delimiters.length == 0 ? "," : concatAll(delimiters));
	}

	protected static String concatAll(String[] strings) {
		StringBuilder s = new StringBuilder();
		for (String string : strings) {
			s.append(string);
		}
		return s.toString();
	}

	protected abstract void execute();

	protected LocalContainerEntityManagerFactoryBean lcemfb;
	protected PersistenceUnitInfo pui;
	protected String[] resourceLocations;
	protected GenericXmlApplicationContext context = new GenericXmlApplicationContext();
	protected String[] delegateArgArray;
	protected Ejb3Configuration ejb3Configuration;
	protected Configuration hibernateConfiguration;

	protected void doMain(String[] args) {
		parseArgs(args);
		init();
		execute();
	}

	protected void parseArgs(String[] args) {
		List<String> delegateArgs = new ArrayList<String>();

		String resources = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith(RESOURCES_ARG_WITH_ASSIGN)) {
				if (args[i].length() < RESOURCES_ARG_MIN_LENGTH) {
					break;
				}
				resources = args[i].substring(args[i]
						.indexOf(RESOURCES_ARG_ASSIGN_OP) + 1);
			} else {
				delegateArgs.add(args[i]);
			}
		}

		if (resources == null || resources.trim().length() == 0) {
			resources = DEFAULT_RESOURCES;
		}

		resourceLocations = splitResourceLocations(resources);

		delegateArgArray = delegateArgs
				.toArray(new String[delegateArgs.size()]);
	}

	protected void init() {
		context.load(resourceLocations);
		context.refresh();
		lcemfb = context.getBean(LocalContainerEntityManagerFactoryBean.class);
		pui = lcemfb.getPersistenceUnitInfo();
		ejb3Configuration = new Ejb3Configuration();
		ejb3Configuration.configure(pui, null);
		hibernateConfiguration = ejb3Configuration.getHibernateConfiguration();
	}
}
