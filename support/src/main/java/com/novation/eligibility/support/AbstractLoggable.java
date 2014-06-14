package com.novation.eligibility.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractLoggable {

	transient protected Logger log = LoggerFactory.getLogger(getClass());

	protected void trace(String msg, Object... args) {
		if (log.isTraceEnabled()) {
			log.trace(msg, args);
		}
	}

	protected void debug(String msg, Object... args) {
		if (log.isDebugEnabled()) {
			log.debug(msg, args);
		}
	}

	protected void info(String msg, Object... args) {
		if (log.isInfoEnabled()) {
			log.info(msg, args);
		}
	}

	protected void warn(String msg, Object... args) {
		if (log.isWarnEnabled()) {
			log.warn(msg, args);
		}
	}

	protected void error(String msg, Object... args) {
		if (log.isErrorEnabled()) {
			log.error(msg, args);
		}
	}
}