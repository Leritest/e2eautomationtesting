package de.dg.servicenow.incidentManagement;

import automation.de.dg.servicenow.enumaration.IncidentStates;
import automation.de.dg.servicenow.enumaration.ListLabels;
import automation.de.dg.servicenow.pages.SnowHomePage;
import automation.de.dg.servicenow.pages.SnowListPage;
import automation.de.dg.servicenow.pages.SnowLoginPage;
import automation.testbase.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>SERVICE NOW</b> [Incident Management]: Search Items Suite
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteItemsSearch {

    /**
     * <b>[Test Method]</b> - Open Incident<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality Open Incident Page.<br>
     * Pre-requirement steps<br>
     * 1. Login to the ServiceNow<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Open List Page<br>
     * 2. Click on Incident in State New<br>
     */
    @Test
    public void tcRequestItemSearch() {
        // initialize classes
        SnowHomePage homePage = new SnowHomePage();
        SnowListPage listPage = new SnowListPage();

        // step - open incident list
        homePage.openListPage();

        // step - navigate to Open incidents
        listPage.setContentState("Fulfillment");
        listPage.flowOpenRecord(ListLabels.REQUESTS, ListLabels.OPEN_ITEMS);
    }

    /**
     * <b>[Test Method]</b> - Set Up Driver and open URL<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to set up Driver and open URL<br>
     * BeforeClass - annotation
     */
    @BeforeClass
    public void setUp() {
        Page page = new Page();
        page.setUpDriver();
        page.driver.get(page.config.getProperty("snowUrl"));

        // step - login via SSO
        SnowLoginPage loginPage = new SnowLoginPage();
        loginPage.flowSsoLogin();
    }

}
