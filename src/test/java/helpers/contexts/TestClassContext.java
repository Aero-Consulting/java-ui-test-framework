package helpers.contexts;

import org.testng.ITestContext;

public final class TestClassContext {
    private static final ThreadLocal<TestClassContext> context = new ThreadLocal<>();

    private final ITestContext testContext;
    private final String hub;
    private final String browserName;
    private final String browserVersion;
    private final String browserSize;
    private final String device;
    private final String skin;
    private final String type;

    public TestClassContext(ITestContext testContext, String hub, String browserName, String browserVersion, String browserSize, String device, String skin, String type) {
        this.testContext = testContext;
        this.hub = hub;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.browserSize = browserSize;
        this.device = device;
        this.skin = skin;
        this.type = type;
    }

    public static void init(
            ITestContext testContext,
            String hub,
            String browserName,
            String browserVersion,
            String browserSize,
            String device,
            String skin,
            String type
    ) {
        context.set(new TestClassContext(
                testContext,
                hub,
                browserName,
                browserVersion,
                browserSize,
                device,
                skin,
                type
        ));
    }

    public static TestClassContext get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }

    public static String hub() {
        return context.get().hub;
    }

    public static String browserName() {
        return context.get().browserName;
    }

    public static String browserVersion() {
        return context.get().browserVersion;
    }

    public static String browserSize() {
        return context.get().browserSize;
    }

    public static String device() {
        return context.get().device;
    }

    public static String skin() {
        return context.get().skin;
    }

    public static String type() {
        return context.get().type;
    }

    public String getHub() {
        return hub;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getBrowserSize() {
        return browserSize;
    }

    public String getDevice() {
        return device;
    }

    public String getSkin() {
        return skin;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TestClassContext{" +
                "testContext=" + testContext +
                ", hub='" + hub + '\'' +
                ", browserName='" + browserName + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", browserSize='" + browserSize + '\'' +
                ", device='" + device + '\'' +
                ", skin='" + skin + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
