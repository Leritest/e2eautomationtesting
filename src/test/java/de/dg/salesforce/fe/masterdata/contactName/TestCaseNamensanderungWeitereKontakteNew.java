package de.dg.salesforce.fe.masterdata.contactName;

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

import java.util.Random;

/**
 * <b>TEST CASE</b> [MasterData/Contacts]: Namensänderung weitere Kontakte
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseNamensanderungWeitereKontakteNew extends Page {

    /**
     * <b>[Test Method]</b> - Test ???? OPT-5608<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality ??????.<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Login to the Salesforce application<br>
     * 2. Navigate to ??.<br>
     */
    @Test(description = "OPT-5608")
    public void tcNamensanderungWeitereKontakteNew() {

        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        Search search = new Search();
        MasterData masterData = new MasterData();
        ProfileSettings profileSettings = new ProfileSettings();
        Random rand = new Random();
        CaseViewPage caseViewPage = new CaseViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();


        String logInAsUser = "Aleksandar Stojanov Test";
        String caseNumber = "00024358";
        String randomNumber = String.valueOf(rand.nextInt(1000));
        String lastNameNew = "StojanovShipping" + randomNumber;

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

        headerPage.clickNavigationMenu(AppList.CASES_APP);
        search.searchCase(caseNumber);

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.verifyIfButtonsAreShownNextToMasterDataCategories();
        masterData.selectMasterDataCategory("Namensänderung weitere Kontakte");
        masterData.verifyContactIsPreSelected();
        masterData.verifyIfContactIsShown("StojanovBilling", true);
        masterData.verifyIfContactIsShown("StojanovInstallation", true);
        masterData.verifyIfContactIsShown("StojanovShipping", true);
        masterData.verifyIfContactIsShown("StojanovConnectionOwner", false);

        masterData.clickWeiter();
        masterData.verifyNamensanderungFieldsVisible(new String[]{"Anrede", "Titel", "Vorname", "Nachname"});
        masterData.changeFieldTo("nachname", lastNameNew);
        masterData.verifyIfContactFieldIsMandatory("Anrede", false);
        masterData.verifyIfContactFieldIsMandatory("Titel", false);
        masterData.verifyIfContactFieldIsMandatory("Vorname", false);
        masterData.verifyIfContactFieldIsMandatory("Nachname", true);
        masterData.documentsAreRequired(true);
        masterData.verifyIfOldNewTableSummaryShown();
        masterData.verifyZusammenfassungNachnameNeuValue(lastNameNew);
        masterData.clickWeiter();
        masterData.closeMasterDataOmniscript();

        caseViewPage.verifyCaseContactLastName(lastNameNew);
        caseViewPage.openAccountInCimFromCase();
        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);
        cimHomePage.waitForCimToLoadAccount();
        cimAccountViewPage.verifyIfContactLastNameChangeIsShownInNotizen(lastNameNew);
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
