package automation.de.dg.salesforce.frontend.pages.common.navigation;

import automation.de.dg.salesforce.enumation.AppList;
import automation.listeners.ExtentListeners;
import automation.testbase.Page;
import automation.utilities.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * <b>PAGES :</b> [Search]: Search bar
 */
public class Search extends Page {

    JavascriptExecutor executor = (JavascriptExecutor)driver;
    HeaderPage headerPage = new HeaderPage();
    By cancelContactAccountButton = By.xpath("//button[@title='Abbrechen']");
    By viewAllAppsCloseButton = By.xpath("//button[@type='button' and (@title='Close this window' or @title='Dieses Fenster schließen')]");
    By searchField = By.xpath("//button[@type='button' and (contains(text(),'Search...') or contains(text(), 'Suche...') or @aria-label='Suche')]");
    By searchFieldInputMore = By.xpath("//input[contains(@placeholder, 'Search...') or contains(@placeholder, 'and more...') or contains(@placeholder, 'Suche.."
                    + ".') or contains(@placeholder, 'und mehr durchsuchen')]");
    By searchResults = By.xpath("//h1[contains(text(),'Search Results') or contains(text(),'Suchergebnisse')]");
    By topResults = By.xpath("//span[contains(text(),'Top Results') or contains(text(),'Beste Ergebnisse')]");
    By topResultsContacts = By.xpath("//a/span/span[contains(text(), 'Kontakte')]/../..");
    By topResultsAccounts = By.xpath("//a/span/span[contains(text(), 'Accounts')]/../..");
    By filterArrowAccounts = By.xpath("//button[@title=\"Listenansicht auswählen: Accounts\" or @title=\"Select a List View: Accounts\"]");
    By allTodayAccountsOption = By.xpath("//span[text()='All Today']");
    By accountSearchFiled = By.xpath("//input[@name=\"Account-search-input\"]");
    By foundAccount = By.xpath("(//a[@data-refid=\"recordId\"])[1]");
    By allAccountsOption = By.xpath("//span[text()='Alle Accounts']");
    By searchFieldTwo = By.xpath("//input[@placeholder='Cases und mehr durchsuchen...' or @placeholder='Suche...']");
    By account = By.xpath("//a[@title='NA']");

    /**
     * <b>[Method]</b> - Close Possible Contacts Account PopUp<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality closes the frequently showed pop-up, that appears in the contact and accounts.<br>
     */
    public void closePossibleContactsAccountPopUp() {
        Log.info("closePossibleContactsAccountPopUp method executing");

        //if(Boolean.TRUE.equals(isElementClickable(cancelContactAccountButton)))

        if(isElementClickable(cancelContactAccountButton)) {
            click(cancelContactAccountButton);
        }
    }

    /**
     * <b>[Method]</b> - Search Contact<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality is responsible for searching and navigation to a contact, based on the account name or number.<br>
     * @param searchContactItem String
     */
    public void searchContact(String searchContactItem) {

        By showMoreResults = By.xpath("//span[contains(text(), 'Show more results for \"" + searchContactItem + "\"') or  contains(text(), 'Weitere Ergebnisse für \"" + searchContactItem + "\" anzeigen')]");
        By searchedContact = By.xpath("//a[@title='" + searchContactItem + "']");

        waitForPageToLoad();
        //closePossibleContactsAccountPopUp();
        //headerPage.closeAllPossiblyOpenTabs();
        headerPage.clickNavigationMenu(AppList.KONTAKTE_APP);

        if(isElementVisible(viewAllAppsCloseButton))
            click(viewAllAppsCloseButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        click(searchField);
        waitForPageToLoad();
        type(searchFieldInputMore, searchContactItem);
        wait.until(ExpectedConditions.visibilityOfElementLocated(showMoreResults));
        click(showMoreResults);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
        wait.until(ExpectedConditions.visibilityOfElementLocated(topResults));
        click(topResultsContacts);

        WebElement element = driver.findElement(By.tagName("body"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).perform();

        //click(searchedContact);
        WebElement searched = driver.findElement(searchedContact);
        executor.executeScript("arguments[0].click();", searched);
        waitForPageToLoad();
        //closePossibleContactsAccountPopUp();
    }

    /**
     * <b>[Method]</b> - Search Again<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality searches one more time<br>
     * @param searchItem String
     */
    public void searchAgain(String searchItem) {
        By search = By.xpath("//button[@type='button' and contains(text(),'" + searchItem + "')]");
        By searchElement = By.xpath("//span[contains(text(), 'Show more results for \"" + searchItem + "\"') or  contains(text(), 'Weitere Ergebnisse für \"" + searchItem + "\" anzeigen')]");

        click(search);
        click(searchElement);
        waitForPageToLoad();
    }

    /**
     * <b>[Method]</b> - Search Account from Search<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality searches for a specific account from the all accounts filter<br>
     * @param account String
     */
    public void searchAccountFromSearch(String account) {
        headerPage.clickNavigationMenu(AppList.ACCOUNTS_APP);
        click(filterArrowAccounts);
        click(allAccountsOption);
       // click(allTodayAccountsOption);
        waitForPageToLoad();
        type(accountSearchFiled, account);
        driver.findElement(accountSearchFiled).sendKeys(Keys.RETURN);
        waitForPageToLoad();
        click(foundAccount);

    }

    /**
     * <b>[Method]</b> - Search All Today Account From Search<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality searches for a specific account from the all today accounts filter<br>
     * @param account String
     */
    public void searchAllTodayAccountFromSearch(String account) {
        headerPage.clickNavigationMenu(AppList.ACCOUNTS_APP);
        click(filterArrowAccounts);
        click(allTodayAccountsOption);
        waitForPageToLoad();
        type(accountSearchFiled, account);
        driver.findElement(accountSearchFiled).sendKeys(Keys.RETURN);
        waitForPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(foundAccount));
        click(foundAccount);
    }

    /**
     * <b>[Method]</b> - Search Case<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality searches for a case<br>
     * @param caseNumber String
     */
    public void searchCase(String caseNumber) {
        Log.info("searchCase method executing");

        waitForPageToLoad();
        By searchedCaseElement = By.xpath("//span/mark[text()='" + caseNumber + "']");
        click(searchField);

        type(searchFieldTwo, caseNumber);
        click(searchedCaseElement);
    }

    /**
     * <b>[Method]</b> - Wait for Account To Sync from CIM<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality waits as long as it is needed for the account to be properly sync to Salesforce, so it can be opened<br>
     * @param id String
     */
    public void waitForAccountToSyncFromCim(String id) {
        Log.info("waitForAccountToSyncFromCim method executing");

        // This loop will rotate for 50 times to check If account is synchronized with salesforce.
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(5000);
                driver.navigate().refresh();
                waitForPageToLoad();
                headerPage.closeAllTabs();
                headerPage.clickNavigationMenu(AppList.ACCOUNTS_APP);
                click(filterArrowAccounts);
                click(allTodayAccountsOption);
                waitForPageToLoad();
                type(accountSearchFiled, id);
                driver.findElement(accountSearchFiled).sendKeys(Keys.RETURN);
                waitForPageToLoad();
            } catch (InterruptedException e) {
                ExtentListeners.test.log(Status.FAIL, e);
            }

            if (!isElementVisible(account) && isElementClickable(foundAccount)) {
                click(foundAccount);
                break;
            }
        }
    }

    public void searchAccountFromHeaderSearch(String accountName) {
        Log.info("searchAccountFromHeaderSearch method executing");


        By showMoreResults = By.xpath("//span[contains(text(), 'Show more results for \"" + accountName + "\"') or  contains(text(), 'Weitere Ergebnisse für \"" + accountName + "\" anzeigen')]");
        By searchedAccount = By.xpath("//a[@title='" + accountName + "']");
        By searchedAccountDuplicate = By.xpath("(//a[@title='" + accountName + "'])[2]");
        By searchedAccountById = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]/div/div/table/tbody/tr/th/span/a");

        waitForPageToLoad();
        //closePossibleContactsAccountPopUp();
        //headerPage.closeAllPossiblyOpenTabs();
        headerPage.clickNavigationMenu(AppList.ACCOUNTS_APP);

        click(searchField);
        waitForPageToLoad();
        type(searchFieldInputMore, accountName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(showMoreResults));
        click(showMoreResults);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
        wait.until(ExpectedConditions.visibilityOfElementLocated(topResults));
        click(topResultsAccounts);

        WebElement element = driver.findElement(By.tagName("body"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).perform();

        //click(searchedContact);
        if(isElementVisible(searchedAccountDuplicate)){
            waitForPageToLoad();
            WebElement searched = driver.findElement(searchedAccountDuplicate);
            executor.executeScript("arguments[0].click();", searched);
            ExtentListeners.test.log(Status.PASS, "Clicking on element: " + searchedAccountDuplicate);
        } else if(isElementVisible(searchedAccount)){
            waitForPageToLoad();
            WebElement searched = driver.findElement(searchedAccount);
            executor.executeScript("arguments[0].click();", searched);
            ExtentListeners.test.log(Status.PASS, "Clicking on element: " + searchedAccount);
        } else {
            waitForPageToLoad();
            WebElement searched = driver.findElement(searchedAccountById);
            executor.executeScript("arguments[0].click();", searched);
            ExtentListeners.test.log(Status.PASS, "Clicking on element: " + searchedAccountById);
        }

        waitForPageToLoad();
        //closePossibleContactsAccountPopUp();
    }
}
