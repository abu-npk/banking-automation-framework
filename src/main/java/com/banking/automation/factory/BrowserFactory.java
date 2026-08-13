package com.banking.automation.factory;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.banking.automation.enums.BrowserType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	private BrowserFactory() {
		throw new UnsupportedOperationException(
				"BrowserFactory should not be instantiated");
	}
	
	public static WebDriver createDriver(final BrowserType browserType) {
		
		if (Objects.isNull(browserType)) {
			throw new IllegalArgumentException(
					"Browser type cannot be null");
		}
		
		WebDriver driverSelection = switch(browserType) {
		case CHROME -> createChromeDriver();
		case FIREFOX -> createFirefoxDriver();
		};
		
		return driverSelection;
	}
	
	public static WebDriver createChromeDriver() {
		WebDriverManager.chromedriver().setup();
		final ChromeOptions chromeOptions = new ChromeOptions();
		return new ChromeDriver(chromeOptions);
	}
	
	public static WebDriver createFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		final FirefoxOptions firefoxOptions = new FirefoxOptions();
		return new FirefoxDriver(firefoxOptions);
	}
}
