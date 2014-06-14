package com.novation.eligibility.liquibase.test.unit;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestLiquibase {
	private static final String CONF_HEADER = "novation-eligibility";

	/**
	 * Make sure all config files have the proper [novation-eligibility] header
	 */
	@Test
	public void testConfigHeaderPresent() throws Exception {
		File root = new File("src/main/resources/conf");
		Collection<File> files = FileUtils.listFiles(root,
				new String[] { "conf" }, true);
		for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			HierarchicalINIConfiguration conf = new HierarchicalINIConfiguration(
					file);
			Assert.assertEquals(conf.getSections().size(), 1,
					"There should be one and only one config section header.");
			Assert.assertFalse(conf.getSection(CONF_HEADER).isEmpty(),
					"Configuration header" + CONF_HEADER
							+ " is missing or empty");
		}
	}

	/**
	 * Make sure all of the configs under a project have the same config keys in
	 * them. If any of the configs have more or less keys than the others, then
	 * one or more of the configs are wrong.
	 * 
	 * Failure example: dev/db.conf = foo: value bar: value
	 * 
	 * test/db.conf = foo: value
	 * 
	 * The second config is missing a key.
	 */
	@Test
	public void testConfigsContainIdenticalKeys() throws Exception {
		// Loop through all directories found in src/main/resources/conf/
		File root = new File("src/main/resources/conf");
		for (File f : root.listFiles()) {
			if (f.isDirectory()) {
				// Store the distinct list of keys found in all configs under
				// this project
				// All configs should have these keys. No less, no more.
				HashSet<String> keysForThisProject = new HashSet<String>();
				// Store all of the configs so we don't have to read through a
				// second time
				HashMap<String, HashSet<String>> configs = getConfigKeysForProject(f
						.getName());
				for (String conf : configs.keySet()) {
					keysForThisProject.addAll(configs.get(conf));
				}
				for (String key : configs.keySet()) {
					Assert.assertTrue(
							CollectionUtils.isEqualCollection(configs.get(key),
									keysForThisProject),
							"Config '"
									+ key
									+ "' is different. Check your db.conf files and make sure they all have the same keys.");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private HashMap<String, HashSet<String>> getConfigKeysForProject(
			String project) throws ConfigurationException {
		HashMap<String, HashSet<String>> allConfigs = new HashMap<String, HashSet<String>>();

		File root = new File("src/main/resources/conf/" + project);
		Collection<File> conf_files = FileUtils.listFiles(root,
				new String[] { "conf" }, true);
		for (Iterator<File> iterator = conf_files.iterator(); iterator
				.hasNext();) {
			File file = (File) iterator.next();
			HierarchicalINIConfiguration conf = new HierarchicalINIConfiguration(
					file);
			HashSet<String> keys = new HashSet<String>();
			Iterator<String> it = conf.getSection(CONF_HEADER).getKeys();
			while (it.hasNext()) {
				keys.add(it.next());
			}
			allConfigs.put(file.getPath(), keys);
		}
		return allConfigs;
	}

}
