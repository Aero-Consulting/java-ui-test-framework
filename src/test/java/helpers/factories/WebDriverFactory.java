package helpers.factories;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public abstract class WebDriverFactory {
    public static final String REMOTE_WEB_DRIVER = "RemoteWebDriver";
    public static final String APPIUM_DRIVER = "AppiumDriver";
    public static final String ANDROID_DRIVER = "AndroidDriver";
    public static final String IOS_DRIVER = "IOSDriver";
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final int MAX_ATTEMPTS_CREATE_DRIVER = 3;

    public static WebDriver createDriver(String driver, URL hubUrl, DesiredCapabilities capabilities) {
        logger.info("Create driver: {}", capabilities);
        for (int attempt = 1; attempt <= MAX_ATTEMPTS_CREATE_DRIVER; attempt++) {
            RemoteWebDriver webDriver;
            try {
                webDriver = create(driver, hubUrl, capabilities);
            } catch (WebDriverException e) {
                logger.warn("Attempt to create driver: {}", attempt);
                logger.warn("Unable to create driver for {}\n{}\n{}", capabilities.getBrowserName(), e.getMessage(), e.getStackTrace());
                continue;
            } catch (Exception e) {
                logger.warn("Unable to create driver for {}\n{}\n{}", capabilities.getBrowserName(), e.getMessage(), e.getStackTrace());
                break;
            }
            logger.info("Driver was created. Session id: {}, capabilities: {}", webDriver.getSessionId(), webDriver.getCapabilities());
            return webDriver;
        }
        logger.error("Driver {} didn't create.", capabilities.getBrowserName());
        throw new RuntimeException("Driver didn't create.");
    }

    private static RemoteWebDriver create(String driver, URL hubUrl, DesiredCapabilities capabilities) {
        switch (driver) {
            case REMOTE_WEB_DRIVER:
                return new RemoteWebDriver(hubUrl, capabilities);
            case APPIUM_DRIVER:
                return new AppiumDriver(hubUrl, capabilities);
            case ANDROID_DRIVER:
                return new AndroidDriver(hubUrl, capabilities);
            case IOS_DRIVER:
                return new IOSDriver(hubUrl, capabilities);
            default:
                throw new IllegalArgumentException(String.format("Unknown driver '%s'.", driver));
        }
    }

    public static void adjustBrowserSize(WebDriver driver, String browserSize) {
        if (browserSize != null && !browserSize.isEmpty()) {
            String[] dimension = browserSize.split("x");
            int width = Integer.parseInt(dimension[0]);
            int height = Integer.parseInt(dimension[1]);
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
        } else {
            logger.warn("Unable to adjust browser size: {} ", browserSize);
        }
    }
}
