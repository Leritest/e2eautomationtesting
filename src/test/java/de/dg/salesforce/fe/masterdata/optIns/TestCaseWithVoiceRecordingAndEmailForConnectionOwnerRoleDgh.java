package de.dg.salesforce.fe.masterdata.optIns;

import automation.de.dg.salesforce.enumation.AppList;
import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.masterdata.MasterData;
import automation.testbase.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * <b>TEST CASE</b> [MasterData/OptIns]: Test With Voice Recording And Email For Connection Owner Role
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseWithVoiceRecordingAndEmailForConnectionOwnerRoleDgh extends Page {
    Random rand = new Random();
    String accountName;
    String contactEmail;
    String randomNumber = String.valueOf(rand.nextInt(1000));
    String contactName = "Aleksandar StojanovConnectionOwner";

    /**
     * <b>[Test Method]</b> - Test With Voice Recording and Email for Connection Owner Role<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tests opt ins with voice recording and email for connection owner role<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Log in as Aleksandar Stojanov Test<br>
     * 2. Open a case<br>
     * 3. Launch Master Data Omniscript
     * 4. Select/Deselect Opt Ins
     * 5. Close Master Data Omniscript
     * 6. Verify the changes
     */
    @Test(description = "OPT-5498")
    public void tCWithVoiceRecordingAndEmailForConnectionOwnerRoleDgh() {

        LoginPage loginPage = new LoginPage();
        HeaderPage headerPage = new HeaderPage();
        MasterData masterData = new MasterData();
        Search search = new Search();

        String logInAsUser = "Aleksandar Stojanov Test";
        String caseNumber = "00023951";
        accountName = "AleksandarA" + randomNumber;
        contactEmail = contactName + "@asd.com";

        loginPage.login(UserRoles.AUTO_ADMIN);
        search.closePossibleContactsAccountPopUp();

        headerPage.loginWithInAppLogInFunctionality(logInAsUser);
        headerPage.closeAllTabs();

        headerPage.clickNavigationMenu(AppList.CASES_APP);
        search.searchCase(caseNumber);

        // Launch Master Data Omni script
        masterData.launchMasterDataOmniscript();
        masterData.verifyIfMasterDataCategorisAreShown();
        masterData.selectMasterDataCategory("Werbeeinstellungen");
        masterData.clickWeiter();
        masterData.verifyIfWerbeeinstellungenOptInsAreShown();
        masterData.verifyIfVoiceRecordingOptionIsShown();
        masterData.clickWeiter();
        masterData.verifyIfVoiceRecordingErrorIsShown();

        masterData.selectVoiceRecordingOption("Sprachaufzeichnung durchgeführt");
        masterData.uncheckAllWerbeeinstellungenOptIns();
        masterData.selectWerbeeinstellungenOptIn("Telefon Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();

        masterData.clickZuruck();

        //Telefon check
        masterData.selectWerbeeinstellungenOptIn("Telefon Opt-In");//uncheck
        masterData.selectWerbeeinstellungenOptIn("E-Mail Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.clickZuruck();

        //E-Mail check
        masterData.selectWerbeeinstellungenOptIn("E-Mail Opt-In");//uncheck
        masterData.selectWerbeeinstellungenOptIn("Verkehrsdaten Opt-In");
        masterData.clickWeiter();
        masterData.verifyIfBestatigungAutomatischIsSelected();
        masterData.clickZuruck();

        //Verkehrsdaten check
        masterData.selectWerbeeinstellungenOptIn("Verkehrsdaten Opt-In");
        masterData.clickWeiter();

        //masterData.verifyIfBestatigungAutomatischIsSelected(true);//vrakja null, to be coded
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

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
