package helpers.listeners;

import org.testng.IMethodSelector;
import org.testng.IMethodSelectorContext;
import org.testng.ITestNGMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class CustomMethodSelector implements IMethodSelector {
    private static final List<String> specifiedGroups;
    private static final boolean isSpecificTestRun;

    static {
        String groups = System.getProperty("testids", "");
        specifiedGroups = asList(groups.split("[,\\s]+"));
        isSpecificTestRun = !groups.isEmpty();
    }

    @Override
    public boolean includeMethod(IMethodSelectorContext context, ITestNGMethod method, boolean isTestMethod) {
        if (!isSpecificTestRun) {
            return true;
        }
        Stream<String> groupsTest = Arrays.stream(method.getGroups());
        boolean include = groupsTest.anyMatch(specifiedGroups::contains);
        if (include) {
            return true;
        } else {
            if (context != null) {
                context.setStopped(true);
            }
            return false;
        }
    }

    @Override
    public void setTestMethods(List<ITestNGMethod> testMethods) {

    }
}
