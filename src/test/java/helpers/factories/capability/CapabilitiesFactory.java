package helpers.factories.capability;

import helpers.contexts.TestMethodContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapabilitiesFactory {
    private static final Logger logger = LoggerFactory.getLogger(CapabilitiesFactory.class);

    private static ICapabilitiesFactory getFactory() {
        String device = TestMethodContext.device();
        if (device != null) return new BrowserStackCapabilitiesFactory();
        String browser = TestMethodContext.browserName().toLowerCase();
        switch (browser) {
            case "chrome":
                return new ChromeCapabilitiesFactory();
            case "firefox":
                return new FirefoxCapabilitiesFactory();
            default:
                throw new IllegalArgumentException(String.format("Unknown browser '%s'.", browser));
        }
    }


    public static DesiredCapabilities getCapabilities() {
        return getFactory().getCapabilities();
    }
}
