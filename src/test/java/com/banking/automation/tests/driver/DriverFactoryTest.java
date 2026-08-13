package com.banking.automation.tests.driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.banking.automation.driver.DriverFactory;
import com.banking.automation.enums.BrowserType;

public class DriverFactoryTest {
	
	@Test
	public void shoudlInitializeAndReturnWebDriver1() {
		DriverFactory.initializeDriver(BrowserType.CHROME);
		final WebDriver driver = DriverFactory.getDriver();
		Assert.assertNotNull(driver, "WebDriver should be initialized...");
		
	}
	@Test
	public void shoudlInitializeAndReturnWebDriver2() {
		DriverFactory.initializeDriver(BrowserType.FIREFOX);
		final WebDriver driver = DriverFactory.getDriver();
		Assert.assertNotNull(driver, "WebDriver should be initialized...");
		
	}
	
	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
