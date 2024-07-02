package automation.de.dg.servicenow.employercenter.pages;

import automation.testbase.Page;
import automation.utilities.Log;
import automation.utilities.TestDataGenerator;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.testng.AssertJUnit.fail;

/**
 * <b>SERVICENOW/EMPLOYER CENTER : MY REQUEST</b> [My Request Page]: My Request Page
 */
public class MyRequestPage extends Page {

    By formGroup = By.xpath(".//div[contains(@class, 'form-group')]");
    By incNumberField = By.id("data.number.name");
    By textField = By.className("timeline-body");

    /**
     * <b>[Method]</b> Validating Incident Fields<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to validate<br>
     * newly created incident fields
     * @throws Exception
     */
    public void validatingIncidentFields() {
        try {
            Log.info("Validating created Incident Fields");

            // getting Incident number
            incidentNumber();
            List<WebElement> list = driver.findElements(formGroup);
            for (WebElement we:list) {
                String header = we.findElement(By.tagName("span")).getText();
                if (header.equals("State")) {
                    //incidentState(we.findElement(By.tagName("div")).findElement(By.tagName("div")));
                    incidentState(we.findElement(By.className("form-control")));
                    break;
                } else if (header.equals("Created")) {
                    incidentCreation(we.findElement(By.className("form-control")));
                }
            }

            list = driver.findElements(textField);
            Boolean hasIncNumber = false;
            Boolean hasDescription = false;
            for (WebElement we:list) {
                String txt = we.findElement(By.tagName("p")).getText();
                if (txt.contains(getDescription())) {
                    hasDescription = true;
                } else if (txt.contains(getIncidentNumber())) {
                    hasIncNumber = true;
                }
            }
            if (hasDescription && hasIncNumber) {
                Log.info("Incident Page contains info regarding incident number and description");
            } else if (!hasDescription) {
                Log.error("Incident Description " + getDescription() + " is wrongly saved");
            } else if (!hasIncNumber) {
                Log.error("Incident Number " + getIncidentNumber() + " is wrongly saved");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Incident Number<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to validate<br>
     * Incident Number
     * @throws Exception
     */
    public void incidentNumber() {
        try {
            String incidentNumber = getText(incNumberField);
            setIncidentNumber(incidentNumber);
            Log.info("Incident Number is " + incidentNumber);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Incident State<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to validate<br>
     * Incident State
     * @throws Exception
     */
    public void incidentState(WebElement we) {
        try {
            String state = we.getText();
            Assert.assertEquals(state, "New", "State of created ticket is not New, but " + state);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public void incidentCreation(WebElement we) {
        String created = we.getText().trim();
        if (!created.contains("just now")) {
            fail("Ticket creation time is not just now, but " + created);
            Log.error("Ticket creation time is not just now, but " + created);
        }
    }

    static String description;
    public void setDescription() {
        String value = "A";
        for (int i=0; i>10; i++) {
            value = value + TestDataGenerator.generateRandomNumber(1, 9);
        }
        description = "this is random description text " + value;
    }

    public String getDescription() {
        return description;
    }

    static String incident;
    public void setIncidentNumber(String value) {
        incident = value;
    }

    public String getIncidentNumber() {
        return incident;
    }

}
