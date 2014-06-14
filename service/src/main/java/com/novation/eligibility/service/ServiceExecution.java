package com.novation.eligibility.service;

import java.io.Serializable;

public interface ServiceExecution<T extends Serializable> {

	/**
	 * Authors should return the payload if the service method is successful and   
	 * and throw an exception if there is an error
	 */
	public T execute();

}