package com.banking.automation.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.banking.automation.driver.DriverFactory;
import com.banking.automation.enums.BrowserType;
import com.banking.automation.utils.LoggerUtility;

import org.testng.annotations.AfterMethod;


public abstract class BaseTest {
	
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void beforeMethod(final String browser) {
		final BrowserType browserType = BrowserType.fromValue(browser);
		LoggerUtility.info(
                "Starting test setup. Browser: "
                        + browserType);

        try {
        	
            
        	DriverFactory.initializeDriver(browserType);

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
