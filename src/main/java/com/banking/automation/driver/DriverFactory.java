package com.banking.automation.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

import com.banking.automation.config.ConfigurationManager;
import com.banking.automation.enums.BrowserType;
import com.banking.automation.exception.DriverException;
import com.banking.automation.factory.BrowserFactory;
import com.banking.automation.utils.LoggerUtility;

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
					"Browser type cannot be null...");
		}
		
		if (Objects.nonNull(DRIVER.get())) {
            throw new DriverException(
                    "WebDriver is already initialized for the current thread...");
        }
		
		LoggerUtility.info("Initializing WebDriver for browser: "+browserType);
		try {
			final WebDriver webDriver = BrowserFactory.createDriver(browserType);
			setDriver(webDriver);
			
			LoggerUtility.info("WebDriver initialized successfully for browser: "+browserType);
		} catch(RuntimeException e) {
			LoggerUtility.error("Failed to initialize WebDriver for browser: "+browserType,
					e);
			throw e;
		}
	}
	
	public static void setDriver(final WebDriver webDriver) {
		
		if(Objects.isNull(webDriver)) {
			throw new DriverException(
					"WebDriver cannot be null...");
		}
		
		DRIVER.set(webDriver);
	}
	
	public static WebDriver getDriver() {
		final WebDriver webDriver = DRIVER.get();
		
		if(Objects.isNull(webDriver)) {
			
			LoggerUtility.error("WebDriver has not been initialized for the current thread...");
			
			throw new DriverException(
					"WebDriver has not been initialized for the current thread...");
		}
		
		return webDriver;
	}
	
	public static void quitDriver() {
		final WebDriver webDriver = DRIVER.get();
		if (Objects.nonNull(webDriver)) {

            LoggerUtility.info("Starting WebDriver cleanup...");

            try {
                webDriver.quit();

                LoggerUtility.info("WebDriver cleanup completed successfully..." );

            } catch (RuntimeException e) {

                LoggerUtility.error("WebDriver cleanup failed.",
                        e);
                throw e;

            } finally {
                DRIVER.remove();
            }

        } else {

            DRIVER.remove();

            LoggerUtility.debug("No WebDriver instance found during cleanup..." );
        }
	}
}
