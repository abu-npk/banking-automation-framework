package com.banking.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.banking.automation.driver.DriverFactory;

public final class ScreenshotUtility {
	private static final String SCREENSHOT_DIRECTORY="screenshots";
	private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
	
	private ScreenshotUtility() {
		throw new UnsupportedOperationException(
				"ScreenshotUtility should not be instantiated...");
	}
	
	public static String captureScreenshot(final String screenshotName) {
		validateScreenshotName(screenshotName);
		
		final WebDriver driver = DriverFactory.getDriver();
		
		if(!(driver instanceof TakesScreenshot)) {
			throw new IllegalStateException(
					"Current WebDriver does not support screenshots...");
		}
		
		final String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
		final String fileName = screenshotName + "_"+timestamp+ ".png";
		final Path screenshotDirectory = Paths.get(SCREENSHOT_DIRECTORY);
		final Path screenshotPath = screenshotDirectory.resolve(fileName);
		
		try {
			Files.createDirectories(screenshotDirectory);
			final File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(sourceFile.toPath(),screenshotPath, StandardCopyOption.REPLACE_EXISTING);
			
			return screenshotPath.toAbsolutePath().toString();
		} catch (IOException e) {
			throw new RuntimeException("Failed to save screenshot: "+ screenshotPath,
					e);
		}
	}
	
	public static byte[] captureScreenshotBytes() {

	    final WebDriver driver = DriverFactory.getDriver();

	    if (!(driver instanceof TakesScreenshot)) {
	        throw new IllegalStateException(
	                "Current WebDriver does not support screenshots..."
	        );
	    }

	    try {
	        return ((TakesScreenshot) driver)
	                .getScreenshotAs(OutputType.BYTES);

	    } catch (RuntimeException exception) {

	        LoggerUtility.error(
	                "Failed to capture screenshot bytes.",
	                exception
	        );

	        throw exception;
	    }
	}
	
	private static void validateScreenshotName(final String screenshotName) {
		if(Objects.isNull(screenshotName) || screenshotName.isBlank()) {
			throw new IllegalArgumentException(
					"Screenshot name cannot be null or blank");
		}
	}
}
