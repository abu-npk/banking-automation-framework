package com.banking.automation.base;

import org.testng.annotations.BeforeMethod;

import com.banking.automation.driver.DriverFactory;
import com.banking.automation.enums.BrowserType;
import com.banking.automation.utils.LoggerUtility;

import org.testng.annotations.AfterMethod;

public abstract class BaseTest {
	private static final BrowserType DEFAULT_BROWSER = BrowserType.CHROME;
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		LoggerUtility.info(
                "Starting test setup. Browser: "
                        + DEFAULT_BROWSER);

        try {

            DriverFactory.initializeDriver(
                    DEFAULT_BROWSER);

            LoggerUtility.info(
                    "Test setup completed successfully.");

        } catch (RuntimeException e) {

            LoggerUtility.error(
                    "Test setup failed.",
                    e);
            throw e;
        }
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		LoggerUtility.info(
                "Starting test cleanup.");

        try {

            DriverFactory.quitDriver();

            LoggerUtility.info(
                    "Test cleanup completed successfully.");

        } catch (RuntimeException e) {

            LoggerUtility.error(
                    "Test cleanup failed.",
                    e);
            throw e;
        }
	}
}
