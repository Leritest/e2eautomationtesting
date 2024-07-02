package automation.de.dg.servicenow.employercenter.pages;

import automation.de.dg.servicenow.common.navigation.Search;
import automation.de.dg.servicenow.constants.SnowPageTitles;
import automation.de.dg.servicenow.enumaration.EmcIncidentTypes;
import automation.enumaration.Timer;
import automation.testbase.Page;
import automation.utilities.Log;
import automation.utilities.TestDataGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static org.testng.AssertJUnit.fail;

/**
 * <b>SERVICENOW/EMPLOYER CENTER : CREATE INCIDENT</b> [Incident Page]: Create Incident Page
 */

public class CreateIncidentPage extends Page {

    By requestByTxt = By.id("select2-chosen-5");
    By callerBtn = By.id("s2id_sp_formfield_caller_id1");
    By callerInput = By.id("s2id_autogen6_search");
    By searchResult = By.id("select2-results-6");

    By findUser = By.id("select2-drop");

    By category = By.id("select2-chosen-1");
    By categoryDropList = By.id("select2-drop");
    By nameOfSwBtn = By.id("s2id_sp_formfield_name_of_software");
    By typeOfHwBtn = By.id("s2id_sp_formfield_type_of_hardware");
    By hwUsedBtn = By.id("s2id_sp_formfield_hardware_used");
    By hwLocationBtn = By.id("s2id_sp_formfield_location");
    By networkConnectionUsedBtn = By.id("s2id_sp_formfield_types_of_network_issue");
    By networkVpnCheckbox = By.id("sp_formfield_vpn_used");
    By hwDeskIdTxt = By.id("sp_formfield_please_provide_desk_id");
    By networkSearchTxt = By.id("sp_formfield_which_network_do_you_want_to_reach_provide_ip_or_url");
    By summaryTxt = By.id("sp_formfield_short_description");
    By commentTxt = By.id("sp_formfield_comments");
    String callerId = "Ratko Zekic";
    By submitBtn = By.id("submit-btn");

    /**
     * <b>[Method]</b> Fulfill Creation Incident Data<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to fulfill<br>
     * mandatory data during incident creation
     * <i>Steps of this scenario:</i><br>
     * 1. Select Caller ID<br>
     * 2. Select Category<br>
     * 3. [Optional] Select Sub-Category<br>
     * 4. Fulfill Summary<br>
     * 5. Fulfill Description<br>
     *
     * @throws Exception
     */
    public void fulfilIncidentCreationData(String impersonateUser, EmcIncidentTypes incident) throws Exception {
        try {
            Log.info("Step - Fulfilling Incident Mandatory fields for Category " + incident);
            Assert.assertEquals(getText(requestByTxt), impersonateUser);

            // select caller
            selectCaller();

            // click on Category to open dropdown list
            selectCategory(incident);
            switch (incident) {
                case SOFTWARE, HARDWARE -> {
                    selectSubcategory(incident);
                }
                case NETWORK -> {
                    selectSubcategory(incident);

                    int count = 0;
                    while (validateElementAttribute(Timer.FiveSecondsTimer, driver.findElement(networkVpnCheckbox), "class", "ng-empty") && count<10) {
                        driver.findElement(By.id("label_a5aaa9731b78bdd0aa6876208b4bcb4d")).click();
                        count++;
                    }
                    type(networkSearchTxt, "192.168.1.10");
                }
            }

            // fulfill Summary field
            type(summaryTxt, "this is random summary text");
            // fulfill Description field
            MyRequestPage requestPage = new MyRequestPage();
            requestPage.setDescription();
            type(commentTxt, requestPage.getDescription());

            // submit incident
            submitTicket();
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Select Caller ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to select Caller ID<br>
     * @throws Exception
     */
    public void selectCaller() {
        try {
            // click on caller ID to open dropdown list
            click(callerBtn);
            // verify pop-up dialog is opened
            wait.until(ExpectedConditions.visibilityOfElementLocated(findUser));
            // write desired user
            type(callerInput, callerId);

            // click on desired user
            Search search = new Search();
            search.selectFromDropdownList(true);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Select Category<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to select Category<br>
     * @throws Exception
     */
    public void selectCategory(EmcIncidentTypes incident) {
        try {
            click(category);
            // verify pop-up dialog is opened
            wait.until(ExpectedConditions.visibilityOfElementLocated(categoryDropList));

            List<WebElement> list;
            // select random category
            list = driver.findElement(categoryDropList).findElement(By.tagName("ul")).findElements(By.tagName("li"));
            for (int i=0; i< list.size(); i++) {
                if (list.get(i).getText().contains(incident.option)) {
                    list.get(i).click();
                    break;
                }
            }
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Select Sub-Category<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to select Sub-Category.<br>
     * It is optional method for certain Category
     * @throws Exception
     */
    public void selectSubcategory(EmcIncidentTypes incident) {
        try {
            Search search = new Search();
            String txt;
            int count = 0;
            switch (incident) {
                case SOFTWARE -> {
                    click(nameOfSwBtn);
                    search.selectFromDropdownList(false);
                    search.getTextByElement(nameOfSwBtn);
                    validateElementSizeIsZero(By.className("select2-result-label"), Timer.SecondTimer);

                    do {
                        click(hwUsedBtn);
                        search.selectFromDropdownList(false);
                        txt = search.getTextByElement(hwUsedBtn);
                        count++;
                    } while(txt.contains("None") && count < 10);
                }
                case HARDWARE -> {
                    do {
                        click(typeOfHwBtn);
                        search.selectFromDropdownList(false);
                        txt = search.getTextByElement(typeOfHwBtn);
                        count++;
                    } while(txt.contains("None") && count < 10);

                    validateElementSizeIsZero(By.className("select2-result-label"), Timer.SecondTimer);

                    click(hwLocationBtn);
                    search.selectFromDropdownList(false);

                    String number = "5";
                    for (int i=0; i<7; i++) {
                        number = number + String.valueOf(TestDataGenerator.generateRandomNumber(1, 9));
                    }
                    type(hwDeskIdTxt, number);
                }
                case NETWORK -> {
                    do {
                        click(networkConnectionUsedBtn);
                        search.selectFromDropdownList(false);
                        txt = search.getTextByElement(networkConnectionUsedBtn);
                        count++;
                    } while(txt.contains("None") && count < 10);
                }
            }
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Submit Ticket<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to sybmit ticket<br>
     * @throws Exception
     */
    public void submitTicket() {
        try {
            Log.info("Submitting incident");
            // click on Submit Button
            click(submitBtn);

            // validate ServiceNow Home Page is opened
            validatePageTitleWithFluentWait(Timer.FiveSecondsTimer, SnowPageTitles.EMPLOYER_CENTER_MY_REQUEST_PAGE);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

}
