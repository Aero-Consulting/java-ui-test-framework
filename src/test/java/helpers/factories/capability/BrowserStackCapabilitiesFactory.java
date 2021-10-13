package helpers.factories.capability;

import helpers.TestConfiguration;
import helpers.contexts.TestMethodContext;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackCapabilitiesFactory implements ICapabilitiesFactory {

    @Override
    public DesiredCapabilities getCapabilities() {
        String nameDevice = TestMethodContext.device();
        TestConfiguration.Device device = TestConfiguration.getInstance().getDevice(nameDevice);
        String methodName = TestMethodContext.methodName();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", device.getDevice());
        capabilities.setCapability("realMobile", device.getRealMobile());
        capabilities.setCapability("os_version", device.getOs_version());
        capabilities.setCapability("newCommandTimeout", 60 * 5);
        capabilities.setCapability("name", methodName);
        return capabilities;
    }
}
