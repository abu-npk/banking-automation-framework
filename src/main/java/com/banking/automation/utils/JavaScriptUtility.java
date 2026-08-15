package com.banking.automation.utils;

import java.util.Objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.banking.automation.driver.DriverFactory;

public final class JavaScriptUtility {

    private JavaScriptUtility() {
        throw new UnsupportedOperationException(
                "JavaScriptUtility should not be instantiated...");
    }

    public static Object executeScript(final String script, final Object... arguments) {

        validateScript(script);
        final JavascriptExecutor executor = getJavascriptExecutor();

        return executor.executeScript(script, arguments);
    }

    public static void scrollIntoView(final WebElement element) {

        validateElement(element);
        executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});",
        		element);
    }

    public static void click(final WebElement element) {

        validateElement(element);
        executeScript("arguments[0].click();",
                element);
    }

    public static void scrollToTop() {

        executeScript(
                "window.scrollTo(0, 0);"
        );
    }

    public static void scrollToBottom() {

        executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");
    }

    public static String getPageReadyState() {

        final Object result = executeScript(
                "return document.readyState;");

        return Objects.toString(result, "");
    }

    public static String getDocumentTitle() {

        final Object result = executeScript(
                "return document.title;");

        return Objects.toString(result, "");
    }

    private static JavascriptExecutor getJavascriptExecutor() {

        final WebDriver driver = DriverFactory.getDriver();

        if (!(driver instanceof JavascriptExecutor)) {
            throw new IllegalStateException(
                    "Current WebDriver does not support JavaScript execution...");
        }

        return (JavascriptExecutor) driver;
    }

    private static void validateScript(final String script) {

        if (Objects.isNull(script) || script.isBlank()) {

            throw new IllegalArgumentException(
                    "JavaScript cannot be null or blank...");
        }
    }

    private static void validateElement(final WebElement element) {

        if (Objects.isNull(element)) {
            throw new IllegalArgumentException(
                    "WebElement cannot be null...");
        }
    }
}