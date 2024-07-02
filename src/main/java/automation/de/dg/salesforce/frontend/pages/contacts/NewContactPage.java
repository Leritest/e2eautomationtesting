package automation.de.dg.salesforce.frontend.pages.contacts;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewContactPage extends Page {
    By nextContactTypeButton = By.xpath("//button//span[text()='Next']");
    By lastNameField = By.xpath("//input[@name=\"lastName\"]");
    By accountNameField = By.xpath("//input[@placeholder=\"Search Accounts...\"]");
    By saveButton = By.xpath("//button[@name='SaveEdit']");
    By toastMessageAlertDialogBox = By.xpath("//div[@role='alertdialog']");
    By emailField = By.xpath("//input[@name=\"Email\"]");
    By phoneField = By.xpath("//input[@name=\"Phone\"]");
    By mobileField = By.xpath("//input[@name=\"MobilePhone\"]");


    public void chooseContactType(String type) {
        By contactOption = By.xpath("//span[text()='" + type + "']");
        waitForPageToLoad();
        click(contactOption);
        click(nextContactTypeButton);
    }

    /**
     * <b>[Method]</b> - Add data for the new contact <br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality populates the fields in New contact sub-tab. <br>
     *
     * @param lastName String
     * @param accountName String
     */
    public void addDataContact(String lastName, String accountName, String email, String phoneNumber, String mobileNumber) {
        By searchedAccountValue = By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']");
        type(lastNameField, lastName);
        type(accountNameField, accountName);
        waitForPageToLoad();
        click(accountNameField);
        click(searchedAccountValue);
        type(emailField, email);
        type(phoneField, phoneNumber);
        type(mobileField, mobileNumber);
    }

    /**
     * <b>[Method]</b> - Save the new contact <br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality saves the new contact. <br>
     */
    public void saveContact() {
        waitForPageToLoad();
        click(saveButton);
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessageAlertDialogBox));
    }
}
