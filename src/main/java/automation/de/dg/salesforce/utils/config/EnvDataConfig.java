package automation.de.dg.salesforce.utils.config;

import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;


/**
 * <b>De.Dg/Salesforce/Utils : Environment Data Config/b> Environment Data Config
 * * <i>Class functionality:</i><br>
 *  *  Class is used to set Salesforce Environment data.
 */

public class EnvDataConfig {

    ResourcesConfig resourcesConfig;

    public EnvDataConfig() {
        resourcesConfig = new ResourcesConfig();
    }
    private static String removeTrailingSlash(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (url.endsWith("/")) {
                url = StringUtils.removeEnd(url, "/");
            } else {
                break;
            }
        }
        return url;
    }
    public String getURL(String applicationName) {
        return removeTrailingSlash(getEnvProperties().getProperty(applicationName + ".URL"));
    }

    private Properties getEnvProperties() {
        return getProperties(loadProperties(resourcesConfig.getEnvironmentProperties()));
    }

    private static Properties getProperties(Properties params) {
        Properties result = new Properties();
        Enumeration<?> names = params.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            result.put(name, params.get(name));
        }
        return result;
    }

    private static Properties loadProperties(String testDataFile) {
        Properties prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream(testDataFile);
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            prop.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String getAbsolutePath() {
        String absPath = Paths.get(".")
                .toAbsolutePath().normalize().toString().replace("\\", "/");

        String modulePath = this.getClass().getClassLoader().getResource(".").getPath();
        modulePath = modulePath.replace("\\", "/");
        modulePath = modulePath.replace("/target/test-classes", "");
        modulePath = modulePath.replace("/target/classes", "");
        modulePath = modulePath.replace(absPath, "");
        modulePath = modulePath.replace("//", "/");

        return absPath + modulePath;
    }

    public String getAPIURL(String applicationName) {
        return removeTrailingSlash(getEnvProperties().getProperty(applicationName + ".API.URL"));
    }


}
