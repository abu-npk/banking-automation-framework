package com.banking.automation.utils;

import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.banking.automation.driver.DriverFactory;

public class FrameUtility {
	private FrameUtility() {
		throw new UnsupportedOperationException(
				"FrameUtility should not be instantiated...");
	}
	
	public static void switchToFrame(final WebElement frame) {
		validateFrame(frame);
		try {
			getDriver().switchTo().frame(frame);
		}catch(NoSuchFrameException e) {
			throw new IllegalStateException(
					"Unable to switch to the supplied frame element...");
		}
	}
	
	public static void switchToFrame(final By locator) {
		validateLocator(locator);
		try {
			WebElement frameElement = getDriver().findElement(locator);
			getDriver().switchTo().frame(frameElement);
		} catch(NoSuchFrameException e) {
			throw new IllegalStateException("Unable to switch to frame: "+locator,e);
		}
	}
	
	public static void switchToFrame(final int index) {

        if (index < 0) {
            throw new IllegalArgumentException(
                    "Frame index cannot be negative...");
        }

        try {
            getDriver().switchTo().frame(index);

        } catch (NoSuchFrameException e) {

            throw new IllegalStateException(
                    "Unable to switch to frame at index: " + index,
                    e);
        }
    }

    public static void switchToParentFrame() {

        getDriver()
                .switchTo()
                .parentFrame();
    }

    public static void switchToDefaultContent() {

        getDriver()
                .switchTo()
                .defaultContent();
    }

    public static int getFrameCount() {

        return getDriver()
                .findElements(By.cssSelector("iframe, frame"))
                .size();
    }

    private static WebDriver getDriver() {

        return DriverFactory.getDriver();
    }

    private static void validateFrame(final WebElement frame) {

        if (Objects.isNull(frame)) {
            throw new IllegalArgumentException(
                    "Frame element cannot be null...");
        }
    }

    private static void validateLocator(final By locator) {

        if (Objects.isNull(locator)) {
            throw new IllegalArgumentException(
                    "Frame locator cannot be null...");
        }
    }
}
