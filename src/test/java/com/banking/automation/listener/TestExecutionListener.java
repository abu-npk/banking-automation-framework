package com.banking.automation.listener;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.banking.automation.reporting.AllureUtility;
import com.banking.automation.utils.LoggerUtility;
import com.banking.automation.utils.ScreenshotUtility;

public final class TestExecutionListener implements ITestListener {

    @Override
    public void onStart(final ITestContext context) {

        LoggerUtility.info(
                "TestNG execution started: "
                        + context.getName());
    }

    @Override
    public void onFinish(final ITestContext context) {

        LoggerUtility.info(
                "TestNG execution finished: "
                        + context.getName()
                        + " | passed="
                        + context.getPassedTests().size()
                        + " | failed="
                        + context.getFailedTests().size()
                        + " | skipped="
                        + context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(final ITestResult result) {

        LoggerUtility.info(
                "TEST STARTED: "
                        + getTestName(result)
        );
    }

    @Override
    public void onTestSuccess(final ITestResult result) {

        LoggerUtility.info(
                "TEST PASSED: "
                        + getTestName(result));
    }

    @Override
    public void onTestFailure(final ITestResult result) {
    	
    	final String testName = getTestName(result);
    	
        LoggerUtility.error(
                "TEST FAILED: "
                        + testName,
                result.getThrowable());
        
        attachFailureEvidence(testName);
    }

    @Override
    public void onTestSkipped(final ITestResult result) {

        LoggerUtility.warn(
                "TEST SKIPPED: "
                        + getTestName(result));
    }
    
    private void attachFailureEvidence(final String testName) {

        try {

            final byte[] screenshot =
                    ScreenshotUtility.captureScreenshotBytes();

            AllureUtility.attachScreenshot(
                    "Failure Screenshot - " + testName,
                    screenshot
            );

            LoggerUtility.info(
                    "Failure screenshot attached to Allure: "
                            + testName
            );

        } catch (RuntimeException exception) {

            LoggerUtility.error(
                    "Unable to attach failure evidence for: "
                            + testName,
                    exception
            );
        }
    }
    
    private String getTestName(final ITestResult result) {

        return result.getTestClass().getName()
                + "#"
                + result.getMethod().getMethodName();
    }

}
