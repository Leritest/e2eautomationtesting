package de.dg.salesforce.fe.masterdata.addressRole;

import automation.de.dg.cim.enumation.UserRolesCim;
import automation.de.dg.cim.pages.HomePage;
import automation.de.dg.cim.pages.LoginPageCim;
import automation.de.dg.cim.pages.accounts.CimAccountViewPage;
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

@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseChangeExistingContactRoleWithoutChangingAddressNew extends Page {

    List<CaseData> caseDataList = new ArrayList<>();
    List<CaseData> caseDataContact = new ArrayList<>();
    CaseData caseData1 = new CaseData();
    CaseData caseData2 = new CaseData();
    Random rand = new Random();
    String randomNumber = String.valueOf(rand.nextInt(1000));
    String contactAnrede = "Herr";
    String contactTitel = "Dr.";
    String contactVorname = "Aleksandar";
    String contactNachname = "Stojanov" + randomNumber;
    String contactGeburtsdatum = "02.12.1923";
    String contactEmail = "asd@asd.com";
    String contactPhoneMobile = "+49 30 654322";
    String accountId = "8029384";
    //String accountId = AccountAPI.addNewAccountInCIM("NEW"); enable after the script is completed

    @Test(description = "OPT-5492")
    public void tcChangeExistingContactRoleWithoutChangingAddressNew() {

        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        MasterData masterData = new MasterData();
        NewCasePage newCasePage = new NewCasePage();
        Search search = new Search();
        CaseViewPage caseViewPage = new CaseViewPage();
        AccountViewPage accountViewPage = new AccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ProfileSettings profileSettings = new ProfileSettings();

        // Inputs
        String logInAsUser = "Aleksandar Stojanov Test";
        String masterDataCategory = "Rechnungsadresse";

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
        search.searchAccountFromHeaderSearch(accountId); //replace with the line above
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
        masterData.verifyContactIsPreSelected();
        masterData.verifyNewContactOptionAvailable();
        masterData.verifyIfContactIsShown("StojanovBilling", true);
        masterData.verifyIfContactIsShown("StojanovInstallation", true);
        masterData.verifyIfContactIsShown("StojanovShipping", true);
        masterData.verifyIfContactIsShown("StojanovConnectionOwner", true);
        masterData.selectContact("ConnectionOwner");
        masterData.clickWeiter();

        masterData.verifyNewAddressOptionAvailable();
        masterData.verifyIfAddressesAreShown();
        masterData.verifyAddressIsPreSelected();
        masterData.clickWeiter();

        String newAddressStreet = masterData.getNewAddressStreet();
        String newAddressNumber = masterData.getNewAddressNumber();
        String newAddressZusatz = masterData.getNewAddressZusatz();
        String newAddressZipCode = masterData.getNewAddressZipCode();
        String newAddressCity = masterData.getNewAddressCity();
        String newAddressCountry = masterData.getNewAddressCountry();
        String fullAddress = newAddressStreet + " " + newAddressNumber + " " + newAddressZusatz + " " + newAddressZipCode + " " + newAddressCity;

        masterData.clickWeiter();
        masterData.verifyIfOldNewTableSummaryShown();

        //compare the values from previous screen to this screen new values
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Straße", newAddressStreet);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Nr.", newAddressNumber);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Zusatz", newAddressZusatz);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("PLZ", newAddressZipCode);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Stadt", newAddressCity);
        masterData.verifyAnschlussinhaberadresseFieldsOldNewTable("Land", newAddressCountry);

        masterData.masterDataSelectDokumente();
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.verifyTypFieldValue("Adressänderung");

        masterData.gotoCaseAktion();
        masterData.verifyIfEmailIsShownInAktion(false);
        masterData.openLastMasterDataReportFromAktion();
        masterData.verifyIfBenachrichtigungSchickenIsChecked(true);
        headerPage.closeSubTab();
        masterData.gotoCaseDetailsFromAktion();
        masterData.openCaseAccount();

        accountViewPage.verifyContactOwnerDetails(masterDataCategory,"Strasse", newAddressStreet);
        accountViewPage.verifyContactOwnerDetails(masterDataCategory,"Nr", newAddressNumber);
        accountViewPage.verifyContactOwnerDetails(masterDataCategory,"Zusatz", newAddressZusatz);
        accountViewPage.verifyContactOwnerDetails(masterDataCategory,"Plz", newAddressZipCode);
        accountViewPage.verifyContactOwnerDetails(masterDataCategory,"Stadt", newAddressCity);
        accountViewPage.verifyContactOwnerDetails(masterDataCategory,"Land", newAddressCountry);

        caseViewPage.openAccountInCimFromCase();

        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);
        cimHomePage.waitForCimToLoadAccount();

        cimAccountViewPage.verifyIfAddresseGeandertIsShownInNotizen(false);
        cimAccountViewPage.verifyIfRechnungAddressContains(fullAddress);

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
        caseDataList.add(caseData1);

        caseData2.setAnrede(contactAnrede);
        caseData2.setTitel(contactTitel);
        caseData2.setVorname(contactVorname);
        caseData2.setNachname(contactNachname);
        caseData2.setGeburtsdatum(contactGeburtsdatum);
        caseData2.setEmail(contactEmail);
        caseData2.setTelefonnummer(contactPhoneMobile);
        caseData2.setMobiltelefon(contactPhoneMobile);
        caseDataContact.add(caseData2);

    }
}
