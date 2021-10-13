package helpers;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public final class TestConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(TestConfiguration.class);
    @Expose
    private static volatile TestConfiguration instance;
    private Site site;
    private HashMap<String, Hub> hubs;
    private HashMap<String, Device> bsdevices;

    private TestConfiguration() {
    }

    public static TestConfiguration getInstance() {
        TestConfiguration result = instance;
        if (result != null) {
            return result;
        }
        synchronized (TestConfiguration.class) {
            if (instance == null) {
                instance = new TestConfiguration();
                instance.readConfiguration();
            }
            return instance;
        }
    }

    private void readConfiguration() {
        Gson gson = new Gson();
        JsonReader reader = null;
        String fileName = System.getProperty("testconfig", "testconfig.json");
        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        instance = gson.fromJson(reader, TestConfiguration.class);
    }

    public String getBaseUrl() {
        return site.baseUrl;
    }

    public URL getHubUrl(String hub) {
        Hub currentHub = hubs.get(hub);
        try {
            return new URL(currentHub.url);
        } catch (MalformedURLException e) {
            logger.error("Check URL for hub:{}", hub);
            throw new RuntimeException(e.getCause());
        }
    }

    public Device getDevice(String device) {
        Device currentDevice = bsdevices.get(device);
        if (currentDevice != null) {
            return currentDevice;
        } else {
            logger.error("Don't find device: {}", device);
            throw new RuntimeException("Don't find device: " + device);
        }
    }

    private class Site {
        private String baseUrl;
    }

    private class Hub {
        private String url;
    }

    public class Device {
        private String browserName;
        private String device;
        private String realMobile;
        private String os_version;

        public String getBrowserName() {
            return browserName;
        }

        public String getDevice() {
            return device;
        }

        public String getRealMobile() {
            return realMobile;
        }

        public String getOs_version() {
            return os_version;
        }
    }
}
