package com.banking.automation.factory;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.banking.automation.enums.BrowserType;
import com.banking.automation.exception.ConfigurationException;
import com.banking.automation.exception.DriverException;
import com.banking.automation.utils.LoggerUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class BrowserFactory {

    private BrowserFactory() {
        throw new UnsupportedOperationException(
                "BrowserFactory should not be instantiated."
        );
    }

    public static WebDriver createDriver(final BrowserType browserType) {

        return createDriver(browserType, false);
    }

    public static WebDriver createDriver(final BrowserType browserType,final boolean headless) {

        validateBrowserType(browserType);

        LoggerUtility.info(
                "Creating browser driver: "
                        + browserType
                        + " (headless="
                        + headless
                        + ")"
        );

        try {

            final WebDriver driver;

            switch (browserType) {

                case CHROME -> {

                    LoggerUtility.debug(
                            "Configuring ChromeDriver.");

                    WebDriverManager
                            .chromedriver()
                            .setup();

                    final ChromeOptions options = new ChromeOptions();

                    configureChromeOptions(
                            options,
                            headless);

                    driver = new ChromeDriver(options);
                }

                case FIREFOX -> {

                    LoggerUtility.debug(
                            "Configuring FirefoxDriver.");

                    WebDriverManager
                            .firefoxdriver()
                            .setup();

                    final FirefoxOptions options = new FirefoxOptions();

                    configureFirefoxOptions(
                            options,
                            headless);

                    driver = new FirefoxDriver(options);
                }

                case EDGE -> {

                    LoggerUtility.debug(
                            "Configuring EdgeDriver.");

                    WebDriverManager
                            .edgedriver()
                            .setup();

                    final EdgeOptions options =new EdgeOptions();

                    configureEdgeOptions(
                            options,
                            headless );

                    driver = new EdgeDriver(options);
                }

                default -> throw new ConfigurationException(
                        "Unsupported browser type: "+ browserType);
            }

            LoggerUtility.info(
                    "Browser driver created successfully: "
                            + browserType
                            + " (headless="
                            + headless
                            + ")"
            );

            return driver;

        } catch (RuntimeException e) {

            LoggerUtility.error(
                    "Failed to create browser driver: "
                            + browserType
                            + " (headless="
                            + headless
                            + ")",
                    e);
            throw new DriverException(
            		"Failed to create browser driver: "
            				+browserType,
            		e);
        }
    }

    private static void configureChromeOptions(final ChromeOptions options, final boolean headless) {

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
    }

    private static void configureFirefoxOptions(final FirefoxOptions options, final boolean headless) {

        if (headless) {
            options.addArguments("-headless");
        }

        options.addPreference(
                "dom.webnotifications.enabled",
                false);
    }

    private static void configureEdgeOptions(final EdgeOptions options,  final boolean headless) {

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
    }

    private static void validateBrowserType(final BrowserType browserType) {

        if (Objects.isNull(browserType)) {
            throw new ConfigurationException(
                    "Browser type cannot be null...");
        }
    }
}