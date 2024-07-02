package de.dg.salesforce.fe.cases;

import automation.de.dg.salesforce.enumation.UserRoles;
import automation.de.dg.salesforce.frontend.pages.GenericRecordsPage;
import automation.de.dg.salesforce.frontend.pages.LoginPage;
import automation.de.dg.salesforce.frontend.pages.account.AccountNamePage;
import automation.de.dg.salesforce.frontend.pages.cases.CasePage;
import automation.de.dg.salesforce.frontend.pages.cases.NewCaseFormPage;
import automation.de.dg.salesforce.frontend.pages.cases.NewCasePage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.HeaderPage;
import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.frontend.pages.contacts.ContactPage;
import automation.de.dg.salesforce.frontend.pages.contacts.NewContactFormPage;

import automation.testbase.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseCloseParentCaseAutomatically extends Page {

	@Test
	public void tcCloseParentCaseAutomatically() {

		// initialize classes
		LoginPage loginPage = new LoginPage();
		HeaderPage headerPage = new HeaderPage();
		GenericRecordsPage genericRecordsPage = new GenericRecordsPage();
		NewCasePage newCasePage = new NewCasePage();
		NewCaseFormPage newCaseFormPage = new NewCaseFormPage();
		NewContactFormPage newContactFormPage = new NewContactFormPage();
		AccountNamePage accountNamePage = new AccountNamePage();
//		AccountNamePage accountNamePage = new AccountNamePage();
//		ContactResultsPage contactResultsPage = new ContactResultsPage();
		CasePage casePage = new CasePage();
//		ChildCasePage childCasePage = new ChildCasePage();
		ContactPage contactPage = new ContactPage();
		Search search = new Search();

		String logInAsUser = "Aleksandar Stojanov Test";
		String caseOrigin = "Telefon";
		String communicationDirection = "Inbound";
		String category = "Baustatus";
		String type = "Baufortschritt";
		String cimCustomerId = "3176578";
		String cimBillingIndividualId = "945744";
		String subject = "Auto OPT-1286";
		String description = "Auto OPT-1286";
		String department = "Frage zur Hausbegehung";
		String code = "BS/C1001";
		String childStatus = "Solved";
		String appOne = "Service Console";
		String appTwo = "Cases";

		loginPage.login(UserRoles.AUTO_ADMIN);
		search.closePossibleContactsAccountPopUp();
		headerPage.closeSplitView();
		headerPage.checkHomepage("Service Console");
		headerPage.closeAllTabs();

		headerPage.checkHomepage(appOne);
		headerPage.loginWithInAppLogInFunctionality(logInAsUser);
		search.closePossibleContactsAccountPopUp();
		headerPage.closeAllTabs();

		headerPage.clickAppLauncher();
		headerPage.searchAppLauncher(appTwo);
		headerPage.clickSearchedResult(appTwo);
		headerPage.clickCasesTab();

		genericRecordsPage.clickNewButton();

		headerPage.closeSplitView();

		newCasePage.clickCustomerCaseOption();
		newCasePage.clickNextButton();

		newCaseFormPage.dropdownOptionPicker("Case Origin", caseOrigin);
		newCaseFormPage.dropdownOptionPicker("Communication-Direction", communicationDirection);
		newCaseFormPage.dropdownOptionPicker("Category", category);
		newCaseFormPage.dropdownOptionPicker("Typ", type);
		newCaseFormPage.typeSubject(subject);
		newCaseFormPage.typeDescription(description);

		newContactFormPage.populateAccountName(cimCustomerId);
		newContactFormPage.clickAccountResult();

		accountNamePage.clickAccountName();

		newCaseFormPage.typeContactName(cimBillingIndividualId);
		newCaseFormPage.clickContactResult();

		accountNamePage.clickAccountName();

		newContactFormPage.clickSaveButton();

		contactPage.verifyContactCreated();

		//String parentCaseNumber = toastMessage.replace("Case \"","").replace("\" was created.","");

		casePage.clickSendOTRSButton();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		casePage.clickDepartment(department);
//		casePage.clickWeiterButton();
//
//
//		casePage.clickTicketErstellenButton();
//
//
//		casePage.clickReturnLink();
//
//
//		casePage.refreshPage();
//
//
//		String numberOfRelatedCases = casePage.getNumberOfRelatedCases();
//		Assert.assertTrue(!numberOfRelatedCases.equals("0"));
//
//		casePage.clickChildCaseLink();
//
//
//		childCasePage.clickCodePencil();
//		childCasePage.clickCode();
//		childCasePage.typeCode(code);
//
//
//		childCasePage.clickStatusPicker();
//		childCasePage.clickStatus(childStatus);
//		childCasePage.clickSaveButton();
//
//
//		childCasePage.refreshPage();
//
//
//		String childCaseStatus = childCasePage.getChildStatus();
//		Assert.assertTrue(childCaseStatus.equals("Closed"));
//
//		childCasePage.clickParentCaseLink();
//
//
//		String parentCaseStatus = casePage.getParentStatus();
//		Assert.assertTrue(parentCaseStatus.equals("Closed"));

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
