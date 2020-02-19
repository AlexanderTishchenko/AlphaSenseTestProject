package utilities;

import core.BrowserDriver;
import pages.PageBase;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Wait {
    public static void waitForPageLoad(BrowserDriver driver, Class clazz) {
        int timeout = PageBase.getPageLoadingTimeout(clazz);
        LocalDateTime date = LocalDateTime.now();
        waitForUri(driver, clazz, timeout);
        timeout = timeout - (int) date.until(LocalDateTime.now(), ChronoUnit.SECONDS);
        waitForAjax(driver, timeout);
        timeout = timeout - (int) date.until(LocalDateTime.now(), ChronoUnit.SECONDS);
        waitForJs(driver, timeout);
    }

    public static void waitForJs(BrowserDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeout);
        ExpectedCondition<Boolean> jsLoad = driver12 -> driver.executeScript("return document.readyState")
                .toString().equals("complete");
        wait.until(jsLoad);
    }

    public static void waitForUri(BrowserDriver driver, Class clazz, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeout);
        ExpectedCondition<Boolean> uriMatches = driver1 -> UriBuilder.isUriMatches(driver, clazz, driver.getUri());
        try {
            wait.until(uriMatches);
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Not expected URI of '%s'. Expected: '%s'. But was: '%s'",
                    clazz.getTypeName(), UriBuilder.getUri(driver, clazz), driver.getWebDriver().getCurrentUrl()));
        }
    }

    public static void waitForAjax(BrowserDriver driver, int timeout) {
        WebDriverWait waitOfStart = new WebDriverWait(driver.getWebDriver(), 1);
        waitOfStart.withTimeout(Duration.ofMillis(100));
        WebDriverWait waitOfEnd = new WebDriverWait(driver.getWebDriver(), timeout);
        try {
            waitOfStart.until(driver1 -> isQueriesInProgress(driver));
        } catch (Exception e) {
            return;
        }
        waitOfEnd.until(driver1 -> !isQueriesInProgress(driver));
    }

    private static boolean isQueriesInProgress(BrowserDriver driver) {
        Boolean isJQueryDefined;
        try {
            isJQueryDefined = (Boolean) driver.executeScript("return window.$ != undefined");
        } catch (NullPointerException e) {
            return false;
        }

        if (isJQueryDefined) {
            try {
                return (Boolean) driver.executeScript("return $.active != 0");
            } catch (WebDriverException | NullPointerException e) {
                return false;
            }
        }
        return false;
    }
}