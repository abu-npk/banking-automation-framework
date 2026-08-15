package com.banking.automation.utils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public final class DropdownUtility {
	private DropdownUtility() {
		throw new UnsupportedOperationException(
				"Dropdownutility should not be instantiated...");
	}
	
	public static void selectByVisibleText(final WebElement dropdown, final String visibleText) {

        validateDropdown(dropdown);
        validateValue(visibleText, "Visible text");

        createSelect(dropdown)
                .selectByVisibleText(visibleText);
    }

    public static void selectByValue(final WebElement dropdown, final String value) {

        validateDropdown(dropdown);
        validateValue(value, "Option value");

        createSelect(dropdown)
                .selectByValue(value);
    }

    public static void selectByIndex(final WebElement dropdown, final int index) {

        validateDropdown(dropdown);

        if (index < 0) {
            throw new IllegalArgumentException(
                    "Dropdown index cannot be negative...");
        }

        createSelect(dropdown)
                .selectByIndex(index);
    }

    public static WebElement getSelectedOption(final WebElement dropdown) {

        validateDropdown(dropdown);

        return createSelect(dropdown)
                .getFirstSelectedOption();
    }

    public static String getSelectedText(final WebElement dropdown) {

        return getSelectedOption(dropdown)
                .getText();
    }

    public static List<WebElement> getOptions(final WebElement dropdown) {

        validateDropdown(dropdown);

        return createSelect(dropdown)
                .getOptions();
    }

    public static List<String> getOptionTexts(final WebElement dropdown) {

        return getOptions(dropdown)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    
    private static Select createSelect(
            final WebElement dropdown) {

        return new Select(dropdown);
    }
    
    //Helpers
    private static void validateDropdown(final WebElement dropdown) {

        if (Objects.isNull(dropdown)) {
            throw new IllegalArgumentException(
                    "Dropdown element cannot be null...");
        }
    }

    private static void validateValue(final String value, final String fieldName) {

        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be null or blank...");
        }
    }
	
}
