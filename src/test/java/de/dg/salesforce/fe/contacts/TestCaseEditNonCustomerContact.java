package de.dg.salesforce.fe.contacts;

import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.GenericRecordsPage;
import automation.de.dg.salesforce.frontend.pages.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactPage;
import automation.de.dg.salesforce.frontend.pages.contacts.NewContactFormPage;
import automation.endpoints.CommonTest;
import automation.endpoints.GetApiAuthorization;
import automation.testbase.Page;
import de.dg.salesforce.fe.basetest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>TEST CASE</b> [contacts]: Edit non Customer Contact
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseEditNonCustomerContact extends Page {

	/**
	 * <b>[Test Method]</b> - Edit Contact<br>
	 * <br>
	 * <i>Test Method functionality:</i><br>
	 * This functionality edits a non customer contact.<br>
	 * <i>Steps of this scenario:</i><br>
	 * 1. Login to the Salesforce application<br>
	 * 2. Navigate to Contacts.<br>
	 * 3. Open a specific contact
	 * 4. Open the edit view for the contact
	 * 5. Edit the necessary fields
	 * 6. Save the edits
	 * 7. Verify if the edits are saved successfully
	 */
	@Test
	public void tcEditNonCustomerContact() {
		// initialize classes
		LoginPage loginPage = new LoginPage();
		HeaderPage headerPage = new HeaderPage();
		GenericRecordsPage genericRecordsPage = new GenericRecordsPage();
		ContactPage contactPage = new ContactPage();
		NewContactFormPage newContactFormPage = new NewContactFormPage();
		GetApiAuthorization getApiAuthorization = new GetApiAuthorization();
		CommonTest commonTest = new CommonTest();

		// Helpers
		//SalesforceRequests salesforceRequests = new SalesforceRequests();

		String salutationUpdate = "Frau";
		String firstName = "Auto";
		String lastName = "NonCustomer";
		String fullName = firstName + " " + lastName;
		String firstNameUpdate = "Auto_Edit";
		String lastNameUpdate = "NonCustomer_Edit";
		String fullNameUpdate = firstNameUpdate + " " + lastNameUpdate;
		String emailUpdate = "j.bourke_edit@extern.deutsche-glasfaser.de";
		String phoneUpdate = "+49 1500 5000002";
		String mobileUpdate = "+49 1500 6000002";
		String mailingStreetUpdate = "Stalbergweg";
		String mailingZipUpdate = "5913 BW";
		String mailingCityUpdate = "Venlo";
		String mailingCountryUpdate = "Niederlande";
		String appOne = "Service Console";
		String appTwo = "Contacts";


		loginPage.login(UserRoles.SALES_OPERATIONS);

		headerPage.clickAppLauncher();
		headerPage.searchAppLauncher(appOne);
		headerPage.clickSearchedResult(appOne);
		headerPage.closeAllTabs();
		headerPage.clickAppLauncher();
		headerPage.searchAppLauncher(appTwo);
		headerPage.clickSearchedResult(appTwo);
		headerPage.clickContactsTab();
		headerPage.closeAllTabs();

		genericRecordsPage.clickListViewSelector();
		genericRecordsPage.selectContactsView("Recently Viewed Contacts");
		genericRecordsPage.searchContact(fullName);
		genericRecordsPage.clickListItem(fullName);

		headerPage.closeSplitView();

		contactPage.clickEditContact();

		newContactFormPage.selectSalutation(salutationUpdate);

		newContactFormPage.populateField("First Name", firstNameUpdate);
		newContactFormPage.populateField("Last Name", lastNameUpdate);
		newContactFormPage.populateField("Email", emailUpdate);
		newContactFormPage.populateField("Phone", phoneUpdate);
		newContactFormPage.populateField("Mobile", mobileUpdate);
		newContactFormPage.typeMailingStreet(mailingStreetUpdate);
		newContactFormPage.populateField("Mailing Zip/Postal Code", mailingZipUpdate);
		newContactFormPage.populateField("Mailing City", mailingCityUpdate);
		newContactFormPage.populateField("Mailing Country", mailingCountryUpdate);

		newContactFormPage.clickSaveButton();

		contactPage.verifyPageContent(salutationUpdate + " " + firstNameUpdate + " " + lastNameUpdate);

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

//
//		// Delete edited Contact
		//String accessToken = salesforceRequests.getAccessToken(adminUsername, adminPassword);
		//String asd = commonTest.getAuthorizedHeader().toString();
		//System.out.println("Ova e asd: " + asd);
		//String token = GetApiAuthorization.getTokenViaAPI(getTokenUrl(), getApiUsername(), getApiPassword(), RestAPIAuthConstants.SF_CLIENT_ID, RestAPIAuthConstants.SF_CLIENT_SECRET);
		//System.out.println("Ova e token: " + commonTest.getAuthorizedHeader());
		//commonTest.getAuthorizedHeader();
//		String query = "select+contact.id+from+contact+where+contact.name+=+" + "'" + fullNameUpdate + "'";
//		String recordId = salesforceRequests.getRecordId(accessToken, query);
//		Integer deletionStatus = salesforceRequests.deleteRecord(accessToken, "Contact", recordId);
//
//		Assert.assertTrue(deletionStatus == 204);

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
