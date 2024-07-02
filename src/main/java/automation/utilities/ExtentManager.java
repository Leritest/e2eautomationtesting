package automation.utilities;

import automation.constants.ReportNameConstants;
import automation.listeners.ExtentApiListener;
import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.Header;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>PAGES : UTILITIES</b> [Extent]: Extent Manager
 */
public class ExtentManager {
    public static String fileName;
    private static String environment;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        String app = Page.config.getProperty("application");
        //htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(app + " - Extent Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(app + " Test Automation Report");

        environment = Page.config.getProperty("sutUrl");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", Page.config.getProperty("author"));
        extent.setSystemInfo("Application", app);
        extent.setSystemInfo("Environment", environment);
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        return extent;
    }

    /**
     * <b>[Method]</b> - Capture Screenshot<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality captures Screenshot from present page<br>
     *
     * @param element By - takes which element needs to be captured according to the test scenario.
     * @throws IOException to be handled when this method si called
     */
    public static void captureScreenshot(By element) throws IOException {
        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
        File screenshot;
        WebElement elementToast;

        if (element != null) {
            elementToast = Page.driver.findElement(element);
            screenshot = elementToast.getScreenshotAs(OutputType.FILE);

            if (elementToast.getAttribute("class").contains("error")) {
                ExtentListeners.test.fail(MediaEntityBuilder.createScreenCaptureFromPath(fileName, "The toast message is captured.").build());
            }
        } else {
            screenshot = ((TakesScreenshot) Page.driver).getScreenshotAs(OutputType.FILE);
        }

        FileUtils.copyFile(screenshot, new File(".//target//surefire-reports//html//" + fileName));
    }

    /**
     * <b>[Method]</b> -Get Current Date Time.<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Method returns local date time as formatted
     *
     * @return String array current date
     */
    public static String[] getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String currentDate = dateTimeFormatter.format(currentDateTime);
        return currentDate.split(" ");
    }

    public static Map<String, File> getReportPathMap() {
        String[] currentDate = getCurrentDateTime();
        File archiveFolder = new File(".\\target\\surefire-reports\\html\\archive\\");
        File archiveSubFolder = new File(archiveFolder + "/" + currentDate[0].replace("/", "-"));
        File sourceFolder = new File(".\\target\\surefire-reports\\html\\");
        File destinationFolder = new File(
                archiveFolder + "/" + currentDate[0].replace("/", "-") + "/" + currentDate[1].replace(":", "-"));
        Map<String, File> reportPathMap = new HashMap<>();
        reportPathMap.put(ReportNameConstants.DESTINATION_FOLDER, destinationFolder);
        reportPathMap.put(ReportNameConstants.ARCHIVE_FOLDER, archiveFolder);
        reportPathMap.put(ReportNameConstants.ARCHIVE_SUB_FOLDER, archiveSubFolder);
        reportPathMap.put(ReportNameConstants.SOURCE_FOLDER, sourceFolder);
        return reportPathMap;
    }

    /**
     * <b>[Method]</b> -Log info details.<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creating an info label in blue color with given log info for
     * used to log request method, url, request body info logging etc.
     *
     * @param log String log info
     */
    public static void logInfoDetails(String log) {
        ExtentListeners.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.BLUE));
    }

    /**
     * <b>[Method]</b> -Log pass details.<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creating a pass label in green color with given log info for reporting
     *
     * @param log String log info
     */

    public static void logPassDetails(String log) {
        ExtentListeners.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    /**
     * <b>[Method]</b> -Log fail details.<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creating a fail label in red color with given log info for reporting
     *
     * @param log String log info
     */
    public static void logFailureDetails(String log) {
        ExtentListeners.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    /**
     * <b>[Method]</b> -Log exception details.<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creating an exception section with fail label in red color with given log info for reporting
     *
     * @param log String log info
     */
    public static void logExceptionDetails(String log) {
        ExtentListeners.extentTest.get().fail(log);
    }

    /**
     * <b>[Method]</b> -Log info details without label color.<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creating info label in blue color but the text it does not have no a background color
     *
     * @param log String log info
     */
    public static void logInfoDetailsWithoutLabelColor(String log) {
        ExtentListeners.extentTest.get().info(log);
    }


    /**
     * <b>[Method]</b> -Log Headers<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to have more readable header format on the report
     * It generates a header table with key value pairs
     *
     * @param headersList headerList
     */

    public static void logHeaders(List<Header> headersList) {
        String[][] arrayHeaders = headersList.stream().map(header -> new String[]{header.getName(), header.getValue()})
                .toArray(String[][]::new);
        ExtentListeners.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
    }

    /**
     * <b>[Method]</b> -Log JSON<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to have a formatted JSON request and response body on the report
     *
     * @param json String
     */
    public static void logJSON(String json) {
        ExtentListeners.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

}