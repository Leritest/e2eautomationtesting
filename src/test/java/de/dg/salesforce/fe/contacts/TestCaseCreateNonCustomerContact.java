package de.dg.salesforce.fe.contacts;

import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.GenericRecordsPage;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.ProfileSettings;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactPage;
import automation.de.dg.salesforce.frontend.pages.contacts.NewContactFormPage;
import automation.de.dg.salesforce.frontend.pages.account.AccountNamePage;
import automation.testbase.Page;
import de.dg.salesforce.fe.basetest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>TEST CASE</b> [contacts]: Create non Customer Contact
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseCreateNonCustomerContact extends Page {

	/**
	 * <b>[Test Method]</b> - Create Contact<br>
	 * <br>
	 * <i>Test Method functionality:</i><br>
	 * This functionality creates a non customer contact.<br>
	 * <i>Steps of this scenario:</i><br>
	 * 1. Login to the Salesforce application<br>
	 * 2. Navigate to Contacts.<br>
	 * 3. Open new contact creation window
	 * 4. Populate the needed fields
	 * 5. Create the contact
	 * 6. Verif if contact is created
	 */
	@Test
	public void tcCreateNonCustomerContact() {
		// initialize classes
		LoginPage loginPage = new LoginPage();
		HeaderPage headerPage = new HeaderPage();
		GenericRecordsPage genericRecordsPage = new GenericRecordsPage();
		NewContactFormPage newContactFromPage = new NewContactFormPage();
		AccountNamePage accountNamePage = new AccountNamePage();
		ContactPage contactPage = new ContactPage();
		ProfileSettings profileSettings = new ProfileSettings();

		//Inputs
		String salutation = "Herr";
		String firstName = "Auto";
		String lastName = "NonCustomer";
		String email = "j.bourke@extern.deutsche-glasfaser.de";
		String phone = "+49 1500 5000001";
		String mobile = "+49 1500 6000001";
		String cimCustomerId = "3176483";
		String mailingStreet = "Hauptstraße";
		String mailingZip = "50226";
		String mailingCity = "Frechen";
		String mailingCountry = "Deutschland";
		String appOne = "Service Console";
		String appTwo = "Contacts";

		loginPage.login(UserRoles.SALES_OPERATIONS);

		headerPage.checkHomepage(appOne);
		headerPage.closeAllTabs();
		headerPage.clickAppLauncher();
		headerPage.searchAppLauncher(appTwo);
		headerPage.clickSearchedResult(appTwo);
		headerPage.clickContactsTab();

		genericRecordsPage.clickNewButton();

		newContactFromPage.selectRadioButton("Non-Customer");
		newContactFromPage.clickButton("Next");
		newContactFromPage.selectSalutation(salutation);
		newContactFromPage.populateField("First Name", firstName);
		newContactFromPage.populateField("Last Name", lastName);
		newContactFromPage.populateAccountName(cimCustomerId);
		newContactFromPage.clickAccountResult();

		accountNamePage.clickAccountName();

		newContactFromPage.populateField("Email", email);
		newContactFromPage.populateField("Phone", phone);
		newContactFromPage.populateField("Mobile", mobile);
		newContactFromPage.typeMailingStreet(mailingStreet);
		newContactFromPage.populateField("Mailing Zip/Postal Code", mailingZip);
		newContactFromPage.populateField("Mailing City", mailingCity);
		newContactFromPage.populateField("Mailing Country", mailingCountry);
		newContactFromPage.clickSaveButton();

		//Verification
		contactPage.verifyContactCreated();
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
