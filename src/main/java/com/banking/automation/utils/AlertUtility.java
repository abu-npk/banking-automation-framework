package com.banking.automation.utils;

import java.util.Objects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.banking.automation.driver.DriverFactory;

public final class AlertUtility {

    private AlertUtility() {
        throw new UnsupportedOperationException(
                "AlertUtility should not be instantiated...");
    }

    public static void accept() {

        getAlert().accept();
    }

    public static void dismiss() {

        getAlert().dismiss();
    }

    public static String getText() {

        return getAlert().getText();
    }

    public static void sendKeys(final String text) {
    	
        if (Objects.isNull(text)) {
            throw new IllegalArgumentException(
                    "Alert input text cannot be null...");
        }
        getAlert().sendKeys(text);
    }

    public static void sendKeysAndAccept(final String text) {
        sendKeys(text);
        accept();
    }

    private static Alert getAlert() {

        final WebDriver driver =DriverFactory.getDriver();
        try {
            return driver.switchTo().alert();
        } catch (NoAlertPresentException exception) {
            throw new IllegalStateException(
                    "No browser alert is currently present.",
                    exception);
        }
    }
}