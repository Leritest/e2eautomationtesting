package de.dg.salesforce.fe.contacts;

import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.GenericRecordsPage;
import automation.de.dg.salesforce.frontend.pages.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactPage;
import automation.de.dg.salesforce.frontend.pages.contacts.NewContactFormPage;
import automation.testbase.Page;
import de.dg.salesforce.fe.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>TEST CASE</b> [contacts]: Edit Standard Contact Error
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseEditStandardContactError extends Page {

	/**
	 * <b>[Test Method]</b> - Edit Contact<br>
	 * <br>
	 * <i>Test Method functionality:</i><br>
	 * This functionality edits a non customer contact.<br>
	 * <i>Steps of this scenario:</i><br>
	 * 1. Login to the Salesforce application<br>
	 * 2. Navigate to Contacts.<br>
	 * 3. Open the specific contact
	 * 4. Open the edit view for the contact
	 * 5. Edit the necessary fields
	 * 6. Save the edits
	 * 7. Verify if the edits are saved successfully
	 */
	@Test
	public void tcEditStandardContactError() {
		// initialize classes
		LoginPage loginPage = new LoginPage();
		HeaderPage headerPage = new HeaderPage();
		GenericRecordsPage genericRecordsPage = new GenericRecordsPage();
		ContactPage contactPage = new ContactPage();
		NewContactFormPage newContactFormPage = new NewContactFormPage();

		String serviceUsername = "auto.service1@deutsche-glasfaser.de.uat";
		String servicePassword = "password_temp!3";
		String firstName = "Auto";
		String lastName = "Standard";
		String fullName = firstName + " " + lastName;
		String firstNameUpdate = "Auto_Edit";
		String appOne = "Service Console";
		String appTwo = "Contacts";

		loginPage.login(UserRoles.SALES_OPERATIONS);

		//Thread.sleep(5000);

		headerPage.checkHomepage(appOne);
//		headerPage.clickAppLauncher();
//		headerPage.searchAppLauncher(appOne);
//		headerPage.clickSearchedResult(appOne);

		//Thread.sleep(5000);

		headerPage.closeAllTabs();
		headerPage.clickAppLauncher();
		headerPage.searchAppLauncher(appTwo);
		headerPage.clickSearchedResult(appTwo);

		//Thread.sleep(1000);

		headerPage.clickContactsTab();

		genericRecordsPage.clickListViewSelector();
		genericRecordsPage.selectContactsView("Recently Viewed Contacts");

		//Thread.sleep(2000);

		genericRecordsPage.searchContact(fullName);

		//Thread.sleep(2000);

		genericRecordsPage.clickListItem(fullName);

		//Thread.sleep(2000);

		headerPage.closeSplitView();

		contactPage.clickEditNamePencil();
		newContactFormPage.populateField("First Name", firstNameUpdate);
		newContactFormPage.clickSaveButton();



		//Assert.assertTrue(contactPage.checkPageContent("Bitte ändere die Kontaktdaten in CIM"));
		contactPage.verifyPageContent("Bitte ändere die Kontaktdaten in CIM");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

//		contactPage.clickErrorDialogClose();
//		contactPage.clickCancelButton();

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
