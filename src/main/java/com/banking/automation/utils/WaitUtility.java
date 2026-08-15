package com.banking.automation.utils;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.banking.automation.config.ConfigurationManager;
import com.banking.automation.driver.DriverFactory;

public class WaitUtility {
	
	private final WebDriver driver;
	private final WebDriverWait wait;
	
	public WaitUtility() {
		this(
				DriverFactory.getDriver(),
				new ConfigurationManager());
	}
	
	public WaitUtility(final WebDriver driver, final ConfigurationManager configurationManager) {
		if(Objects.isNull(driver)) {
			throw new IllegalArgumentException(
					"Webdriver cannot be null...");
		}
		
		if(Objects.isNull(configurationManager)) {
			throw new IllegalArgumentException(
					"Configuration cannot be null...");
		}
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver,
				Duration.ofSeconds(configurationManager.getExplicitWait())
				);
	}
	
	//Visibility
	public WebElement waitForVisibility(final By locator) {
		validateLocator(locator);
		return wait.until(
				ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//Clickability
	public WebElement waitForClickability(final By locator) {
		validateLocator(locator);
		return wait.until(
				ExpectedConditions.elementToBeClickable(locator));
	}
	
	//ForPresence
	public WebElement waitForPresence(final By locator) {
		validateLocator(locator);
		return wait.until(
				ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//Invisibility
	public Boolean waitForInvisibility(final By locator) {
		validateLocator(locator);
		return wait.until(
				ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	//Wait for text
	public boolean waitForTest(final By locator, final String text) {
		validateLocator(locator);
		if(Objects.isNull(text) || text.isBlank()) {
			throw new IllegalArgumentException(
					"Expected text cannot be null or blank...");
		}
		
		return wait.until(
				ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	
	//Wait for title
	public boolean waitForTitle(final String title) {

        if (Objects.isNull(title) || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Expected title cannot be null or blank...");
        }

        return wait.until(
                ExpectedConditions.titleContains(title));
    }
	
	//Helper
	private void validateLocator(final By locator) {
		if(Objects.isNull(locator)) {
			throw new IllegalArgumentException(
					"Locator cannot be null...");
		}
	}
}
