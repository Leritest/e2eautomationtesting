package automation.de.dg.salesforce.frontend.pages.common.navigation;

import automation.de.dg.salesforce.enumation.AppList;
import automation.testbase.Page;
import automation.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;
import static automation.de.dg.salesforce.enumation.AppList.CASES_APP;

/**
 * <b>PAGES :</b> [HeaderPage]: Header Page
 */
public class HeaderPage extends Page {

	Actions actions = new Actions(driver);
	By appLauncher = By.xpath("//*[@class='slds-r5']");
	By appSearch = By.xpath("//input[@placeholder='Search apps and items...' or @placeholder='Anwendungen u. Elemente durchs.']");
	By contactsTab = By.xpath("//a[@title='Contacts']");
	By pageTitle = By.xpath("//*[@class='appName slds-context-bar__label-action slds-context-bar__app-name']//span");
	By searchPage = By.xpath("//p[@class='slds-truncate']");
	By casesTab = By.xpath("//a[@title='Cases']");
	By gearIcon = By.xpath("//lightning-icon[@class=\"slds-icon-utility-setup slds-button__icon slds-global-header__icon forceIcon slds-icon_container\"]");
	By setupOption = By.id("related_setup_app_home");
	By setupSearchBar = By.xpath("//input[@title=\"Search Setup\"]");
	By loginButton = By.xpath("//input[@value=\" Login \" or @value = \"Anmelden\"]");
	By possibleTabsXpath = By.xpath("//div[contains(@class, 'tabContainer')"
			+ "]/div[@role='tablist']/descendant::ul[@role='presentation' and contains(@class, 'tabBarItems')]/li/div[2]");
	By navigationMenuArrow = By.xpath("//button[@type='button' and (@title='Show Navigation Menu' or @title='Navigationsmenü anzeigen')]");
	By searchField = By.xpath("//input[contains(@title,'Setup durchsuchen') or contains(@title, 'Search Setup')]");
	By logutInAppUser = By.xpath("//a[contains(@href, 'logout')]");
	By subTabElement = By.xpath("//button[contains(@title, 'Close') and @tabindex='-1']");
	By tabXpath = By.xpath("//div[2]/div/div/ul[2]/li[2]/div[2]/button/lightning-primitive-icon");
	By closeAllButton = By.xpath("//button[text()='Close All' or text()='Alle schließen']");
	By splitView = By.xpath("//button[contains(@class, 'open')]");
	By firstSubTab = By.xpath("//a[@role=\"tab\" and contains(@class, 'tabHeader') and @tabindex='-1']");
	By tabElement = By.xpath("//button[(contains(@title, 'schließen') or contains(@title, 'Close')) and @tabindex ='-1']");
	By xButtonSubTabElement = By.xpath("//div[contains(@class, 'slds-p-right')]//button[contains(@title, 'Close') and @tabindex='0']");
	By subTabCloseAlternative = By.xpath("//div[contains(@class, 'slds-p-right')]//button[@tabindex='0' and not(contains(@title, 'Aktionen'))]");



	/**
	 * <b>[Method]</b> - Click App Launcher<br>
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
	 * <b>[Method]</b> - Search App Launcher<br>
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
	 * <b>[Method]</b> - Click Searched Result<br>
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
	 * This functionality closes the specified tab<br>
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
	 * This functionality closes all the opened tabs<br>
	 */
	public void closeAllTabs(){
		Log.info("closeAllTabs method executing");

		for (int i = 0; i < 1; i++) {
			if(isElementClickable(tabXpath)){
				waitForPageToLoad();
				actions.keyDown(Keys.SHIFT).sendKeys("w").perform();
				actions.keyUp(Keys.SHIFT).perform();
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				if(isElementPresent(closeAllButton)) {
					click(closeAllButton);
				}
			}
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
		Log.info("closeSplitView method executing");

		boolean splitViewOpen = !driver.findElements(splitView).isEmpty();
		if (splitViewOpen) {
			click(splitView);
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
		Log.info("checkHomepage method executing");

		String title = driver.findElement(pageTitle).getText();
		if (!title.equals(homepage)) {
			click(appLauncher);
			wait.until(ExpectedConditions.visibilityOfElementLocated(appSearch));
			type(appSearch, homepage);
			wait.until(ExpectedConditions.elementToBeClickable(searchPage));
			click(searchPage);
			waitForPageToLoad();
			closeAllTabs();
			//closeAllPossiblyOpenTabs();
		}
	}

	/**
	 * <b>[Method]</b> - Go to Setup<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality navigates to Setup from Home Page<br>
	 */
	public void gotoSetup() {
		Log.info("gotoSetup method executing");

		waitForPageToLoad();
		wait.until(ExpectedConditions.elementToBeClickable(gearIcon));
		click(gearIcon);
		click(setupOption);
	}

	/**
	 * <b>[Method]</b> - Login as User<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality navigates to Setup to login as different user<br>
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

	/**
	 * <b>[Method]</b> - Close All Possibly Open tabs<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality closes all possibly open tabs<br>
	 */
	public void closeAllPossiblyOpenTabs() {
		waitForPageToLoad();
		if (!isElementPresent(possibleTabsXpath)) {
			return;
		}

		List<WebElement> tabs = driver.findElements(possibleTabsXpath);
		int i = tabs.size();

		while (i > 1) {
			waitForPageToLoad();
			click(possibleTabsXpath);
			i--;
		}
	}

	/**
	 * <b>[Method]</b> - Click Navigation Menu<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality clicks on navigation menu app<br>
	 * @param appName AppList
	 */
	public void clickNavigationMenu(AppList appName) {
		Log.info("clickNavigationMenu method executing");

		String app;
		switch (appName) {
			case CASES_APP -> app = AppList.CASES_APP.option;
			case ACCOUNTS_APP -> app = AppList.ACCOUNTS_APP.option;
			case CONTACTS_APP -> app = AppList.CONTACTS_APP.option;
			case KONTAKTE_APP -> app = AppList.KONTAKTE_APP.option;
			default -> app = CASES_APP.option;
		}
		By searchItemElement = By.xpath("//a[@data-label='" + config.getProperty(app) + "']");
		By searchElement = By.xpath("//a[@title='" + config.getProperty(app) + "']/span[text()='" + config.getProperty(app) + "']");

		waitForPageToLoad();
		wait.until(ExpectedConditions.presenceOfElementLocated(navigationMenuArrow));
		click(navigationMenuArrow);

		wait.until(ExpectedConditions.elementToBeClickable(searchItemElement));
		click(searchItemElement);
		wait.until(ExpectedConditions.presenceOfElementLocated(searchElement));

	}

	/**
	 * <b>[Method]</b> - Login with In App Log In Functionality<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality tries to log in as different user<br>
	 * @param user String
	 */
	public void loginWithInAppLogInFunctionality(String user) {
		Log.info("loginWithInAppLogInFunctionality method executing");

		waitForPageToLoad();
		gotoSetup();
		List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(searchField));

		type(searchField, user);
		waitForPageToLoad();

		By searchedUser = By.xpath("//span[@title='"+user+"']");
		wait.until(ExpectedConditions.presenceOfElementLocated(searchedUser));
		click(searchedUser);

		waitForPageToLoad();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		click(loginButton);
		driver.switchTo().defaultContent();
		waitForPageToLoad();
		boolean isPresent = driver.findElements(By.cssSelector(".slds-icon-utility-close path")).size() > 0;

		if(isPresent) {
			driver.findElement(By.cssSelector(".slds-icon-utility-close path")).click();
		}
	}

	/**
	 * <b>[Method]</b> - Logout from In-App Log In User<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality tries to log out from the in-app logged user<br>
	 */
	public void logoutFromInAppLogInUser() {
		waitForPageToLoad();
		click(logutInAppUser);
	}

	/**
	 * <b>[Method]</b> - Close Sub Tab<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality closes a sub tab<br>
	 */
	public void closeSubTab() {
		Log.info("closeSubTab method executing");

		boolean isPresent = driver.findElements(subTabElement).size() > 0;
		boolean isPresentXButtonSubTabElement = driver.findElements(subTabElement).size() > 0;

		if(isPresent){
			waitForPageToLoad();
			//WebElement element = driver.findElement(subTabElement);
			//actions.moveToElement(element).click().build().perform();
			click(subTabElement);
		} else if(isPresentXButtonSubTabElement){
			waitForPageToLoad();
			//WebElement element = driver.findElement(xButtonSubTabElement);
			//actions.moveToElement(element).click().build().perform();
			click(xButtonSubTabElement);
		} else {
			waitForPageToLoad();
			click(subTabCloseAlternative);
		}
	}

	/**
	 * <b>[Method]</b> - Close Tab<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality closes a tab<br>
	 */
	public void closeTab() {
		waitForPageToLoad();
		click(tabElement);
	}

	/**
	 * <b>[Method]</b> - Go to First Sub Tab<br>
	 * <br>
	 * <i>Method functionality:</i><br>
	 * This functionality navigates to the first sub tab<br>
	 */
	public void gotoFirstSubTab() {
		waitForPageToLoad();
		click(firstSubTab);
	}
}
