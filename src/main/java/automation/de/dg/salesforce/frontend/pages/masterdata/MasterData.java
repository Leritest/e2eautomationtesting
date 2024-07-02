package automation.de.dg.salesforce.frontend.pages.masterdata;

import automation.de.dg.salesforce.frontend.pages.cases.CasePage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.utils.CaseData;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MasterData extends Page {

    JavascriptExecutor jse = (JavascriptExecutor)driver;
    Actions actions = new Actions(driver);
    CasePage casePage = new CasePage();
    Search search = new Search();
    LocalDate currentDate = LocalDate.now();

    By weiterButton = By.xpath("//button[@type='button' and @aria-label='Weiter']");
    By beendenButton = By.xpath("//div[contains(@class,'show_medium')]/descendant::span[contains(text(),'Beenden')]");
    By newContactAnrede = By.xpath("(//input[@aria-haspopup='listbox' and not(@placeholder)])[1]");
    By newContactTitel = By.xpath("(//input[@aria-haspopup='listbox' and not(@placeholder)])[2]");
    By newContactVorname = By.xpath("//span[contains(text(), 'Vorname')]/ancestor::div[1]/following-sibling::div/descendant::input[1]");
    By newContactNachname = By.xpath("//span[contains(text(), 'Nachname')]/ancestor::div[1]/following-sibling::div/descendant::input[1]");
    By newContactGeburtsDatum = By.xpath("//input[@data-id=\"date-picker-slds-input\"]");
    By newContactEmail = By.xpath("//span[contains(text(), 'E-Mail')]/ancestor::div[1]/following-sibling::div/descendant::input[1]");
    By newContactTelefonnummer = By.xpath("//span[contains(text(), 'Telefonnummer')]/ancestor::div[1]/following-sibling::div/descendant::input[1]");
    By newContactMobilTelefon = By.xpath("//span[contains(text(), 'Mobiltelefon')]/ancestor::div[1]/following-sibling::div/descendant::input[1]");
    By newAddressSuchen = By.xpath("//td[text()='Neue Adresse suchen und verwenden']//parent::tr//label");
    By addressSuchen = By.xpath("//span[text()='Adresse suchen']//parent::label//parent::label//following::input[@class=\"slds-input\"]");
    By verwenden = By.xpath("//span[contains(text(), 'Verwenden')]/parent::button");
    By newAddressStrasse = By.xpath("//span[contains(text(), 'Straße')]/parent::label/parent::div/following-sibling::div/input");
    By newAddressNumber = By.xpath("//span[contains(text(), 'Nr.')]/parent::label/parent::div/following-sibling::div/input");
    By newAddressZusatz = By.xpath("//span[contains(text(), 'Zusatz')]/parent::label/parent::div/following-sibling::div/input");
    By newAddressPlz = By.xpath("//span[contains(text(), 'PLZ')]/parent::label/parent::div/following-sibling::div/input");
    By newAddressStadt = By.xpath("//span[contains(text(), 'Stadt')]/parent::label/parent::div/following-sibling::div/input");
    By newAddressLand = By.xpath("//span[contains(text(), 'Land')]/parent::label/parent::div/following-sibling::div/input");
    By newAddressUnvalidierte = By.xpath("//span[contains(text(), \"Unvalidierte Adresse übernehmen\")]/preceding::span[1]");
    By contactsColumns = By.xpath("//table[@class=\"table slds-table slds-table_bordered slds-table_cell-buffer\"]//th");
    By weiterButtonContact = By.xpath("(//button[@aria-label=\"Weiter\"])[1]");
    By beenden = By.xpath("(//button[@aria-label=\"Beenden\"])[1]");
    By radioButtonAlleDokumente = By.xpath("//span[text()='Alle Dokumente vorhanden']//parent::label//span[@class=\"slds-radio_faux\"]");
    By anderungenVollstandig = By.xpath("//span[text()='Änderungen vollständig erfasst']");
    By typFieldValue = By.xpath("//span[text()='Typ']//ancestor::dl//child::dd//lightning-formatted-text");
    By masterDataCategories = By.xpath("//button[@class=\"vlocity-btn slds-button slds-button_brand slds-button_stretch\"]//span");
    By neuenKontaktErfassenRadio = By.xpath("//td[contains(text(), 'Neuen Kontakt erfassen')]/parent::tr/descendant::label");
    By neueAdresseRadio = By.xpath("//td[contains(text(), 'Neue Adresse suchen und verwenden')]/parent::tr/descendant::label");
    By bisher = By.xpath("//h4[text()='Bisher']");
    By neu = By.xpath("//h4[text()='Neu']");
    By contactRadioButtons = By.xpath("//label[@class=\"slds-radio__label\"]");
    By addressRadioButtons = By.xpath("//label[@class=\"slds-radio__label\"]");
    By evnTypDropdown = By.xpath("(//input[@data-value=\"REDUCED\" or @data-value='FULL'])[1]");
    By sprachaufzeichnung = By.xpath("//span[text()='Sprachaufzeichnung durchgeführt']");
    By sprachaufzeichnungNicht = By.xpath("//span[text()='Sprachaufzeichnung nicht erforderlich']");
    By errorMessageVoiceRecording = By.xpath("//div[@role='alert' and text()='!Eine Auswahl ist erforderlich']");
    By telefonOptInLabel = By.xpath("(//span[text()='Telefon Opt-In'])[1]");
    By telefonOptInLabelAlternative = By.xpath("(//span[text()='Telefon Opt-In'])[2]");
    By eMailOptInLabel = By.xpath("(//span[text()='E-Mail Opt-In'])[1]");
    By eMailOptInLabelAlternative = By.xpath("(//span[text()='E-Mail Opt-In'])[2]");
    By verkehrsdatenOptInLabel = By.xpath("(//span[text()='Verkehrsdaten Opt-In'])[1]");
    By verkehrsdatenOptInLabelAlternative = By.xpath("(//span[text()='Verkehrsdaten Opt-In'])[2]");
    By successfulyMessageChanges = By.xpath("//div[text()='Die Änderung wurde erfolgreich durchgeführt']");
    By zuruckButton = By.xpath("(//span[text()='Zurück']//parent::button)[1]");
    By caseKontaktElement = By.xpath("//span[text()='Kontaktname']//ancestor::dl//child::dd/div[@class=\"slds-form-element__control\"]//records-hoverable-link");
    By caseAccountElement = By.xpath("//span[text()='Accountname']//ancestor::dl//child::dd/div[@class=\"slds-form-element__control\"]//records-hoverable-link");
    By phonebookEntriesInputFields = By.xpath("//input[@class=\"vlocity-input slds-input\"]");
    By phonebookEntriesDropdownFields = By.xpath("//input[@class=\"slds-input slds-listbox__option-text_entity\"]");
    By dateElement = By.xpath("(//span[text()='Erstellt von'])[2]//ancestor::div[@class=\"slds-form-element slds-hint-parent test-id__output-root slds-form-element_readonly slds-form-element_horizontal\"]//lightning-formatted-text");
    By benachrichtigungSchickenCheckbox = By.xpath("(//span[text()='Benachrichtigung schicken'])[1]//ancestor::div[@class=\"slds-form-element slds-hint-parent test-id__output-root slds-form-element_readonly slds-form-element_horizontal\"]//lightning-input");
    By sprachaufzeichnungCheckbox = By.xpath("(//span[text()='Sprachaufzeichnung'])[1]//ancestor::div[@class=\"slds-form-element slds-hint-parent test-id__output-root slds-form-element_readonly slds-form-element_horizontal\"]//span[@part=\"indicator\"]");
    By emailIconShownBefore1Minute = By.xpath("(//span[text() = 'Gerade eben'])[1]//ancestor::div[@class=\"slds-grid slds-wrap\"]//lightning-icon[@icon-name=\"standard:email\"]");
    By bestatigungAutomatischCheckbox = By.xpath("//span[text()='Bestätigung automatisch versenden?']//parent::label//span[@class=\"slds-checkbox_faux\"]");
    By bestatigungAutomatischCheckboxPhoneBook = By.xpath("//span[text()='Bestätigung automatisch versenden']//parent::label//span[@class=\"slds-checkbox_faux\"]");
    By einzelverbindungsnachweisLabel = By.xpath("//h1[text()='Einzelverbindungsnachweis']");
    By auswahlLabel = By.xpath("//h1[text()='Auswahl der zu ändernden Stammdaten']");
    By actualRechnungsadresse = By.xpath("//td[contains(text(), 'Rechnungsadresse')]");
    By actualAnschlussinhaberadresse = By.xpath("//td[contains(text(), 'Anschlussinhaberadresse')]");
    By actualLieferadresse = By.xpath("//td[contains(text(), 'Lieferadresse')]");
    By pageTitleElement = By.xpath("//h1[@class='slds-page-header__title slds-p-horizontal_medium slds-text-heading--medium "
            + "slds-m-top_medium os-step-label']");
    By TelefonbucheintrageradioButton = By.xpath("//span[@class=\"slds-radio_faux\"]");
    By caseDetailsLink = By.xpath("//li[@title=\"Details\"]");
    By aktionLink = By.xpath("//li[@title=\"Aktion\"]");
    By closeMasterDataReportButton = By.xpath("//span[contains(text(), 'Stammdatenänderung schließen')]//parent::button//lightning-primitive-icon");
    By companyName = By.xpath("//lightning-formatted-text[@class=\"custom-truncate\"]");
    By masterDataOmniscriptButton = By.xpath("(//button[@type='button' and (text()='Stammdatenänderung' or text()='Master Data Request')])[2]");
    By masterDataOmniscriptButtonTwo = By.xpath("//button[@type='button' and (text()='Stammdatenänderung' or text()='Master Data Request')]");
    By lastMasterDataReportOne = By.xpath("(//span[text() = 'Gerade eben'])[2]//ancestor::div[@class=\"slds-grid slds-wrap\"]//span//span[@data-aura-class=\"forceChatterEntityLink\"]//a//span");
    By lastMasterDataReportTwo = By.xpath("(//span[text() = 'Gerade eben'])[3]//ancestor::div[@class=\"slds-grid slds-wrap\"]//span//span[@data-aura-class=\"forceChatterEntityLink\"]//a//span");
    By lastMasterDataReportThree = By.xpath("(//span[text() = 'Gerade eben'])[4]//ancestor::div[@class=\"slds-grid slds-wrap\"]//span//span[@data-aura-class=\"forceChatterEntityLink\"]//a//span");
    By bestatigungAutomatischVersendenCheckbox = By.xpath("//span[text()='Bestätigung automatisch versenden']//preceding-sibling::span");
    By fieldElement = By.xpath("//label[@vlocity_cmt-input_input_slds or @vlocity_cmt-combobox_combobox_slds or @vlocity_cmt-datepicker_datepicker_slds]");
    By lastNameError = By.xpath("//div[text()='!Nachname is required.']");
    By selectAtLeastOneRoleMessage = By.xpath("//p[text()='Bitte wählen Sie mindestens eine Rolle aus']");
    By installationContactCannotBeEditedMessage = By.xpath("//p[text()='Die Rolle Ansprechpartner Installation kann nicht bearbeitet werden.']");
    By verwandtTab = By.xpath("//a[@data-label=\"Verwandt\"]");
    By phoneCheckedNeu = By.xpath("(//vlocity_cmt-omniscript-checkbox//span[@class=\"slds-checkbox_faux\"])[3]");
    By emailUnCheckedNeu = By.xpath("(//vlocity_cmt-omniscript-checkbox//span[@class=\"slds-checkbox_faux\"])[8]");
    By verkehrsdatenUncheckedNeu = By.xpath("(//vlocity_cmt-omniscript-checkbox//span[@class=\"slds-checkbox_faux\"])[12]");
    By phoneUncheckedBisher = By.xpath("(//vlocity_cmt-omniscript-checkbox//span[@class=\"slds-checkbox_faux\"])[2]");
    By emailUnCheckedBisher = By.xpath("(//vlocity_cmt-omniscript-checkbox//span[@class=\"slds-checkbox_faux\"])[6]");
    By verkehrsdatenCheckedBisher = By.xpath("(//vlocity_cmt-omniscript-checkbox//span[@class=\"slds-checkbox_faux\"])[9]");
    By nachnameNeu = By.xpath("//slot[@vlocity_cmt-omniscriptstep_omniscriptstep]//vlocity_cmt-omniscript-text-block[18]//div[contains(@style, 'background-color')]");
    By nachnameNeuAlternative = By.xpath("//slot[@vlocity_cmt-omniscriptstep_omniscriptstep]//vlocity_cmt-omniscript-text-block[24]//div[contains(@style, 'background-color')]");
    By evnTypNewValue = By.xpath("(//sub[text()='EVN-Typ']//ancestor::div[contains(@style, 'float: left')]//div[contains(@style, 'background-color')])[2]");
    By kontaktField = By.xpath("//span[text()='Kontakt']//ancestor::div[contains(@class, 'slds-hint-parent')]//a//span");
    By abbrechenButton = By.xpath("//div[@class=\"omniscript-sfl-actions\"]//span[text()=\"Abbrechen\"]");
    By anderungenButton = By.xpath("//button[@aria-label=\"Änderungen vollständig erfasst\"]");
    By telefonCheckboxElement = By.xpath("(//span[text()='Telefon Opt-In']//parent::label//span[@class=\"slds-checkbox_faux\"])[2]");
    By eMailCheckboxElement = By.xpath("(//span[text()='E-Mail Opt-In']//parent::label//span[@class=\"slds-checkbox_faux\"])[2]");
    By verkehrsdatenCheckboxElement = By.xpath("(//span[text()='Verkehrsdaten Opt-In']//parent::label//span[@class=\"slds-checkbox_faux\"])[2]");
    By evnTypCurrentdropdown = By.xpath("(//input[@class=\"slds-input slds-listbox__option-text_entity\"])[1]");
    By streetInput = By.xpath("//span[text()='Straße']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");
    By numberInput = By.xpath("//span[text()='Nr.']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");
    By zusatzInput = By.xpath("//span[text()='Zusatz']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");
    By zipCodeInput = By.xpath("//span[text()='PLZ']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");
    By cityInput = By.xpath("//span[text()='Stadt']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");
    By countryInput = By.xpath("//span[text()='Land']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");



    /**
     * <b>[Method]</b> - Select Master Data Case<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects master data case<br>
     * @param masterDataCase String
     */
    public void selectMasterDataCase(String masterDataCase) {
        Log.info("selectMasterDataCase method executing");

        By caseName = By.xpath("//span[text()='" + masterDataCase + "']");
        wait.until(ExpectedConditions.elementToBeClickable(caseName));
        List<WebElement> caseElements = getElements(caseName);
        if (!caseElements.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView()", caseElements.get(0));
            caseElements.get(0).click();
            ExtentListeners.test.log(Status.PASS, "Clicking on element: " + caseName);
        }
    }

    /**
     * <b>[Method]</b> - Launch Master Data OmniScript<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality launches the master data omniscript when a case is opened<br>
     */
    public void launchMasterDataOmniscript() {
        Log.info("launchMasterDataOmniscript method executing");

        waitForPageToLoad();

        boolean isPresent = driver.findElements(masterDataOmniscriptButton).size() > 0;
        if(isPresent){
            click(masterDataOmniscriptButton);
        } else {
            click(masterDataOmniscriptButtonTwo);
        }
    }

    /**
     * <b>[Method]</b> - Select Master Data Category<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the wanted Master Data Category<br>
     * @param masterDataCategoryValue String
     */
    public void selectMasterDataCategory(String masterDataCategoryValue) {
        Log.info("selectMasterDataCategory method executing");

        By masterDataCategory = By.xpath("//span[@class = 'btnLabel' and text()='" + masterDataCategoryValue + "']");
        wait.until(ExpectedConditions.elementToBeClickable(masterDataCategory));
        WebElement masterDataElement = driver.findElement(masterDataCategory);
        masterDataElement.click();
        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + masterDataCategory);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(masterDataCategory));
    }

    public void scrollToTop() {
        Log.info("scrollToTop method executing");

        waitForPageToLoad();

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        actions.sendKeys(Keys.PAGE_UP).perform();

    }

    /**
     * <b>[Method]</b> - Change Field To<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality edits the value of the specified field with a new value<br>
     * @param fieldToChange String
     * @param newValue String
     */
    public void changeFieldTo(String fieldToChange, String newValue) {
        Log.info("changeFieldTo method executing");

        waitForPageToLoad();

        if (fieldToChange.equalsIgnoreCase("Firmenname") || fieldToChange.equalsIgnoreCase("Company name")) {
            By lastName = By.xpath("//span[text()='Firmenname']/ancestor::div[@class='slds-form-element slds-form-container']/descendant::input");
            clearField(lastName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            type(lastName, newValue);
            return;
        }

        if (fieldToChange.equalsIgnoreCase("nachname") || fieldToChange.equalsIgnoreCase("lastname")) {
            By lastName = By.xpath("//*[@data-omni-key='TextContactLastName']/slot/c-input/div/div[2]/input");
            //clearField(lastName);
            driver.findElement(lastName).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            type(lastName, newValue);
            return;
        }

        if (fieldToChange.equalsIgnoreCase("vorname") || fieldToChange.equalsIgnoreCase("firstname")) {
            By lastName = By.xpath("//*[@data-omni-key='TextContactFirstName']/slot/c-input/div/div[2]/input");
            clearField(lastName);
            type(lastName, newValue);
            return;
        }

        if (fieldToChange.equalsIgnoreCase("Weitere Vornamen") || fieldToChange.equalsIgnoreCase("middle name")) {
            By lastName = By.xpath("//*[@data-omni-key='TextContactMiddleName']/slot/c-input/div/div[2]/input");
            clearField(lastName);
            type(lastName, newValue);
        }
    }

    /**
     * <b>[Method]</b> - Delete Field<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clears the value of the specified field<br>
     * @param fieldToDelete String
     */
    public void deleteField(String fieldToDelete) {
        Log.info("deleteField method executing");

        if (fieldToDelete.equalsIgnoreCase("Firmenname") || fieldToDelete.equalsIgnoreCase("Company name")) {
            By companyName = By.xpath("//span[text()='Firmenname']/ancestor::div[@class='slds-form-element "
                    + "slds-form-container']/descendant::input");
            click(companyName);
            clearField(companyName);
            return;
        }

        if (fieldToDelete.equalsIgnoreCase("nachname") || fieldToDelete.equalsIgnoreCase("lastname")) {
            By lastName = By.xpath("//*[@data-omni-key='TextContactLastName']/slot/c-input/div/div[2]/input");

            click(lastName);
            driver.findElement(lastName).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);

            //clearField(lastName);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            click(lastName);
            return;
        }

        if (fieldToDelete.equalsIgnoreCase("vorname") || fieldToDelete.equalsIgnoreCase("firstname")) {
            By firstName = By.xpath("//*[@data-omni-key='TextContactFirstName']/slot/c-input/div/div[2]/input");
            click(firstName);
            clearField(firstName);
            return;
        }

        if (fieldToDelete.equalsIgnoreCase("Weitere Vornamen") || fieldToDelete.equalsIgnoreCase("middle name")) {
            By middleName = By.xpath("//*[@data-omni-key='TextContactMiddleName']/slot/c-input/div/div[2]/input");
            click(middleName);
            clearField(middleName);
        }
    }

    /**
     * <b>[Method]</b> - Documents are Required<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the radio button inside the omniscript for the documents to be required<br>
     * @param documentsAreRequiredAndAvailable boolean
     */
    public void documentsAreRequired(boolean documentsAreRequiredAndAvailable) {
        Log.info("documentsAreRequired method executing");

        WebElement weiter = driver.findElement(weiterButton);

        By radioButtonToSelect = documentsAreRequiredAndAvailable ?
                By.xpath("//label/span[text()='Alle Dokumente vorhanden']/../span[1]") :
                By.xpath("//label/span[text()='Dokument nicht erforderlich']/../span[1]");

        WebElement radioButton = driver.findElement(radioButtonToSelect);
        jse.executeScript("arguments[0].scrollIntoView()", radioButton);
        click(radioButtonToSelect);

        jse.executeScript("arguments[0].scrollIntoView()", weiter);
        click(weiterButton);
    }

    /**
     * <b>[Method]</b> - Select Contact<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the specified contact<br>
     * @param contact String
     */
    public void selectContact(String contact) {
        Log.info("selectContact method executing");

        Log.info("selectContact method executing");
        By user = By.xpath("//td[contains(text(), '" + contact + "')]/parent::tr/descendant::label");
        By userAdditionalElement = By.xpath("(//td[contains(text(), '" + contact + "')]/parent::tr/descendant::label)[2]");
        boolean isPresent = driver.findElements(userAdditionalElement).size() > 0;
        if(isPresent){
            WebElement userAdditional = driver.findElement(userAdditionalElement);
            jse.executeScript("arguments[0].scrollIntoView()", userAdditional);
            click(userAdditionalElement);
        } else {
            WebElement userElement = driver.findElement(user);
            jse.executeScript("arguments[0].scrollIntoView()", userElement);
            click(user);
        }
    }

    /**
     * <b>[Method]</b> - Select Address<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the wanted address when creating the master data<br>
     * @param address String
     */
    public void selectAddress(String address) {
        Log.info("selectAddress method executing");

        By user = By.xpath("//td[contains(text(), '" + address + "')]/parent::tr//label");
        click(user);
    }

    /**
     * <b>[Method]</b> - Create New Contact<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality chooses the option to create new contact and populating the needed fields<br>
     * @param contactData List
     */
    public void createNewContact(List<CaseData> contactData) {
        Log.info("createNewContact method executing");

        for (CaseData caseData : contactData) {

            if(caseData.getAnrede() != null) {
                click(newContactAnrede);
                casePage.selectAnrede(caseData.getAnrede());
            }

            if(caseData.getTitel() != null) {
                click(newContactTitel);
                casePage.selectContactTitel(caseData.getTitel());
            }

            if(caseData.getVorname() != null) {
                click(newContactVorname);
                type(newContactVorname, caseData.getVorname());
            }
            if(caseData.getNachname() != null) {
                click(newContactNachname);
                type(newContactNachname, caseData.getNachname());
            }

            if(caseData.getGeburtsdatum() != null) {
                click(newContactGeburtsDatum);
                type(newContactGeburtsDatum, caseData.getGeburtsdatum());
            }

            if(caseData.getEmail() != null) {
                click(newContactEmail);
                type(newContactEmail, caseData.getEmail());
            }

            if(caseData.getTelefonnummer() != null) {
                click(newContactTelefonnummer);
                type(newContactTelefonnummer, caseData.getTelefonnummer());
            }

            if(caseData.getMobiltelefon() != null) {
                click(newContactMobilTelefon);
                type(newContactMobilTelefon, caseData.getMobiltelefon());
            }

        }
        WebElement element = driver.findElement(weiterButtonContact);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        click(weiterButtonContact);
    }

    /**
     * <b>[Method]</b> - Select New Address Option<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality chooses the select new address option in order for the form of creating new address to be opened<br>
     */
    public void selectNewAddressOption(){
        Log.info("selectNewAddressOption method executing");

        click(newAddressSuchen);
        click(weiterButtonContact);
    }

    /**
     * <b>[Method]</b> - Click Unvalidierte Adresse<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality chooses the Unvalidierte Adresse option, in order to continue with a unvalidated address<br>
     */
    public void clickUnvalidierteAdresse() {
        Log.info("clickUnvalidierteAdresse method executing");

        wait.until(ExpectedConditions.presenceOfElementLocated(newAddressUnvalidierte));
        click(newAddressUnvalidierte);
    }

    /**
     * <b>[Method]</b> - Create New or Edit Address<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality populates the field when creating or editing an address<br>
     * @param addressData List
     */
    public void createNewOrEditAddress(List<CaseData> addressData) {
        Log.info("createNewOrEditAddress method executing");

        waitForPageToLoad();
        for (CaseData caseData : addressData) {
            type(addressSuchen, caseData.getAdresseSuchen());
            click(verwenden);

            wait.until(ExpectedConditions.presenceOfElementLocated(newAddressUnvalidierte));
            click(newAddressUnvalidierte);

            click(newAddressStrasse);
            driver.findElement(newAddressStrasse).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(newAddressStrasse, caseData.getStrasse());

            click(newAddressNumber);
            driver.findElement(newAddressNumber).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(newAddressNumber, caseData.getNr());

            click(newAddressZusatz);
            driver.findElement(newAddressZusatz).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(newAddressZusatz, caseData.getZusatz());

            click(newAddressPlz);
            waitForPageToLoad();
            driver.findElement(newAddressPlz).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(newAddressPlz, caseData.getPlz());

            click(newAddressStadt);
            driver.findElement(newAddressStadt).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(newAddressStadt, caseData.getStadt());

            click(newAddressLand);
            driver.findElement(newAddressLand).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            type(newAddressLand, caseData.getLand());

            WebElement element = driver.findElement(weiterButton);

            jse.executeScript("arguments[0].scrollIntoView()", element);
            click(weiterButton);
        }
    }

    /**
     * <b>[Method]</b> - Master Data Select Dokumente<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality select Alle dokumente option and then proceeds to the next screen by clicing on the next button<br>
     */
    public void masterDataSelectDokumente() {
        Log.info("masterDataSelectDokumente method executing");

        waitForPageToLoad();
        WebElement element = driver.findElement(weiterButtonContact);

        jse.executeScript("arguments[0].scrollIntoView()", element);
        click(weiterButtonContact);
        waitForPageToLoad();
        click(radioButtonAlleDokumente);
        click(weiterButtonContact);
        waitForPageToLoad();
    }

    /**
     * <b>[Method]</b> - Close Master Data Omniscript<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality closes the master data omniscript when the execution comes to the last page of the master data omniscript<br>
     */
    public void closeMasterDataOmniscript() {
        Log.info("closeMasterDataOmniscript method executing");

        wait.until(ExpectedConditions.visibilityOfElementLocated(beenden));
        click(beenden);
        wait.until(ExpectedConditions.visibilityOfElementLocated(anderungenVollstandig));
        click(anderungenVollstandig);
        wait.until(ExpectedConditions.visibilityOfElementLocated(typFieldValue));
    }

    /**
     * <b>[Method]</b> - Click Weiter<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the next button<br>
     */
    public void clickWeiter() {
        Log.info("clickWeiter method executing");

        waitForPageToLoad();
        WebElement element = driver.findElement(weiterButtonContact);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        click(weiterButtonContact);
    }

    /**
     * <b>[Method]</b> - Double Click Weiter<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality double-clicks on the next button<br>
     */
    public void doubleClickWeiter() {
        Log.info("doubleClickWeiter method executing");

        waitForPageToLoad();
        click(weiterButtonContact);
        click(weiterButtonContact);
    }

    /**
     * <b>[Method]</b> - Choose Evn Typ Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to choose the EVN Typ field value, it clicks the dropdown and selects the specified option from the dropdown<br>
     * @param value String
     */
    public void chooseEvnTypValue(String value) {
        Log.info("chooseEvnTypValue method executing");

        waitForPageToLoad();
        By evnTypValue = By.xpath("(//span[text()='" + value + "'])[1]");
        String dropdownValue = driver.findElement(evnTypDropdown).getAttribute("value");
        String evnTypOne = "EVN ungekürzt";
        String evnTypTwo = "EVN gekürzt";
        String evnTypThree = "Kein EVN";

        if (dropdownValue.equals(value)) {
            // If the current dropdown value is the same as the specified value, select a different value
            if (value.equals(evnTypOne)) {
                click(evnTypDropdown);
                click(By.xpath("(//span[text()='" + evnTypTwo + "'])[1]"));
            } else if (value.equals(evnTypTwo)) {
                click(evnTypDropdown);
                click(By.xpath("(//span[text()='" + evnTypThree + "'])[1]"));
            } else if (value.equals(evnTypThree)) {
                click(evnTypDropdown);
                click(By.xpath("(//span[text()='" + evnTypOne + "'])[1]"));
            }

        } else {
            click(evnTypDropdown);
            click(evnTypValue);
        }
    }

    /**
     * <b>[Method]</b> - Click Beenden<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to click on the Finish button<br>
     */
    public void clickBeenden() {
        Log.info("clickBeenden method executing");

        click(beendenButton);
    }

    /**
     * <b>[Method]</b> - Select Voice Recording Option<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to select the specified option for Voice Recording field<br>
     * @param option String
     */
    public void selectVoiceRecordingOption(String option) {
        Log.info("selectVoiceRecordingOption method executing");

        By optionElement = By.xpath("//span[text()='" + option + "']");
        click(optionElement);
    }

    /**
     * <b>[Method]</b> - Select Werbeeinstellungen OptIn<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to select Werbeeinstellung opt-in<br>
     * @param field String
     */
    public void selectWerbeeinstellungenOptIn(String field) {
        Log.info("selectWerbeeinstellungenOptIn method executing");

        waitForPageToLoad();
        By checkboxElement = By.xpath("//span[text()='" + field + "']//parent::label//span[@class=\"slds-checkbox_faux\"]");
        By checkboxElementAlternative = By.xpath("(//span[text()='" + field + "']//parent::label//span[@class=\"slds-checkbox_faux\"])[2]");
        if(isElementClickable(checkboxElement)){
            click(checkboxElement);
        } else {
            click(checkboxElementAlternative);
        }

    }

    /**
     * <b>[Method]</b> - Select Werbeeinstellungen OptIn<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to select Werbeeinstellung opt-in<br>
     */
    public void uncheckAllWerbeeinstellungenOptIns() {
        Log.info("uncheckAllWerbeeinstellungenOptIns method executing");

        waitForPageToLoad();

        if(isElementClickable(telefonCheckboxElement)) click(telefonCheckboxElement);
        if(isElementClickable(eMailCheckboxElement)) click(eMailCheckboxElement);
        if(isElementClickable(verkehrsdatenCheckboxElement)) click(verkehrsdatenCheckboxElement);

    }

    /**
     * <b>[Method]</b> - Click Zuruck<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is used to click on Back button<br>
     */
    public void clickZuruck() {
        Log.info("clickZuruck method executing");

        WebElement element = driver.findElement(zuruckButton);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        click(zuruckButton);
    }

    /**
     * <b>[Method]</b> - Open Case Contact<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is to scroll into view and click on the Case Contact element<br>
     */
    public void openCaseContact() {
        Log.info("openCaseContact method executing");

        WebElement element = driver.findElement(caseKontaktElement);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        waitForPageToLoad();
        click(caseKontaktElement);
    }


    /**
     * <b>[Method]</b> - Open Case Account<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is to scroll into view and click on the Case Account element<br>
     */
    public void openCaseAccount(){
        Log.info("openCaseAccount method executing");

        waitForPageToLoad();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.sendKeys(Keys.PAGE_UP).perform();

        By frame = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-case_-record_-page4___-case___-v-i-e-w/forcegenerated-flexipage_case_record_page4_case__view_js/record_flexipage-desktop-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-three-col-template-desktop2/div/div/div/flexipage-record-home-scrollable-column[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_case___0121w000000ddnraac___compact___view___recordlayout2/records-highlights2/div[1]/div[2]/slot/records-highlights-details-item/div/p[1]");
        click(frame);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        if(isElementVisible(caseAccountElement)) {

            WebElement element = driver.findElement(caseAccountElement);

            new Actions(driver)
                    .moveToElement(element)
                    .perform();

            actions.sendKeys(Keys.PAGE_DOWN).perform();

            jse.executeScript("arguments[0].scrollIntoView()", element);
            jse.executeScript("arguments[0].click()", element);
        } else {
            try {
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement element = driver.findElement(caseAccountElement);
            new Actions(driver)
                    .moveToElement(element)
                    .perform();

            jse.executeScript("arguments[0].scrollIntoView()", element);
            jse.executeScript("arguments[0].click()", element);

        }


    }

    /**
     * <b>[Method]</b> - Get Company Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns the name of the Company<br>
     */
    public String getCompanyName() {
        Log.info("getCompanyName method executing");

        return getText(companyName);
    }

    /**
     * <b>[Method]</b> - Open Last Master Data Report from Aktion<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality navigates to Aktion tab in a case and then searches for the last Master Data report and opens it<br>
     */
    public void openLastMasterDataReportFromAktion() {
        Log.info("openLastMasterDataReportFromAktion method executing");

        List<By> lastMasterDataReportList = Arrays.asList(lastMasterDataReportOne, lastMasterDataReportTwo, lastMasterDataReportThree);

        // Check which locator is present
        for (By locator : lastMasterDataReportList) {
            List<WebElement> elements = driver.findElements(locator);

            if (!elements.isEmpty()) {

                WebElement element = driver.findElement(locator);
                jse.executeScript("arguments[0].scrollIntoView()", element);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                actions.moveToElement(element).click().build().perform();
                waitForPageToLoad();

                break; // Exit the loop if an element is found
            }
        }
    }

    /**
     * <b>[Method]</b> - Close Master Data Report<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality closes the Master Data Report tab<br>
     */
    public void closeMasterDataReport() {
        Log.info("closeMasterDataReport method executing");

        click(closeMasterDataReportButton);
    }

    /**
     * <b>[Method]</b> - Go to Case Aktion<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality navigates to case Aktion tab<br>
     */
    public void gotoCaseAktion() {
        Log.info("gotoCaseAktion method executing");

        waitForPageToLoad();
        click(aktionLink);
    }

    /**
     * <b>[Method]</b> - Go to Case Details from Aktion<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality navigates to case details from the Aktion tab<br>
     */
    public void gotoCaseDetailsFromAktion() {
        Log.info("gotoCaseDetailsFromAktion method executing");

        WebElement element = driver.findElement(caseDetailsLink);
        waitForPageToLoad();
        jse.executeScript("arguments[0].scrollIntoView()", element);
        actions.sendKeys(Keys.PAGE_UP).build().perform();
        click(caseDetailsLink);
    }

    /**
     * <b>[Method]</b> - Select Telefonbucheintrage Phone Number<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the Telefonbucheintrage radio button<br>
     */
    public void selectTelefonbucheintragePhoneNumber() {
        Log.info("selectTelefonbucheintragePhoneNumber method executing");

        waitForPageToLoad();
        click(TelefonbucheintrageradioButton);
    }

    /**
     * <b>[Method]</b> - Select PhoneBook Entries Page Checkbox<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the specified checkbox in the phonebook entries page<br>
     * @param checkboxName String
     */
    public void selectPhoneBookEntriesPageCheckbox(String checkboxName) {
        Log.info("selectPhoneBookEntriesPageCheckbox method executing");

        By checkbox = By.xpath("//span[text()='" + checkboxName + "']//preceding-sibling::span");
        WebElement element = driver.findElement(checkbox);
        actions.moveToElement(element).click().build().perform();
        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + checkbox);
    }

    /**
     * <b>[Method]</b> - Click Bestatigung Automatisch Versenden Checkbox<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the Bestatigung Automatisch Versenden checkbox <br>
     */
    public void clickBestatigungAutomatischVersendenCheckbox() {
        Log.info("clickBestatigungAutomatischVersendenCheckbox method executing");

        WebElement element = driver.findElement(bestatigungAutomatischVersendenCheckbox);
        actions.moveToElement(element).click().build().perform();
        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + bestatigungAutomatischVersendenCheckbox);
    }

    /**
     * <b>[Method]</b> - Populate PhoneBook Entries Input Field<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality populates the specified input field in the PhoneBook entries<br>
     * @param fieldName String
     * @param value String
     */
    public void populatePhoneBookEntriesInputField(String fieldName, String value) {
        Log.info("populatePhoneBookEntriesInputField method executing");

        By field = By.xpath("//span[text()='" + fieldName + "']//ancestor::div[@class=\"slds-form-element slds-form-container\"]//input");
        WebElement element = driver.findElement(field);
        String fieldValue = element.getAttribute("value");

        if(fieldValue.isEmpty()){
            jse.executeScript("arguments[0].scrollIntoView()", element);
            type(field, value);
        } else {
            jse.executeScript("arguments[0].scrollIntoView()", element);
            click(field);
            driver.findElement(field).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
            ExtentListeners.test.log(Status.PASS, "Deleted value '" + fieldValue + "' from field '" + fieldName + "'");
            type(field, value);
        }
    }

    /**
     * <b>[Method]</b> - Select PhoneBook Entries Dropdown Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality selects the specified value for a specific dropdown in the PhoneBook entries<br>
     * @param dropdownName String
     * @param value String
     */
    public void selectPhoneBookEntriesDropdownValue(String dropdownName, String value) {
        Log.info("selectPhoneBookEntriesDropdownValue method executing");

        By field = By.xpath("//span[text()='" + dropdownName + "']//ancestor::div[@class=\"slds-combobox slds-dropdown-trigger slds-dropdown-trigger_click\"]//input");
        By valueElement = By.xpath("//div[@data-label=\"" + value + "\"]");

        waitForPageToLoad();
        click(field);
        WebElement elementTwo = driver.findElement(valueElement);
        actions.moveToElement(elementTwo).click().perform();
        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + valueElement);
    }

    /**
     * <b>[Method]</b> - Select Role Checkbox<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the checkbox next to the specified Role<br>
     * @param roleName String
     */
    public void selectRoleCheckbox(String roleName) {
        Log.info("selectRoleCheckbox method executing");

        By roleCheckbox = By.xpath("(//span[contains(@class, 'element__label') and text()='" + roleName + "'])[2]//preceding-sibling::span");
        By roleCheckboxAlternative = By.xpath("(//span[contains(@class, 'element__label') and text()='" + roleName + "'])[1]//preceding-sibling::span");


        if(isElementClickable(roleCheckbox)) {
            waitForPageToLoad();
            click(roleCheckbox);
        } else {
            waitForPageToLoad();
            click(roleCheckboxAlternative);
        }


    }

    /**
     * <b>[Method]</b> - Goto Verwandt Tab MData Report<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on the verwandt tab link in the master data report to navigate to the Verwandt tab<br>
     */
    public void gotoVerwandtTabMDataReport() {
        Log.info("gotoVerwandtTabMDataReport method executing");

        waitForPageToLoad();
        click(verwandtTab);
    }

    public String getNamensanderungAnschlussinhaberNachnameCurrentValue() {
        Log.info("getNamensanderungAnschlussinhaberNachnameCurrentValue method executing");

        By nachnameField = By.xpath("(//input[@class=\"vlocity-input slds-input\"])[3]");

        String nachnameValue =  driver.findElement(nachnameField).getAttribute("value");

        return nachnameValue;
    }

    public String getEvnTypCurrentValue() {
        Log.info("getEvnTypCurrentValue method executing");

        waitForPageToLoad();

        return driver.findElement(evnTypCurrentdropdown).getAttribute("value");
    }

    public String getNewAddressStreet() {
        Log.info("getNewAddressStreet method executing");

        return driver.findElement(streetInput).getAttribute("value");
    }

    public String getNewAddressNumber() {
        Log.info("getNewAddressNumber method executing");

        return driver.findElement(numberInput).getAttribute("value");
    }
    public String getNewAddressZusatz() {
        Log.info("getNewAddressZusatz method executing");

        return driver.findElement(zusatzInput).getAttribute("value");
    }

    public String getNewAddressZipCode() {
        Log.info("getNewAddressZipCode method executing");

        return driver.findElement(zipCodeInput).getAttribute("value");
    }

    public String getNewAddressCity() {
        Log.info("getNewAddressCity method executing");

        return driver.findElement(cityInput).getAttribute("value");
    }

    public String getNewAddressCountry() {
        Log.info("getNewAddressCountry method executing");

        return driver.findElement(countryInput).getAttribute("value");
    }

    public void selectContactToDeactivate(String contactName) {
        Log.info("selectContactToDeactivate method executing");

        waitForPageToLoad();
        By contactCheckBox = By.xpath("//td[text()='" + contactName + "']//preceding-sibling::td//span[@part=\"indicator\"]");
        click(contactCheckBox);
    }

    //-------------------------------VERIFICATIONS--------------------------------------------------------------------\\

    /**
     * <b>[Method]</b> - Verify Page Title Should Be<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the page title is the same as the one expected<br>
     * @param masterDataCategory String
     */
    public void verifyPageTitleShouldBe(String masterDataCategory) {
        Log.info("verifyPageTitleShouldBe method executing");

        wait.until(ExpectedConditions.presenceOfElementLocated(pageTitleElement));
        String pageTitle = getText(pageTitleElement);
        Assert.assertEquals(pageTitle, masterDataCategory);
    }

    /**
     * <b>[Method]</b> - Verify Contacts<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the contact details are the same as the expected ones<br>
     * @param contactsList List
     */
    public void verifyContacts(List<CaseData> contactsList) {
        Log.info("verifyContacts method executing");

        waitForPageToLoad();
        int i = 0;
        for (CaseData caseData : contactsList) {
            int rowIndex = i + 1;
            By rowIndexFirst = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[1]//label");
            By rowIndexSecond = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[2]");
            By rowIndexThird = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[3]");
            By rowIndexFourth = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[4]");
            By rowIndexFifth = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[5]");
            By rowIndexSixth = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[6]");
            By rowIndexSeventh = By.xpath("//*[@data-omni-key='OmniScriptDatatable']/descendant::tbody/descendant::tr[" + rowIndex + "]/descendant::td[7]");

            if (!driver.findElements(rowIndexFirst).isEmpty()) {
                Boolean test = Boolean.parseBoolean(caseData.getAuswahlen());
                Assert.assertEquals(driver.findElement(rowIndexFirst).isSelected(), test);
            }

            if (!driver.findElements(rowIndexSecond).isEmpty()) {
                Assert.assertEquals(caseData.getName(), getText(rowIndexSecond));
                ExtentListeners.test.log(Status.PASS, "Verification OK: Name: " + caseData.getName() + " is as expected");
            }

            if (!driver.findElements(rowIndexThird).isEmpty()) {
                Assert.assertEquals(caseData.getEmail(), getText(rowIndexThird));
                ExtentListeners.test.log(Status.PASS, "Verification OK: Email: " + caseData.getEmail() + " is as expected");
            }

            if (!driver.findElements(rowIndexFourth).isEmpty()) {
                Assert.assertEquals(caseData.getTelefonnummer(), getText(rowIndexFourth));
            }

            if (!driver.findElements(rowIndexFifth).isEmpty()) {
                Assert.assertEquals(caseData.getMobiltelefon(), getText(rowIndexFifth));
            }

            if (!driver.findElements(rowIndexSixth).isEmpty()) {
                Assert.assertEquals(caseData.getGeburtsdatum(), getText(rowIndexSixth));
            }

            if (!driver.findElements(rowIndexSeventh).isEmpty()) {
                Assert.assertEquals(caseData.getRollen(), getText(rowIndexSeventh));
            }
            i++;
        }
    }

    /**
     * <b>[Method]</b> - Verify Fields Are Updated With The New Values<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the fields are updated with the new values in the master data requests<br>
     * @param fieldsMasterDataRequests List
     */
    public void verifyFieldsAreUpdatedWithTheNewValues(List<CaseData> fieldsMasterDataRequests) {
        Log.info("verifyFieldsAreUpdatedWithTheNewValues method executing");

        HeaderPage headerPage = new HeaderPage();

        int i = 0;
        for (CaseData caseData : fieldsMasterDataRequests) {
            int rowIndex = i + 1;

            By rowIndexSecond = By.xpath("//table[@aria-label='MasterDataRequests' or @aria-label='Feldänderungen']/child::thead/following-sibling::tbody/tr["
                    + rowIndex + "]/child::td[2]");
            By rowIndexThird = By.xpath("//table[@aria-label='MasterDataRequests' or @aria-label='Feldänderungen']/child::thead/following-sibling::tbody/tr["
                    + rowIndex + "]/child::td[3]");
            By rowIndexFourth = By.xpath("//table[@aria-label='MasterDataRequests' or @aria-label='Feldänderungen']/child::thead/following-sibling::tbody/tr["
                    + rowIndex + "]/child::td[4]");

            if (!driver.findElements(rowIndexSecond).isEmpty()) {
                Assert.assertEquals(caseData.getFieldThatChanges(), getText(rowIndexSecond));
            }

            if (!driver.findElements(rowIndexThird).isEmpty()) {
                Assert.assertEquals(caseData.getOldValue(), getText(rowIndexThird));
            }

            if (!driver.findElements(rowIndexFourth).isEmpty()) {
                Assert.assertEquals(caseData.getNewValue(), getText(rowIndexFourth));
            }
        }
        headerPage.closeTab("Feldänderungen");
    }

    /**
     * <b>[Method]</b> - Verify Columns Contact Adding Visibility<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the specified fields when adding contacts are visible on the page<br>
     * @param fields String[]
     */
    public void verifyColumnsContactAddingVisibility(String[] fields) {
        Log.info("verifyColumnsContactAddingVisibility method executing");

        List<WebElement> actualVisibleFields = driver.findElements(contactsColumns);
        int i = 0;
        for (WebElement r : actualVisibleFields) {
            if(r.getText().equals("Name") || r.getText().equals("E-Mail") || r.getText().equals("Rollen")) {
                Assert.assertEquals(r.getText(), fields[i], "Columns visibility verification");
                ExtentListeners.test.log(Status.PASS,"Verification OK: Column '" + r.getText() + "' is visible on the page");
                i++;
                if(i == 3){
                    break;
                }
            }
        }
    }

    /**
     * <b>[Method]</b> - Verify Typ Field Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the value of the Typ field is as the same with the expected one<br>
     * @param expectedValue String
     */
    public void verifyTypFieldValue(String expectedValue) {
        Log.info("verifyTypFieldValue method executing");

        waitForPageToLoad();
        Assert.assertEquals(getText(typFieldValue), expectedValue, "Typ field value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The value of the field 'Typ' is: " + getText(typFieldValue));
    }

    /**
     * <b>[Method]</b> - Verify If Master Data Categories Are Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if all the specified Master Data categories are shown on the page<br>
     */
    public void verifyIfMasterDataCategorisAreShown() {
        Log.info("verifyIfMasterDataCategorisAreShown method executing");

        String[] categories = new String[]{"Namensänderung Anschlussinhaber (inkl. Firma)", "Namensänderung weitere Kontakte",
                "Kontaktdatenänderung", "Anschlussinhaberadresse", "Rechnungsadresse", "Lieferadresse", "Zahlungsdaten",
                "Einzelverbindungsnachweis", "Werbeeinstellungen", "Telefonbucheinträge", "Bevollmächtigter oder Ansprechpartner Installation"};
        waitForPageToLoad();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(masterDataCategories));
        List<WebElement> actualVisibleCategories = driver.findElements(masterDataCategories);
        int i = 0;
        for (WebElement r : actualVisibleCategories) {
            if(r.getText().equals("Namensänderung Anschlussinhaber (inkl. Firma)") || r.getText().equals("Namensänderung weitere Kontakte")
                    || r.getText().equals("Kontaktdatenänderung") || r.getText().equals("Anschlussinhaberadresse")
                    || r.getText().equals("Rechnungsadresse") || r.getText().equals("Lieferadresse") || r.getText().equals("Zahlungsdaten")
                    || r.getText().equals("Einzelverbindungsnachweis") || r.getText().equals("Werbeeinstellungen")
                    || r.getText().equals("Telefonbucheinträge") || r.getText().equals("Bevollmächtigter oder Ansprechpartner Installation"))   {

                Assert.assertEquals(r.getText(), categories[i], "Columns visibility verification");
                ExtentListeners.test.log(Status.PASS,"Verification OK: Master Data category '" + r.getText() + "' is visible on the page");
                i++;
                if(i == 15){
                    break;
                }
            }

        }
    }

    /**
     * <b>[Method]</b> - Verify If Buttons Are Shown Next To Master Data Categories<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if all the buttons are shown next to the master data categories<br>
     */
    public void verifyIfButtonsAreShownNextToMasterDataCategories() {
        Log.info("verifyIfButtonsAreShownNextToMasterDataCategories method executing");

        if(isElementPresent(abbrechenButton)) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Button Abbrechen is visible on the page");
        } else {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Button Abbrechen is NOT visible on the page");
            Assert.assertTrue(true, "Button Abbrechen is not visible on the page");
        }

        if(isElementPresent(anderungenButton)) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Button Änderungen vollständig erfasst is visible on the page");
        } else {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Button Änderungen vollständig erfasst is NOT visible on the page");
            Assert.assertTrue(true, "Button Änderungen vollständig erfasst is not visible on the page");
        }
    }

    /**
     * <b>[Method]</b> - Verify New Contact Option Available<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a new contact option is available on the page<br>
     */
    public void verifyNewContactOptionAvailable() {
        Log.info("verifyNewContactOptionAvailable method executing");

        boolean isPresent = driver.findElements(neuenKontaktErfassenRadio).size() > 0;

        if(isPresent){
            ExtentListeners.test.log(Status.PASS,"Verification OK: Neuen Kontakt Erfassen Option is visible on the page");
        } else {
            Assert.fail("Neuen Kontakt Erfassen Option not visible");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Neuen Kontakt Erfassen Option is NOT visible on the page");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Role Column Is Empty<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if role column of a contact is empty<br>
     */
    public void verifyIfRoleColumnIsEmpty(String contactName, Boolean shouldBeEmpty) {
        Log.info("verifyIfRoleColumnIsEmpty method executing");

        By roleColumn = By.xpath("//td[text()='" + contactName + "']//following-sibling::td[5]");
        WebElement element = driver.findElement(roleColumn);

        if(shouldBeEmpty){
            Assert.assertEquals(getText(roleColumn), "");
            ExtentListeners.test.log(Status.PASS,"Verification OK: Column 'Rollen' of contact '" + contactName + "' has empty value");
        } else if(!shouldBeEmpty) {
            Assert.assertNotEquals(getText(roleColumn), "");
            ExtentListeners.test.log(Status.PASS,"Verification OK: Column 'Rollen' of contact '" + contactName + "' has value");
        } else {
            Assert.fail("Column 'Rollen' does not have the expected value state");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Column 'Rollen' of contact '" + contactName + "' does not have the expected value state");
        }
    }

    /**
     * <b>[Method]</b> - Verify Role Column Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if role column value is the same as expected <br>
     */
    public void verifyRoleColumnValue(String contactName, String expectedValue) {
        Log.info("verifyRoleColumnValue method executing");

        By roleColumn = By.xpath("//td[text()='" + contactName + "']//following-sibling::td[5]");
        WebElement element = driver.findElement(roleColumn);

        Assert.assertEquals(getText(roleColumn), expectedValue);
        ExtentListeners.test.log(Status.PASS,"Verification OK: Column 'Rollen' of contact '" + contactName + "' has the expected value: " + expectedValue);

    }

    /**
     * <b>[Method]</b> - Verify Fields Visible<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the specified fields are visible on the page<br>
     * @param fields String[]
     */
    public void verifyFieldsVisible(String[] fields) {
        Log.info("verifyFieldsVisible method executing");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(fieldElement));
        List<WebElement> actualVisibleCategories = driver.findElements(fieldElement);
        WebElement element = driver.findElement(fieldElement);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        int i = 0;
        for (WebElement r : actualVisibleCategories) {

            Assert.assertEquals(r.getText().replaceFirst("^\\*", ""), fields[i], "Columns visibility verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + r.getText() + "' is visible on the page");
            i++;
        }
    }

    /**
     * <b>[Method]</b> - Verify New Address Option Available<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the new address option is visible on the page<br>
     */
    public void verifyNewAddressOptionAvailable() {
        Log.info("verifyNewAddressOptionAvailable method executing");

        waitForPageToLoad();
        boolean isPresent = driver.findElements(neueAdresseRadio).size() > 0;
        if(isPresent){
            ExtentListeners.test.log(Status.PASS,"Verification OK: Neue Adresse suchen und verwenden Option is visible on the page");
        } else {
            Assert.fail("Neue Adresse suchen und verwenden Option not visible");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Neue Adresse suchen und verwenden Option is NOT visible on the page");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Addresses Are Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the addresses (Billing, Connection Owner, etc...) are shown on the page<br>
     */
    public void verifyIfAddressesAreShown() {
        Log.info("verifyIfAddressesAreShown method executing");

        waitForPageToLoad();
        Assert.assertTrue(actualRechnungsadresse.toString().contains("Rechnungsadresse"));
        ExtentListeners.test.log(Status.PASS,"Verification OK: Address '" + getText(actualRechnungsadresse) + "' is visible on the page");
        Assert.assertTrue(actualAnschlussinhaberadresse.toString().contains("Anschlussinhaberadresse"));
        ExtentListeners.test.log(Status.PASS,"Verification OK: Address '" + getText(actualAnschlussinhaberadresse) + "' is visible on the page");
        Assert.assertTrue(actualLieferadresse.toString().contains("Lieferadresse"));
        ExtentListeners.test.log(Status.PASS,"Verification OK: Address '" + getText(actualLieferadresse) + "' is visible on the page");

    }

    /**
     * <b>[Method]</b> - Verify If Old New Table Summary Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the old and new values table summary in the master data omniscript is shown after changing field values<br>
     */
    public void verifyIfOldNewTableSummaryShown(){
        Log.info("verifyIfOldNewTableSummaryShown method executing");

        boolean isPresentBisher = driver.findElements(bisher).size() > 0;
        boolean isPresentNeu = driver.findElements(neu).size() > 0;
        if(isPresentBisher && isPresentNeu){
            ExtentListeners.test.log(Status.PASS,"Verification OK: 'Bisher' and 'Neu' table is visible on the page");
        } else {
            Assert.fail("Bisher and Neu not visible");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: 'Bisher' and 'Neu' table is NOT visible on the page");
        }
    }

    /**
     * <b>[Method]</b> - Verify Old New Table Checkboxes Checked State<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies the state of the checkboxes in the old new table summary<br>
     * @param checkboxName String
     * @param expectedCheckedState boolean
     */
    public void verifyOldNewTableCheckboxesCheckedState(String checkboxName, boolean expectedCheckedState) {
        Log.info("verifyOldNewTableCheckboxesCheckedState method executing");

        waitForPageToLoad();

        Boolean actualCheckedStatePhoneNeu = isElementClickable(phoneCheckedNeu);
        Boolean actualCheckedStateEmailNeu = isElementClickable(emailUnCheckedNeu);
        Boolean actualCheckedStateVerkehrsdatenNeu = isElementClickable(verkehrsdatenUncheckedNeu);
        Boolean actualCheckedStatePhoneBisher = isElementClickable(phoneUncheckedBisher);
        Boolean actualCheckedStateEmailBisher = isElementClickable(emailUnCheckedBisher);
        Boolean actualCheckedStateVerkehrsdatenBisher = isElementClickable(verkehrsdatenCheckedBisher);

        if(checkboxName.equals("Telefon Neu")){
            if(expectedCheckedState && actualCheckedStatePhoneNeu) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Telefon Opt-In Neu is checked");
            } else if(!expectedCheckedState && !actualCheckedStatePhoneNeu) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Telefon Opt-In Neu is un-checked");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Telefon Opt-In Neu is not with the expected state. Actual checked state is: " + actualCheckedStatePhoneNeu);
                Assert.fail("Telefon Opt-In Neu checked state is not as expected");
            }
        }

        if(checkboxName.equals("Email Neu")){
            if(expectedCheckedState && !actualCheckedStateEmailNeu) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Email Opt-In Neu is checked");
            } else if(!expectedCheckedState && actualCheckedStateEmailNeu) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Email Opt-In Neu is un-checked");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Email Opt-In Neu is not with the expected state. Actual checked state is: " + actualCheckedStateEmailNeu);
                Assert.fail("Email Opt-In Neu checked state is not as expected");
            }
        }

        if(checkboxName.equals("Verkehrsdaten Neu")){
            if(expectedCheckedState && !actualCheckedStateVerkehrsdatenNeu) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Verkehrsdaten Opt-In Neu is checked");
            } else if(!expectedCheckedState && actualCheckedStateVerkehrsdatenNeu) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Verkehrsdaten Opt-In Neu is un-checked");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Verkehrsdaten Opt-In Neu is not with the expected state. Actual checked state is: " + actualCheckedStateVerkehrsdatenNeu);
                Assert.fail("Verkehrsdaten Opt-In Neu checked state is not as expected");
            }
        }

        if(checkboxName.equals("Telefon Bisher")){
            if(expectedCheckedState && !actualCheckedStatePhoneBisher) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Telefon Opt-In Bisher is checked");
            } else if(!expectedCheckedState && actualCheckedStatePhoneBisher) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Telefon Opt-In Bisher is un-checked");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Telefon Opt-In Bisher is not with the expected state. Actual checked state is: " + actualCheckedStatePhoneBisher);
                Assert.fail("Telefon Opt-In Bisher checked state is not as expected");
            }
        }

        if(checkboxName.equals("Email Bisher")){
            if(expectedCheckedState && !actualCheckedStateEmailBisher) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Email Opt-In Bisher is checked");
            } else if(!expectedCheckedState && actualCheckedStateEmailBisher) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Email Opt-In Bisher is un-checked");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Email Opt-In Bisher is not with the expected state. Actual checked state is: " + actualCheckedStateEmailBisher);
                Assert.fail("Email Opt-In Bisher checked state is not as expected");
            }
        }

        if(checkboxName.equals("Verkehrsdaten Bisher")){
            if(expectedCheckedState && actualCheckedStateVerkehrsdatenBisher) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Verkehrsdaten Opt-In Bisher is checked");
            } else if(!expectedCheckedState && !actualCheckedStateVerkehrsdatenBisher) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Verkehrsdaten Opt-In Bisher is un-checked");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Verkehrsdaten Opt-In Bisher is not with the expected state. Actual checked state is: " + actualCheckedStateVerkehrsdatenBisher);
                Assert.fail("Verkehrsdaten Opt-In Bisher checked state is not as expected");
            }
        }
    }

    /**
     * <b>[Method]</b> - Verify Contact is Pre-Selected<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a contact is already pre-selected<br>
     */
    public void verifyContactIsPreSelected() {
        Log.info("verifyContactIsPreSelected method executing");

        waitForPageToLoad();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(contactRadioButtons));
        List<WebElement> radioButtons = driver.findElements(contactRadioButtons);
        boolean isAnyRadioButtonSelected = false;

        for (WebElement radioButton : radioButtons) {
            String afterContent = (String) ((JavascriptExecutor) driver)
                    .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", radioButton);
            if (afterContent != null && !afterContent.isEmpty()) {
                isAnyRadioButtonSelected = true;
                ExtentListeners.test.log(Status.PASS,"Verification OK: Contact radio button is pre selected");
                break;
            }
        }
        if (!isAnyRadioButtonSelected) {
            Assert.fail("No contact radio button is selected.");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: No Contact radio button is pre selected.");
        }
    }

    /**
     * <b>[Method]</b> - Verify Address is Pre-Selected<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a address is already pre-selected<br>
     */
    public void verifyAddressIsPreSelected() {
        Log.info("verifyAddressIsPreSelected method executing");

        waitForPageToLoad();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addressRadioButtons));
        List<WebElement> radioButtons = driver.findElements(addressRadioButtons);
        boolean isAnyRadioButtonSelected = false;

        for (WebElement radioButton : radioButtons) {
            String afterContent = (String) ((JavascriptExecutor) driver)
                    .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", radioButton);
            if (afterContent != null && !afterContent.isEmpty()) {
                isAnyRadioButtonSelected = true;
                ExtentListeners.test.log(Status.PASS,"Verification OK: Address radio button is pre selected");
                break;
            }
        }
        if (!isAnyRadioButtonSelected) {
            Assert.fail("No address radio button is selected.");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: No Address radio button is pre selected.");
        }
    }

    /**
     * <b>[Method]</b> - Verify Master Data Opened<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if Master Data omniscript is opened<br>
     */
    public void verifyMasterDataOpened() {
        Log.info("verifyMasterDataOpened method executing");

        Boolean appeared = isElementVisible(auswahlLabel);
        if(appeared) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: 'Auswahl der zu ändernden Stammdaten' is visible");
        } else {
            Assert.fail("'Auswahl der zu ändernden Stammdaten' is not visible");
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: 'Auswahl der zu ändernden Stammdaten' is not visible");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Einzelverbindungsnachwe is Displayed Correctly<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if Einzelverbindungsnachwe and dropdown Evn-Typ are visible and displayed correctly on the page<br>
     */
    public void verifyIfEinzelverbindungsnachweisDisplayedCorrectly() {
        Log.info("chooseEvnTypValue method executing");

        Assert.assertEquals(isElementVisible(einzelverbindungsnachweisLabel), true);
        ExtentListeners.test.log(Status.PASS,"Verification OK: Label '" + getText(einzelverbindungsnachweisLabel) + "' is visible on the page");

        Assert.assertEquals(isElementVisible(evnTypDropdown), true);
        ExtentListeners.test.log(Status.PASS,"Verification OK: Dropdown 'EVN-Typ' is visible on the page");
    }

    /**
     * <b>[Method]</b> - Verify If Evn Type New Value Is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if evn typ new value is visible and displayed correctly on the page<br>
     * @param newValue String
     */
    public void verifyIfEvnTypeNewValueIsShown(String newValue) {
        Log.info("verifyIfEvnTypeNewValueIsShown method executing");

        By evnTypNewValue = By.xpath("//div[text()='" + newValue + "']");
        if(isElementPresent(evnTypNewValue)){
            ExtentListeners.test.log(Status.PASS,"Verification OK: EVN Typ new value '" + newValue + "' is visible");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: EVN Typ new value '" + newValue + "' is NOT visible");
            Assert.fail("EVN Typ new value is not visible");
        }
    }

    /**
     * <b>[Method]</b> - Verify Changes are Made Successfully<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if changes are done succssfully before closing the master data omniscript<br>
     */
    public void verifyChangesAreMadeSuccessfully() {
        Log.info("verifyChangesAreMadeSuccessfully method executing");

        if(isElementVisible(successfulyMessageChanges)){
            ExtentListeners.test.log(Status.PASS,"Verification OK: Changes are made successfully");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Changes are NOT done successfully");
            Assert.fail("Changes are not done successfully");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Werbeeinstellungen Opt-Ins are Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if Werbeeinstellungen opt-ins (Telefon, eMail, Verkehrsdaten) are properly shown and visible on the page<br>
     */
    public void verifyIfWerbeeinstellungenOptInsAreShown() {
        Log.info("verifyIfWerbeeinstellungenOptInsAreShown method executing");

        if(isElementPresent(telefonOptInLabel)){
            Assert.assertTrue(isElementPresent(telefonOptInLabel));
            ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(telefonOptInLabel) + "' is visible on the page");
        } else {
            Assert.assertTrue(isElementPresent(telefonOptInLabelAlternative));
            ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(telefonOptInLabelAlternative) + "' is visible on the page");
        }

        if(isElementPresent(eMailOptInLabel)){
            Assert.assertTrue(isElementPresent(eMailOptInLabel));
            ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(eMailOptInLabel) + "' is visible on the page");
        } else {
            Assert.assertTrue(isElementPresent(eMailOptInLabelAlternative));
            ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(eMailOptInLabelAlternative) + "' is visible on the page");
        }

        if(isElementClickable(verkehrsdatenOptInLabel)){
            Assert.assertTrue(isElementPresent(verkehrsdatenOptInLabel));
            ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(verkehrsdatenOptInLabel) + "' is visible on the page");
        } else {
            Assert.assertTrue(isElementPresent(verkehrsdatenOptInLabelAlternative));
            ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(verkehrsdatenOptInLabelAlternative) + "' is visible on the page");
        }

    }

    /**
     * <b>[Method]</b> - Verify If Voice Recording Option is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a voice recording option is properly shown and visible on the page<br>
     */
    public void verifyIfVoiceRecordingOptionIsShown() {
        Log.info("verifyIfVoiceRecordingOptionIsShown method executing");

        Assert.assertTrue(isElementPresent(sprachaufzeichnung));
        ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(sprachaufzeichnung) + "' is visible on the page");

        Assert.assertTrue(isElementPresent(sprachaufzeichnungNicht));
        ExtentListeners.test.log(Status.PASS, "Verification OK: Field: '" + getText(sprachaufzeichnungNicht) + "' is visible on the page");
    }

    /**
     * <b>[Method]</b> - Verify If Voice Recording Error is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a voice recording error is properly shown and visible on the page<br>
     */
    public void verifyIfVoiceRecordingErrorIsShown() {
        Log.info("verifyIfVoiceRecordingErrorIsShown method executing");

        Assert.assertTrue(isElementPresent(errorMessageVoiceRecording));
        ExtentListeners.test.log(Status.PASS, "Verification OK: Error Message: '" + getText(errorMessageVoiceRecording) + "' is visible on the page");
    }

    /**
     * <b>[Method]</b> - Verify If Bestatigung Automatisch Is Selected<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if Bestatigung Automatisch checkbox is selected on the page<br>
     */
    public void verifyIfBestatigungAutomatischIsSelected() {
        Log.info("verifyIfBestatigungAutomatischIsSelected method executing");

        if(isElementVisible(bestatigungAutomatischCheckbox)){
            WebElement checkboxElement = driver.findElement(bestatigungAutomatischCheckbox);
            jse.executeScript("arguments[0].scrollIntoView()", checkboxElement);

            String afterContent = (String) ((JavascriptExecutor) driver)
                    .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", checkboxElement);

            // Check if the checkbox is selected
            if (afterContent != null && !afterContent.isEmpty()) {
                ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Bestätigung automatisch versenden' is checked");
            } else {
                ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Bestätigung automatisch versenden' is NOT checked");
                Assert.fail("Checkbox state is not as expected");
            }
        } else {
            WebElement checkboxElementTwo = driver.findElement(bestatigungAutomatischCheckboxPhoneBook);
            jse.executeScript("arguments[0].scrollIntoView()", checkboxElementTwo);

            String afterContent = (String) ((JavascriptExecutor) driver)
                    .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", checkboxElementTwo);

            // Check if the checkbox is selected
            if (afterContent != null && !afterContent.isEmpty()) {
                ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Bestätigung automatisch versenden' is checked");
            } else {
                ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Bestätigung automatisch versenden' is NOT checked");
                Assert.fail("Checkbox state is not as expected");
            }
        }
    }

    /**
     * <b>[Method]</b> - Verify If Contact Opt-Ins Fields Updated<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if contact opt-ins fields are correctly updated<br>
     * @param optIn String
     */
    public void verifyIfContactOptInsFieldsUpdated(String optIn) {
        Log.info("verifyIfContactOptInsFieldsUpdated method executing");

        By date = switch (optIn) {
            case "Telefon Opt-In" ->
                    By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Contact.PhoneOptInChangeDate__c']//lightning-formatted-text");
            case "E-Mail Opt-In" ->
                    By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Contact.EmailOptInChangeDate__c']//lightning-formatted-text");
            case "Verkehrsdaten Opt-In" ->
                    By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Contact.TrafficDataUsageChangeDate__c']//lightning-formatted-text");
            default -> null;
        };

        WebElement element = driver.findElement(date);

        waitForPageToLoad();
        actions.moveToElement(element).build().perform();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);

        Assert.assertEquals(formattedDate, getText(date));
        ExtentListeners.test.log(Status.PASS, "Verification OK: '" + optIn + " geändert am' date value is the todays date: " + formattedDate);
    }

    /**
     * <b>[Method]</b> - Verify Kontakt New Last Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if contact last name is as specified<br>
     * @param newLastName String
     */
    public void verifyKontaktNewLastName(String newLastName) {
        Log.info("verifyKontaktNewLastName method executing");

        driver.navigate().refresh();
        waitForPageToLoad();
        search.closePossibleContactsAccountPopUp();
        By contactElement = By.xpath("//a[@class=\"flex-wrap-ie11 slds-truncate\"]//span[@lwc-47ngqe6rvah and contains(text(), '" + newLastName + "')]");

        if(isElementVisible(contactElement)) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: There is a contact with last name as specified, that means contact last name is as changed to the new company name");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: There is no contact with last name as specified");
            Assert.fail("There is no kontakt with the last name as specified");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Email Is Shown In Aktion<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if sent email is shown in aktion tab<br>
     * @param emailIsShown boolean
     */
    public void verifyIfEmailIsShownInAktion(Boolean emailIsShown) {
        Log.info("verifyIfEmailIsShownInAktion method executing");

        String expectedTimeAgoSentEmail = "Gerade eben";

        waitForPageToLoad();

        if(emailIsShown){
            if(isElementVisible(emailIconShownBefore1Minute)){
                ExtentListeners.test.log(Status.PASS, "Verification OK: The email sent is shown in Aktion Tab");
            } else {
                ExtentListeners.test.log(Status.FAIL, "Verification FAIL: The email sent is NOT shown in Aktion Tab");
                Assert.fail("Email not visible");
            }
        } else {
            if(isElementVisible(emailIconShownBefore1Minute)){
                ExtentListeners.test.log(Status.PASS, "Verification FAIL: The email sent is shown in Aktion Tab");
                Assert.fail("Email is sent");
            } else {
                ExtentListeners.test.log(Status.PASS, "Verification OK: There is NO email sent shown in Aktion Tab");
            }
        }
    }

    /**
     * <b>[Method]</b> - Verify If Sprachaufzeichnun is Checked<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if Sprachaufzeichnun checkbox is checked<br>
     */
    public void verifyIfSprachaufzeichnungIsChecked() {
        Log.info("verifyIfSprachaufzeichnungIsChecked method executing");

        waitForPageToLoad();
        WebElement checkboxElement = driver.findElement(sprachaufzeichnungCheckbox);

        String afterContent = (String) ((JavascriptExecutor) driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", checkboxElement);
        if (afterContent != null && !afterContent.isEmpty()) {
            ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Sprachaufzeichnung' is checked");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Sprachaufzeichnung' is NOT checked");
            Assert.fail("Checkbox state is not as expected");
        }
    }

    /**
     * <b>[Method]</b> - Verify If BenachrichtigungSchicken is Checked<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if BenachrichtigungSchicken checkbox is checked<br>
     * @param isChecked boolean
     */
    public void verifyIfBenachrichtigungSchickenIsChecked(Boolean isChecked) {
        Log.info("verifyIfBenachrichtigungSchickenIsChecked method executing");

        waitForPageToLoad();
        WebElement checkboxElement = driver.findElement(benachrichtigungSchickenCheckbox);
        boolean isCheckboxChecked = checkboxElement.getAttribute("checked") != null;

        if(isChecked) {
            if (isCheckboxChecked) {
                ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Benachrichtigung schicken' is checked");
            } else {
                ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Benachrichtigung schicken' is NOT checked");
                Assert.fail("Checkbox state is not as expected");
            }
        } else {
            if (isCheckboxChecked) {
                ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Checkbox 'Benachrichtigung schicken' is checked");
                Assert.fail("Checkbox state is not as expected");
            } else {
                ExtentListeners.test.log(Status.PASS, "Verification OK: Checkbox 'Benachrichtigung schicken' is NOT checked");
            }

        }
    }

    /**
     * <b>[Method]</b> - Verify Who did Master Data Changes is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the correct user is shown that did the master data changes<br>
     * @param expectedUser String
     */
    public void verifyWhoDidMasterDataChangesIsShown(String expectedUser) {
        Log.info("verifyWhoDidMasterDataChangesIsShown method executing");

        By whoDidChangesElement = By.xpath("(//span[text()='Erstellt von'])[2]//ancestor::div[@class=\"slds-form-element slds-hint-parent test-id__output-root slds-form-element_readonly slds-form-element_horizontal\"]//span[text()='" + expectedUser + "']");

        if(isElementVisible(whoDidChangesElement)){
            ExtentListeners.test.log(Status.PASS, "Verification OK: User '" + expectedUser + "' did the Master Data changes.");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: User '" + expectedUser + "' didn't do the Master Data changes.");
            Assert.fail("User '" + expectedUser + "' didn't do the Master Data changes.");
        }
    }

    /**
     * <b>[Method]</b> - Verify Master Data Changes Date is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if Master Data changes date is shown<br>
     */
    public void verifyMasterDataChangesDateIsShown() {
        Log.info("verifyMasterDataChangesDateIsShown method executing");

        if(isElementVisible(dateElement)){
            ExtentListeners.test.log(Status.PASS, "Verification OK: Date is shown next to the user who did Master Data changes");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: Date is NOT shown next to the user who did Master Data changes");
            Assert.fail(" Date is NOT shown next to the user who did Master Data changes");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Person Is Shown In History Changes<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the user who did the changes is shown in the history changes<br>
     * @param expectedPerson String
     */
    public void verifyIfPersonIsShownInHistoryChanges(String expectedPerson) {
        Log.info("verifyIfPersonIsShownInHistoryChanges method executing");

        By personNameElement = By.xpath("(//span[text() = 'Gerade eben'])[1]//ancestor::div[@class=\"slds-grid slds-wrap\"]//span[text()='" + expectedPerson + "']");

        if(isElementVisible(personNameElement)){
            ExtentListeners.test.log(Status.PASS, "Verification OK: User '" + expectedPerson + "' is shown in the history changes");
        } else {
            ExtentListeners.test.log(Status.FAIL, "Verification FAIL: User '" + expectedPerson + "' is NOT shown in the history changes");
            Assert.fail("User '" + expectedPerson + "' is NOT shown in the history changes");
        }
    }

    /**
     * <b>[Method]</b> - Verify If PhoneBook Entries Input Fields Are Empty<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if phonebook entries input fields are empty<br>
     * @param empty boolean
     */
    public void verifyIfPhoneBookEntriesInputFieldsAreEmpty(Boolean empty) {
        Log.info("verifyIfPhoneBookEntriesInputFieldsAreEmpty method executing");

        List<WebElement> allInputFields = driver.findElements(phonebookEntriesInputFields);

        String[] elementsArray = {
                "Vorname",
                "Nachname",
                "Firma",
                "Strasse",
                "Hausnummer",
                "Zusatz",
                "PLZ",
                "ORT",
                "Land"
        };

        if(empty){
            int i = 0;
            for (WebElement r : allInputFields) {
                jse.executeScript("arguments[0].scrollIntoView()", r);
                String fieldValue = r.getAttribute("value");
                Assert.assertTrue(fieldValue.isEmpty());
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + elementsArray[i] + "' is empty");
                i++;
            }
        } else {
            int i = 0;
            for (WebElement r : allInputFields) {
                jse.executeScript("arguments[0].scrollIntoView()", r);
                String fieldValue = r.getAttribute("value");
                Assert.assertFalse(fieldValue.isEmpty());
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + elementsArray[i] + "' is NOT empty");
                i++;
            }
        }

    }

    /**
     * <b>[Method]</b> - Verify If PhoneBook Entries Dropdown Fields Are Empty<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if phonebook entries dropdown fields are empty<br>
     * @param empty boolean
     */
    public void verifyIfPhoneBookEntriesDropdownFieldsAreEmpty(Boolean empty) {
        Log.info("verifyIfPhoneBookEntriesDropdownFieldsAreEmpty method executing");

        List<WebElement> allInputFields = driver.findElements(phonebookEntriesDropdownFields);

        String[] elementsArray = {"Verwendung", "Anrede"};

        if(empty) {
            int i = 0;
            for (WebElement r : allInputFields) {
                jse.executeScript("arguments[0].scrollIntoView()", r);
                String fieldValue = r.getAttribute("value");
                Assert.assertTrue(fieldValue.isEmpty());
                ExtentListeners.test.log(Status.PASS,"Verification OK: Dropdown '" + elementsArray[i] + "' is empty");
                i++;
            }
        } else {
            int i = 0;
            for (WebElement r : allInputFields) {
                jse.executeScript("arguments[0].scrollIntoView()", r);
                String fieldValue = r.getAttribute("value");
                Assert.assertFalse(fieldValue.isEmpty());
                ExtentListeners.test.log(Status.PASS,"Verification OK: Dropdown '" + elementsArray[i] + "' is NOT empty");
                i++;
            }
        }
    }

    /**
     * <b>[Method]</b> - Verify If Last Name Error When Creating New Contact is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if an error message is shown when leaving the nachname field empty when creating contact<br>
     */
    public void verifyIfLastNameErrorWhenCreatingNewContactIsShown() {
        Log.info("verifyIfLastNameErrorWhenCreatingNewContactIsShown method executing");

        waitForPageToLoad();
        if(isElementVisible(lastNameError)){
            ExtentListeners.test.log(Status.PASS,"Verification OK: Error '!Nachname is required.' is visible on the page");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Error '!Nachname is required.' is NOT visible on the page");
            Assert.fail();
        }
    }

    /**
     * <b>[Method]</b> - Verify If Rolle Auswahlen Screen is Displayed<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if screen Rolle Auswahlen is Displayed on the page<br>
     * @param titleOfScreen String
     */
    public void verifyIfScreenIsDisplayed(String titleOfScreen) {
        Log.info("verifyIfScreenIsDisplayed method executing");

        By screenTitle = By.xpath("//h1[text()='" + titleOfScreen + "']");

        if(isElementVisible(screenTitle)) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Screen '" + titleOfScreen + "' is displayed");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Screen '" + titleOfScreen + "' is NOT displayed");
            Assert.fail();
        }
    }

    /**
     * <b>[Method]</b> - Verify If Role is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a specific role is shown on the page when working with Master Data category: Bevollmächtigter oder Ansprechpartner Installation<br>
     * @param roleName String
     */
    public void verifyIfRoleIsShown(String roleName) {
        Log.info("verifyIfRoleIsShown method executing");

        By roleText = By.xpath("(//span[contains(@class, 'element__label') and text()='" + roleName + "'])[2]");

        if(isElementVisible(roleText)) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Role '" + roleName + "' is displayed");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Role '" + roleName + "' is NOT displayed");
            Assert.fail();
        }
    }

    /**
     * <b>[Method]</b> - Verify If Select at Least One Role Message is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a select at least one role error message is shown when the screen to choose a role is opened<br>
     */
    public void verifyIfSelectAtLeastOneRoleMessageIsShown() {
        Log.info("verifyIfSelectAtLeastOneRoleMessageIsShown method executing");

        if(isElementVisible(selectAtLeastOneRoleMessage)) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Error Message 'Bitte wählen Sie mindestens eine Rolle aus' is displayed");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Error Message 'Bitte wählen Sie mindestens eine Rolle aus' is NOT displayed");
            Assert.fail();
        }
    }

    /**
     * <b>[Method]</b> - Verify If Installation Contact Role Cannot be Edited Message is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if an error message is shown stating that the installation contact cannot be edited <br>
     * @param shouldBeShown boolean
     */
    public void verifyIfInstallationContactRoleCannotBeEditedMessageIsShown(boolean shouldBeShown) {
        Log.info("verifyIfInstallationContactRoleCannotBeEditedMessageIsShown method executing");

        waitForPageToLoad();
        if(shouldBeShown) {
            if(isElementVisible(installationContactCannotBeEditedMessage)) {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Error Message 'Die Rolle Ansprechpartner Installation kann nicht bearbeitet werden.' is displayed");
            } else {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Error Message 'Die Rolle Ansprechpartner Installation kann nicht bearbeitet werden.' is NOT displayed");
                Assert.fail();
            }
        } else {
            if(isElementVisible(installationContactCannotBeEditedMessage)) {
                ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Error Message 'Die Rolle Ansprechpartner Installation kann nicht bearbeitet werden.' is displayed");
                Assert.fail();
            } else {
                ExtentListeners.test.log(Status.PASS,"Verification OK: Error Message 'Die Rolle Ansprechpartner Installation kann nicht bearbeitet werden.' is NOT displayed");
            }
        }
    }

    /**
     * <b>[Method]</b> - Verify If New Created Contact is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if contact is created and displayed successfully<br>
     * @param contactFirstName String
     * @param contactLastName String
     */
    public void verifyIfNewCreatedContactIsShown(String contactFirstName, String contactLastName) {
        Log.info("verifyIfNewCreatedContactIsShown method executing");

        By contactNameText = By.xpath("//td[text()='" + contactFirstName + " " + contactLastName + "']");

        if(isElementVisible(contactNameText)) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Newly created contact '" + contactFirstName + " " + contactLastName + "' is displayed");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Newly created contact '" + contactFirstName + " " + contactLastName + "' is NOT displayed");
            Assert.fail();
        }
    }

    /**
     * <b>[Method]</b> - Verify Contact Role<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the role of a specific contact is correct<br>
     * @param contactFirstName String
     * @param contactLastName String
     * @param expectedRole String
     */
    public void verifyContactRole(String contactFirstName, String contactLastName, String expectedRole) {
        Log.info("verifyContactRole method executing");

        By roleName = By.xpath("//td[text()='" + contactFirstName + " " + contactLastName + "']//following-sibling::td");

        Assert.assertEquals(getText(roleName), expectedRole);
        ExtentListeners.test.log(Status.PASS,"Verification OK: The role of the contact '" + contactFirstName + " " + contactLastName + "' is '" + expectedRole + "'");
    }

    /**
     * <b>[Method]</b> - Verify Master Data Report Field Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if a field value in a master data report is as expected<br>
     * @param fieldName String
     * @param expectedValue String
     */
    public void verifyMasterDataReportFieldValue(String fieldName, String expectedValue) {
        Log.info("verifyMasterDataReportFieldValue method executing");

        By textFields = By.xpath("//span[text()='" + fieldName + "']//ancestor::div[contains(@class, 'slds-hint-parent')]//lightning-formatted-text");

        if(fieldName.equals("Kontakt")) {
            waitForPageToLoad();
            Assert.assertEquals(getText(kontaktField), expectedValue, "Kontakt field value verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: The field '" + fieldName + " has the correct value '" + expectedValue + "'");
        } else if(fieldName.equals("Änderungstyp") || fieldName.equals("Dokumente erforderlich?")){
            waitForPageToLoad();
            Assert.assertEquals(getText(textFields), expectedValue, "Field '" + fieldName + "' value verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: The field '" + fieldName + " has the correct value '" + expectedValue + "'");
        }
    }

    /**
     * <b>[Method]</b> - Verify Master Data Report Related Field Change<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the value of the specific field in master data report -> related tab is changed<br>
     * @param fieldName String
     * @param expectedValue String
     */
    public void verifyMasterDataReportRelatedFieldChange(String fieldName, String expectedValue) {
        Log.info("verifyMasterDataReportRelatedFieldChange method executing");

        By fieldNewValueElement = By.xpath("(//span[@title='" + fieldName + "']//ancestor::tr//lightning-primitive-cell-factory[@data-label=\"Neuer Wert\"]//span)[2]");

        waitForPageToLoad();
        Assert.assertEquals(getText(fieldNewValueElement), expectedValue, "Field '" + fieldName + "' new value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The field '" + fieldName + "' has the new correct value '" + expectedValue + "'");
    }

    /**
     * <b>[Method]</b> - Verify Evn Typ New Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the new value of the evn typ is as expected<br>
     * @param expectedValue String
     */
    public void verifyEvnTypNewValue(String expectedValue) {
        Log.info("verifyEvnTypNewValue method executing");

        waitForPageToLoad();
        Assert.assertEquals(getText(evnTypNewValue), expectedValue, "Field EVN Typ new value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The field 'EVN Typ'  new value has the new correct value '" + expectedValue + "'");
    }

    /**
     * <b>[Method]</b> - Verify If Contact Is Shown<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the contact is shown<br>
     * @param contactName String
     * @param shouldExist boolean
     */
    public void verifyIfContactIsShown(String contactName, boolean shouldExist) {
        Log.info("verifyIfContactIsShown method executing");

        int g = 0;
        for (int i = 1; i < 5; i++) {
            By contactNachname = By.xpath("//table[@class=\"table slds-table slds-table_bordered slds-table_cell-buffer\"]//tbody//tr[" + i + "]//td[contains(text(), '" + contactName + "')]");

            if(isElementVisible(contactNachname)){
                g = 1;

            }
        }
        if((g==1) && shouldExist) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Contact with Nachname: " + contactName + " exists.");
        } else if((g==0) && !shouldExist) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Contact with Nachname: " + contactName + " does not exists.");
        } else if((g==1) && !shouldExist){
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Contact with Nachname: " + contactName + " exists.");
            Assert.fail("Verification FAIL: Contact with Nachname: " + contactName + " exists.");
        } else {
            ExtentListeners.test.log(Status.FAIL,"Verification FAIL: Contact with Nachname: " + contactName + " does not exists.");
            Assert.fail("Verification FAIL: Contact with Nachname: " + contactName + " does not exists.");
        }
    }

    /**
     * <b>[Method]</b> - Verify If Contact Field Is Mandatory<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the contact field is mandatory<br>
     * @param fieldName String
     * @param shouldBeMandatory boolean
     */
    public void verifyIfContactFieldIsMandatory(String fieldName, boolean shouldBeMandatory) {
        Log.info("verifyIfContactFieldIsMandatory method executing");

        By fieldElement = By.xpath("//span[text()='" + fieldName + "']//preceding::abbr[@title=\"Eine Auswahl ist erforderlich\"]");

        if(isElementPresent(fieldElement) && shouldBeMandatory) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + fieldName + "' is mandatory" );
        } else if(!isElementPresent(fieldElement) && !shouldBeMandatory) {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + fieldName + "' is NOT mandatory" );
        } else {
            ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + fieldName + "' does not have the correct mandatory state");
            Assert.fail("Field does not have the correct mandatory state");
        }
    }

    /**
     * <b>[Method]</b> - Verify Zusammenfassung Nachname Neu Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the Zusammenfassung Last Name New value<br>
     * @param expectedValue String
     */
    public void verifyZusammenfassungNachnameNeuValue(String expectedValue) {
        Log.info("verifyZusammenfassungNachnameNeuValue method executing");

        if(isElementPresent(nachnameNeu)) {
            Assert.assertEquals(getText(nachnameNeu), expectedValue, "Nachname New value verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: The field 'Nachname' new value has the new correct value '" + expectedValue + "'");
        } else {
            Assert.assertEquals(getText(nachnameNeuAlternative), expectedValue, "Nachname New value verification");
            ExtentListeners.test.log(Status.PASS,"Verification OK: The field 'Nachname' new value has the new correct value '" + expectedValue + "'");
        }

    }

    /**
     * <b>[Method]</b> - Verify Namensanderung Fields Visible<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality verifies if the specified fields are visible on the Namensanderung page<br>
     * @param fields String[]
     */
    public void verifyNamensanderungFieldsVisible(String[] fields) {
        Log.info("verifyFieldsVisible method executing");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(fieldElement));
        List<WebElement> actualVisibleCategories = driver.findElements(fieldElement);
        WebElement element = driver.findElement(fieldElement);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        int i = 0;
        for (WebElement r : actualVisibleCategories) {
            if(i!=1){
                Assert.assertEquals(r.getText().replaceFirst("^\\*", ""), fields[i], "Columns visibility verification");
                ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + r.getText() + "' is visible on the page");
                i++;
            }

        }
    }

    public void verifyEvnTypOldValueSummaryTable(String expectedValue) {
        Log.info("verifyEvnTypOldValueSummaryTable method executing");

        waitForPageToLoad();
        By evnOldValueSummary = By.xpath("(//h4[text()='Bisher']//ancestor::span//div[contains(@style, 'background-color')])[1]");

        Assert.assertEquals(getText(evnOldValueSummary), expectedValue, "EVN Typ Old value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: EVN Typ Old value is as expected: " + expectedValue);
    }

    public void verifyEvnTypNewValueSummaryTable(String expectedValue) {
        Log.info("verifyEvnTypNewValueSummaryTable method executing");

        waitForPageToLoad();
        By evnNewValueSummary = By.xpath("(//h4[text()='Neu']//ancestor::span//div[contains(@style, 'background-color')])[2]");

        Assert.assertEquals(getText(evnNewValueSummary), expectedValue, "EVN Typ New value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: EVN Typ New value is as expected: " + expectedValue);
    }

    public void verifyAnschlussinhaberadresseFieldsOldNewTable(String field, String expectedValue) {
        Log.info("verifyAnschlussinhaberadresseFieldsOldNewTable method executing");

        By newAddressValue = By.xpath("(//sub[text()='" + field + "'])[2]//ancestor::div[contains(@style, 'float')]//div[contains(@style, 'background')]");

        Assert.assertEquals(getText(newAddressValue), expectedValue, "Address field verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: Field '" + field + " has the expected value:" + expectedValue + " in the New Summary Table");
    }

    public void verifyInactiveContactNewValue(boolean expectedState) {
        Log.info("verifyInactiveContactState method executing");

        waitForPageToLoad();
        String expectedValue;
        if(expectedState) {
            expectedValue = "true";
        } else {
            expectedValue = "false";
        }

        By newValue = By.xpath("//span[@title=\"Inaktiver Kontakt\"]//ancestor::td//following-sibling::td[@data-label=\"Neuer Wert\"]//span[@title]");

        WebElement element = driver.findElement(newValue);

        Assert.assertEquals(element.getAttribute("title"), expectedValue, "Inactive contact new Value verification");
        ExtentListeners.test.log(Status.PASS,"Verification OK: The contact inactive status new value has the expected state: " + expectedValue);


    }
}
