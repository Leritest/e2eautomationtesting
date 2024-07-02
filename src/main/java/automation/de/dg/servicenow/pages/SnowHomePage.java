package automation.de.dg.servicenow.pages;

import automation.de.dg.servicenow.constants.SnowPageTitles;
import automation.de.dg.servicenow.enumaration.HeaderLabelNames;
import automation.enumaration.Timer;
import automation.testbase.Page;
import org.openqa.selenium.*;

import java.util.List;

/**
 * <b>SERVICENOW/PAGES : PAGES</b> [Home Page]: Home Page
 */
public class SnowHomePage extends Page {

    static By rootShadow = By.tagName("macroponent-f51912f4c700201072b211d4d8c26010");
    static By listToolbar = By.cssSelector("sn-canvas-toolbar");
    static By listBtn = By.id("list");

    /**
     * <b>[Method]</b> Open List Page<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to open<br>
     * list page for further desired action
     * @throws Exception
     */
    public void openListPage() {
        try {

            validateElementExist(Timer.FiveSecondsTimer, rootShadow);
            /*Thread.sleep(2500);
            WebElement root1 = driver.findElement(By.tagName("macroponent-f51912f4c700201072b211d4d8c26010"));
            SearchContext shadowRoot = root1.getShadowRoot();
            WebElement shadowRoot2 = shadowRoot.findElement(By.id("item-snCanvasAppshellMain"));
            shadowRoot = shadowRoot2.getShadowRoot();
            WebElement shadowRoot3 = shadowRoot.findElement(By.cssSelector("macroponent-c276387cc331101080d6d3658940ddd2"));
            shadowRoot = shadowRoot3.getShadowRoot();
            WebElement shadowRoot4 = shadowRoot.findElement(By.cssSelector("sn-canvas-toolbar"));
            shadowRoot = shadowRoot4.getShadowRoot();
            WebElement shadowRoot5 = shadowRoot.findElement(By.cssSelector("#list"));
            shadowRoot5.click();*/

            // find Shadow WebElement
            WebElement shadow;

            //Thread.sleep(2500);
            validateElementExist(Timer.FiveSecondsTimer, rootShadow);

            shadow = findElementInsideShadow(driver.findElement(rootShadow), By.id("item-snCanvasAppshellMain"));
            shadow = findElementInsideShadow(shadow, By.cssSelector("macroponent-c276387cc331101080d6d3658940ddd2"));

            // save Shadow WebElement that will separate List view and Content view
            SnowListPage.setShadowMainElement(shadow);

            // find WebElement to click on List
            WebElement el;
            el = findElementInsideShadow(shadow, listToolbar);
            el = findElementInsideShadow(el, listBtn);
            el.click();

            // validate ServiceNow List Page is opened
            validatePageTitle(SnowPageTitles.SERVICE_NOW_LIST_PAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> Open List Page<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to open<br>
     * list page for further desired action
     * @throws Exception
     */

    public void getOpenItem() {
        try {
            validateElementExist(Timer.FiveSecondsTimer, rootShadow);
            WebElement shadow;
            // find main Shadow WebElement
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> Open List Page<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to open<br>
     * list page for further desired action
     * @throws Exception
     */

    public void getOpenHeaderPage(HeaderLabelNames requestType) {
        try {
            validateElementExist(Timer.FiveSecondsTimer, rootShadow);
            WebElement shadow;
            // find main Shadow WebElement
            shadow = findElementInsideShadow(driver.findElement(rootShadow), By.cssSelector("sn-polaris-layout"));
            shadow = findElementInsideShadow(shadow, By.cssSelector("sn-polaris-header"));
            shadow = findElementInsideShadow(shadow, By.cssSelector(".starting-header-zone"));

            List<WebElement> list = shadow.findElements(By.className("sn-polaris-tab"));
            System.out.println(list.size());
            for (WebElement we:list) {
                System.out.println(we.getAttribute("aria-label"));
                if (we.getAttribute("aria-label").contains("Workspaces")) {
                    we.click();
                    Thread.sleep(2500);
                    System.out.println(driver.getTitle());
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
