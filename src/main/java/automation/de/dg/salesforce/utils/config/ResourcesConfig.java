package automation.de.dg.salesforce.utils.config;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;


/**
 * <b>De.Dg/Salesforce/Utils : Resource Config/b> Resource Config
 * * <i>Class functionality:</i><br>
 *  *  Class is used to set Resource Config property file.
 */

public class ResourcesConfig {

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
    private String getResourcesPath(String packageName) {
        String filePathString = getAbsolutePath() + "src/" + packageName + "/resources";
        File f = new File(filePathString);
        if (!f.exists())
            filePathString = getAbsolutePath();
        return filePathString;
    }
    public String getTestResourcesPath() {
        return getResourcesPath("test");
    }
    public String getInputDir() {
        return getTestResourcesPath() + "/test-data/inputDir";
    }

    private String getPomProperty(String propertyName) {
        Model model = null;
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try {
            model = reader.read(new FileReader(getAbsolutePath() + "/pom.xml"));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return model.getProperties().getProperty(propertyName);
    }

    public String getEnvironmentProperties() {
        if (System.getProperty("env.properties") == null) {
            return getAbsolutePath() + getPomProperty("env.properties");
        } else {
            return getAbsolutePath() + System.getProperty("env.properties");
        }
    }

    /*public String getEnvironmentProperties() {
        if (System.getProperty("env.properties") == null) {
            return getAbsolutePath() + getPomProperty("env.properties");
        } else {
            return getAbsolutePath() + System.getProperty("env.properties");
        }*/


}
