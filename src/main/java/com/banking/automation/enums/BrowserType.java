package com.banking.automation.enums;

import com.banking.automation.exception.ConfigurationException;
import java.util.Arrays;

public enum BrowserType {
	CHROME,
	FIREFOX,
	EDGE;
	
	public static BrowserType fromValue(final String value) {

        if (value == null || value.isBlank()) {
            throw new ConfigurationException(
                    "Browser configuration cannot be null or blank.");
        }
        
        return Arrays.stream(values())
                .filter(browser ->
                browser.name().equalsIgnoreCase(value.trim()))
        .findFirst()
        .orElseThrow(() ->
                new ConfigurationException(
                        "Unsupported browser: " + value));
    }
}
