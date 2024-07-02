package automation.de.dg.salesforce.frontend.pages.cases;

import automation.de.dg.salesforce.frontend.pages.common.navigation.Search;
import automation.de.dg.salesforce.utils.CaseData;
import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * <b>PAGES :</b> [NewCasePage]: New Case Page
 */
public class NewCasePage extends Page {
	Search search = new Search();
	By customerCaseOption = By.xpath("//*[text()='Customer Case']");
	By nextButton = By.xpath("//*[text()='Next']");
	By cancelContactsAccountButton = By.xpath("//button[@title='Abbrechen']");
	By casesLink = By.xpath("//span[text()='Cases']/parent::a");
	By newCaseButton = By.xpath("//button[@name='NewCase']");
	By newCaseSaveButton = By.xpath("  //button[@name='SaveEdit']");
	By saveButton = By.xpath("//button[text()='Save' or text()='Speichern']");
	By newButton = By.xpath("//div[@title=\"Neu\"]");
	By newCaseArrow = By.xpath("//span[@title=\"Cases\"]//ancestor::div[@class=\"slds-media slds-media--center slds-has-flexi-truncate\"]//button[@part=\"button button-icon\"]");
	By neuCaseButton = By.xpath("//span[@class=\"slds-truncate\" and text()='Neu']");

	/**
	 * <b>[Method]</b> - Click Customer Case Option<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on customer case option<br>
	 */
	public void clickCustomerCaseOption() {
		Log.info("clickCustomerCaseOption method executing");

		click(customerCaseOption);
    }

	/**
	 * <b>[Method]</b> - Click Next Button<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the next button<br>
	 */
	public void clickNextButton() {
		Log.info("clickNextButton method executing");

		click(nextButton);
    }

	/**
	 * <b>[Method]</b> - Click New Case<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the next button<br>
	 */
	public void createNewCase() {
		Log.info("createNewCase method executing");

		click(newButton);
	}

	/**
	 * <b>[Method]</b> - Create New Case from Account<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the new button for creating new case from account<br>
	 */
	public void createNewCaseFromAccount() {
		Log.info("createNewCaseFromAccount method executing");

		waitForPageToLoad();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		WebElement element = driver.findElement(newCaseArrow);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
		click(newCaseArrow);

		click(neuCaseButton);
		waitForPageToLoad();
	}

	/**
	 * <b>[Method]</b> - New Case is Created with the Following Data<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality creates new case with the following data<br>
	 * @param caseDataList List
	 */
	public void aNewCaseIsCreatedWithTheFollowingData(List<CaseData> caseDataList, String typeOfContact) {
		Log.info("aNewCaseIsCreatedWithTheFollowingData method executing");

		Search search = new Search();
		CasePage casePage = new CasePage();

		for (CaseData caseData : caseDataList) {
			//casePage.selectStatusCase(caseData.getStatus());
			casePage.selectOriginCase(caseData.getCaseOrigin());
			//casePage.selectCommunicationDirection(caseData.getCommunicationDirection());
			casePage.selectCategory(caseData.getCategory());
			if(caseData.getContactName() != null){
				casePage.searchContact(caseData.getContactName());
				casePage.selectContact(caseData.getContactName(), typeOfContact);
			}
		}
		saveTheCase();
	}

	/**
	 * <b>[Method]</b> - Create Case from Account<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality creates new case from the Account<br>
	 */
	public void createCaseFromAccount() {
		Log.info("createCaseFromAccount method executing");

		waitForPageToLoad();
		search.closePossibleContactsAccountPopUp();
		//wait.waitForElementToBeDisplayed(CasesElements.NEW_CASE_FROM_ACCOUNT_ARROW);
		click(casesLink);
		waitForPageToLoad();
		click(newCaseButton);
		waitForPageToLoad();
		wait.until(ExpectedConditions.presenceOfElementLocated(newCaseSaveButton));

	}

	/**
	 * <b>[Method]</b> - Save the Case<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality saves the case by clicking on the save button<br>
	 */
	public void saveTheCase() {
		Log.info("saveTheCase method executing");

		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		click(saveButton);
	}
    
}
