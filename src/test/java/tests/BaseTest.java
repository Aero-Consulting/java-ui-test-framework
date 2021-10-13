package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.TestConfiguration;
import helpers.contexts.TestClassContext;
import helpers.contexts.TestMethodContext;
import helpers.factories.WebDriverFactory;
import helpers.factories.capability.CapabilitiesFactory;
import helpers.factories.objects.ObjectFactory;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private final TestConfiguration testConfiguration = TestConfiguration.getInstance();

    @BeforeSuite
    public void beforeSuite() {
        Configuration.reopenBrowserOnFail = true;
        Configuration.baseUrl = testConfiguration.getBaseUrl();
        Configuration.timeout = 5000;
    }

    @BeforeTest
    public void beforeTest() {
    }

    @BeforeClass
    @Parameters({"hub", "browserName", "browserVersion", "browserSize", "device", "skin", "type"})
    public void beforeClass(ITestContext testContext,
                            @Optional("local") String hub,
                            @Optional("chrome") String browserName,
                            @Optional("") String browserVersion,
                            @Optional("1920x1080") String browserSize,
                            @Optional String device,
                            @Optional String skin,
                            @Optional("desktop") String type) {
        TestClassContext.init(testContext, hub, browserName, browserVersion, browserSize, device, skin, type);
        ObjectFactory.initObjects(this);
    }

    @BeforeMethod
    @Parameters({"device", "skin", "driver"})
    public void beforeMethod(Method method,
                             @Optional String skin,
                             @Optional String device,
                             @Optional("RemoteWebDriver") String driver) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        TestMethodContext.init(method);
        DesiredCapabilities capabilities = CapabilitiesFactory.getCapabilities();

        WebDriver webDriver = WebDriverFactory.createDriver(
                driver,
                testConfiguration.getHubUrl(TestMethodContext.hub()),
                capabilities
        );
        if (device == null && skin == null) {
            WebDriverFactory.adjustBrowserSize(webDriver, TestMethodContext.browserSize());
        }
        WebDriverRunner.setWebDriver(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        TestMethodContext.remove();
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }

    @AfterClass
    public void afterClass(ITestContext testContext) {
        TestClassContext.remove();
    }

    @AfterTest
    protected void afterTest() {
    }

    @AfterSuite
    public void afterSuite() {
    }
}



