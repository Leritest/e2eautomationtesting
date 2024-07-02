package automation.de.dg.servicenow.employercenter;

import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static automation.testbase.Page.*;

/**
 * <b>SERVICENOW/COMMON : NAVIGATION</b> [Header Page]: Header Page
 */

public class EmployerCenterHeaderPage extends Page {

    By avatarBtn = By.xpath(".//li[@ng-if='showAvatar']");
    By impersonateBtn = By.xpath(".//a[@ng-click='impersonate()']");
    By modalPopup = By.xpath("//div[@role='dialog' ]");

    /**
     * <b>[Method]</b> - Open Avator Dropdownbr>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to open<br>
     * dropdown list under Avator icon<br>
     */
    public void openAvatorDropdown() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(avatarBtn));
            click(avatarBtn);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Impersonate Feature<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to perform impersonate<br>
     */

    public void clickOnImpersonate() {
        try {
            Log.info("Click on button for Impersonation");
            // click on Impersonate button
            wait.until(ExpectedConditions.elementToBeClickable(impersonateBtn));
            click(impersonateBtn);
            // verify pop-up dialog is opened
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalPopup));
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

}
