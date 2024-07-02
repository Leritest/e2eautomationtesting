package de.dg.salesforce.fe.masterdata.phonebookEntries;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.ProfileSettings;
import automation.de.dg.salesforce.frontend.pages.account.AccountViewPage;
import automation.de.dg.salesforce.frontend.pages.cases.NewCasePage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.masterdata.MasterData;
import automation.de.dg.salesforce.utils.CaseData;
import automation.testbase.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <b>TEST CASE</b> [MasterData/OptIns]: Test Edit Phone Book Entry
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseEditPhoneBookEntryDgb extends Page {

    List<CaseData> caseDataList = new ArrayList<>();
    CaseData caseData1 = new CaseData();
    String accountName = "3940647";

    /**
     * <b>[Test Method]</b> - Test Edit PhoneBook Entry<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tests the process of editing phonebook entry in Salesforce<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in to Salesforce<br>
     * 2. Find the specific account<br>
     * 3. Create new case
     * 4. Create Master Data Telefonbucheinträge
     * 5. Verify if email sent
     * 6. Open account in CIM
     * 7. Open phone book entry
     * 8. Verify if changes are reflected
     */
    @Test(description = "OPT-5128")
    public void tcEditPhoneBookEntryDgb() {
        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        Search search = new Search();
        MasterData masterData = new MasterData();
        NewCasePage newCasePage = new NewCasePage();
        AccountViewPage accountViewPage = new AccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        Random rand = new Random();
        ProfileSettings profileSettings = new ProfileSettings();

        String randomNumber = String.valueOf(rand.nextInt(1000));
        String logInAsUser = "Aleksandar Stojanov Test";
        //String accountName = "ASDGB007";

        String salutation = "Divers";
        String firstName = "Aleksandar" + randomNumber;
        String lastName = "Stojanov" + randomNumber;
        String companyName = "Qinshift" + randomNumber;
        String street = "Strasse" + randomNumber;
        String houseNumber = "20" + randomNumber;
        String houserNumberSuffix = "A" + randomNumber;
        String postCode = "2" + randomNumber;
        String city = "Dortmund" + randomNumber;
        String country = "DE" + randomNumber;

        loginPage.login(UserRoles.AUTO_ADMIN);
        search.closePossibleContactsAccountPopUp();
        headerPage.closeSplitView();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();

        // Log in as different user
        headerPage.loginWithInAppLogInFunctionality(logInAsUser);
        headerPage.closeAllTabs();
        search.closePossibleContactsAccountPopUp();
        profileSettings.changeEnvironmentalLangTo("Deutsch");
        headerPage.closeAllTabs();

        search.searchAccountFromHeaderSearch(accountName);
        search.closePossibleContactsAccountPopUp();
        headerPage.closeTab();

        // Create a new case
        newCasePage.createNewCaseFromAccount();
        newCasePage.aNewCaseIsCreatedWithTheFollowingData(caseDataList, "Billing");

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.selectMasterDataCategory("Telefonbucheinträge");
        masterData.clickWeiter();
        masterData.selectTelefonbucheintragePhoneNumber();
        masterData.clickWeiter();

        masterData.verifyIfPhoneBookEntriesInputFieldsAreEmpty(false);
        masterData.verifyIfPhoneBookEntriesDropdownFieldsAreEmpty(false);
        masterData.selectPhoneBookEntriesPageCheckbox("Eintrag Telefonbuch");
        masterData.selectPhoneBookEntriesPageCheckbox("Adresse im Telefonbuch aufnehmen");
        masterData.selectPhoneBookEntriesPageCheckbox("Eintrag digitales Telefonbuch");
        masterData.selectPhoneBookEntriesPageCheckbox("Adresse im digitalen Telefonbuch aufnehmen");
        masterData.selectPhoneBookEntriesPageCheckbox("Inverssuche erlauben");

        //populating dropdowns
        masterData.selectPhoneBookEntriesDropdownValue("Verwendung", "Fax");
        masterData.selectPhoneBookEntriesDropdownValue("Anrede", salutation);

        //populating input fields
        masterData.populatePhoneBookEntriesInputField("Vorname", firstName);
        masterData.populatePhoneBookEntriesInputField("Nachname", lastName);
        masterData.populatePhoneBookEntriesInputField("Firma", companyName);
        masterData.populatePhoneBookEntriesInputField("Straße", street);
        masterData.populatePhoneBookEntriesInputField("Hausnummer", houseNumber);
        masterData.populatePhoneBookEntriesInputField("Zusatz", houserNumberSuffix);
        masterData.populatePhoneBookEntriesInputField("PLZ", postCode);
        masterData.populatePhoneBookEntriesInputField("Ort", city);
        masterData.populatePhoneBookEntriesInputField("Land", country);

        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.clickBestatigungAutomatischVersendenCheckbox();
        masterData.clickWeiter();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.gotoCaseAktion();

        masterData.verifyIfEmailIsShownInAktion(false);
        masterData.openLastMasterDataReportFromAktion();

        masterData.verifyIfBenachrichtigungSchickenIsChecked(false);

        headerPage.logoutFromInAppLogInUser();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();
        search.searchAccountFromHeaderSearch(accountName);

        search.closePossibleContactsAccountPopUp();
        accountViewPage.openContractData();
        accountViewPage.openCustomerSubscription();
        accountViewPage.gotoRelatedTabSubscription();
        accountViewPage.openPhoneNumberName();
        accountViewPage.openPhonebookEntry();
        headerPage.closeSubTab();
        headerPage.closeSubTab();
        headerPage.closeSubTab();

        accountViewPage.verifyPhonebookEntryFieldValue("Salutation", salutation);
        accountViewPage.verifyPhonebookEntryFieldValue("First Name", firstName);
        accountViewPage.verifyPhonebookEntryFieldValue("Last Name", lastName);
        accountViewPage.verifyPhonebookEntryFieldValue("Company Name", companyName);
        accountViewPage.verifyPhonebookEntryFieldValue("Street", street);
        accountViewPage.verifyPhonebookEntryFieldValue("House Number", houseNumber);
        accountViewPage.verifyPhonebookEntryFieldValue("House Number Suffix", houserNumberSuffix);
        accountViewPage.verifyPhonebookEntryFieldValue("Post Code", postCode);
        accountViewPage.verifyPhonebookEntryFieldValue("City", city);
        accountViewPage.verifyPhonebookEntryFieldValue("Country", country);

        accountViewPage.verifyIfCheckboxIsChecked("Entry");
        accountViewPage.verifyIfCheckboxIsChecked("Digital Entry");
        accountViewPage.verifyIfCheckboxIsChecked("Include Address");
        accountViewPage.verifyIfCheckboxIsChecked("Include Digital Address");
        accountViewPage.verifyIfCheckboxIsChecked("Inverted Search");

        headerPage.gotoFirstSubTab();
        headerPage.closeSubTab();

        search.closePossibleContactsAccountPopUp();
        accountViewPage.openAccountInCim();

        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);

        //wait for CIM to load Account
        cimHomePage.waitForCimToLoadAccount();
        cimAccountViewPage.openPhoneNumber();
        cimAccountViewPage.openPhoneBook();

        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("Vorname", firstName);
        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("Nachname", lastName);
        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("Straße", street);
        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("Hausnummer", houseNumber);
        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("Zusatz", houserNumberSuffix);
        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("PLZ", postCode);
        cimAccountViewPage.verifyPhoneBookEntryFieldValueCim("Ort", city);

    }

    /**
     * <b>[Test Method]</b> - Set Up Driver and open URL<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to set up Driver and open URL<br>
     * BeforeClass - annotation
     */
    @BeforeClass
    public void setUp() {
        driver.get(config.getProperty("sutUrl"));
        setUpDriver();
    }

    /**
     * <b>[Test Method]</b> - Init Testing Resource<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to init Testing Resource<br>
     * BeforeTest - annotation
     */
    @BeforeTest
    public void initResource() {
        caseData1.setCaseOrigin("Telefon");
        caseData1.setCategory("Stammdaten");
        caseData1.setContactName(accountName);
        caseDataList.add(caseData1);
    }
}
