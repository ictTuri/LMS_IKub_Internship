package com.project.lms.exception;

public class ObjectIdNotFound extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ObjectIdNotFound(String message) {
		super(message);
	}

	public ObjectIdNotFound(String message, Throwable cause) {
		super(message, cause);
	}
}
