package de.dg.salesforce.fe.masterdata.optIns;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
import automation.de.dg.salesforce.enumation.AppList;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.account.AccountViewPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactViewPage;
import automation.de.dg.salesforce.frontend.pages.masterdata.MasterData;
import automation.de.dg.salesforce.frontend.pages.ProfileSettings;
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
public class TestCaseChangeOptInsForLocalContactDgb extends Page {
    Random rand = new Random();
    String accountName;
    String contactEmail;
    String randomNumber = String.valueOf(rand.nextInt(1000));
    String contactName = "Aleksandar StojanovConnectionOwner";

    /**
     * <b>[Test Method]</b> - Test Change Opt Ins for Local Contact<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tests opt ins for local contact<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Open a case<br>
     * 3. Launch Master Data Omniscript
     * 4. Select/Deselect Opt Ins
     * 5. Close Master Data Omniscript
     * 6. Verify the changes
     */
    @Test(description = "OPT-8166")
    public void tCWithVoiceRecordingAndEmailForConnectionOwnerRoleDgb() {

        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        MasterData masterData = new MasterData();
        Search search = new Search();
        ContactViewPage contactViewPage = new ContactViewPage();
        AccountViewPage accountViewPage = new AccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ProfileSettings profileSettings = new ProfileSettings();

        String logInAsUser = "Aleksandar Stojanov Test";
        String caseNumber = "00023846";
        accountName = "AleksandarA" + randomNumber;
        contactEmail = contactName + "@asd.com";

        loginPage.login(UserRoles.AUTO_ADMIN);
        search.closePossibleContactsAccountPopUp();
        headerPage.closeSplitView();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();

        headerPage.loginWithInAppLogInFunctionality(logInAsUser);
        headerPage.closeAllTabs();
        profileSettings.changeEnvironmentalLangTo("Deutsch");
        headerPage.closeAllTabs();

        headerPage.clickNavigationMenu(AppList.CASES_APP);
        search.searchCase(caseNumber);

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.selectMasterDataCategory("Werbeeinstellungen");
        masterData.verifyContactIsPreSelected();
        masterData.clickWeiter();
        masterData.verifyIfWerbeeinstellungenOptInsAreShown();
        masterData.verifyIfVoiceRecordingOptionIsShown();
        masterData.clickWeiter();
        masterData.verifyIfVoiceRecordingErrorIsShown();

        masterData.selectVoiceRecordingOption("Sprachaufzeichnung durchgeführt");
        masterData.selectWerbeeinstellungenOptIn("Telefon Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.verifyIfOldNewTableSummaryShown();
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Neu", true);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Bisher", false);
        masterData.clickZuruck();

        //Telefon check
        masterData.selectWerbeeinstellungenOptIn("Telefon Opt-In");//uncheck
        masterData.selectWerbeeinstellungenOptIn("E-Mail Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.verifyIfOldNewTableSummaryShown();
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Neu", true);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Bisher", false);
        masterData.clickZuruck();

        //E-Mail check
        masterData.selectWerbeeinstellungenOptIn("E-Mail Opt-In");//uncheck
        masterData.selectWerbeeinstellungenOptIn("Verkehrsdaten Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.verifyIfOldNewTableSummaryShown();
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Neu", true);
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Bisher", false);
        masterData.clickZuruck();

        //Verkehrsdaten check
        masterData.selectWerbeeinstellungenOptIn("Verkehrsdaten Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.verifyIfOldNewTableSummaryShown();//
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Neu", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Telefon Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Email Bisher", false);
        masterData.verifyOldNewTableCheckboxesCheckedState("Verkehrsdaten Bisher", false);
        masterData.clickWeiter();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.gotoCaseAktion();
        masterData.verifyIfEmailIsShownInAktion(true);
        masterData.verifyIfPersonIsShownInHistoryChanges(logInAsUser);
        masterData.openLastMasterDataReportFromAktion();
        masterData.verifyIfSprachaufzeichnungIsChecked();
        masterData.verifyIfBenachrichtigungSchickenIsChecked(true);
        masterData.verifyWhoDidMasterDataChangesIsShown(logInAsUser);
        masterData.verifyMasterDataChangesDateIsShown();
        masterData.closeMasterDataReport();
        masterData.gotoCaseDetailsFromAktion();
        masterData.openCaseContact();
        masterData.verifyIfContactOptInsFieldsUpdated("Telefon Opt-In");
        masterData.verifyIfContactOptInsFieldsUpdated("E-Mail Opt-In");
        masterData.verifyIfContactOptInsFieldsUpdated("Verkehrsdaten Opt-In");
        contactViewPage.verifyContactOptInCheckedState("Telefon Opt-In", false);
        contactViewPage.verifyContactOptInCheckedState("Email Opt-In", false);
        contactViewPage.verifyContactOptInCheckedState("Verkehrsdaten Opt-In", false);

        accountViewPage.openAccountInCim();
        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);
        cimHomePage.waitForCimToLoadAccount();
        cimAccountViewPage.verifyIfOptinIsShownInNotizen(false);

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
