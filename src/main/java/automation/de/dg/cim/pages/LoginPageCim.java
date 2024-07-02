package automation.de.dg.cim.pages;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import automation.utilities.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * <b>PAGES :</b> [LoginPageCim]: CIM Login Page
 */
public class LoginPageCim extends Page {

    By username = By.xpath("//input[@name=\"j_username\"]");
    By password = By.xpath("//input[@name=\"j_password\"]");
    By loginButton = By.id("button-login-login");

    /**
     * <b>[Method]</b> - Test Log in<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to Log in based on user and password specified in Config.properties<br>
     * @param role UserRolesCim
     */
    public void loginCimWeb(UserRolesCim role) {
        Log.info("loginCimWeb method executing");

        String userRole;
        try {
            switch (role) {
                //case CIM_ADMIN -> userRole = UserRoles.CIM_ADMIN.option;
                default -> userRole = UserRolesCim.CIM_ADMIN.option;
            }
            waitForPageToLoad();
            type(username, config.getProperty(userRole + ".USERNAME"));
            type(password, config.getProperty(userRole + ".PASSWORD"));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            click(loginButton);
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException exception) {
            ExtentListeners.test.log(Status.FAIL, exception);
        }
    }
}
