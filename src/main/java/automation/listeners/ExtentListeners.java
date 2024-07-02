package automation.listeners;

import automation.constants.ReportNameConstants;
import automation.properties.PropertyManager;
import automation.testbase.Page;
import automation.utilities.ExtentManager;
import automation.utilities.Log;
import automation.utilities.MessageUtils;
import automation.utilities.ProjectUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * <b>LISTENERS</b> [Extent]: Extent Listener
 */
public class ExtentListeners implements ITestListener, ISuiteListener, IInvokedMethodListener {

    ProjectUtils archiveReport = new ProjectUtils();
    static String fileName = "Extent Report";
    public static ExtentTest test;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extent;
    Map<String, File> reportPathMap = ExtentManager.getReportPathMap();


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        Log.info("I am in beforeInvocation method");
        IInvokedMethodListener.super.beforeInvocation(method, testResult, context);
        if (method.getTestMethod().getMethodName().equals("setUp")) {
            test = extent.createTest(testResult.getTestClass().getName());
        }
        // set executing class name
        Page.setExecutingClassName(testResult.getTestClass().getName());
    }

    /**
     * <b>[Method]</b> - On Start<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality creates instance of extent reports<br>
     * @param context ITestContext
     */
    public void onStart(ITestContext context) {
        Log.info("-----------------------------------------------------------------");
        Log.info("I am in onStart method");
        extent=  ExtentManager.createInstance(".\\target\\surefire-reports\\html\\" + fileName);
        // initialize property file and store value before test start
        PropertyManager property = new PropertyManager();
        try {
            PropertyManager.ConfigFileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        Log.info("I am in onFinish method");
        if(extent != null){
            extent.flush();
        }
        Log.info("-----------------------------------------------------------------");
    }

    /**
     * <b>[Method]</b> - On Test Start<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality prepares an extent test object with test class name - method name.<br>
     * i.e. testcases.restAssuredAPI.accounts.CreateBusinessAccountAPITest - createBusinessAccountTest
     * @param result ITestContext
     */
    public void onTestStart(ITestResult result) {
        Log.info("-----------------------------------------------------------------");
        Log.info("Test Case " + getTestMethodName(result) + " is starting.");

        test = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        Log.info("Test Case " + getTestMethodName(result) + " is successfully finished.");
        String methodName = result.getMethod().getMethodName();
        String logText = MessageUtils.getLogText(methodName, " PASSED");
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        //test.log(Status.PASS, markup);
        Log.info("-----------------------------------------------------------------");
    }
    /**
     * <b>[Method]</b> - On Test Failure<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality writes Error log into report
     * and also a formatted exception log part.<br>
     * @param result ITestResult
     */

    public void onTestFailure(ITestResult result) {
        Log.info("Test Case " + getTestMethodName(result) + " is failed.");
        String className = result.getTestClass().getName();
        if (className.contains("restAssuredAPI") || className.contains("endpoints") || className.contains("database") || className.contains("end2end")){
            ExtentManager.logFailureDetails(result.getThrowable().getMessage());

            String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
            stackTrace = stackTrace.replaceAll(",", "<br>");
            String formattedTrace = "<details>\n" +
                    " <summary> Click Here to See Exception Logs</summary>\n" +
                    " " + stackTrace + "\n" +
                    "</details>\n";
            ExtentManager.logExceptionDetails(formattedTrace);
        } else {
            try {
                if (Page.getDriver() != null) {
                    ExtentManager.captureScreenshot(null);
                    String methodName = result.getMethod().getMethodName();
                    String logText = MessageUtils.getLogText(methodName, " FAILED");
                    String reportErrorMessage = MessageUtils.getReportErrorResultText(result);
                    test.fail(reportErrorMessage, MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName).build());
                    Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
                    test.log(Status.FAIL, markup);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.info("-----------------------------------------------------------------");
    }

    /**
     * <b>[Method]</b> - On Test Skipped<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality writes Warning log into report for tests that are skipped.<br>
     *
     * @param result ITestResult
     */
    public void onTestSkipped(ITestResult result) {
        Log.info("I am in onTestSkipped method");
        String className = result.getTestClass().getName();
        boolean check = false;
        if (className.contains("restAssuredAPI") || className.contains("endpoints") || className.contains("database") || className.contains("end2end")){
            check = true;
        }

        String methodName = result.getMethod().getMethodName();
        String logText = MessageUtils.getLogText(methodName, " SKIPPED");
        String reportErrorMessage = MessageUtils.getReportErrorResultText(result);
        if (!check) {
            try {
                ExtentManager.captureScreenshot(null);


                test.skip(reportErrorMessage, MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName).build());
                Markup markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
                test.log(Status.SKIP, markup);
                Page.quit(); // Close current browser
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * <b>[Method]</b> - On Start<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality deletes related files from source folder on the beginning of the test.
     * i.e. RestAssured_Extent_Report.html <br>
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
     * @param suite ISuite
     */

    public void onFinish(ISuite suite) {
        Log.info("I am in onFinish iSuite method");
        archiveReport.archiveReport(reportPathMap.get(ReportNameConstants.DESTINATION_FOLDER),
                reportPathMap.get(ReportNameConstants.SOURCE_FOLDER));
        archiveReport.directoryCleanUp(reportPathMap.get(ReportNameConstants.ARCHIVE_FOLDER), 10);
        archiveReport.directoryCleanUp(reportPathMap.get(ReportNameConstants.ARCHIVE_SUB_FOLDER), 10);

        if (Page.getDriver() != null) {
            Page.quit();
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

}