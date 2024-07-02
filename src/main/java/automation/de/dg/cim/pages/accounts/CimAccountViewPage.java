package automation.de.dg.cim.pages.accounts;

import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import automation.utilities.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <b>PAGES :</b> [CimAccountViewPage]: CIM Account View Page
 */
public class CimAccountViewPage extends Page {

    JavascriptExecutor jse = (JavascriptExecutor) driver;
    By phonebook = By.xpath("//a[@title=\"Telefonbucheintrag\"]");
    By anlegenButton = By.xpath("//button[contains(text(), 'Anlegen')]");
    By closeTelefonNummer = By.xpath("//button[@id=\"button-customer_phonenumber_list-close\"]");
    By linkToSalesforce = By.xpath("//a[contains(text(), 'link to Salesforce')]");
    By evnValue = By.xpath("//tr//td[normalize-space()='EVN']");
    By betrifftValue = By.xpath("//tr//td[normalize-space()='Stammdaten']");
    By dateValue = By.xpath("//tr[@ng-repeat=\"ticket in tickets\"]//td[contains(text(), '2024')]");
    By caseCim = By.xpath("//tr//a[contains(@title, 'Ticket in')]");
    By optIn = By.xpath("//a//span[text()='Optin geändert']");
    By addresseNotizen = By.xpath("//a//span[text()='Adresse geändert']");
    By accountName = By.xpath("//div[@id=\"customerName\"]");
    By addressElement = By.xpath("//div[@id='connectionOwnerAddress']");
    By billingAddress = By.xpath("//div[@id='customerBillingAddress']");
    By vertragGeandert = By.xpath("//span[text()='Vertrag geändert']//parent::a");
    By evnTyp = By.xpath("//span[contains(@ng-bind-html, 'breakNewLines') and contains(text(), 'EVN')]");
    By individual = By.xpath("//a//span[text()='Individual geändert']");
    By actualStringInCim = By.xpath("(//span[text()='Individual geändert']//parent::td//span[@class=\"ng-binding\" and contains(text(), 'Rechnungsadresse geändert:')])[1]");
    By actualStringInCimLieferadresse = By.xpath("(//span[text()='Individual geändert']//parent::td//span[@class=\"ng-binding\" and contains(text(), 'abweichende Lieferadresse')])[1]");
    By addresseGeandertNotizen = By.xpath("//a//span[text()='Addresse geändert']");
    By optInNotizen = By.xpath("//a//span[text()='Optin geändert']");

    /**
     * <b>[Method]</b> - Open Phone number<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality executes the shortcut e+t+1 to open the phone number<br>
     */
    public void openPhoneNumber() {
        Log.info("openPhoneNumber method executing");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Actions(driver)
                .keyDown("e")
                .keyDown("t")
                .keyDown("1")
                .perform();

        waitForPageToLoad();
        new Actions(driver)
                .keyUp("e")
                .keyUp("t")
                .keyUp("1")
                .perform();
        ExtentListeners.test.log(Status.PASS, "Phone number opened");
    }

    /**
     * <b>[Method]</b> - Open Phone Book<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the phone book icon to open it<br>
     */
    public void openPhoneBook() {
        Log.info("openPhoneBook method executing");

        waitForPageToLoad();
        click(phonebook);

    }

    /**
     * <b>[Method]</b> - Edit PhoneBook Field<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality removes the current value from a field in the Phonebook and types the new value<br>
     * @param fieldName String
     * @param value String
     */
    public void editPhoneBookField(String fieldName, String value) {
        Log.info("editPhoneBookField method executing");

        if(fieldName.equals("PLZ")) {
            By fieldElement = By.xpath("//input[@placeholder=\"" + fieldName + "\" and @id='zipcode']");
            driver.findElement(fieldElement).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(fieldElement, value);

        } else if(fieldName.equals("Ort")) {
            By fieldElement = By.xpath("//input[@placeholder=\"" + fieldName + "\" and @name='cityInput']");
            driver.findElement(fieldElement).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(fieldElement, value);

        } else {
            By fieldElement = By.xpath("//input[@placeholder=\"" + fieldName + "\"]");
            driver.findElement(fieldElement).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(fieldElement, value);
        }
    }

    /**
     * <b>[Method]</b> - Click Anlegen<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the Anlegen (Save) button in the phonebook<br>
     */
    public void clickAnlegen() {
        Log.info("clickAnlegen method executing");

        WebElement element = driver.findElement(anlegenButton);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        click(anlegenButton);
    }

    /**
     * <b>[Method]</b> - Close Telefon Nummer View<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality closes the phone number view/popup<br>
     */
    public void closeTelefonNummerView() {
        Log.info("closeTelefonNummerView method executing");

        waitForPageToLoad();
        click(closeTelefonNummer);
    }

    /**
     * <b>[Method]</b> - Open Kundennummer in Salesforce<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality opens in Salesforce the contact of the currently opened account in CIM<br>
     */
    public void openKundennummerInSalesforce() {
        Log.info("openKundennummerInSalesforce method executing");

        waitForPageToLoad();
        WebElement element = driver.findElement(linkToSalesforce);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        click(linkToSalesforce);
    }

    /**
     * <b>[Method]</b> - Click Optin Geandert<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on Optin Geandert hyperlink<br>
     */
    public void clickOptinGeandert() {
        Log.info("clickOptinGeandert method executing");

        waitForPageToLoad();
        click(optIn);

    }

    /**
     * <b>[Method]</b> - Click Adresse Geandert<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on Adresse Geandert hyperlink<br>
     */
    public void clickAdresseGeandert() {
        Log.info("clickAdresseGeandert method executing");

        waitForPageToLoad();
        click(addresseNotizen);

    }

    //-------------------------------VERIFICATIONS--------------------------------------------------------------------\\

    /**
     * <b>[Method]</b> - Verify Phone Book Entry Field Value CIM<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies the specified field if it has the correct value as expected<br>
     * @param field String
     * @param value String
     */
    public void verifyPhoneBookEntryFieldValueCim(String field, String value) {
        Log.info("verifyPhoneBookEntryFieldValueCim method executing");

        if(field.equals("PLZ")) {
            By fieldElement = By.xpath("//input[@placeholder=\"" + field + "\" and @id='zipcode']");
            WebElement element = driver.findElement(fieldElement);
            Assert.assertEquals(element.getAttribute("value"), value);
            ExtentListeners.test.log(Status.PASS,"Verification OK: CIM Field '" + field + "' has the correct value '" + value + "'");
        } else if(field.equals("Ort")) {
            By fieldElement = By.xpath("//input[@placeholder=\"" + field + "\" and @name='cityInput']");
            WebElement element = driver.findElement(fieldElement);
            Assert.assertEquals(element.getAttribute("value"), value);
            ExtentListeners.test.log(Status.PASS,"Verification OK: CIM Field '" + field + "' has the correct value '" + value + "'");
        } else {
            By fieldElement = By.xpath("//input[@placeholder=\"" + field + "\"]");
            WebElement element = driver.findElement(fieldElement);
            Assert.assertEquals(element.getAttribute("value"), value);
            ExtentListeners.test.log(Status.PASS,"Verification OK: CIM Field '" + field + "' has the correct value '" + value + "'");
        }

    }

    /**
     * <b>[Method]</b> - Verify Account Name Cim<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the account in Cim is as expected<br>
     * @param expectedAccountName String
     */
    public void verifyAccountNameCim(String expectedAccountName) {
        Log.info("verifyAccountNameCim method executing");

        for (int i = 0; i < 5; i++) {
            if(!isElementPresent(accountName)){
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                driver.navigate().refresh();
                waitForPageToLoad();
            } else if (isElementPresent(accountName)){
                break;
            }
        }

        Assert.assertTrue(getText(accountName).contains(expectedAccountName), "Element text does not contain the expected content");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The account name in CIM is as expected: " + expectedAccountName);
    }

    /**
     * <b>[Method]</b> - Verify If Address Contains<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the address of the account in CIM contains specific string<br>
     * @param value String
     */
    public void verifyIfAddressContains(String value) {
        Log.info("verifyIfAddressContains method executing");

        Assert.assertTrue(getText(addressElement).contains(value), "Element text does not contain the expected content");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The address in CIM contains the following: " + value);
    }

    /**
     * <b>[Method]</b> - Verify If Rechnung Address Contains<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the rechnung address of the account in CIM contains specific string<br>
     * @param value String
     */
    public void verifyIfRechnungAddressContains(String value) {
        Log.info("verifyIfRechnungAddressContains method executing");

        Assert.assertTrue(getText(billingAddress).contains(value), "Billing address does not contain the expected content");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The address in CIM contains the following: " + value);
    }

    /**
     * <b>[Method]</b> - Verify Case Details<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies the details of a case<br>
     * @param caseNumber String
     */
    public void verifyCaseDetails(String caseNumber) {
        Log.info("verifyCaseDetails method executing");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();

        String currentDate = dtf.format(now);

        Assert.assertEquals(getText(dateValue), currentDate);
        Assert.assertTrue(isElementPresent(evnValue), "Is EVN value present");
        Assert.assertTrue(isElementPresent(betrifftValue), "is BetrifftValue Present");
        Assert.assertEquals(getText(caseCim), caseNumber, "Case Number verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: Case with Type 'Evn' is visible and the date of the case '" + caseNumber + "' is todays date: " + currentDate);
    }

    /**
     * <b>[Method]</b> - Verify Notizen<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if notizen exists <br>
     */
    public void verifyNotizen(String newValue) {
        Log.info("verifyNotizen method executing");

        String evnChange = null;

        switch (newValue) {
            case "EVN gekürzt":
                evnChange = "EVN Typ geändert in REDUCED";
                break;
            case "EVN ungekürzt":
                evnChange = "EVN Typ geändert in FULL";
                break;
        }

        waitForPageToLoad();

        click(vertragGeandert);

        Assert.assertEquals(getText(evnTyp), evnChange, "EVN change verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: EVN Type is changed: " + evnChange);

    }

    /**
     * <b>[Method]</b> - Verify If Optin Is Shown In Notizen<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if optin is shown in notizen <br>
     * @param shouldBeVisible boolean
     */
    public void verifyIfOptinIsShownInNotizen(boolean shouldBeVisible) {
        Log.info("verifyIfOptinIsShownInNotizen method executing");

        if(shouldBeVisible) {

            Assert.assertTrue(isElementPresent(optInNotizen));
        } else {

            Assert.assertFalse(isElementPresent(optInNotizen));
        }
        ExtentListeners.test.log(Status.PASS,"Verification OK: Optin is visible in the Notizen menu");
    }

    /**
     * <b>[Method]</b> - Verify Notizen Optin<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies the notizen optin<br>
     * @param expectedOptIn String
     */
    public void verifyNotizenOptin(String expectedOptIn) {
        Log.info("verifyNotizenOptin method executing");

        By notizenOptinText = By.xpath("//span[contains(@ng-bind-html, 'memo.content | breakNewLines') and contains(., '" + expectedOptIn + " zuletzt')]");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String currentDate = dateTimeFormatter.format(currentDateTime);

        Assert.assertTrue(getText(notizenOptinText).contains(currentDate), "Notizen optin");
        ExtentListeners.test.log(Status.PASS,"Verification OK: Optin is visible in the Notizen menu");
    }

    /**
     * <b>[Method]</b> - Verify If Contact Last Name Change Is Shown In Notizen<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if teh contact last name change is shown in the notizen<br>
     * @param expectedLastName String
     */
    public void verifyIfContactLastNameChangeIsShownInNotizen(String expectedLastName) {
        Log.info("verifyIfContactLastNameChangeIsShownInNotizen method executing");

        waitForPageToLoad();
        click(individual);

        String lastName;
        if(isElementClickable(actualStringInCim)){
            lastName = getText(actualStringInCim);
        } else {
            lastName = getText(actualStringInCimLieferadresse);
        }

        Assert.assertTrue(lastName.contains(expectedLastName));
        ExtentListeners.test.log(Status.PASS,"Verification OK: New changed Contact Last Name is shown in CIM: '" + expectedLastName + "'");

    }

    /**
     * <b>[Method]</b> - Verify Notizen Addresse<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies the notizen addresse<br>
     * @param expectedAddresse String
     */
    public void verifyNotizenAddresse(String expectedAddresse) {
        Log.info("verifyNotizenAddresse method executing");

        By notizenAddresseText = By.xpath("//span[contains(@ng-bind-html, 'memo.content | breakNewLines') and contains(., '" + expectedAddresse + "')]");

        Assert.assertTrue(getText(notizenAddresseText).contains(expectedAddresse), "Notizen optin");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The new address is visible in the Notizen menu");
    }

    /**
     * <b>[Method]</b> - Verify If Addresse Geandert Is Shown In Notizen<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if addresse geandert is shown in notizen <br>
     * @param shouldBeVisible boolean
     */
    public void verifyIfAddresseGeandertIsShownInNotizen(boolean shouldBeVisible) {
        Log.info("verifyIfOptinIsShownInNotizen method executing");

        if(shouldBeVisible) {
            Assert.assertTrue(isElementPresent(addresseGeandertNotizen));
            ExtentListeners.test.log(Status.PASS,"Verification OK: Address change is visible in the Notizen menu");
        } else {
            Assert.assertFalse(isElementPresent(addresseGeandertNotizen));
            ExtentListeners.test.log(Status.PASS,"Verification OK: Address change is NOT visible in the Notizen menu");
        }
    }
}

