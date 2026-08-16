package com.banking.automation.reporting;

import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;

public final class AllureUtility {

    private AllureUtility() {
        throw new UnsupportedOperationException(
                "AllureUtility should not be instantiated...");
    }

    public static void attachScreenshot(
            final String name,
            final byte[] screenshot) {

        if (screenshot == null || screenshot.length == 0) {
            return;
        }

        Allure.addAttachment(
                name,
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png");
    }
}