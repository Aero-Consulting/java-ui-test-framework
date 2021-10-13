package helpers.contexts;

import java.lang.reflect.Method;

public class TestMethodContext {
    private static final ThreadLocal<TestMethodContext> context = ThreadLocal.withInitial(TestMethodContext::new);

    private TestClassContext testContext;
    private Method testMethod;

    private TestMethodContext() {
        testContext = TestClassContext.get();
    }

    public static void init(Method method) {
        TestMethodContext currentContext = context.get();
        currentContext.testMethod = method;
    }

    public static TestMethodContext get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }

    public static String methodName() {
        return get().testMethod.getName();
    }

    public static String hub() {
        return get().getTestContext().getHub();
    }

    public static String browserName() {
        return get().getTestContext().getBrowserName();
    }

    public static String browserVersion() {
        return get().getTestContext().getBrowserVersion();
    }

    public static String browserSize() {
        return get().getTestContext().getBrowserSize();
    }

    public static String device() {
        return get().getTestContext().getDevice();
    }

    public static String skin() {
        return get().getTestContext().getSkin();
    }

    public static String type() {
        return get().getTestContext().getType();
    }

    public TestClassContext getTestContext() {
        return testContext;
    }

    public Method getTestMethod() {
        return testMethod;
    }
}
