package automation.de.dg.salesforce.frontend.pages.common.navigation;

import automation.de.dg.salesforce.elements.AppLauncherMenuElements;
import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static automation.de.dg.salesforce.elements.AppLauncherMenuElements.APP_LAUNCHER_ELEMENT_TYPE;

/**
 * <b>PAGES :</b> [AppLauncherPage]: App Launcher Page
 */
public class AppLauncherPage extends Page {

    By waffleIcon = By.xpath("//div[@role='navigation']/descendant::div[@class='slds-icon-waffle']");
    By viewAllApps = By.xpath("//button[@aria-label='View All Applications' or @aria-label='Alle Anwendungen anzeigen']");

    /**
     * <b>[Method]</b> - Navigate to Page<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality navigates to specific page<br>
     * @param pageToNavigateTo String
     */
    public void navigateToPage(String pageToNavigateTo) {
        wait.until(ExpectedConditions.elementToBeClickable(waffleIcon));
        click(waffleIcon);
        wait.until(ExpectedConditions.elementToBeClickable(viewAllApps));
        click(viewAllApps);
        By pageElementOption = AppLauncherMenuElements.createPageElement(pageToNavigateTo, APP_LAUNCHER_ELEMENT_TYPE.get(pageToNavigateTo));
        wait.until(ExpectedConditions.presenceOfElementLocated(pageElementOption));
        click(pageElementOption);
        waitForPageToLoad();

    }
}
