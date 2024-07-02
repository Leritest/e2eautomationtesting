package automation.de.dg.servicenow.pages;

import automation.de.dg.servicenow.constants.SnowPageTitles;
import automation.de.dg.servicenow.enumaration.IncidentStates;
import automation.de.dg.servicenow.enumaration.ListLabels;
import automation.enumaration.Timer;
import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * <b>SERVICENOW/PAGES : PAGES</b> [List Page]: List Page
 */
public class SnowListPage extends Page {

    static By listMain = By.cssSelector("sn-canvas-main");
    static By listScreen = By.cssSelector("sn-canvas-screen");
    static By recordListMenuRoot = By.cssSelector("now-record-list-menu-connected");
    static By recordListMenu = By.cssSelector("now-list-menu");
    static By listMenuDisplay = By.cssSelector("sn-list-menu-display");
    static By listContentTree = By.cssSelector("now-content-tree");
    static By recordListMain = By.cssSelector("now-record-list-connected");
    static By recordList = By.cssSelector("now-record-list");
    static By recordContent = By.cssSelector("now-grid");
    static By recordTable = By.cssSelector("tbody");


    /**
     * <b>[Method]</b> Open Incident Flow<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to<br>
     * open incident based on desired state<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Navigate to Content Page<br>
     * 2. Click on desired Record<br>
     * 3. Verify proper Page is opened
     */
    public void flowOpenRecord(ListLabels mainLabel, ListLabels label) {
        // open 'Open Incident' Page
        openContentPage(mainLabel, label);
        // click on incident in status New
        openRecord();
    }

    /**
     * <b>[Method]</b> Open Content Page<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to open<br>
     * content page with validation of opened page
     * @param mainLabel ListLabels
     * @param label ListLabels
     */
    public void openContentPage(ListLabels mainLabel, ListLabels label) {
        try {
            Log.info("Navigating to List Content Page");
            // navigate to List content elements
            navigateToListShadowElements();

            // navigate to Content Tree Element
            validateElementExist(Timer.FiveSecondsTimer, By.cssSelector("li[aria-label='" + mainLabel.option + "']"));
            WebElement we = findElementInsideShadow(getContentTreeElement(), By.cssSelector("li[aria-label='" + mainLabel.option + "']"));

            // click on Content Element
            Log.info("Clicking on " + label.option + " under " + mainLabel.option + " in the List Content Page");
            we = we.findElement(By.cssSelector("li[aria-label='" + label.option + "']"));
            we.click();

            // validate Content Page is opened
            validatePageTitle(label.option + " | " + SnowPageTitles.SERVICE_NOW_HOME_PAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> Navigate to List Elements<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to map<br>
     * shadow web elements that is used<br>
     * for list and record navigation
     * @throws Exception
     */
    public void navigateToListShadowElements() {
        WebElement shadow;
        try {
            shadow = findElementInsideShadow(getShadowMainElement(), listMain);

            // find visible element
            SearchContext content = shadow.getShadowRoot();
            List<WebElement> list = content.findElements(listScreen);
            for (WebElement el:list) {
                if (el.isDisplayed()) {
                    shadow = el;
                    break;
                }
            }

            // navigate to Shadow WebElement that will separate List view and Content view
            shadow = findElementInsideShadow(shadow, By.cssSelector("macroponent-2c08111d0fc21010036a83fa68767ef6"));

            // set main shadow Content List Element
            setContentRecordElement(shadow);

            // navigate to proper incident
            shadow = findElementInsideShadow(shadow, recordListMenuRoot);
            shadow = findElementInsideShadow(shadow, recordListMenu);
            shadow = findElementInsideShadow(shadow, listMenuDisplay);
            shadow = findElementInsideShadow(shadow, listContentTree);

            // set main shadow List Tree Element
            setContentTreeElement(shadow);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> Open Record<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to open<br>
     * some desired incident record
     * @throws Exception
     */
    public void openRecord() {
        try {
            // navigate to Table elements
            WebElement shadow;
            shadow = findElementInsideShadow(getContentRecordElement(), recordListMain);
            shadow = findElementInsideShadow(shadow, recordList);
            shadow = findElementInsideShadow(shadow, recordContent);
            shadow = findElementInsideShadow(shadow, recordTable);

            // find all row elements inside table
            List<WebElement> listLine = shadow.findElements(By.tagName("tr"));
            String title = null;
            for (WebElement we:listLine) {
                // find all cell elements inside row
                List<WebElement> listColumn = we.findElements(By.tagName("td"));
                WebElement clickEl;
                for (WebElement el:listColumn) {
                    if (el.getAttribute("aria-label") != null) {
                        // check if Cell element is in proper Stage/State
                        if (el.getAttribute("aria-label").contains(getContentState())) {
                            Log.info("Clicking on element in Stage/State " + getContentState());
                            clickEl = listColumn.get(2);
                            title = listColumn.get(2).getAttribute("aria-label");
                            // click on desired element
                            clickEl.click();
                            break;
                        }
                    }
                }
                break;
            }

            waitForPageToLoad();
            // validate desired incident is opened
            Assert.assertEquals(driver.getTitle(), title + " | DG UAT", "Content Page for record " + title + " is not opened");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static WebElement shadowMain;
    /**
     * <b>[Method]</b> - Setting Shadow Main Element<br>
     * used for separating Toolbar, List Tree and Content list
     * @param we WebElement
     */
    public static void setShadowMainElement(WebElement we) {
        shadowMain = we;
    }

    /**
     * <b>[Method]</b> - Getting Shadow Main Element<br>
     *
     * @return shadowMain WebElement
     */
    public static WebElement getShadowMainElement() {
        return shadowMain;
    }

    static WebElement contentTreeMain;
    /**
     * <b>[Method]</b> - Setting Shadow Tree Element<br>
     * used for separating List Tree and Content list
     * @param we WebElement
     */
    public static void setContentTreeElement(WebElement we) {
        contentTreeMain = we;
    }

    /**
     * <b>[Method]</b> - Getting Shadow Tree Element<br>
     *
     * @return contentTreeMain WebElement
     */
    public static WebElement getContentTreeElement() {
        return contentTreeMain;
    }

    static WebElement contentRecordMain;
    /**
     * <b>[Method]</b> - Setting Shadow Content Element<br>
     * used for Content list
     * @param we WebElement
     */
    public static void setContentRecordElement(WebElement we) {
        contentRecordMain = we;
    }

    /**
     * <b>[Method]</b> - Getting Shadow Record Element<br>
     *
     * @return contentRecordMain WebElement
     */
    public static WebElement getContentRecordElement() {
        return contentRecordMain;
    }

    static String contentState;
    public static void setContentState(String state) {
        contentState = state;
    }

    public static String getContentState() {
        return contentState;
    }

}
