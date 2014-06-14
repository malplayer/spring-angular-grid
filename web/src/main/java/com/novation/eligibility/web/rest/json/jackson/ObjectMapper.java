package com.novation.eligibility.web.rest.json.jackson;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

/**
 * Custom Jackson2 {@link com.fasterxml.jackson.databind.ObjectMapper} subclass
 * that has different default settings:
 * <ul>
 * <li>Defaults to not fail when bean classes with no properties defined are
 * encountered.</li>
 * <li>Defaults to only rendering non-null bean properties.</li>
 * <li>Defaults to ISO 8601 datetime format.</li>
 * </ul>
 * 
 * @see #configureCustomDefaults()
 */
@SuppressWarnings("serial")
public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {

	public static final boolean DEFAULT_FAIL_ON_EMPTY_BEANS = false;
	public static final Include DEFAULT_INCLUDE = Include.NON_NULL;
	public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String DEFAULT_DATE_FORMAT = ISO_8601;

	public ObjectMapper() {
		super();
		configureCustomDefaults();
	}

	public ObjectMapper(JsonFactory jf, DefaultSerializerProvider sp,
			DefaultDeserializationContext dc) {
		super(jf, sp, dc);
		configureCustomDefaults();
	}

	public ObjectMapper(JsonFactory jf) {
		super(jf);
		configureCustomDefaults();
	}

	public ObjectMapper(ObjectMapper src) {
		super(src);
		configureCustomDefaults();
	}

	protected void configureCustomDefaults() {
		configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
				DEFAULT_FAIL_ON_EMPTY_BEANS);
		setSerializationInclusion(DEFAULT_INCLUDE);
		setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
	}
}
