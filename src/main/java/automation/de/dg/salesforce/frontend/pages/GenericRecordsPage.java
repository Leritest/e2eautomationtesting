package automation.de.dg.salesforce.frontend.pages;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * <b>PAGES :</b> [GenericRecordsPage]: Generic Records Page
 */
public class GenericRecordsPage extends Page {
	By newButton = By.xpath("//*[@title='New' or @title='Neu']/div");
	By listViewSelector = By.xpath("//*[@title='Select a List View: Contacts']");
	By searchList = By.xpath("//input[@placeholder='Search this list...']");

	/**
	 * <b>[Method]</b> - Click new button<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on new button for creating new contact<br>
	 */
	public void clickNewButton() {
		click(newButton);
	}

	/**
	 * <b>[Method]</b> - Click view selector<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the view selector button<br>
	 */
	public void clickListViewSelector() {
		click(listViewSelector);
	}

	/**
	 * <b>[Method]</b> - Select view <br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality selects the specified contacts view<br>
	 * @param view String
	 */
	public void selectContactsView(String view) {
		By viewOption = By.xpath("//*[text()='" + view + "']");
		click(viewOption);
	}

	/**
	 * <b>[Method]</b> - Search contacts<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality searches for specific contact<br>
	 * @param searchString String
	 */
	public void searchContact(String searchString) {
		click(searchList);
		type(searchList, searchString);
		driver.findElement(searchList).sendKeys(Keys.RETURN);
	}

	/**
	 * <b>[Method]</b> - Click list item<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality opens the first item in the list<br>
	 * @param recordName String
	 */
	public void clickListItem(String recordName) {
		waitForPageToLoad();
		By record = By.xpath("(//a[text()='" + recordName + "'])[1]");
		click(record);
	}
}
