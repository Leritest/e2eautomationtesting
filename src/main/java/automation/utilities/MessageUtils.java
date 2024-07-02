package automation.utilities;

import automation.enumaration.TestResultStatusesEnum;
import org.testng.ITestResult;

/**
 * <b>PAGES : UTILITIES</b> [Message]: Message Utils
 */
public class MessageUtils {
    /**
     * Creating a string as Screenshot failure and adding Error description to return
     *
     * @param result result message of execution
     * @return generated text will return
     */
    public static String getReportErrorResultText(ITestResult result) {
        StringBuilder sb = new StringBuilder();
        if (result.getStatus() == TestResultStatusesEnum.SKIP.getValue()) {
            sb.append("<b><font color=coral>" + "Screenshot of skip" + "</font></b><br>")
                    .append("<b>" + (result.getThrowable().getMessage().split("\n"))[0] + "</b>");
        } else {
            sb.append("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>")
                    .append("<b>" + (result.getThrowable().getMessage().split("\n"))[0] + "</b>");
        }
        return sb.toString();
    }

    /**
     * This method is creating and returning a text for extend report which shows the state
     * and which method has this state
     *
     * @param paramName method name
     * @param state     can be Pass, Skip, Fail
     * @return returning generated text like Test Case:- CREATEACCOUNT PASSED
     */
    public static String getLogText(String paramName, String state) {
        return "<b>" + "Test Case:- " + paramName.toUpperCase() + state.toUpperCase() + "</b>";
    }
}
