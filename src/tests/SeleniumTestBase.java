package tests;

import core.BrowserDriver;
import logger.CustomLog;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class SeleniumTestBase {
    private BrowserDriver driver;

    BrowserDriver getBrowser() {
        if (driver != null) {
            return driver;
        } else {
            driver = new BrowserDriver();
            return driver;
        }
    }

    @BeforeSuite
    public void setUp() {
        CustomLog.info("Test start time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logErrorAndTakeScreenshot("Test fails with reason: " + result.getThrowable(), result);

            LogEntries logs = driver.getWebDriver().manage().logs().get(LogType.BROWSER);
            List<LogEntry> logEntries = logs.filter(Level.SEVERE);
            for (LogEntry logEntry : logEntries) {
                CustomLog.error("Java Script error has been detected:");
                CustomLog.error(new Date(logEntry.getTimestamp()) + " " + logEntry.getLevel() + " " + logEntry.getMessage());
            }
        }
    }

    private void logErrorAndTakeScreenshot(String message, ITestResult result) {
        CustomLog.errorWithScreenshot(message, driver, result.getMethod().getMethodName());
    }

    @AfterSuite
    public void tearDown() {
        CustomLog.info("Test end time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @AfterClass
    public static void generalTearDown() {
        CustomLog.debug("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        BrowserDriver.quit();
    }
}