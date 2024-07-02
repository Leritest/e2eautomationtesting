package automation.de.dg.salesforce.frontend.pages.contacts;

import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * <b>PAGES :</b> [ContactPage]: Contact Page
 */
public class ContactPage extends Page {

	By toastMessage = By.cssSelector(".toastMessage");
	By editContact = By.xpath("//button[@name=\"Edit\"]");
	By editNamePencil = By.xpath("//*[@title='Edit Name']");
	By newButton = By.xpath("//div[@title=\"Neu\" or @title=\"New\"]");

	/**
	 * <b>[Method]</b> - Edit contact<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on edit button, so the edit contact form opens<br>
	 */
	public void clickEditContact() {
		waitForPageToLoad();
		click(editContact);
	}

	/**
	 * <b>[Method]</b> - Edit name Pencil<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on edit pencil icon next to the Account name<br>
	 */
	public void clickEditNamePencil() {
		waitForPageToLoad();
		click(editNamePencil);
	}

	//-------------------------------VERIFICATIONS--------------------------------------------------------------------\\

	/**
	 * <b>[Method]</b> - Verify Contact created<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality verifies if the contact is created successfully<br>
	 */
	public void verifyContactCreated() {
		String toastMessageText = getText(toastMessage);
		Assert.assertTrue(toastMessageText.contains("was created"));
		ExtentListeners.test.log(Status.PASS, "Verification OK - Contact was created successfully");
	}

	/**
	 * <b>[Method]</b> - Verify Page Content<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality verifies if a specific text appears on the page<br>
	 * @param text String
	 */
	public void verifyPageContent(String text) {
		boolean displayed = !driver.findElements(By.xpath("//*[text()='" + text + "']")).isEmpty();
		Assert.assertTrue(displayed);
		ExtentListeners.test.log(Status.PASS, "Verification OK - Contract is edited successfully, the new contract name is: " + text);
	}

	/**
	 * <b>[Method]</b> - Click New Button<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the new button to start creating new contact<br>
	 */
	public void clickNewContact() {
		waitForPageToLoad();
		click(newButton);
	}
}
