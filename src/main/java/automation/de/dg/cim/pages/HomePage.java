package automation.de.dg.cim.pages;

import automation.testbase.Page;
import automation.utilities.Log;

/**
 * <b>PAGES :</b> [HomePage]: CIM Home Page
 */
public class HomePage extends Page {

    /**
     * <b>[Method]</b> - Wait for CIM to Load Account<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality waits and then refresh the page in order for the account to be properly loaded in CIM<br>
     */
    public void waitForCimToLoadAccount() {
        Log.info("waitForCimToLoadAccount method executing");

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().refresh();
        waitForPageToLoad();
    }
}
