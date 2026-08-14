package com.banking.automation.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

import com.banking.automation.config.ConfigurationManager;
import com.banking.automation.enums.BrowserType;
import com.banking.automation.factory.BrowserFactory;

public final class DriverFactory {
	
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	
	private static final ConfigurationManager CONFIGURATION_MANAGER = new ConfigurationManager();
	
	private DriverFactory() {
		throw new UnsupportedOperationException("DriverFactory should not be instantiated...");
	}
	
	public static void initializeDriver() {
		initializeDriver(CONFIGURATION_MANAGER.getBrowser());
	}
	
	public static void initializeDriver(final BrowserType browserType) {
		if(Objects.isNull(browserType)){
			throw new IllegalStateException(
					"Browser type cannot be null");
		}
		
		if (Objects.nonNull(DRIVER.get())) {
            throw new IllegalStateException(
                    "WebDriver is already initialized for the current thread."
            );
        }
		
		final WebDriver webDriver = BrowserFactory.createDriver(browserType);
		setDriver(webDriver);
	}
	
	public static void setDriver(final WebDriver webDriver) {
		
		if(Objects.isNull(webDriver)) {
			throw new IllegalArgumentException(
					"WebDriver cannot be null...");
		}
		
		DRIVER.set(webDriver);
	}
	
	public static WebDriver getDriver() {
		final WebDriver webDriver = DRIVER.get();
		
		if(Objects.isNull(webDriver)) {
			throw new IllegalStateException(
					"WebDriver has not been initialized for the current thread...");
		}
		
		return webDriver;
	}
	
	public static void quitDriver() {
		final WebDriver webDriver = DRIVER.get();
		
		try {
			if(Objects.nonNull(webDriver)) {
				webDriver.quit();
			}
		} finally {
			DRIVER.remove();
		}
	}
}
