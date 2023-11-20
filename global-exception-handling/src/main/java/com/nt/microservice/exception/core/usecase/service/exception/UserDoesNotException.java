package com.nt.microservice.exception.core.usecase.service.exception;

public class UserDoesNotException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342604592705517649L;

	
	public UserDoesNotException(Long id) {
        super(String.format("id %s doesn't exist in database", id));
	}

}
