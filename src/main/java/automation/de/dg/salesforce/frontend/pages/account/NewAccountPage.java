package automation.de.dg.salesforce.frontend.pages.account;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class NewAccountPage extends Page {

    JavascriptExecutor je = (JavascriptExecutor) driver;
    By nextAccountTypeButton = By.xpath("//button//span[text()='Next']");
    By accountNameField = By.xpath("//input[@name='Name']");
    By saveButton = By.xpath("//button[@name='SaveEdit']");
    By companyNameField = By.xpath("//input[@name=\"CustomerAddressCompanyName__c\"]");
    By streetField = By.xpath("//input[@name=\"CustomerAddressStreet__c\"]");
    By houseNumberField = By.xpath("//input[@name=\"CustomerAddressHouseNumber__c\"]");
    By houseNumberSuffixField = By.xpath("//input[@name=\"CustomerAddressHouseNumberSuffix__c\"]");


    /**
     * <b>[Method]</b> - Choose Account Type<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality chooses the account type upon creation<br>
     */
    public void chooseAccountType(String type) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        By businessRadioButton = By.xpath("//span[text()='" + type + "']");
        click(businessRadioButton);
        click(nextAccountTypeButton);
    }

    /**
     * <b>[Method]</b> - Add data for the new account <br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality populates the fields for the new account<br>
     * @param nameValue String
     * @param companyName String
     * @param street String
     * @param houseNumber String
     * @param houseNumberSuffix String
     */
    public void addDataAccount(String nameValue, String companyName, String street, String houseNumber, String houseNumberSuffix) {
        type(accountNameField, nameValue);
        WebElement element = driver.findElement(companyNameField);
        je.executeScript("arguments[0].scrollIntoView(true);", element);
        type(companyNameField, companyName);
        type(streetField, street);
        type(houseNumberField, houseNumber);
        type(houseNumberSuffixField, houseNumberSuffix);

    }

    /**
     * <b>[Method]</b> - Save the new account <br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality saves the new account<br>
     */
    public void saveAccount() {
        click(saveButton);
    }
}
