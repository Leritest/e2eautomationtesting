package de.dg.salesforce.fe.masterdata.evn;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
import automation.de.dg.salesforce.enumation.AppList;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.ProfileSettings;
import automation.de.dg.salesforce.frontend.pages.cases.CaseViewPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.masterdata.MasterData;
import automation.testbase.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>TEST CASE</b> [MasterData/EVN]: Test Business Account Can Be Changed
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseBusinessAccountCanBeChangedDgb extends Page {

    /**
     * <b>[Test Method]</b> - Business Account Can Be Changed<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality checks if business account can be changed<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Find a case<br>
     * 3. Launch Master Data Omniscript
     * 4. Change the EVN value
     * 5. Close the Master Data Omniscript
     * 7. Verify the changes
     */
    @Test(description = "OPT-4350")
    public void tcBusinessAccountCanBeChangedDgb(){

        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        MasterData masterData = new MasterData();
        Search search = new Search();
        CaseViewPage caseViewPage = new CaseViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ProfileSettings profileSettings = new ProfileSettings();

        String logInAsUser = "Aleksandar Stojanov Test";
        String caseNumber = "00024370";

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

        // Search for a case
        headerPage.clickNavigationMenu(AppList.CASES_APP);
        search.searchCase(caseNumber);

        masterData.launchMasterDataOmniscript();
        masterData.verifyMasterDataOpened();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.verifyIfButtonsAreShownNextToMasterDataCategories();
        masterData.selectMasterDataCategory("Einzelverbindungsnachweis");
        masterData.verifyIfEinzelverbindungsnachweisDisplayedCorrectly();
        String oldEvnValue = masterData.getEvnTypCurrentValue();
        masterData.chooseEvnTypValue("EVN ungekürzt");
        String newEvnValue = masterData.getEvnTypCurrentValue();
        masterData.clickWeiter();
        masterData.verifyIfOldNewTableSummaryShown();
        masterData.verifyEvnTypOldValueSummaryTable(oldEvnValue);
        masterData.verifyEvnTypNewValueSummaryTable(newEvnValue);

        masterData.clickWeiter();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.verifyTypFieldValue("EVN");

        caseViewPage.openAccountInCimFromCase();

        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);
        cimHomePage.waitForCimToLoadAccount();

        cimAccountViewPage.verifyNotizen(newEvnValue);
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
}
