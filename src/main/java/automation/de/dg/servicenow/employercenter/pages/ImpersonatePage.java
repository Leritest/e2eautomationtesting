package automation.de.dg.servicenow.employercenter.pages;

import automation.de.dg.servicenow.common.navigation.Search;
import automation.de.dg.servicenow.employercenter.EmployerCenterHeaderPage;
import automation.enumaration.Timer;
import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

/**
 * <b>SERVICENOW/EMPLOYER CENTER : IMPERSONATE</b> [Impersonate Page]: Impersonate Page
 */

public class ImpersonatePage extends Page {

    By searchUser = By.id("select2-chosen-2");
    By findUser = By.id("select2-drop");
    By writeUser = By.id("s2id_autogen2_search");
    By subAvatar = By.className("sub-avatar");

    /**
     * <b>[Method]</b> - Search for Impersonate User<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to select<br>
     * impersonate user
     * <i>Steps of this scenario:</i><br>
     * 1. Open DropDown list under Avator icon<br>
     * 2. Click on Impersonate button<br>
     * 3. Search for desired impersonate user<br>
     * 4. Select desired impersonate<br>
     * 5. Verify impersonation is finished<br>
     *
     * @throws Exception
     */

    public void flowImpersonationOfUser(String impersonateUser) {
        // initialize classes
        EmployerCenterHeaderPage headerPage = new EmployerCenterHeaderPage();

        try {
            Log.info("Step - Flow of user's Impersonation");
            headerPage.openAvatorDropdown();
            headerPage.clickOnImpersonate();
            searchForImpersonateUser(impersonateUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * <b>[Method]</b> - Search for Impersonate User<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to select<br>
     * impersonate user
     * @throws StaleElementReferenceException
     * @throws Exception
     */

    public void searchForImpersonateUser(String impersonateUser) {
        try {
            Log.info("Searching for Impersonate User " + impersonateUser);
            // click to search for impersonate user
            wait.until(ExpectedConditions.elementToBeClickable(searchUser));
            click(searchUser);
            // verify pop-up dialog is opened
            wait.until(ExpectedConditions.visibilityOfElementLocated(findUser));
            // write desired user
            type(writeUser, impersonateUser);

            // click on desired user
            Log.info("Click on Impersonate User " + impersonateUser);
            Search search = new Search();
            search.selectFromDropdownList(true);

            // verify pop-up dialog is opened
            WebElement we;
            int count = 0;
            do {
                we = driver.findElement(subAvatar);
                count++;
            } while (!validateElementAttribute(Timer.FiveSecondsTimer, we, "aria-label", impersonateUser) || count < 10);
            Assert.assertEquals(validateElementAttribute(Timer.SecondTimer, we, "aria-label", impersonateUser), true, "Impersonate process is not successfully performed");
        } catch (StaleElementReferenceException se) {
            se.printStackTrace();
            Log.error(se.getLocalizedMessage());
            throw new RuntimeException(se);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

}
