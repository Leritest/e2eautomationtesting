package automation.de.dg.salesforce.frontend.pages.account;

import automation.testbase.Page;
import org.openqa.selenium.By;

/**
 * <b>PAGES :</b> [AccountsPage]: Accounts Page
 */
public class AccountsPage extends Page {
	By newButton = By.xpath("//div[@title=\"Neu\" or @title=\"New\"]");

	/**
	 * <b>[Method]</b> - Click New Button<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the new button to start creating new account<br>
	 */
	public void clickNewAccount() {
		waitForPageToLoad();
		click(newButton);
    }
}
