package automation.de.dg.servicenow.employercenter;

import automation.de.dg.servicenow.constants.SnowPageTitles;
import automation.de.dg.servicenow.pages.MicrosoftOnlinePage;
import automation.enumaration.Timer;
import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

/**
 * <b>SERVICENOW/EMPLOYER PAGE : SETTINGS</b> [Employer Page]: Home Page
 */

public class EmployerCenterHomePage extends Page {

    By createIncident = By.xpath(".//span[text()='Create Incident']");

    /**
     * <b>[Method]</b> - Login on EMC Home PAge via SSO<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to perform<br>
     * login on Employee Center Page via SSO
     * @throws StaleElementReferenceException
     * @throws ElementClickInterceptedException
     * @throws TimeoutException
     */

    public static void loginOnEmcHomePage() {
        try {
            Log.info("Step - Login on Employer Center Home Page");
            // check if Sign In page is opened
            MicrosoftOnlinePage.loginOnMicrosoftAccount();
            // validate ServiceNow Home Page is opened
            validatePageTitle(SnowPageTitles.EMPLOYER_CENTER_HOME_PAGE);
        } catch (StaleElementReferenceException se) {
            throw new RuntimeException(se);
        } catch (ElementClickInterceptedException ee) {
            throw new RuntimeException(ee);
        } catch (TimeoutException te) {
            throw new RuntimeException(te);
        }
    }


    /**
     * <b>[Method]</b> - Create Incident<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to create incident<br>
     */

    public void openCreateIncidentPage() {
        try {
            Log.info("Step - Click on Create Incident Button");
            click(createIncident);

            // validate ServiceNow Home Page is opened
            validatePageTitleWithFluentWait(Timer.FiveSecondsTimer, SnowPageTitles.EMPLOYER_CENTER_CREATE_INCIDENT_PAGE);
        } catch (TimeoutException te) {
            te.printStackTrace();
            Log.error(te.getLocalizedMessage());
            throw new RuntimeException(te);
        }
    }

}
