package automation.de.dg.salesforce.frontend.pages;

import automation.de.dg.salesforce.enumation.UserRoles;
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
 * <b>PAGES : SETTINGS</b> [Login]: Login Page
 */
public class LoginPage extends Page {
    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.xpath("//input[@name='Login']");

    /**
     * <b>[Method]</b> - Test Log in<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to Log in based on user and password specified in Config.properties<br>
     * @param role UserRoles
     */
    public void login(UserRoles role) {
        Log.info("login method executing");

        String userRole;
        try {
            switch (role) {
                case AUTO_ADMIN -> userRole = UserRoles.AUTO_ADMIN.option;
                case ACCOUNT_MANAGER -> userRole = UserRoles.ACCOUNT_MANAGER.option;
                case SALES_OPERATIONS -> userRole = UserRoles.SALES_OPERATIONS.option;
                case CUSTOMER_OPERATIONS -> userRole = UserRoles.CUSTOMER_OPERATIONS.option;
                case CUSTOMER_OPERATIONS_AGENT -> userRole = UserRoles.CUSTOMER_OPERATIONS_AGENT.option;
                case AUTO_CUSTOMER_OPERATIONS_AGENT -> userRole = UserRoles.AUTO_CUSTOMER_OPERATIONS_AGENT.option;
                default -> userRole = UserRoles.SALES_OPERATIONS.option;
            }
            type(username, config.getProperty(userRole + ".USERNAME"));
            type(password, config.getProperty(userRole + ".PASSWORD"));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            click(loginButton);
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException exception) {
            ExtentListeners.test.log(Status.FAIL, exception);
        }
    }
}