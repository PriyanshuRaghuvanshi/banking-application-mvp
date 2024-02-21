package com.nagarro.accountmanagement.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{

	
	// send response entity error object
	public ResourceNotFoundException() {
		super("Resource not found on server !!");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
