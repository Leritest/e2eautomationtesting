package de.dg.salesforce.fe.masterdata.phonebookEntries;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.account.AccountViewPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactViewPage;
import automation.testbase.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * <b>TEST CASE</b> [MasterData/OptIns]: Test Change Opt Ins for Local Contact
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseModifyPhoneBookInCimDgh extends Page {

    /**
     * <b>[Test Method]</b> - Test Edit Phone Book Entry<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tests the process of editing a phone book entry in CIM<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in to Salesforce<br>
     * 2. Find the specific account<br>
     * 3. Open it in CIM
     * 4. Open phone book entry
     * 5. Edit two fields
     * 6. Save the changes
     * 7. Open the account in Salesforce
     * 8. Open the phone book entry in Salesforce
     * 9. Verify if changes are reflected
     */
    @Test(description = "OPT-5231")
    public void tcEditPhoneBookEntryDgh() {
        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        Search search = new Search();
        AccountViewPage accountViewPage = new AccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ContactViewPage contactViewPage = new ContactViewPage();
        Random rand = new Random();

        String randomNumber = String.valueOf(rand.nextInt(1000));
        String accountName = "3941245";
        String firstName = "Ace" + randomNumber;
        String street = "Am Bernesterfeld " + randomNumber;

        loginPage.login(UserRoles.AUTO_ADMIN);
        search.closePossibleContactsAccountPopUp();
        headerPage.closeSplitView();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();
        search.searchAccountFromHeaderSearch(accountName);
        search.closePossibleContactsAccountPopUp();
        accountViewPage.openAccountInCim();
        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);

        cimHomePage.waitForCimToLoadAccount();
        cimAccountViewPage.openPhoneNumber();
        cimAccountViewPage.openPhoneBook();
        cimAccountViewPage.editPhoneBookField("Vorname", firstName);
        cimAccountViewPage.editPhoneBookField("Straße", street);
        cimAccountViewPage.clickAnlegen();

        cimAccountViewPage.closeTelefonNummerView();
        cimHomePage.waitForCimToLoadAccount();
        cimAccountViewPage.openKundennummerInSalesforce();

        contactViewPage.openAccountOfContact();
        search.closePossibleContactsAccountPopUp();
        headerPage.closeTab();

        accountViewPage.openContractData();
        accountViewPage.openCustomerSubscription();
        accountViewPage.gotoRelatedTabSubscription();
        headerPage.closeSubTab();
        accountViewPage.openPhoneNumberName();
        accountViewPage.openPhonebookEntry();
        headerPage.closeSubTab();
        headerPage.closeSubTab();

        accountViewPage.waitForPhoneBookEntryEditsToSyncWithSalesforce(street);

        accountViewPage.verifyPhonebookEntryFieldValue("First Name", firstName);
        accountViewPage.verifyPhonebookEntryFieldValue("Street", street);

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

    }
}
