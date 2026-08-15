package com.banking.automation.utils;

import java.util.Objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.banking.automation.driver.DriverFactory;

public class ActionsUtility {
	private ActionsUtility() {
		throw new UnsupportedOperationException(
				"ActionsUtility should not be instantiated...");
	}
	
	//Move
    public static void moveToElement(final WebElement element) {

        validateElement(element);
        createActions()
                .moveToElement(element)
                .perform();
    }

    public static void click(final WebElement element) {

        validateElement(element);
        createActions()
                .click(element)
                .perform();
    }

    public static void doubleClick(final WebElement element) {

        validateElement(element);
        createActions()
                .doubleClick(element)
                .perform();
    }

    public static void contextClick(final WebElement element) {

        validateElement(element);
        createActions()
                .contextClick(element)
                .perform();
    }

    public static void dragAndDrop(final WebElement source, final WebElement target) {

        validateElement(source);
        validateElement(target);
        createActions()
                .dragAndDrop(source, target)
                .perform();
    }

    public static void sendKeys(final WebElement element, final CharSequence... keys) {

        validateElement(element);
        if (Objects.isNull(keys) || keys.length == 0) {
            throw new IllegalArgumentException(
                    "Keyboard input cannot be null or empty...");
        }

        createActions()
                .click(element)
                .sendKeys(keys)
                .perform();
    }

    public static void sendKey(final Keys key) {

        if (Objects.isNull(key)) {
            throw new IllegalArgumentException(
                    "Keyboard key cannot be null...");
        }

        createActions()
                .sendKeys(key)
                .perform();
    }

    private static Actions createActions() {

        final WebDriver driver = DriverFactory.getDriver();
        return new Actions(driver);
    }

    private static void validateElement(final WebElement element) {

        if (Objects.isNull(element)) {
            throw new IllegalArgumentException(
                    "WebElement cannot be null...");
        }
    }
}
