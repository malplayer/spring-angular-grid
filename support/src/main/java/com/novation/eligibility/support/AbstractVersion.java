package com.novation.eligibility.support;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * A convenient superclass for use by subclasses in a component to identify the
 * component's version. By default, it looks for a Java properties file resource
 * called <code>version.properties</code> located next to the subclass of this
 * class, with the property <code>version</code>.
 * <p/>
 * This class also provides a {@link #doMain(String...)} method for subclasses
 * to call from their own static <code>void main(String...)</code> method.
 */
public abstract class AbstractVersion {

	/**
	 * The default name of the property to look for in the properties resource.
	 */
	public static final String DEFAULT_VERSION_PROPERTY_NAME = "version";

	/**
	 * The default name of the properties file resource to load.
	 */
	public static final String DEFAULT_RELATIVE_VERSION_PROPERTIES_RESOURCE_NAME = "version.properties";

	protected Resource versionPropertiesResource;
	protected String versionPropertyName = DEFAULT_VERSION_PROPERTY_NAME;

	public AbstractVersion() {
		setVersionPropertiesResource(new ClassRelativeResourceLoader(getClass())
				.getResource(DEFAULT_RELATIVE_VERSION_PROPERTIES_RESOURCE_NAME));
	}

	public AbstractVersion(Resource versionPropertiesResource) {
		setVersionPropertiesResource(versionPropertiesResource);
	}

	public void setVersionPropertiesResource(Resource versionPropertiesResource) {
		this.versionPropertiesResource = versionPropertiesResource;
	}

	public Resource getVersionPropertiesResource() {
		return versionPropertiesResource;
	}

	public String getVersion() {
		Properties p;
		try {
			p = PropertiesLoaderUtils
					.loadProperties(getVersionPropertiesResource());
			return p.getProperty(getVersionPropertyName());
		} catch (IOException e) {
			return null;
		}
	}

	public String getVersionPropertyName() {
		return versionPropertyName;
	}

	/**
	 * Convenient method that subclasses' <code>main(String...)</code> methods
	 * can delegate to.
	 */
	public void doMain(String... args) {
		System.out.println(getVersion());
	}
}
