package automation.de.dg.salesforce.frontend.pages.account;

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
 * <b>PAGES :</b> [AccountViewPage]: Account View Page
 */
public class AccountViewPage extends Page {
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    Actions actions = new Actions(driver);
    By contractDataLink = By.xpath("//a[@data-label=\"Contract Data\" or @data-label=\"Vertragsdaten\"]");
    By contractDataLinkDuplicate = By.xpath("(//a[@data-label=\"Contract Data\" or @data-label=\"Vertragsdaten\"])[2]");
    By firstCustomerSubscription = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-consumer-private-constumer___-account___-v-i-e-w/forcegenerated-flexipage_consumerprivateconstumer_account__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[3]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[2]/slot/flexipage-component2[2]/slot/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list-internal/div/div/lst-primary-display-manager/div/lst-primary-display/lst-primary-display-grid/lst-primary-display-grid-shim/lst-bundle_act_core-related-list-desktop_datatable/div[2]/div/div/table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-primitive-custom-cell/lst-output-lookup/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    By relatedTab = By.xpath("//a[@data-tab-value=\"relatedListsTab\"]");
    By phoneNumberForm = By.xpath("//span[@title=\"Phone Numbers\" or @title=\"Telefonnummern\"]");
    By firstPhoneNumberName = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section[2]/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-subscription_-record_-page8___vlocity_cmt__-subscription__c___-v-i-e-w/forcegenerated-flexipage_subscription_record_page8_vlocity_cmt__subscription__c__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[2]/div[1]/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[1]/slot/flexipage-component2/slot/lst-related-list-container/div/div[3]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list-internal/div/div/lst-primary-display-manager/div/lst-primary-display/lst-primary-display-grid/lst-primary-display-grid-shim/lst-bundle_act_core-related-list-desktop_datatable/div[2]/div/div/table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-primitive-custom-cell/lst-output-lookup/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    By firstPhonebookEntry = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section[3]/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-phone_-number_-record_-page___-phone-number__c___-v-i-e-w/forcegenerated-flexipage_phone_number_record_page_phonenumber__c__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-simple-view-template2/div/div[2]/div/slot/flexipage-component2[2]/slot/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list-internal/div/div/lst-primary-display-manager/div/lst-primary-display/lst-primary-display-grid/lst-primary-display-grid-shim/lst-bundle_act_core-related-list-desktop_datatable/div[2]/div/div/table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-primitive-custom-cell/lst-output-lookup/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    By refresh = By.xpath("(//button[@title=\"Refresh\"])[3]");
    By accountInCim = By.xpath("//span[@data=\"action\"]");
    By contactsAccounts = By.xpath("//span[@title='Kontakte' and text()='Kontakte']");
    By contactNameLink = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section[2]/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-consumer-private-constumer___-account___-v-i-e-w/forcegenerated-flexipage_consumerprivateconstumer_account__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[3]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[1]/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_account___0127r000000m0bnqaq___full___view___recordlayout2/records-record-layout-block/slot/records-record-layout-section[5]/div/div/div/slot/records-record-layout-row/slot/records-record-layout-item[1]/div/div/div[2]/span/slot[1]/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    By contactNameLinkAlternative = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[1]/div/div/section[2]/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-consumer-private-constumer___-account___-v-i-e-w/forcegenerated-flexipage_consumerprivateconstumer_account__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[3]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[1]/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_account___0127r000000m0boqaq___full___view___recordlayout2/records-record-layout-block/slot/records-record-layout-section[4]/div/div/div/slot/records-record-layout-row/slot/records-record-layout-item[1]/div/div/div[2]/span/slot[1]/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    By allContacts = By.xpath("//span[text()='Alle anzeigen']//ancestor::a[contains(@href, 'Contacts')]");
    By accountName = By.xpath("//lightning-formatted-text[@class=\"custom-truncate\"]");


    /**
     * <b>[Method]</b> - Open Contract Data<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens the contract data<br>
     */
    public void openContractData() {
        Log.info("openContractData method executing");

        waitForPageToLoad();
        if(isElementClickable(contractDataLink)) {
            wait.until(ExpectedConditions.elementToBeClickable(contractDataLink));
            click(contractDataLink);
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(contractDataLinkDuplicate));
            click(contractDataLinkDuplicate);
        }
    }

    /**
     * <b>[Method]</b> - Open Customer Subscription<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality navigates and opens customer subscription<br>
     */
    public void openCustomerSubscription() {
        Log.info("openCustomerSubscription method executing");

        waitForPageToLoad();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        click(refresh);
        WebElement subscriptionForm = driver.findElement(firstCustomerSubscription);
        jse.executeScript("arguments[0].scrollIntoView()", subscriptionForm);
        click(firstCustomerSubscription);
    }

    /**
     * <b>[Method]</b> - Go to Related Tab Subscription<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on Related tab in Subscription<br>
     */
    public void gotoRelatedTabSubscription() {
        Log.info("gotoRelatedTabSubscription method executing");

        waitForPageToLoad();
        click(relatedTab);
    }

    /**
     * <b>[Method]</b> - Open Phone Number Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality finds and clicks on the phone number name<br>
     */
    public void openPhoneNumberName() {
        Log.info("openPhoneNumberName method executing");

        waitForPageToLoad();
        WebElement phoneForm = driver.findElement(phoneNumberForm);
        jse.executeScript("arguments[0].scrollIntoView()", phoneForm);
        click(firstPhoneNumberName);
    }

    /**
     * <b>[Method]</b> - Open Phone Number Entry<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the phone book entry<br>
     */
    public void openPhonebookEntry() {
        Log.info("openPhonebookEntry method executing");

        waitForPageToLoad();
        click(firstPhonebookEntry);
    }

    /**
     * <b>[Method]</b> - Open Account in CIM<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens the current account from Salesforce in CIM<br>
     */
    public void openAccountInCim() {
        Log.info("openAccountInCim method executing");

        waitForPageToLoad();

        By frame = By.xpath("(//iframe[contains(@id, 'iFrameResizer')])[2]");
        By frameAlternative = By.xpath("(//iframe[contains(@id, 'iFrameResizer')])[1]");

        if(isElementClickable(frame)){
            WebElement iFrame = driver.findElement(frame);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
            ExtentListeners.test.log(Status.PASS,"Switching to frame: " + frame);
        } else {
            WebElement iFrame = driver.findElement(frameAlternative);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
            ExtentListeners.test.log(Status.PASS,"Switching to frame: " + frameAlternative);
        }


        wait.until(ExpectedConditions.elementToBeClickable(accountInCim));
        WebElement element = driver.findElement(accountInCim);
        actions.moveToElement(element).click().build().perform();
        ExtentListeners.test.log(Status.PASS,"Clicking on element: " + accountInCim);

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

    /**
     * <b>[Method]</b> - Wait for Phonebook Entry Edits to Sync with Salesforce<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality waits for 5 seconds and then does refresh until it see the phone book changes reflected/sync on the Salesforce side<br>
     */
    public void waitForPhoneBookEntryEditsToSyncWithSalesforce(String feldValueToCheck) {
        Log.info("waitForPhoneBookEntryEditsToSyncWithSalesforce method executing");

        By streetName = By.xpath("//lightning-formatted-text[text()='" + feldValueToCheck + "']");

        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(5000);
                driver.navigate().refresh();
                ExtentListeners.test.log(Status.PASS, "Refreshing the page, phonebook entry edits synchronization is still not done");
                waitForPageToLoad();
            } catch (InterruptedException e) {
                ExtentListeners.test.log(Status.FAIL, e);
            }

            if (isElementVisible(streetName)) {
                ExtentListeners.test.log(Status.PASS, "Synchronization is done, edits of phonebook entry are shown");
                break;
            }
        }
    }

    /**
     * <b>[Method]</b> Open Contacts View of Account<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens contacts view of a specific account<br>
     */
    public void openContactsViewOfAccount() {
        Log.info("openContactsViewOfAccount method executing");

        waitForPageToLoad();
        click(contactsAccounts);
    }

    /**
     * <b>[Method]</b> Open Contact from Account<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens contact from the account view<br>
     */
    public void openContactFromAccount(String contactName) {
        Log.info("openContactFromAccount method executing");

        waitForPageToLoad();

        WebElement element;

        if(isElementPresent(contactNameLink)) {
            element = driver.findElement(contactNameLink);
        } else {
            element = driver.findElement(contactNameLinkAlternative);
        }
        jse.executeScript("arguments[0].scrollIntoView()", element);
        jse.executeScript("arguments[0].click()", element);

    }

    /**
     * <b>[Method]</b> Open Contact from Account View<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens contact from the account view<br>
     */
    public void openContactfromAccountView(String contactName) {
        Log.info("openContactfromAccountView method executing");

        waitForPageToLoad();

        By contactNameLink = By.xpath("//span[text()='" + contactName + "']//ancestor::lightning-primitive-cell-factory[@data-label=\"Kontaktname\"]//span[@data-proxy-id]");


        By contactFromAllContacts = By.xpath("(//tbody//span[text()='" + contactName + "'])[2]");
        By contactFromAllContactsAlternative = By.xpath("(//tbody//span[text()='" + contactName + "'])[1]");

        WebElement element;

        if(isElementPresent(contactNameLink)) {
            element = driver.findElement(contactNameLink);
            jse.executeScript("arguments[0].scrollIntoView()", element);
            jse.executeScript("arguments[0].click()", element);
            ExtentListeners.test.log(Status.PASS,"Clicking on element: " + contactNameLink);
        } else {
            element = driver.findElement(allContacts);
            jse.executeScript("arguments[0].scrollIntoView()", element);
            jse.executeScript("arguments[0].click()", element);
            ExtentListeners.test.log(Status.PASS,"Clicking on element: " + allContacts);

            waitForPageToLoad();

            if(isElementClickable(contactFromAllContacts)){
                click(contactFromAllContacts);
            } else {
                click(contactFromAllContactsAlternative);
            }
        }
    }

    public void createNewContactFromAccountView(String salutation, String firstName, String lastName, String email, String phone, String mobile, String birthDate) {
        Log.info("createNewContactFromAccountView method executing");

        waitForPageToLoad();
        By salutationField = By.xpath("//button[@name=\"salutation\"]");
        By salutationOption = By.xpath("//lightning-base-combobox-item[@data-value='" + salutation + "']");
        By newButton = By.xpath("//button[@name=\"NewContact\"]");
        By firstNameField = By.xpath("//input[@name=\"firstName\"]");
        By lastNameField = By.xpath("//input[@name=\"lastName\"]");
        By emailField = By.xpath("//input[@name=\"Email\"]");
        By phoneField = By.xpath("//input[@name=\"Phone\"]");
        By mobileField = By.xpath("//input[@name=\"MobilePhone\"]");
        By birthDateField = By.xpath("//input[@name=\"Birthdate\"]");
        By saveButton = By.xpath("//button[@name=\"SaveEdit\"]");


        wait.until(ExpectedConditions.elementToBeClickable(newButton));
        click(newButton);

        waitForPageToLoad();

        type(lastNameField, lastName);

        click(salutationField);
        wait.until(ExpectedConditions.elementToBeClickable(salutationOption));
        click(salutationOption);
        type(firstNameField, firstName);

        type(emailField, email);
        type(phoneField, phone);
        type(mobileField, mobile);
        type(birthDateField, birthDate);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        click(saveButton);


    }

    //-------------------------------VERIFICATIONS--------------------------------------------------------------------\\

    /**
     * <b>[Method]</b> -Verify Phone Book Entry Field Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the phone book entry field value is the same as the one that is expected<br>
     * @param field String
     * @param value String
     */
    public void verifyPhonebookEntryFieldValue(String field, String value) {
        Log.info("verifyPhonebookEntryFieldValue method executing");

        By fieldElement = By.xpath("//span[text()='" + field + "']//ancestor::div[contains(@class, 'slds-hint-parent')]//lightning-formatted-text");
        Assert.assertEquals(getText(fieldElement), value);
        ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "' has the correct value '" + value + "'");
    }

    /**
     * <b>[Method]</b> -Verify If checkbox is checked<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the needed checkbox state is checked<br>
     * @param checkboxName String
     */
    public void verifyIfCheckboxIsChecked(String checkboxName) {
        Log.info("verifyIfCheckboxIsChecked method executing");

        waitForPageToLoad();
        By checkbox = By.xpath("(//span[text()='" + checkboxName + "'])[1]//ancestor::div[contains(@data-target-selection-name, 'PhoneBook')]//span[@part=\"indicator\"]");
        WebElement checkboxElement = driver.findElement(checkbox);

        String afterContent = (String) ((JavascriptExecutor) driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", checkboxElement);
        if (afterContent != null && !afterContent.isEmpty()) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox '" + checkboxName + "' is checked");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox '" + checkboxName + "' is NOT checked");
            Assert.fail("Checkbox '" + checkboxName + "' state is not as expected");
        }
    }

    /**
     * <b>[Method]</b> -Verify Account Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the account name is as expected<br>
     * @param expectedName String
     */
    public void verifyAccountName(String expectedName) {
        Log.info("verifyAccountName method executing");

        Assert.assertEquals(getText(accountName), expectedName);
        ExtentListeners.test.log(Status.PASS,"Verification OK: Account Name  has the correct value '" + expectedName + "'");
    }

    /**
     * <b>[Method]</b> -Verify Contact Details<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the contact details are as expected<br>
     * @param contactName String
     * @param field String
     * @param expectedValue String
     */
    public void verifyContactDetails(String contactName, String field, String expectedValue) {
        Log.info("verifyContactDetails method executing");

        waitForPageToLoad();
        By contact = By.xpath("//a[@class=\"flex-wrap-ie11\"]//span[text()='" + contactName + "']");
        WebElement element = driver.findElement(contact);
        jse.executeScript("arguments[0].scrollIntoView()", element);

        switch (field) {
            case "Strasse" -> {
                By fieldElement = By.xpath("//span[text()='Straße (Anschrift)']//ancestor::div[contains(@class, 'hint-parent')]//lightning-formatted-text");

                Assert.assertEquals(getText(fieldElement), expectedValue);
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
            }
            case "Nr" -> {
                By fieldElement = By.xpath("//span[text()='Hausnummer (Anschrift)']//ancestor::div[contains(@class, 'hint-parent')]//lightning-formatted-text");

                Assert.assertEquals(getText(fieldElement), expectedValue);
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
            }
            case "Zusatz" -> {
                By fieldElement = By.xpath("//span[text()='Hausnummernzusatz (Anschrift)']//ancestor::div[contains(@class, 'hint-parent')]//lightning-formatted-text");

                Assert.assertEquals(getText(fieldElement), expectedValue);
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
            }
            case "Plz" -> {
                By fieldElement = By.xpath("//span[text()='Postleitzahl (Anschrift)']//ancestor::div[contains(@class, 'hint-parent')]//lightning-formatted-text");

                Assert.assertEquals(getText(fieldElement), expectedValue);
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
            }
            case "Stadt" -> {
                By fieldElement = By.xpath("//span[text()='Ort (Anschrift)']//ancestor::div[contains(@class, 'hint-parent')]//lightning-formatted-text");

                Assert.assertEquals(getText(fieldElement), expectedValue);
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
            }
            case "Land" -> {
                By fieldElement = By.xpath("//span[text()='Land (Anschrift)']//ancestor::div[contains(@class, 'hint-parent')]//lightning-formatted-text");

                Assert.assertEquals(getText(fieldElement), expectedValue);
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
            }
        }
    }

    /**
     * <b>[Method]</b> -Verify Contact Owner Details<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the contact owner details are as expected<br>
     * @param field String
     * @param expectedValue String
     */
    public void verifyContactOwnerDetails(String addressType, String field, String expectedValue) {
        Log.info("verifyContactOwnerDetails method executing");

        waitForPageToLoad();

        if(addressType.equals("Anschlussinhaberadresse")) {
            By contact = By.xpath("//span[text()='Anschlussinhaber']");
            WebElement element = driver.findElement(contact);
            jse.executeScript("arguments[0].scrollIntoView()", element);

            switch (field) {
                case "Strasse" -> {
                    By fieldElement = By.xpath("//span[text()='Straße (Anschrift)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Nr" -> {
                    By fieldElement = By.xpath("//span[text()='Hausnummer (Anschrift)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Zusatz" -> {
                    By fieldElement = By.xpath("//span[text()='Hausnummernzusatz (Anschrift)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Plz" -> {
                    By fieldElement = By.xpath("//span[text()='Postleitzahl (Anschrift)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Stadt" -> {
                    By fieldElement = By.xpath("//span[text()='Ort (Anschrift)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Land" -> {
                    By fieldElement = By.xpath("//span[text()='Land (Anschrift)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
            }

        } else if(addressType.equals("Rechnungsadresse")) {

            By contact = By.xpath("//span[text()='Rechnungsempfänger']");
            WebElement element = driver.findElement(contact);
            jse.executeScript("arguments[0].scrollIntoView()", element);

            switch (field) {
                case "Strasse" -> {
                    By fieldElement = By.xpath("//span[text()='Straße (Rechnungsadresse)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Nr" -> {
                    By fieldElement = By.xpath("//span[text()='Hausnummer (Rechnungsadresse)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Zusatz" -> {
                    By fieldElement = By.xpath("//span[text()='Hausnummernzusatz (Rechnungsadresse)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Plz" -> {
                    By fieldElement = By.xpath("//span[text()='Postleitzahl (Rechnungsadresse)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Stadt" -> {
                    By fieldElement = By.xpath("//span[text()='Ort (Rechnungsadresse)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
                case "Land" -> {
                    By fieldElement = By.xpath("//span[text()='Land (Rechnungsadresse)']//ancestor::dl//lightning-formatted-text");

                    Assert.assertEquals(getText(fieldElement), expectedValue);
                    ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + "'  has the correct value '" + expectedValue + "'");
                }
            }
        }

    }



}
