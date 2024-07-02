package automation.de.dg.salesforce.frontend.pages.cases;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * <b>PAGES :</b> [NewCaseFormPage]: New Case Form Page
 */
public class NewCaseFormPage extends Page {

	JavascriptExecutor je = (JavascriptExecutor) driver;
	By subjectField = By.xpath("//input[@name=\"Subject\"]");
	By descriptionField = By.xpath("//label[text()='Description']//parent::lightning-textarea//textarea");
	By contactNameField = By.xpath("//input[@placeholder=\"Search Contacts...\"]");
	By contactResult = By.xpath("//span[text()='New Contact']//ancestor::lightning-base-combobox-item//preceding-sibling::lightning-base-combobox-item");

	/**
	 * <b>[Method]</b> - Dropdown Option Picker<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality picks the specified option from a dropdown list<br>
	 * @param field String
	 * @param option String
	 */
	public void dropdownOptionPicker(String field, String option) {
		By caseOriginPicker = By.xpath("//label[text()='" + field + "']//parent::div//lightning-base-combobox");
		By origin = By.xpath("//*[@title='" + option + "']");
		click(caseOriginPicker);
		click(origin);
    }

	/**
	 * <b>[Method]</b> - Type subject<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality types a specific text in the subject field<br>
	 * @param subject String
	 */
	public void typeSubject(String subject) {
		WebElement element = driver.findElement(subjectField);
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		click(subjectField);
		type(subjectField, subject);
    }

	/**
	 * <b>[Method]</b> - Type Description<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality types a specific text in the description field<br>
	 * @param description String
	 */
	public void typeDescription(String description) {
		click(descriptionField);
		type(descriptionField, description);
    }

	/**
	 * <b>[Method]</b> - Type Contact Name<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality types a specific name in the contact name field<br>
	 * @param cimId String
	 */
	public void typeContactName(String cimId) {
		type(contactNameField, cimId);
		click(contactNameField);
    }

	/**
	 * <b>[Method]</b> - Click Contact Result<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on a contact result<br>
	 */
	public void clickContactResult() {
		waitForPageToLoad();
		click(contactResult);
    }
	
}
