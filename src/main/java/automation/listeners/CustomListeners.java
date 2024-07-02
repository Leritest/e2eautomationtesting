package automation.listeners;

import automation.testbase.Page;
import org.testng.*;

import java.util.Date;


/**
 * <b>LISTENERS</b> [Custom]: Custom Listener
 */
public class CustomListeners extends Page implements ITestListener, ISuiteListener {
    static Date date = new Date();
    public String messageBody;

    public void onFinish(ITestContext arg0) {
        //TODO Auto-generated method stub
    }

    public void onStart(ITestContext arg0) {
        //TODO Auto-generated method stub
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        //TODO Auto-generated method stub
    }

    public void onTestFailure(ITestResult arg0) {
    }

    public void onTestSkipped(ITestResult arg0) {
    }

    public void onTestStart(ITestResult arg0) {
    }

    public void onTestSuccess(ITestResult arg0) {
    }

    public void onFinish(ISuite arg0) {
    }

    public void onStart(ISuite arg0) {
        //TODO Auto-generated method stub
    }

}