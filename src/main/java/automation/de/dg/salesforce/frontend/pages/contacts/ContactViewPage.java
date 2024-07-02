package automation.de.dg.salesforce.frontend.pages.contacts;

import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import automation.utilities.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * <b>PAGES :</b> [ContactViewPage]: Contact View Page
 */
public class ContactViewPage extends Page {

    JavascriptExecutor jse = (JavascriptExecutor) driver;
    By accountNameLink = By.xpath("//p[@title=\"Account Name\"]//ancestor::records-highlights-details-item//a");
    By inactiveCheckbox = By.xpath("//span[text()='Inaktiver Kontakt']//preceding-sibling::span");
    By detailsTab = By.xpath("//a[@data-tab-value=\"detailTab\" and @data-label=\"Details\"]");
    By caseTab = By.xpath("//a[@role='tab' and contains(@title, 'Case')]");


    /**
     * <b>[Method]</b> - Open Account of Contact<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens the account associated with the contact currently opened<br>
     */
    public void openAccountOfContact() {
        Log.info("openAccountOfContact method executing");

        waitForPageToLoad();
        WebElement element = driver.findElement(accountNameLink);
        jse.executeScript("arguments[0].click()", element);
        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + accountNameLink);
        //click(accountNameLink);
    }

    /**
     * <b>[Method]</b> - Switch To Case Tab<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the already opened case tab<br>
     */
    public void switchToCaseTab() {
        Log.info("switchToCaseTab method executing");

        waitForPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(caseTab));
        click(caseTab);
    }

    /**
     * <b>[Method]</b> - Go To Contact Details Tabl<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the details tab on the opened contact<br>
     */
    public void goToContactDetailsTab() {
        Log.info("switchToCaseTab method executing");

        waitForPageToLoad();
        click(detailsTab);
    }
    //-------------------------------VERIFICATIONS--------------------------------------------------------------------\\

    /**
     * <b>[Method]</b> - Verify Contact Field Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality a specific field value for a contact<br>
     * @param expectedValue String
     * @param field String
     */
    public void verifyContactFieldValue(String field, String expectedValue) {
        Log.info("verifyContactFieldValue method executing");

        if(field.equals("Titel")) {
            waitForPageToLoad();

            By fieldNewValueElement = By.xpath("//div[contains(@data-target-selection-name, 'Titel')]//lightning-formatted-text");
            Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");

        } else if(field.equals("Telefonnummer")){
            By fieldNewValueElement = By.xpath("(//span[text()='Telefonnummer'])[3]//ancestor::div[contains(@class, 'slds-hint-parent')]//a");
            By fieldNewValueElementAlternative = By.xpath("(//span[text()='Telefonnummer'])//ancestor::div[contains(@class, 'slds-hint-parent')]//a");

            waitForPageToLoad();

            if(isElementPresent(fieldNewValueElement)){
                Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
                ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
            } else {
                Assert.assertEquals(getText(fieldNewValueElementAlternative), expectedValue, "Field '" + field + "' new value verification");
                ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
            }

        } else if(field.equals("Mobiltelefon")) {
            By fieldNewValueElement = By.xpath("(//span[text()='Mobiltelefon'])//ancestor::div[contains(@class, 'slds-hint-parent')]//a");
            By fieldNewValueElementAlternative = By.xpath("(//span[text()='Mobiltelefon'])[2]//ancestor::div[contains(@class, 'slds-hint-parent')]//a");

            waitForPageToLoad();

            if(isElementPresent(fieldNewValueElement)){
                Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
                ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
            } else {
                Assert.assertEquals(getText(fieldNewValueElementAlternative), expectedValue, "Field '" + field + "' new value verification");
                ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
            }

        } else if(field.equals("E-Mail")) {
            By fieldNewValueElement = By.xpath("(//span[text()='E-Mail'])//ancestor::div[contains(@class, 'slds-hint-parent')]//a");

            waitForPageToLoad();
            Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
            ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");

        } else if(field.equals("ISP")) {
            By fieldNewValueElement = By.xpath("//records-record-layout-item[@field-label=\"ISP\"]//lightning-formatted-text");
            By fieldNewValueElementAlternative = By.xpath("(//records-record-layout-item[@field-label=\"ISP\"]//lightning-formatted-text)[2]");

            waitForPageToLoad();
            if(isElementClickable(fieldNewValueElement)) {
                Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
                ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
            } else {
                Assert.assertEquals(getText(fieldNewValueElementAlternative), expectedValue, "Field '" + field + "' new value verification");
                ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
            }

        } else if(field.equals("Geburtsdatum")) {
            By fieldNewValueElement = By.xpath("//records-record-layout-item[@field-label=\"Geburtsdatum\"]//lightning-formatted-text");

            waitForPageToLoad();
            Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
            ExtentListeners.test.log(Status.PASS, "Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");

        } else if(field.equals("Name")){
            By fieldNewValueElement = By.xpath("//lightning-formatted-name");

            waitForPageToLoad();
            Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + field + "' new value verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: The field '" + field + " has the new correct value '" + expectedValue + "'");
        }
    }

    public void verifyContactOptInCheckedState(String checkboxName, boolean checkedState) {
        Log.info("verifyContactOptInCheckedState method executing");

        By checkboxElement = By.xpath("//records-record-layout-item[@field-label='" + checkboxName + "']//lightning-primitive-input-checkbox");

        WebElement element = driver.findElement(checkboxElement);

        // Check if the element has the attribute using JavaScript
        boolean hasAttribute = (boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].hasAttribute('checked');", element);

        Assert.assertEquals(hasAttribute, checkedState);
        ExtentListeners.test.log(Status.PASS,"Verification OK: Checkbox '" + checkboxName + "' has the correct checked state: " + checkedState);
    }

    public void verifyInactiveContactStatus(boolean expectedState) {
        Log.info("verifyInactiveContactStatus method executing");

        waitForPageToLoad();

        WebElement checkboxElement = driver.findElement(inactiveCheckbox);

        String afterContent = (String) ((JavascriptExecutor) driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content')", checkboxElement);

        if (!expectedState && afterContent.equals("none")) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Inactive contact' is NOT checked");
        } else if (expectedState && afterContent.equals("\"\"")) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Inactive contact' is checked");
        } else if(expectedState && afterContent.equals("none")) {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Inactive contact' is NOT checked");
            Assert.fail("'Inactive contact' Checkbox state is not as expected");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Inactive contact' is checked");
            Assert.fail("'Inactive contact' Checkbox state is not as expected");
        }
    }
}
