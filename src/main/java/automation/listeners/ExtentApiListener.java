package automation.listeners;

import automation.constants.ReportNameConstants;
import automation.properties.PropertyManager;
import automation.utilities.ExtentManager;
import automation.utilities.ProjectUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;

/**
 * <b>LISTENERS</b> [Extent]: Extent Api Listener
 */

public class ExtentApiListener implements ITestListener, ISuiteListener {

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    protected static ExtentTest test;
    private static ExtentReports extentReports;
    ProjectUtils archiveReport = new ProjectUtils();
    Map<String, File> reportPathMap = ExtentManager.getReportPathMap();

    /**
     * <b>[Method]</b> - On Start<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creates instance of extent reports<br>
     *
     * @param context ITestContext
     */
    public void onStart(ITestContext context) {
        extentReports = ExtentManager.createInstance(".\\target\\surefire-reports\\html\\" + "RestAssured_Extent_Report");
        // import values from config.properties file and store it
        PropertyManager property = new PropertyManager();
        try {
            PropertyManager.ConfigFileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        extentTest = new ThreadLocal<>();
    }

    /**
     * <b>[Method]</b> - On Finish<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality writes log into report.<br>
     *
     * @param context ITestContext
     */

    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    /**
     * <b>[Method]</b> - On Test Start<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality prepares an extent test object with test class name - method name.<br>
     * i.e. testcases.restAssuredAPI.accounts.CreateBusinessAccountAPITest - createBusinessAccountTest
     *
     * @param result ITestContext
     */
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    /**
     * <b>[Method]</b> - On Test Failure<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality writes Error log into report
     * and also a formatted exception log part.<br>
     *
     * @param result ITestResult
     */

    public void onTestFailure(ITestResult result) {
        ExtentManager.logFailureDetails(result.getThrowable().getMessage());

        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",", "<br>");
        String formattedTrace = "<details>\n" +
                " <summary> Click Here to See Exception Logs</summary>\n" +
                " " + stackTrace + "\n" +
                "</details>\n";
        ExtentManager.logExceptionDetails(formattedTrace);
    }

    /**
     * <b>[Method]</b> - On Start<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality deletes related files from source folder on the beginning of the test.
     * i.e. RestAssured_Extent_Report.html <br>
     *
     * @param suite ISuite
     */

    public void onStart(ISuite suite) {
        archiveReport.fileCleanUp(reportPathMap.get(ReportNameConstants.SOURCE_FOLDER));
    }

    /**
     * <b>[Method]</b> - On Finish<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creates an archive folder for related date and time and
     * If there is more than defined folder exists under archive folder than it is deleting
     * the older folders until reaching the defined number
     * i.e. RestAssured_Extent_Report.html <br>
     *
     * @param suite ISuite
     */

    public void onFinish(ISuite suite) {
        archiveReport.archiveReport(reportPathMap.get(ReportNameConstants.DESTINATION_FOLDER),
                reportPathMap.get(ReportNameConstants.SOURCE_FOLDER));
        archiveReport.directoryCleanUp(reportPathMap.get(ReportNameConstants.ARCHIVE_FOLDER), 10);
        archiveReport.directoryCleanUp(reportPathMap.get(ReportNameConstants.ARCHIVE_SUB_FOLDER), 10);
    }

}
