package com.novation.eligibility.rest.controller.exception;

/**
 * Exception indicating a desired business entity or record cannot be found.
 */
public class UnknownResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownResourceException(String msg) {
        super(msg);
    }
}
