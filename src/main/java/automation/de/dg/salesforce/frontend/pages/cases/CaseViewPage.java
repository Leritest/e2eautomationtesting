package automation.de.dg.salesforce.frontend.pages.cases;

import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import automation.utilities.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>PAGES :</b> [CaseViewPage]: Case View Page
 */
public class CaseViewPage extends Page {

    JavascriptExecutor jse = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);

    By caseCategory = By.xpath("//span[text()='Kategorie']//ancestor::div[contains(@class, 'slds-hint-parent')]//lightning-formatted-text");
    By accountName = By.xpath("//span[text()='Accountname']//ancestor::div[contains(@class, 'slds-hint-parent')]//a//span");
    By kontaktName = By.xpath("//span[text()='Kontaktname']//ancestor::div[contains(@class, 'slds-hint-parent')]//a//span");
    By kontaktEmail = By.xpath("//span[text()='Kontakt-E-Mail']//ancestor::div[contains(@class, 'slds-hint-parent')]//a");
    By accountNameLink = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-case_-record_-page4___-case___-v-i-e-w/forcegenerated-flexipage_case_record_page4_case__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[3]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[3]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/flexipage-column2[1]/div/slot/flexipage-field[1]/slot/record_flexipage-record-field/div/div/dl/dd/div/span/slot[1]/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    By accountInCim = By.xpath("//span[@data=\"action\"]");
    By caseKontaktElement = By.xpath("//span[text()='Kontaktname']//ancestor::dl//child::dd/div[@class=\"slds-form-element__control\"]//records-hoverable-link");
    By caseId = By.xpath("//p[@title=\"Case-Id\"]//parent::div//lightning-formatted-text");


    /**
     * <b>[Method]</b> - Open Account of Case<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens the account associated with the case currently opened<br>
     */
    public void openAccountOfCase() {
        Log.info("openAccountOfCase method executing");

        waitForPageToLoad();

        WebElement element = driver.findElement(accountNameLink);
        jse.executeScript("arguments[0].click()", element);
        //driver.findElement(By.id("window")).click();
        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + accountNameLink);
        //click(accountNameLink);
    }

    /**
     * <b>[Method]</b> - Open Account in CIM From Case<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens the current account from Salesforce in CIM from Case<br>
     */
    public void openAccountInCimFromCase() {
        Log.info("openAccountInCim method executing");

        waitForPageToLoad();

        By frame = By.xpath("//iframe[contains(@id, 'iFrameResizer')]");
        By frameSecond = By.xpath("(//iframe[contains(@id, 'iFrameResizer')])[2]");

        if(isElementClickable(frame)) {
            WebElement iFrame = driver.findElement(frame);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
        } else {
            WebElement iFrame = driver.findElement(frameSecond);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
        }

        //By accountInCimAlternative = By.xpath("/html/body/div[1]/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/div[3]/div[1]/a");

        wait.until(ExpectedConditions.elementToBeClickable(accountInCim));
        //wait.until(ExpectedConditions.elementToBeClickable(accountInCimAlternative));
        WebElement element = driver.findElement(accountInCim);
        //WebElement element = driver.findElement(accountInCimAlternative);
        actions.moveToElement(element).click().build().perform();

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        String currentTab = driver.getWindowHandle();
        int currentIndex = browserTabs.indexOf(currentTab);

        int nextIndex = (currentIndex + 1) % browserTabs.size();

        for (int i = 0; i < 5; i++) {
            // Check if the next tab index is within the valid range
            if (nextIndex < browserTabs.size()) {
                driver.switchTo().window(browserTabs.get(nextIndex));
                ExtentListeners.test.log(Status.PASS,"Switching to next browser tab");
                break;
            } else {
                // Handling the situation where the next tab index is out of range
                actions.moveToElement(element).click().build().perform();
                ExtentListeners.test.log(Status.PASS, "Clicking on element: " + accountInCim);
            }
            i++;
        }
    }

    public String getCaseId() {
        Log.info("getCaseId method executing");

        waitForPageToLoad();

        return getText(caseId);
    }

    //-------------------------------VERIFICATIONS--------------------------------------------------------------------\\
    public void verifyCaseCategory(String expectedCategory) {
        Log.info("verifyCaseCategory method executing");

        waitForPageToLoad();
        Assert.assertEquals(expectedCategory, getText(caseCategory));
        ExtentListeners.test.log(Status.PASS, "Verification OK: Case Category is  '" + expectedCategory + "' as expected");
    }

    public void verifyIfCaseHasAccountConnected() {
        Log.info("verifyIfCaseHasAccountConnected method executing");

        By frame = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-case_-record_-page4___-case___-v-i-e-w/forcegenerated-flexipage_case_record_page4_case__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_case___0121w000000ddnraac___compact___view___recordlayout2/records-highlights2/div[1]/div[2]/slot/records-highlights-details-item/div/p[1]");
        click(frame);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        if (isElementPresent(accountName)) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: The case has account assigned");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: The case has NO account assigned");
            Assert.fail("The case has no account assigned");
        }
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    public void verifyIfCaseHasContactAssigned() {
        Log.info("verifyIfCaseHasContactAssigned method executing");

        if (isElementPresent(kontaktName)) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: The case has contact assigned");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: The case has NO contact assigned");
            Assert.fail("The case has no contact assigned");
        }
    }

    public void verifyIfCaseContactHasEmailAddress() {
        Log.info("verifyIfCaseContactHasEmailAddress method executing");

        waitForPageToLoad();
        if (isElementPresent(kontaktEmail)) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: The case contact has email address");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: The case contact has NO email address");
            Assert.fail("The case contact has no email address");
        }
    }

    public void verifyCaseContactLastName(String expectedLastName) {
        Log.info("verifyCaseContactLastName method executing");

        waitForPageToLoad();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        By frame = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-case_-record_-page4___-case___-v-i-e-w/forcegenerated-flexipage_case_record_page4_case__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_case___0121w000000ddnraac___compact___view___recordlayout2/records-highlights2/div[1]/div[2]/slot/records-highlights-details-item/div/p[1]");
        click(frame);
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement element = driver.findElement(caseKontaktElement);

        new Actions(driver)
                .moveToElement(element)
                .perform();

        jse.executeScript("arguments[0].scrollIntoView()", element);

        String caseContactFullName = getText(caseKontaktElement);

        Assert.assertTrue(caseContactFullName.contains(expectedLastName), "Nachname New value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The contact 'Nachname' new value has the new correct value '" + expectedLastName + "' in the Case View Page");


    }
}
