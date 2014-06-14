package com.novation.eligibility.rest.controller.exception;

/**
 * ConflictExceptions should correspond to HTTP status code 409 errors
 *
 */
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflictException(String msg) {
		super(msg);
	}
}
