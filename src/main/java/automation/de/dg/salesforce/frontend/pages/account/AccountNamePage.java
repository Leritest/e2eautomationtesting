package automation.de.dg.salesforce.frontend.pages.account;

import automation.testbase.Page;
import org.openqa.selenium.By;

/**
 * <b>PAGES :</b> [AccountNamePage]: Account Name Page
 */
public class AccountNamePage extends Page {
	By accountName = By.xpath("/html/body/div[4]/div[2]/div[3]/div[2]/div/div[2]/div/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td[1]/a");

	/**
	 * <b>[Method]</b> - Click Account Name<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the account name<br>
	 */
	public void clickAccountName() {
		waitForPageToLoad();
		click(accountName);
    }

	//-------------------------------VERIFICATIONS--------------------------------------------------------------------\\

}
