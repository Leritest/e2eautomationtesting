package de.dg.salesforce.fe.masterdata.contactData;

import automation.de.dg.salesforce.enumation.AppList;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.ProfileSettings;
import automation.de.dg.salesforce.frontend.pages.account.AccountViewPage;
import automation.de.dg.salesforce.frontend.pages.cases.CaseViewPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactViewPage;
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
 * <b>TEST CASE</b> [MasterData/AuthRepAndInstallation]: Test Create New Contact with a Role
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseCreateNewContactWithARoleNew extends Page {

    List<CaseData> caseDataContact = new ArrayList<>();
    List<CaseData> tLastName = new ArrayList<>();
    CaseData caseData1 = new CaseData();
    CaseData caseData3 = new CaseData();
    Random rand = new Random();
    String randomNumber = String.valueOf(rand.nextInt(1000));
    String contactAnrede = "Herr";
    String contactTitel = "Dr.";
    String contactVorname = "Ace" + randomNumber;
    String contactNachname = "Stojanov" + randomNumber;
    String contactGeburtsdatum = "02.12.1923";
    String contactEmail = "a.stojanov@extern.deutsche-glasfaser.de";
    String contactPhoneMobile = "+38971234569";

    /**
     * <b>[Test Method]</b> - Test Edit Phone Book Entry<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tests the process of editing a phone book entry in CIM<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in to Salesforce<br>
     * 2. ???????
     */
    @Test(description = "OPT-5579")
    public void tcCreateNewContactWithARoleNew() {

        LoginPage loginPage = new LoginPage();
        Search search = new Search();
        HeaderPage headerPage = new HeaderPage();
        CaseViewPage caseViewPage = new CaseViewPage();
        MasterData masterData = new MasterData();
        AccountViewPage accountViewPage = new AccountViewPage();
        ContactViewPage contactViewPage = new ContactViewPage();
        ProfileSettings profileSettings = new ProfileSettings();

        // Inputs
        String logInAsUser = "Aleksandar Stojanov Test";
        String caseNumber = "00024559";
        String caseCategory = "Stammdaten";
        String firstRoleName = "Ansprechpartner Installation";
        String secondRoleName = "Bevollmächtigter";
        String firstScreenTitle = "Rolle auswählen";
        String secondScreenTitle = "Zugewiesene Rollen & Funktion: Kontakte deaktivieren";
        String contactFullName = contactVorname + " " + contactNachname;
        String accountISP = "NEW";

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

        headerPage.clickNavigationMenu(AppList.CASES_APP);
        search.searchCase(caseNumber);
        caseViewPage.verifyCaseCategory(caseCategory);
        caseViewPage.verifyIfCaseHasAccountConnected();
        caseViewPage.verifyIfCaseHasContactAssigned();
        caseViewPage.verifyIfCaseContactHasEmailAddress();

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.selectMasterDataCategory("Bevollmächtigter oder Ansprechpartner Installation");
        masterData.verifyColumnsContactAddingVisibility(new String[]{"Name", "E-Mail", "Rollen"});
        masterData.verifyNewContactOptionAvailable();
        masterData.selectContact("Neuen Kontakt erfassen");
        masterData.verifyFieldsVisible(new String[]{"Anrede", "Titel", "Vorname", "Nachname", "Geburtsdatum", "E-Mail", "Telefonnummer", "Mobiltelefon"});

        // Create new contact inside Master Data
        masterData.createNewContact(caseDataContact);
        masterData.verifyIfScreenIsDisplayed(firstScreenTitle);
        masterData.verifyIfRoleIsShown(firstRoleName);
        masterData.verifyIfRoleIsShown(secondRoleName);
        masterData.verifyIfSelectAtLeastOneRoleMessageIsShown();
        masterData.selectRoleCheckbox(firstRoleName);
        masterData.verifyIfInstallationContactRoleCannotBeEditedMessageIsShown(true);
        masterData.selectRoleCheckbox(firstRoleName);
        masterData.selectRoleCheckbox(secondRoleName);
        masterData.verifyIfInstallationContactRoleCannotBeEditedMessageIsShown(false);
        masterData.clickWeiter();
        masterData.verifyIfScreenIsDisplayed(secondScreenTitle);
        masterData.verifyIfNewCreatedContactIsShown(contactVorname, contactNachname);
        masterData.verifyContactRole(contactVorname, contactNachname, secondRoleName);
        masterData.documentsAreRequired(false);
        masterData.verifyChangesAreMadeSuccessfully();
        masterData.closeMasterDataOmniscript();
        masterData.verifyTypFieldValue("Bevollmächtigter oder Ansprechpartner Installation");

        masterData.gotoCaseAktion();
        masterData.verifyIfEmailIsShownInAktion(true);
        masterData.openLastMasterDataReportFromAktion();
        masterData.verifyIfBenachrichtigungSchickenIsChecked(true);
        masterData.verifyMasterDataReportFieldValue("Kontakt", contactFullName);
        masterData.verifyMasterDataReportFieldValue("Änderungstyp", "Kontaktrollen");
        masterData.verifyMasterDataReportFieldValue("Dokumente erforderlich?", "Nicht erforderlich");
        masterData.gotoVerwandtTabMDataReport();
        masterData.verifyMasterDataReportRelatedFieldChange("Geburtsdatum", contactGeburtsdatum);
        masterData.verifyMasterDataReportRelatedFieldChange("E-Mail", contactEmail);
        masterData.verifyMasterDataReportRelatedFieldChange("Vorname", contactVorname);
        masterData.verifyMasterDataReportRelatedFieldChange("Nachname", contactNachname);
        masterData.verifyMasterDataReportRelatedFieldChange("Mobiltelefon", contactPhoneMobile);
        masterData.verifyMasterDataReportRelatedFieldChange("Anrede", contactAnrede);
        headerPage.closeSubTab();
        masterData.gotoCaseDetailsFromAktion();

        caseViewPage.openAccountOfCase();
        search.closePossibleContactsAccountPopUp();

        accountViewPage.openContactfromAccountView(contactFullName);
        contactViewPage.verifyContactFieldValue("Name", contactAnrede + " " + contactFullName);
        contactViewPage.verifyContactFieldValue("Titel", contactTitel);
        contactViewPage.verifyContactFieldValue("ISP", accountISP);
        contactViewPage.verifyContactFieldValue("Geburtsdatum", contactGeburtsdatum);
        contactViewPage.verifyContactFieldValue("Telefonnummer", contactPhoneMobile);
        contactViewPage.verifyContactFieldValue("Mobiltelefon", contactPhoneMobile);
        contactViewPage.verifyContactFieldValue("E-Mail", contactEmail);

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
        caseData1.setAnrede(contactAnrede);
        caseData1.setTitel(contactTitel);
        caseData1.setVorname(contactVorname);
        caseData1.setNachname(contactNachname); // later to be removed
        caseData1.setGeburtsdatum(contactGeburtsdatum);
        caseData1.setEmail(contactEmail);
        caseData1.setTelefonnummer(contactPhoneMobile);
        caseData1.setMobiltelefon(contactPhoneMobile);
        caseDataContact.add(caseData1);

        caseData3.setNachname(contactNachname);
        caseData3.setMobiltelefon(contactPhoneMobile);
        tLastName.add(caseData3);
    }
}
