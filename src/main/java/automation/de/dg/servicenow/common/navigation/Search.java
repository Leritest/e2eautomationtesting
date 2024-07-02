package automation.de.dg.servicenow.common.navigation;

import automation.enumaration.Timer;
import automation.testbase.Page;
import automation.utilities.Log;
import automation.utilities.TestDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.AssertJUnit.fail;

/**
 * <b>SERVICENOW/NAVIGATION : SEARCH</b> [Navigation]: Search
 */
public class Search extends Page {

    By searchResult = By.className("select2-results");

    /**
     * <b>[Method]</b> - Select from Dropdown List<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality tries to select<br>
     * impersonate user
     * @throws StaleElementReferenceException
     */
    public void selectFromDropdownList(Boolean shouldFirst) {
        List<WebElement> mainList;
        List<WebElement> list;
        WebElement we;
        WebElement el = null;
        try {
            // find displayed search dropdown list
            mainList = driver.findElements(searchResult);
            for (WebElement elem:mainList) {
                if (elem.isDisplayed()) {
                    el = elem;
                    break;
                }
            }
            int count = 0;
            do {
                list = el.findElements(By.tagName("li"));
                if (shouldFirst) {
                    we = list.get(0);
                } else {
                    we = list.get(TestDataGenerator.generateRandomNumber(1, list.size())-1);
                }
                count++;
            } while (!validateElementAttribute(Timer.SecondTimer, we, "class", "select2-result-selectable") && count < 10);
            we.click();
        } catch (StaleElementReferenceException se) {
            Log.error(se.getLocalizedMessage());
            throw new RuntimeException(se);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public String getTextByElement(By by) {
        List<WebElement> list = driver.findElement(by).findElements(By.className("select2-chosen"));
        String text = null;
        for (WebElement we:list) {
            if (we.isDisplayed()) {
                text = we.getText();
                break;
            }
        }
        return text;
    }

}
