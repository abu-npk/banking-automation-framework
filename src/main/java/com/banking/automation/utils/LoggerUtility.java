package com.banking.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtility {
	private static final Logger LOGGER = LogManager.getLogger(LoggerUtility.class);
	
	private LoggerUtility() {
		throw new UnsupportedOperationException(
				"LoggerUtility should not be instantiated...");
	}
	
	//Info log
	public static void info(final String message) {
		LOGGER.info(message);
	}
	
	//Warning message
	public static void warn(final String message) {
		LOGGER.warn(message);
	}
	
	//Error message
	public static void error(final String message, final Throwable exception) {
		LOGGER.error(message, exception);
	}
	
	//Error message without Exception
	public static void error(final String message) {
		LOGGER.error(message);
	}
	
	//Debug message
	public static void debug(final String message) {
		LOGGER.debug(message);
	}
	
	//Trace message
	public static void trace(final String message) {
		LOGGER.trace(message);
	}
}
