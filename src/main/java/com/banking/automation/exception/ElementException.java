package com.banking.automation.exception;

public class ElementException extends FrameworkException {

    private static final long serialVersionUID = 1L;

    public ElementException(final String message) {
        super(message);
    }

    public ElementException(final String message, final Throwable cause) {
        super(message, cause);
    }
}