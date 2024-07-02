package automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;

    /**
     * <b>[Method]</b> - Retry implementation<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality will retry test script after FAILURE<br>
     * Max try is set to 1 as we want only one retry After FAILURE. We close the instance of current browser and starting new browser and retry<br>
     *
     * @param iTestResult The result of the test method that just ran
     * @return true if the test method has to be retried, false otherwise.
     */
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) { // Check if test not succeed
            if (count < maxTry) { // Check if maxtry count is reached
                count++; // Increase the maxTry count by 1
                return true;// Tells TestNG to re-run the test
            }
        }
        return false;
    }
}