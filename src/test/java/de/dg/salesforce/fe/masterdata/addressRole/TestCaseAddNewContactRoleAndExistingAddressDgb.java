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

/**
 * <b>TEST CASE</b> [MasterData/AddressRole]: Test Add New Contact Role and Existing Address
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseAddNewContactRoleAndExistingAddressDgb extends Page {

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
    String contactEmail = "a.stojanov@extern.deutsche-glasfaser.de";
    String contactPhoneMobile = "+49 30 654322";
    String accountId = "1314857";
    //String accountId = AccountAPI.addNewAccountInCIM("DGB"); enable after the script is completed

    /**
     * <b>[Test Method]</b> - Add New Contact Role and Existing Address<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality adds new contact role and existing address<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Create a case<br>
     * 3. Launch Master Data Omniscript
     * 4. Create new contact
     * 5. Choose existing address
     * 6. Close Master Data Omniscript
     * 7. Verify the changes
     */
    @Test(description = "OPT-5488")
    public void tcAddNewContactRoleAndExistingAddressDgb() {

        // initialize classes
        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        MasterData masterData = new MasterData();
        NewCasePage newCasePage = new NewCasePage();
        Search search = new Search();
        AccountViewPage accountViewPage = new AccountViewPage();
        LoginPageCim loginPageCim = new LoginPageCim();
        HomePage cimHomePage = new HomePage();
        CimAccountViewPage cimAccountViewPage = new CimAccountViewPage();
        ProfileSettings profileSettings = new ProfileSettings();


        // Inputs
        String logInAsUser = "Aleksandar Stojanov Test";
        String contactFullName = contactVorname + " " + contactNachname;
        String strasse = "Hochbrücker Straße";
        String nr = "122";
        String zusatz = "S";
        String plz = "52525";
        String stadt = "Heinsberg";
        String land = "DE";

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
        newCasePage.aNewCaseIsCreatedWithTheFollowingData(caseDataList, "Billing");

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.verifyIfButtonsAreShownNextToMasterDataCategories();
        masterData.selectMasterDataCategory("Anschlussinhaberadresse");
        masterData.verifyColumnsContactAddingVisibility(new String[]{"Name", "E-Mail", "Rollen"});
        masterData.verifyNewContactOptionAvailable();
        masterData.verifyContactIsPreSelected();

        masterData.selectContact("Neuen Kontakt erfassen");
        masterData.verifyFieldsVisible(new String[]{"Anrede", "Titel", "Vorname", "Nachname", "Geburtsdatum", "E-Mail", "Telefonnummer", "Mobiltelefon"});

        // Create new contact inside Master Data
        masterData.createNewContact(caseDataContact);
        masterData.verifyNewAddressOptionAvailable();
        masterData.verifyIfAddressesAreShown();
        masterData.verifyAddressIsPreSelected();
        masterData.selectAddress("Lieferadresse");
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
        masterData.verifyIfBestatigungAutomatischIsSelected();
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
        masterData.verifyIfEmailIsShownInAktion(true);
        masterData.openLastMasterDataReportFromAktion();
        masterData.verifyIfBenachrichtigungSchickenIsChecked(true);
        headerPage.closeSubTab();
        masterData.gotoCaseDetailsFromAktion();
        masterData.openCaseAccount();

        accountViewPage.verifyContactDetails(contactFullName, "Strasse", strasse);
        accountViewPage.verifyContactDetails(contactFullName, "Nr", nr);
        accountViewPage.verifyContactDetails(contactFullName, "Zusatz", zusatz);
        accountViewPage.verifyContactDetails(contactFullName, "Plz", plz);
        accountViewPage.verifyContactDetails(contactFullName, "Stadt", stadt);
        accountViewPage.verifyContactDetails(contactFullName, "Land", land);


        accountViewPage.openAccountInCim();
        loginPageCim.loginCimWeb(UserRolesCim.CIM_ADMIN);
        cimHomePage.waitForCimToLoadAccount();

        cimAccountViewPage.verifyIfAddressContains(strasse);
        cimAccountViewPage.verifyIfAddressContains(nr);
        cimAccountViewPage.verifyIfAddressContains(zusatz);
        cimAccountViewPage.verifyIfAddressContains(plz);
        cimAccountViewPage.verifyIfAddressContains(stadt);

        cimAccountViewPage.clickAdresseGeandert();
        cimAccountViewPage.verifyNotizenAddresse(fullAddress);

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

        caseData2.setAnrede(contactAnrede);
        caseData2.setTitel(contactTitel);
        caseData2.setVorname(contactVorname);
        caseData2.setNachname(contactNachname);
        caseData2.setGeburtsdatum(contactGeburtsdatum);
        caseData2.setEmail(contactEmail);
        caseData2.setTelefonnummer(contactPhoneMobile);
        caseData2.setMobiltelefon(contactPhoneMobile);
        caseDataContact.add(caseData2);

//        caseData3.setAdresseSuchen("");
//        caseData3.setStrasse("Am Bernesterfeld");
//        caseData3.setNr("30");
//        caseData3.setZusatz("A");
//        caseData3.setPlz("85625");
//        caseData3.setStadt("Glonn");
//        caseData3.setLand("DE");
//        caseDataAddress.add(caseData3);

    }
}
