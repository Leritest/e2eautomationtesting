package de.dg.salesforce.fe.masterdata.addressRole;

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
 * <b>TEST CASE</b> [MasterData/AddressRole]: Test Change existing Contact Role and New Address
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseChangeExistingContactRoleAndNewAddressDgh extends Page {

    List<CaseData> caseDataList = new ArrayList<>();
    List<CaseData> caseDataAddress = new ArrayList<>();
    CaseData caseData1 = new CaseData();
    CaseData caseData3 = new CaseData();
    Random rand = new Random();
    String randomNumber = String.valueOf(rand.nextInt(1000));
    String adresseSuchen = "Am Bernesterfeld, 85625 Glonn, Germany";
    String newStrasse = "Am Bernesterfeld";
    String newNr = "30";
    String newZusatz = "A";
    String newPlz = "85" + randomNumber;
    String newStadt = "Glonn";
    String newLand = "DE";
    String accountId = "3941733";
    //String accountId = AccountAPI.addNewAccountInCIM("DGB"); enable after the script is completed

    /**
     * <b>[Test Method]</b> - Chane Existing Contact Role and New Address<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality adds new contact role and new address<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Create a case<br>
     * 3. Launch Master Data Omniscript
     * 4. Select existing contact
     * 5. Create new address
     * 6. Close Master Data Omniscript
     * 7. Verify the changes
     */
    @Test(description = "OPT-5490")
    public void tcChangeExistingContactRoleAndNewAddressDgh() {

        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        MasterData masterData = new MasterData();
        NewCasePage newCasePage = new NewCasePage();
        Search search = new Search();
        HomePage cimHomePage = new HomePage();
        LoginPageCim loginPageCim = new LoginPageCim();
        AccountViewPage accountViewPage = new AccountViewPage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ProfileSettings profileSettings = new ProfileSettings();

        // Inputs
        String logInAsUser = "Aleksandar Stojanov Test";
        String contactFullName = "Aleksandar StojanovConnectionOwner";
        String masterDataCategory = "Anschlussinhaberadresse";

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

        //search.waitForAccountToSyncFromCim(accountId); enable after the script is completed
        search.searchAccountFromHeaderSearch(accountId);
        search.closePossibleContactsAccountPopUp();

        // Create new case
        newCasePage.createNewCaseFromAccount();
        newCasePage.aNewCaseIsCreatedWithTheFollowingData(caseDataList, "ConnectionOwner");

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.verifyIfButtonsAreShownNextToMasterDataCategories();
        masterData.selectMasterDataCategory(masterDataCategory);
        masterData.verifyColumnsContactAddingVisibility(new String[]{"Name", "E-Mail", "Rollen"});
        masterData.verifyNewContactOptionAvailable();
        masterData.verifyContactIsPreSelected();
        masterData.verifyIfContactIsShown("StojanovBilling", true);
        masterData.verifyIfContactIsShown("StojanovInstallation", true);
        masterData.verifyIfContactIsShown("StojanovShipping", true);
        masterData.verifyIfContactIsShown("StojanovConnectionOwner", true);
        masterData.selectContact("ConnectionOwner");
        masterData.clickWeiter();

        // Create new address inside Master Data
        masterData.verifyNewAddressOptionAvailable();
        masterData.verifyIfAddressesAreShown();
        masterData.verifyAddressIsPreSelected();
        masterData.selectNewAddressOption();
        masterData.createNewOrEditAddress(caseDataAddress);
        masterData.verifyIfOldNewTableSummaryShown();


        //compare the values from previous screen to this screen new values
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Straße", newStrasse);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Nr.", newNr);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Zusatz", newZusatz);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("PLZ", newPlz);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Stadt", newStadt);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Land", newLand);


        masterData.masterDataSelectDokumente();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.verifyTypFieldValue("Adressänderung");

        masterData.gotoCaseAktion();
        masterData.verifyIfEmailIsShownInAktion(true);
        masterData.openLastMasterDataReportFromAktion();
        masterData.verifyIfBenachrichtigungSchickenIsChecked(true);
        headerPage.closeSubTab();
        masterData.gotoCaseDetailsFromAktion();
        masterData.openCaseAccount();

        accountViewPage.verifyContactDetails(contactFullName, "Strasse", newStrasse);
        accountViewPage.verifyContactDetails(contactFullName, "Nr", newNr);
        accountViewPage.verifyContactDetails(contactFullName, "Zusatz", newZusatz);
        accountViewPage.verifyContactDetails(contactFullName, "Plz", newPlz);
        accountViewPage.verifyContactDetails(contactFullName, "Stadt", newStadt);
        accountViewPage.verifyContactDetails(contactFullName, "Land", newLand);

        accountViewPage.openAccountInCim();
        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);
        cimHomePage.waitForCimToLoadAccount();

        cimAccountViewPage.verifyIfAddressContains(newStrasse);
        cimAccountViewPage.verifyIfAddressContains(newNr);
        cimAccountViewPage.verifyIfAddressContains(newZusatz);
        cimAccountViewPage.verifyIfAddressContains(newPlz);
        cimAccountViewPage.verifyIfAddressContains(newStadt);
        cimAccountViewPage.verifyAccountNameCim(contactFullName);

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
     * BeforeClass - annotation
     */
    @BeforeTest
    public void initResource() {

        caseData1.setCaseOrigin("Telefon");
        caseData1.setCategory("Stammdaten");
        caseData1.setContactName(accountId);
        caseDataList.add(caseData1);

        caseData3.setAdresseSuchen(adresseSuchen);
        caseData3.setStrasse(newStrasse);
        caseData3.setNr(newNr);
        caseData3.setZusatz(newZusatz);
        caseData3.setPlz(newPlz);
        caseData3.setStadt(newStadt);
        caseData3.setLand(newLand);
        caseDataAddress.add(caseData3);


    }
}
