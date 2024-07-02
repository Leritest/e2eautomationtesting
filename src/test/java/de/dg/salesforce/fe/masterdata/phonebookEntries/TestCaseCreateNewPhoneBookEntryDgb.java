package de.dg.salesforce.fe.masterdata.phonebookEntries;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
import automation.de.dg.salesforce.endpoints.controllers.AccountAPI;
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

/**
 * <b>TEST CASE</b> [MasterData/OptIns]: Test Create New Phone Book Entry
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseCreateNewPhoneBookEntryDgb extends Page {

    List<CaseData> caseDataList = new ArrayList<>();
    CaseData caseData1 = new CaseData();

    /**
     * <b>[Test Method]</b> - Test Create New PhoneBook Entry<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tests the creating new phonebook entry<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Create an account<br>
     * 3. Create a case
     * 4. Launch Master Data Omniscript
     * 5. Create the phonebook
     * 6. Verify if email is sent
     * 7. Open the Master Data Report
     * 8. Find the phonebook entry created before
     * 9. Verify the fields value
     * 10. Open account in CIM
     * 11. Verify the phonebook entry in CIM
     */
    @Test(description = "OPT-5123")
    public void tcCreateNewPhoneBookEntryDgb() {
        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        Search search = new Search();
        MasterData masterData = new MasterData();
        NewCasePage newCasePage = new NewCasePage();
        AccountViewPage accountViewPage = new AccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ProfileSettings profileSettings = new ProfileSettings();

        String logInAsUser = "Aleksandar Stojanov Test";
        String accountId = AccountAPI.addNewAccountInCIM("DGB");
        String salutation = "Herr";
        String firstName = "Aleksandar";
        String lastName = "Stojanov";
        String companyName = "Qinshift";
        String street = "Strasse";
        String houseNumber = "20";
        String houserNumberSuffix = "A";
        String postCode = "2";
        String city = "C";
        String country = "DE";

        loginPage.login(UserRoles.AUTO_ADMIN);
        search.closePossibleContactsAccountPopUp();
        headerPage.closeSplitView();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();

        // Log in as different user
        headerPage.loginWithInAppLogInFunctionality(logInAsUser);
        headerPage.closeAllTabs();
        profileSettings.changeEnvironmentalLangTo("Deutsch");
        headerPage.closeAllTabs();

        search.waitForAccountToSyncFromCim(accountId);
        search.closePossibleContactsAccountPopUp();

        // Create new case
        newCasePage.createNewCaseFromAccount();
        newCasePage.aNewCaseIsCreatedWithTheFollowingData(caseDataList, "Billing");

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.selectMasterDataCategory("Telefonbucheinträge");
        masterData.clickWeiter();
        masterData.selectTelefonbucheintragePhoneNumber();
        masterData.clickWeiter();
        masterData.verifyIfPhoneBookEntriesInputFieldsAreEmpty(true);
        masterData.verifyIfPhoneBookEntriesDropdownFieldsAreEmpty(true);
        masterData.selectPhoneBookEntriesPageCheckbox("Eintrag Telefonbuch");
        masterData.selectPhoneBookEntriesPageCheckbox("Adresse im Telefonbuch aufnehmen");
        masterData.selectPhoneBookEntriesPageCheckbox("Eintrag digitales Telefonbuch");
        masterData.selectPhoneBookEntriesPageCheckbox("Adresse im digitalen Telefonbuch aufnehmen");
        masterData.selectPhoneBookEntriesPageCheckbox("Inverssuche erlauben");

        //populating dropdowns
        masterData.selectPhoneBookEntriesDropdownValue("Verwendung", "Telefon");
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
        masterData.clickWeiter();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();

        masterData.gotoCaseAktion();
        masterData.verifyIfEmailIsShownInAktion(true);
        masterData.openLastMasterDataReportFromAktion();
        masterData.verifyIfBenachrichtigungSchickenIsChecked(true);

        headerPage.logoutFromInAppLogInUser();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();
        search.searchAllTodayAccountFromSearch(accountId);

        search.closePossibleContactsAccountPopUp();
        accountViewPage.openContractData();
        accountViewPage.openCustomerSubscription();
        accountViewPage.gotoRelatedTabSubscription();
        accountViewPage.openPhoneNumberName();
        accountViewPage.openPhonebookEntry();
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
        caseData1.setContactName("Aleksandar StojanovBilling");
        caseDataList.add(caseData1);
    }
}
