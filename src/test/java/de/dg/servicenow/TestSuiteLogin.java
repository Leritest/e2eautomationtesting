package de.dg.servicenow;

import automation.de.dg.servicenow.pages.SnowLoginPage;
import automation.testbase.Page;
import automation.utilities.Log;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>SERVICE NOW</b> [Login]: Login Suite
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteLogin {

    /**
     * <b>[Test Method]</b> - SSO Login<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality login to ServiceNow via SSO.<br>
     * <i>Steps of this scenario:</i><br>
     * 1. Login to the ServiceNow application<br>
     */
    @Test
    public void tcLoginViaSso() {
        // initialize classes
        SnowLoginPage loginPage = new SnowLoginPage();

        // step - login via SSO
        loginPage.flowSsoLogin();
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
        page.driver.get(page.config.getProperty("snowUrl"));
        page.setUpDriver();

    }

}
