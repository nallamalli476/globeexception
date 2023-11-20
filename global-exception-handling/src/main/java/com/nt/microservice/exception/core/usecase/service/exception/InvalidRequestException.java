package com.nt.microservice.exception.core.usecase.service.exception;

public class InvalidRequestException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -9020376732205962230L;

	public InvalidRequestException(String message) {
	        super(message);
	 }
}
