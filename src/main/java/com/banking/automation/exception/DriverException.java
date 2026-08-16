package com.banking.automation.exception;

public class DriverException extends FrameworkException {
	
	private static final long serialVersionUID = 1L;
	
	public DriverException(final String message) {
		super(message);
	}
	
	public DriverException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
