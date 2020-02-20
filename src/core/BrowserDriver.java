package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ConfigReader;
import utilities.PathsUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {
    private static BrowserType browserType;
    private ConfigReader config;
    private static WebDriver webDriver;

    public BrowserDriver() {
        config = new ConfigReader();
        browserType = config.getBrowser();
    }

    public WebDriver getWebDriver() {
        if (isBrowserOpened()) {
            return webDriver;
        } else {
            forceBrowserDriver(browserType);
            return webDriver;
        }
    }

    public ConfigReader getConfig() {
        return config;
    }

    private static Boolean isBrowserOpened() {
        return webDriver != null;
    }

    private static void forceBrowserDriver(BrowserType browser) {
        if (isBrowserOpened()) {
            quit();
        }
        switch (browser) {
            case CHROME: {
                System.setProperty("webdriver.CHROME.driver", "chromedriver.exe");
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", PathsUtil.GetDownloadFolder());

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                webDriver = new ChromeDriver(options);
                break;
            }
            case FIREFOX: {
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;
            }
            case EDGE: {
                System.setProperty("webdriver.EDGE.driver", "MicrosoftWebDriver.exe");
                webDriver = new EdgeDriver();
                break;
            }
            default: {
                throw new Error("Incorrect browser in settings");
            }
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public URI getUri() {
        try {
            return new URI(getWebDriver().getCurrentUrl());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToNewWindow() {
        String parentWindow = getWebDriver().getWindowHandle();
        Set<String> windowHandles = getWebDriver().getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindow)) {
                getWebDriver().switchTo().window(windowHandle);
            }
        }
    }

    public static void quit() {
        if (isBrowserOpened()) {
            webDriver.quit();
        }
    }

    public Object executeScript(String script) {
        return ((JavascriptExecutor)webDriver).executeScript(script);
    }

    public Object executeScript(String script, Object[] parameters) {
        return ((JavascriptExecutor)webDriver).executeScript(script, parameters);
    }
}