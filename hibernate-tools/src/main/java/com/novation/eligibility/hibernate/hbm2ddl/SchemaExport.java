package com.novation.eligibility.hibernate.hbm2ddl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.internal.StandardServiceRegistryImpl;
import org.hibernate.tool.hbm2ddl.ImportSqlCommandExtractor;

public class SchemaExport extends SpringLcemfbHibernateTool {

	private static final String DEFAULT_IMPORT_FILE = "/import.sql";

	protected org.hibernate.tool.hbm2ddl.SchemaExport delegate;
	protected boolean script = true;
	protected boolean drop = false;
	protected boolean create = false;
	protected boolean halt = false;
	protected boolean export = true;
	protected String outFile = null;
	protected String importFile = DEFAULT_IMPORT_FILE;
	protected String propFile = null;
	protected boolean format = false;
	protected String delim = null;
	protected StandardServiceRegistryImpl serviceRegistry;

	public static void main(String[] args) {
		new SchemaExport().doMain(args);
	}

	@Override
	protected void execute() {
		parseDelegateArguments(delegateArgArray);

		delegate = new org.hibernate.tool.hbm2ddl.SchemaExport(
				hibernateConfiguration)
				.setDelimiter(delim)
				.setFormat(format)
				.setHaltOnError(halt)
				.setOutputFile(outFile)
				.setImportSqlCommandExtractor(
						serviceRegistry
								.getService(ImportSqlCommandExtractor.class));

		delegate.execute(script, export, drop, create);
	}

	protected void parseDelegateArguments(String[] args) {
		try {
			for (int i = 0; i < args.length; i++) {
				if (args[i].startsWith("--")) {
					if (args[i].equals("--quiet")) {
						script = false;
					} else if (args[i].equals("--drop")) {
						drop = true;
					} else if (args[i].equals("--create")) {
						create = true;
					} else if (args[i].equals("--haltonerror")) {
						halt = true;
					} else if (args[i].equals("--text")) {
						export = false;
					} else if (args[i].startsWith("--output=")) {
						outFile = args[i].substring(9);
					} else if (args[i].startsWith("--import=")) {
						importFile = args[i].substring(9);
					} else if (args[i].startsWith("--properties=")) {
						propFile = args[i].substring(13);
					} else if (args[i].equals("--format")) {
						format = true;
					} else if (args[i].startsWith("--delimiter=")) {
						delim = args[i].substring(12);
					} else if (args[i].startsWith("--config=")) {
						hibernateConfiguration.configure(args[i].substring(9));
					} else if (args[i].startsWith("--naming=")) {
						hibernateConfiguration
								.setNamingStrategy((NamingStrategy) ReflectHelper
										.classForName(args[i].substring(9))
										.newInstance());
					}
				} else {
					String filename = args[i];
					if (filename.endsWith(".jar")) {
						hibernateConfiguration.addJar(new File(filename));
					} else {
						hibernateConfiguration.addFile(filename);
					}
				}
			}

			if (propFile != null) {
				Properties props = new Properties();
				props.putAll(hibernateConfiguration.getProperties());
				props.load(new FileInputStream(propFile));
				hibernateConfiguration.setProperties(props);
			}

			if (importFile != null) {
				hibernateConfiguration.setProperty(
						AvailableSettings.HBM2DDL_IMPORT_FILES, importFile);
			}

			serviceRegistry = createServiceRegistry(hibernateConfiguration
					.getProperties());
		} catch (Exception x) {
			throw new RuntimeException(x.getMessage(), x);
		}
	}

	protected StandardServiceRegistryImpl createServiceRegistry(
			Properties properties) {
		Environment.verifyProperties(properties);
		ConfigurationHelper.resolvePlaceHolders(properties);
		return (StandardServiceRegistryImpl) new ServiceRegistryBuilder()
				.applySettings(properties).buildServiceRegistry();
	}
}
