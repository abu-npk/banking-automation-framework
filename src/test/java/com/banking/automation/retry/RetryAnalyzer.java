package com.banking.automation.retry;

import org.openqa.selenium.WebDriverException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.banking.automation.exception.DriverException;
import com.banking.automation.utils.LoggerUtility;

public final class RetryAnalyzer implements IRetryAnalyzer {
	private static final int MAX_RETRY_COUNT = 1;
	
	private int retryCount;
	
	@Override
	public boolean retry(final ITestResult result) {

        final Throwable throwable = result.getThrowable();

        if (!isRetryable(throwable)) {
            LoggerUtility.debug(
                    "Test failure is not retryable: "
                            + result.getName());

            return false;
        }

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            LoggerUtility.warn(
                    "Retrying test: "
                            + result.getName()
                            + " | attempt="
                            + (retryCount + 1)
                            + " | maxRetries="
                            + MAX_RETRY_COUNT);

            return true;
        }

        LoggerUtility.error(
                "Retry limit reached for test: "
                        + result.getName());

        return false;
    }
	
	private boolean isRetryable(final Throwable throwable) {
		if(throwable == null) {
			return false;
		}
		return throwable instanceof DriverException || throwable instanceof WebDriverException;
	}
}
