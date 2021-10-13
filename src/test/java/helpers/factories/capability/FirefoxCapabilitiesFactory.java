package helpers.factories.capability;

import helpers.contexts.TestMethodContext;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxCapabilitiesFactory implements ICapabilitiesFactory {
    @Override
    public DesiredCapabilities getCapabilities() {
        String browserName = TestMethodContext.browserName();
        String browserVersion = TestMethodContext.browserVersion();
        String methodName = TestMethodContext.methodName();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(browserVersion);
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.setCapability("moz:webdriverClick", false);
        firefoxOptions.setAcceptInsecureCerts(true);
        capabilities.merge(firefoxOptions);
        return capabilities;
    }
}
