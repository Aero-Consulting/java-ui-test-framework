package helpers.factories.capability;

import helpers.contexts.TestMethodContext;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class ChromeCapabilitiesFactory implements ICapabilitiesFactory {
    @Override
    public DesiredCapabilities getCapabilities() {
        String browserName = TestMethodContext.browserName();
        String browserVersion = TestMethodContext.browserVersion();
        String skin = TestMethodContext.skin();
        String methodName = TestMethodContext.methodName();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(browserVersion);
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("skin", skin);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        capabilities.merge(chromeOptions);
        return capabilities;
    }
}
