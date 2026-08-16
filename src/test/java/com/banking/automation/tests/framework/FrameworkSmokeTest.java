package com.banking.automation.tests.framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.automation.base.BaseTest;
import com.banking.automation.driver.DriverFactory;

public class FrameworkSmokeTest extends BaseTest {
	@Test(groups = {"smoke"},
			description="Validates browser initialization and WebDriver lifecycle")
	public void verifyWebDriverIsInitialized() {
		Assert.assertNotNull(DriverFactory.getDriver(),
				"WebDriver should be initialized before the test...");
	}
}
