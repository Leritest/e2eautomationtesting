package automation.de.dg.servicenow.pages;

import automation.enumaration.Timer;
import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.logging.LogType;

import java.lang.reflect.Method;

/**
 * <b>SERVICENOW/PAGES : SETTINGS</b> [Login]: Microsoft Online Page
 */
public class MicrosoftOnlinePage extends Page {

    static By click = By.xpath(".//small[contains(text(), 'extern.deutsche-glasfaser.de')]");

    /**
     * <b>[Method]</b> - Login on Microsoft Account<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to login on Microsoft Account<br>
     * @throws StaleElementReferenceException
     * @throws ElementClickInterceptedException
     * @throws TimeoutException
     */
    public static void loginOnMicrosoftAccount() {
        try {
            // check if Sign In page is opened
            validatePageTitle("Sign in to your account");
            // click to login
            click(click);
        } catch (StaleElementReferenceException se) {
            Log.error(se.getLocalizedMessage());
            throw new RuntimeException(se);
        } catch (ElementClickInterceptedException ee) {
            Log.error(ee.getLocalizedMessage());
            throw new RuntimeException(ee);
        } catch (TimeoutException te) {
            Log.error(te.getLocalizedMessage());
            throw new RuntimeException(te);
        }
    }

}
