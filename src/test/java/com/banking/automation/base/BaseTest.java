package com.banking.automation.base;

import org.testng.annotations.BeforeMethod;

import com.banking.automation.driver.DriverFactory;

import org.testng.annotations.AfterMethod;

public abstract class BaseTest {
  
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
	  DriverFactory.initializeDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
	  DriverFactory.quitDriver();
  }
}
