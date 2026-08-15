package com.banking.automation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.banking.automation.driver.DriverFactory;

public class WindowUtility {
	
	private WindowUtility() {
		
        throw new UnsupportedOperationException(
                "WindowUtility should not be instantiated."
        );
    }

    public static String getCurrentWindowHandle() {

        return getDriver().getWindowHandle();
    }

    public static Set<String> getAllWindowHandles() {

        return getDriver().getWindowHandles();
    }

    public static List<String> getWindowHandlesAsList() {

        return new ArrayList<>(
                getDriver().getWindowHandles());
    }

    public static void switchToWindow(final String windowHandle) {

        validateWindowHandle(windowHandle);

        final Set<String> availableHandles =
                getDriver().getWindowHandles();

        if (!availableHandles.contains(windowHandle)) {
            throw new IllegalArgumentException(
                    "Window handle does not exist: "
                            + windowHandle
            );
        }

        getDriver().switchTo()
                .window(windowHandle);
    }

    public static void switchToNewWindow() {

        final String currentHandle = getCurrentWindowHandle();

        for (final String handle : getAllWindowHandles()) {

            if (!handle.equals(currentHandle)) {
                switchToWindow(handle);
                return;
            }
        }

        throw new IllegalStateException(
                "No additional browser window or tab was found...");
    }

    public static void switchToWindowByTitle(final String expectedTitle) {

        validateValue(
                expectedTitle,
                "Expected title");

        final WebDriver driver = getDriver();

        final String originalHandle = driver.getWindowHandle();

        for (final String handle : driver.getWindowHandles()) {

            driver.switchTo().window(handle);

            if (expectedTitle.equals(driver.getTitle())) {
                return;
            }
        }

        driver.switchTo().window(originalHandle);

        throw new IllegalStateException(
                "No browser window found with title: "
                        + expectedTitle);
    }

    public static void closeCurrentWindow() {

        getDriver().close();
    }

    public static void switchToOriginalWindow(final String originalWindowHandle) {

        switchToWindow(originalWindowHandle);
    }

    public static int getWindowCount() {

        return getDriver()
                .getWindowHandles()
                .size();
    }

    private static WebDriver getDriver() {

        return DriverFactory.getDriver();
    }

    private static void validateWindowHandle(final String windowHandle) {

        validateValue(
                windowHandle,
                "Window handle");
    }

    private static void validateValue(final String value, final String fieldName) {

        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be null or blank...");
        }
    }
}
