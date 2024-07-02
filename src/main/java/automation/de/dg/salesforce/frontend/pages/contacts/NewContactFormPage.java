package automation.de.dg.salesforce.frontend.pages.contacts;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * <b>PAGES :</b> [NewContactFormPage]: New Contact Form Page
 */
public class NewContactFormPage extends Page {

	By salutationPicker = By.xpath("//*[text()='Salutation']/../div");
	By accountNameField = By.xpath("//input[@placeholder=\"Search Accounts...\"]");
	By accountResult = By.xpath("//span[starts-with(@title, 'Show All Results for')]");
	By mailingStreetField = By.xpath("//textarea[@autocomplete=\"street-address\"]");
	By saveButton = By.xpath("//button[text()='Save']");

	/**
	 * <b>[Method]</b> - Select radio button<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects a specific radio button<br>
	 * @param radioButtonName String
	 */
	public void selectRadioButton(String radioButtonName) {
		By radioButton = By.xpath("//span[text()='" + radioButtonName + "']//parent::label//span");
		click(radioButton);
	}

	/**
	 * <b>[Method]</b> - Click button<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on a specified button<br>
	 * @param buttonName String
	 */
	public void clickButton(String buttonName) {
		By button = By.xpath("//span[@class=\" label bBody\" and text()='" + buttonName + "']");
		click(button);
	}

	/**
	 * <b>[Method]</b> - Select Salutation<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the specified salutation<br>
	 * @param salutation String
	 */
	public void selectSalutation(String salutation) {
		waitForPageToLoad();
		By salutationSelection = By.xpath("//*[text()='" + salutation + "']");
		click(salutationPicker);
		click(salutationSelection);
    }

	/**
	 * <b>[Method]</b> - Populate field<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality populates the specified filed with a value<br>
	 * @param fieldName String
	 * @param value String
	 */
	public void populateField(String fieldName, String value) {
		By field;
		if(fieldName.equals("Email") || fieldName.equals("Phone") || fieldName.equals("Mobile") || fieldName.equals("Mailing Zip/Postal Code")
				|| fieldName.equals("Mailing City") || fieldName.equals("Mailing Country")){
			if(fieldName.isEmpty()){
				field = By.xpath("//label[text()='" + fieldName + "']//parent::div//input");
				type(field, value);
			} else {
				field = By.xpath("//label[text()='" + fieldName + "']//parent::div//input");
				driver.findElement(field).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
				type(field, value);
			}
		} else {
			if(fieldName.isEmpty()){
				field = By.xpath("//input[@placeholder='" + fieldName + "']");
				type(field, value);
			} else{
				field = By.xpath("//input[@placeholder='" + fieldName + "']");
				driver.findElement(field).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
				type(field, value);
			}

		}
    }

	/**
	 * <b>[Method]</b> - Populate Account Name<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality populates the account name field with a specified value<br>
	 * @param cimId String
	 */
	public void populateAccountName(String cimId) {
		type(accountNameField, cimId);
    }

	/**
	 * <b>[Method]</b> - Click Account Result<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality click on the searched account result<br>
	 */
	public void clickAccountResult() {
		click(accountResult);
    }

	/**
	 * <b>[Method]</b> - Type Mailing Street<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality types a value in the Mailing Street field<br>
	 * @param mailingStreet String
	 */
	public void typeMailingStreet(String mailingStreet) {
		type(mailingStreetField, mailingStreet);
    }

	/**
	 * <b>[Method]</b> - Click Save<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the save button<br>
	 */
	public void clickSaveButton() {
		click(saveButton);
    }
	
}
