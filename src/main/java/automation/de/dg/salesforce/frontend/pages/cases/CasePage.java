package automation.de.dg.salesforce.frontend.pages.cases;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * <b>PAGES :</b> [CasePage]: Case Page
 */
public class CasePage extends Page {

	JavascriptExecutor je = (JavascriptExecutor) driver;
	By otrsButton = By.xpath("//*[text()='Send to OTRS']");
	By additionalInformation = By.xpath("//*[text()='Additional Information']");
	By statusDropdown = By.xpath("//*[text()='Status']/following-sibling::div/descendant::div[1]/descendant::button");
	By originCaseDropdown = By.xpath("//*[text()='Case Origin' or text()='Eingangskanal']/following-sibling::div/descendant::div[1]");
	By commDirectionDropdown = By.xpath("//*[text()='Communication-Direction' or text()='Kommunikationsrichtung']/following-sibling::div/descendant::div[1]");
	By categoryDropdown = By.xpath("//*[text()='Category' or text()='Kategorie']/following-sibling::div/descendant::div[1]");
	By contactInput = By.xpath("//input[@placeholder='Search Contacts...' or @placeholder='Kontakte durchsuchen...']");
	By caseSearchField = By.xpath("//input[@name=\"Case-search-input\"]");
	By firstElement = By.xpath("(//a[@data-refid=\"recordId\"])[1]");

	/**
	 * <b>[Method]</b> - Click on Send OTRS<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on send OTRS button in a case<br>
	 */
	public void clickSendOTRSButton() {
		waitForPageToLoad();
		WebElement additionalInfoSection = driver.findElement(additionalInformation);
		je.executeScript("arguments[0].scrollIntoView(true);", additionalInfoSection);
//		scrollIntoView(additionalInformationSection);
//		waitUntilElementIsClickableElement(sendOTRSButton, 10);
//		sendOTRSButton.click();
		click(otrsButton);
	}

	/**
	 * <b>[Method]</b> - Click on Department<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on department<br>
	 * @param department String
	 */
	public void clickDepartment(String department) {
		WebElement additionalInfoSection = driver.findElement(additionalInformation);
		je.executeScript("arguments[0].scrollIntoView(true);", additionalInfoSection);
		String xPath = "//*[contains(text(),'" + department + "')]";
		driver.findElement(By.xpath(xPath)).click();
	}

	/**
	 * <b>[Method]</b> - Select Status Case<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the status of the case<br>
	 * @param originCase String
	 */
	public void selectStatusCase(String originCase) {
		wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
		click(statusDropdown);
		click(statusDropdown);
		By originCaseElement = By.xpath("//span[@title='" + originCase + "']");
		wait.until(ExpectedConditions.elementToBeClickable(originCaseElement));
		click(originCaseElement);
	}

	/**
	 * <b>[Method]</b> - Click Origin Case<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the origin of the case<br>
	 * @param originCase String
	 */
	public void selectOriginCase(String originCase) {
		wait.until(ExpectedConditions.elementToBeClickable(originCaseDropdown));
		click(originCaseDropdown);
		By originCaseElement = By.xpath("//span[@title='" + originCase + "']");
		By originCaseElementDuplicate = By.xpath("(//span[@title='" + originCase + "'])[2]");
		By originCaseElementAlternative = By.xpath("(//span[@title='" + originCase + "'])[3]");
		if(isElementClickable(originCaseElement)) {
			wait.until(ExpectedConditions.elementToBeClickable(originCaseElement));
			click(originCaseElement);
		} else if(isElementClickable(originCaseElementDuplicate)) {
			wait.until(ExpectedConditions.elementToBeClickable(originCaseElementDuplicate));
			click(originCaseElementDuplicate);
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(originCaseElementAlternative));
			click(originCaseElementAlternative);
		}
	}

	/**
	 * <b>[Method]</b> - Select Communication Direction<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the communication direction of the case<br>
	 * @param communicationDirection String
	 */
	public void selectCommunicationDirection(String communicationDirection) {
		wait.until(ExpectedConditions.elementToBeClickable(commDirectionDropdown));
		click(commDirectionDropdown);
		By originCaseElement = By.xpath("//a[@title='" + communicationDirection + "']");
		wait.until(ExpectedConditions.elementToBeClickable(originCaseElement));
		click(originCaseElement);
	}

	/**
	 * <b>[Method]</b> - Select Category<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects case category<br>
	 * @param category String
	 */
	public void selectCategory(String category) {
		wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
		click(categoryDropdown);
		By categoryElement = By.xpath("//span[@title='" + category + "']");
		WebElement element = driver.findElement(categoryElement);
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(categoryElement));
		click(categoryElement);
	}

	/**
	 * <b>[Method]</b> - Search Contact<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality searches for a specific contact<br>
	 * @param contact String
	 */
	public void searchContact(String contact) {
		wait.until(ExpectedConditions.elementToBeClickable(contactInput));
		type(contactInput, contact);
	}

	/**
	 * <b>[Method]</b> - Select Contact<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects specific contact<br>
	 * @param contact String
	 */
	public void selectContact(String contact, String typeOfContact) {
		By resultElement = By.xpath("//lightning-base-combobox-formatted-text[@title='" + contact + "']");

		if(contact.matches(".*[a-zA-Z].*")){
			wait.until(ExpectedConditions.elementToBeClickable(resultElement));
			click(resultElement);
		} else {
			By alleErgebnise = By.xpath("//span[contains(@title, 'Alle Ergebnisse')]");
			By wantedContact = By.xpath("//a[contains(@title, '" + typeOfContact + "')]");

			wait.until(ExpectedConditions.elementToBeClickable(alleErgebnise));
			click(alleErgebnise);

			wait.until(ExpectedConditions.elementToBeClickable(wantedContact));
			click(wantedContact);
		}

	}

	/**
	 * <b>[Method]</b> - Select Anrede<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the wanted anrede value<br>
	 * @param anrede String
	 */
	public void selectAnrede(String anrede) {
		By anredeOption = By.xpath("//span[text()='" + anrede + "']");
		wait.until(ExpectedConditions.elementToBeClickable(anredeOption));
		click(anredeOption);
	}

	/**
	 * <b>[Method]</b> - Select Contact Titel<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the contact title<br>
	 * @param titel String
	 */
	public void selectContactTitel(String titel) {
		By titelOption = By.xpath("//span[text()='" + titel + "']");
		wait.until(ExpectedConditions.elementToBeClickable(titelOption));
		click(titelOption);
	}

	/**
	 * <b>[Method]</b> - Search and Open a Case<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality searches for a case and opens it<br>
	 * @param searchInput String
	 */
	public void searchAndOpenACase(String searchInput) {
		type(caseSearchField, searchInput);
		driver.findElement(caseSearchField).sendKeys(Keys.RETURN);
		waitForPageToLoad();
		click(firstElement);
	}
}
