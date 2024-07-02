package de.dg.salesforce.fe.masterdata.accounts;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
import automation.de.dg.salesforce.endpoints.controllers.AccountAPI;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.ProfileSettings;
import automation.de.dg.salesforce.frontend.pages.account.AccountViewPage;
import automation.de.dg.salesforce.frontend.pages.cases.CaseViewPage;
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
 * <b>TEST CASE</b> [MasterData/Accounts]: Test change company name
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseChangeCompanyNameDgb extends Page {

    Random rand = new Random();
    List<CaseData> caseDataList = new ArrayList<>();
    CaseData caseData1 = new CaseData();
    String randomNumber = String.valueOf(rand.nextInt(1000));
    String accountId = AccountAPI.addNewAccountInCIM("DGB");

    /**
     * <b>[Test Method]</b> - Test change company name OPT-5595<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality changes the company name.<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Create a case<br>
     * 3. Launch Master Data Omniscript
     * 4. Change company name field
     * 5. Close Master Data Omniscript
     * 6. Verify the changes
     */
    @Test(description = "OPT-5595")
    public void tcChangeCompanyNameDgb() {

        LoginPage loginPage = new LoginPage();
        Search search = new Search();
        MasterData masterData = new MasterData();
        HeaderPage headerPage = new HeaderPage();
        NewCasePage newCasePage = new NewCasePage();
        AccountViewPage accountViewPage = new AccountViewPage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        ProfileSettings profileSettings = new ProfileSettings();

        String logInAsUser = "Aleksandar Stojanov Test";
        String newCompanyName = "ASDGB01"+randomNumber;

        loginPage.login(UserRoles.AUTO_ADMIN);
        search.closePossibleContactsAccountPopUp();
        headerPage.closeSplitView();
        headerPage.checkHomepage("Service Console");
        headerPage.closeAllTabs();

        headerPage.loginWithInAppLogInFunctionality(logInAsUser);
        headerPage.closeAllTabs();
        search.closePossibleContactsAccountPopUp();
        profileSettings.changeEnvironmentalLangTo("Deutsch");
        headerPage.closeAllTabs();

        search.waitForAccountToSyncFromCim(accountId);
        search.closePossibleContactsAccountPopUp();

        // Create new case
        newCasePage.createNewCaseFromAccount();
        newCasePage.aNewCaseIsCreatedWithTheFollowingData(caseDataList, "Billing");
        masterData.launchMasterDataOmniscript();
        masterData.selectMasterDataCategory("Namensänderung Anschlussinhaber (inkl. Firma)");
        masterData.changeFieldTo("company name", newCompanyName);
        String nachname = masterData.getNamensanderungAnschlussinhaberNachnameCurrentValue();
        masterData.documentsAreRequired(true);
        masterData.verifyIfOldNewTableSummaryShown();
        masterData.verifyZusammenfassungNachnameNeuValue(nachname);
        masterData.clickWeiter();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.verifyTypFieldValue("Namensänderung (inkl. Firma)");

        masterData.openCaseAccount();
        accountViewPage.verifyAccountName(newCompanyName);

        accountViewPage.openAccountInCim();
        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);

        cimHomePage.waitForCimToLoadAccount();

        cimAccountViewPage.verifyAccountNameCim(newCompanyName);

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
        caseData1.setContactName(accountId);
        caseDataList.add(caseData1);
    }
}
