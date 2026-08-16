package com.banking.automation.exception;

public class FrameworkException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public FrameworkException(final String message) {
		super(message);
	}
	
	public FrameworkException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
