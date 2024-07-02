package automation.de.dg.servicenow.pages;

import automation.de.dg.servicenow.constants.SnowPageTitles;
import automation.testbase.Page;
import org.openqa.selenium.*;

/**
 * <b>SERVICENOW/PAGES : PAGES</b> [Login]: Login Page
 */

public class SnowLoginPage extends Page {

    /**
     * <b>[Method]</b> - Test Log in<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to Log in based on<br>
     * user and password specified in Config.properties<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. click on SSO account to log in
     * 2. Verify ServiceNow Home Page is opened
     */
    public void flowSsoLogin() {
        try {
            // check if Sign In page is opened
            MicrosoftOnlinePage.loginOnMicrosoftAccount();
            // validate ServiceNow Home Page is opened
            validatePageTitle(SnowPageTitles.SERVICE_NOW_HOME_PAGE);
        } catch (StaleElementReferenceException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        } catch (ElementClickInterceptedException ee) {
            ee.printStackTrace();
            throw new RuntimeException(ee);
        } catch (TimeoutException te) {
            te.printStackTrace();
            throw new RuntimeException(te);
        }
    }

}
