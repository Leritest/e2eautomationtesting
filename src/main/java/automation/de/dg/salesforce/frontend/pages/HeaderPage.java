package automation.de.dg.salesforce.frontend.pages;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class HeaderPage extends Page {

	By appLauncher = By.xpath("//*[@class='slds-r5']");
	By appSearch = By.xpath("//input[@placeholder='Search apps and items...' or @placeholder='Anwendungen u. Elemente durchs.']");
	By contactsTab = By.xpath("//a[@title='Contacts']");
	By pageTitle = By.xpath("//*[@class='appName slds-context-bar__label-action slds-context-bar__app-name']//span");
	By searchPage = By.xpath("//p[@class='slds-truncate']");
	By casesTab = By.xpath("//a[@title='Cases']");
	By gearIcon = By.xpath("//lightning-icon[@class=\"slds-icon-utility-setup slds-button__icon slds-global-header__icon forceIcon slds-icon_container\"]");
	By setupOption = By.id("related_setup_app_home");
	By setupSearchBar = By.xpath("//input[@title=\"Search Setup\"]");
	By loginButton = By.xpath("//input[@value=\" Login \"]");
	By splitView = By.xpath("//button[contains(@class, 'open')]");

	/**
	 * <b>[Method]</b> - Open app launcher<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality opens the app launcher by clicking on the waffle icon<br>
	 */
	public void clickAppLauncher() {
		waitForPageToLoad();
		wait.until(ExpectedConditions.visibilityOfElementLocated(appLauncher));
		click(appLauncher);

	}

	/**
	 * <b>[Method]</b> - Search app launcher<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality searches for an app using the search bar in the app launcher<br>
	 * @param searchString String
	 */
	public void searchAppLauncher(String searchString) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(appSearch));
		type(appSearch, searchString);
	}

	/**
	 * <b>[Method]</b> - Open searched app<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality opens the searched app in the app launcher<br>
	 * @param searchStr String
	 */
	public void clickSearchedResult(String searchStr) {
		By searchedApp = By.xpath("//a[@data-label='" + searchStr + "']//p[@class='slds-truncate']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchedApp));
		click(searchedApp);
	}

	/**
	 * <b>[Method]</b> - Close tab<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality closes specified tab<br>
	 * @param tabTitle String
	 */
	public void closeTab(String tabTitle) {
		By tab = By.xpath("//*[@title='Close " + tabTitle + "']");
		click(tab);
	}

	/**
	 * <b>[Method]</b> - Close all tabs<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality closes all of the opened tabs<br>
	 */
	public void closeAllTabs(){
		waitForPageToLoad();
		List<WebElement> tabs = driver.findElements(By.xpath("//div[2]/div/div/ul[2]/li[*]/div[2]/button/lightning-primitive-icon"));
		String tabXpath = "//div[2]/div/div/ul[2]/li[2]/div[2]/button/lightning-primitive-icon";
		int i = tabs.size();

		while (i > 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			driver.findElement(By.xpath(tabXpath)).click();
			i--;
		}

	}

	/**
	 * <b>[Method]</b> - Click Contacts tab<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks and navigates to the Contact tab in the tab menu<br>
	 */
	public void clickContactsTab() {
		click(contactsTab);
	}

	/**
	 * <b>[Method]</b> - Click Cases tab<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks and navigates to the Cases tab in the tab menu<br>
	 */
	public void clickCasesTab() {
		click(casesTab);
	}


	/**
	 * <b>[Method]</b> - Close split view<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on the split view so it closes<br>
	 */
	public void closeSplitView() {
		boolean splitViewOpen = !driver.findElements(splitView).isEmpty();

		if (splitViewOpen) {
			wait.until(ExpectedConditions.elementToBeClickable(splitView));
			click(splitView);
			waitForPageToLoad();
		}
	}

	/**
	 * <b>[Method]</b> - Check Home Page<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality checks which page is set as Home Page<br>
	 * @param homepage String
	 */
	public void checkHomepage(String homepage) {
		String title = driver.findElement(pageTitle).getText();
		if (!title.equals(homepage)) {
			click(appLauncher);
			wait.until(ExpectedConditions.visibilityOfElementLocated(appSearch));
			type(appSearch, homepage);
			wait.until(ExpectedConditions.elementToBeClickable(searchPage));
			click(searchPage);
		}
	}

	/**
	 * <b>[Method]</b> - Go to Setup<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality navigates to Setup from Home Page<br>
	 */
	public void gotoSetup() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.elementToBeClickable(gearIcon));
		click(gearIcon);
		click(setupOption);
	}

	/**
	 * <b>[Method]</b> - Login as specific user<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality navigates to setup to login as different user<br>
	 * @param userName String
	 */
	public void loginAsUser(String userName){
		gotoSetup();
		waitForPageToLoad();
		List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
		By searchedUserLogin = By.xpath("//span[@title=\"" + userName + "\"]");
		type(setupSearchBar, userName);
		waitForPageToLoad();
		click(searchedUserLogin);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		click(loginButton);
	}

}
