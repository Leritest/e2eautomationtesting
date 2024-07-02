package de.dg.servicenow.incidentManagement;

import automation.de.dg.servicenow.employercenter.EmployerCenterHomePage;
import automation.de.dg.servicenow.employercenter.pages.CreateIncidentPage;
import automation.de.dg.servicenow.employercenter.pages.ImpersonatePage;
import automation.de.dg.servicenow.employercenter.pages.MyRequestPage;
import automation.de.dg.servicenow.enumaration.EmcIncidentTypes;
import automation.testbase.Page;
import org.testng.annotations.*;

/**
 * <b>SERVICE NOW</b> [Incident Management]: Create Incident Suite
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteIncidentCreation {

    String impersonateUser = "Lesley Wessels";

    /**
     * <b>[Test Method]</b> - Create INQUIRY Incident via Impersonate User<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tries to create INQUIRY incident via Impersonate User.<br>
     *
     *  Pre-requirement steps<br>
     *  1. Login to the ServiceNow Employer Center application<br>
     *  2. Open Avatar Dropdown list<br>
     *  3. Click on Impersonate<br>
     *  4. Perform Impersonation<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Click on Create Incident button<br>
     * 2. Select INQUIRY incident type<br>
     * 3. Fulfill Mandatory Fields<br>
     * 4. Click on Submit button<br>
     * 5. Verify Incident is created<br>
     */
    @Test
    public void tcCreateInquiryIncidentViaImpersonate() throws Exception {
        // initialize classes
        EmployerCenterHomePage homePage = new EmployerCenterHomePage();
        CreateIncidentPage createIncident = new CreateIncidentPage();
        MyRequestPage requestPage = new MyRequestPage();

        // step - create incident
        homePage.openCreateIncidentPage();
        createIncident.fulfilIncidentCreationData(impersonateUser, EmcIncidentTypes.INQUIRY);

        // step - validate incident fields
        requestPage.validatingIncidentFields();
    }

    /**
     * <b>[Test Method]</b> - Create SOFTWARE Incident via Impersonate User<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tries to create SOFTWARE incident via Impersonate User.<br>
     *
     *  Pre-requirement steps<br>
     *  1. Login to the ServiceNow Employer Center application<br>
     *  2. Open Avatar Dropdown list<br>
     *  3. Click on Impersonate<br>
     *  4. Perform Impersonation<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Click on Create Incident button<br>
     * 2. Select SOFTWARE incident type<br>
     * 3. Fulfill Mandatory Fields<br>
     * 4. Click on Submit button<br>
     * 5. Verify Incident is created<br>
     */
    @Test
    public void tcCreateSoftwareIncidentViaImpersonate() throws Exception {
        // initialize classes
        EmployerCenterHomePage homePage = new EmployerCenterHomePage();
        CreateIncidentPage createIncident = new CreateIncidentPage();
        MyRequestPage requestPage = new MyRequestPage();

        // step - create incident
        homePage.openCreateIncidentPage();
        createIncident.fulfilIncidentCreationData(impersonateUser, EmcIncidentTypes.SOFTWARE);

        // step - validate incident fields
        requestPage.validatingIncidentFields();
    }

    /**
     * <b>[Test Method]</b> - Create HARDWARE Incident via Impersonate User<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tries to create HARDWARE incident via Impersonate User.<br>
     *
     *  Pre-requirement steps<br>
     *  1. Login to the ServiceNow Employer Center application<br>
     *  2. Open Avatar Dropdown list<br>
     *  3. Click on Impersonate<br>
     *  4. Perform Impersonation<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Click on Create Incident button<br>
     * 2. Select HARDWARE incident type<br>
     * 3. Fulfill Mandatory Fields<br>
     * 4. Click on Submit button<br>
     * 5. Verify Incident is created<br>
     */
    @Test
    public void tcCreateHardwareIncidentViaImpersonate() throws Exception {
        // initialize classes
        EmployerCenterHomePage homePage = new EmployerCenterHomePage();
        CreateIncidentPage createIncident = new CreateIncidentPage();
        MyRequestPage requestPage = new MyRequestPage();

        // step - create incident
        homePage.openCreateIncidentPage();
        createIncident.fulfilIncidentCreationData(impersonateUser, EmcIncidentTypes.HARDWARE);

        // step - validate incident fields
        requestPage.validatingIncidentFields();
    }

    /**
     * <b>[Test Method]</b> - Create NETWORK Incident via Impersonate User<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality tries to create NETWORK incident via Impersonate User.<br>
     *
     *  Pre-requirement steps<br>
     *  1. Login to the ServiceNow Employer Center application<br>
     *  2. Open Avatar Dropdown list<br>
     *  3. Click on Impersonate<br>
     *  4. Perform Impersonation<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Click on Create Incident button<br>
     * 2. Select NETWORK incident type<br>
     * 3. Fulfill Mandatory Fields<br>
     * 4. Click on Submit button<br>
     * 5. Verify Incident is created<br>
     */
    @Test
    public void tcCreateNetworkIncidentViaImpersonate() throws Exception {
        // initialize classes
        EmployerCenterHomePage homePage = new EmployerCenterHomePage();
        CreateIncidentPage createIncident = new CreateIncidentPage();
        MyRequestPage requestPage = new MyRequestPage();

        // step - create incident
        homePage.openCreateIncidentPage();
        createIncident.fulfilIncidentCreationData(impersonateUser, EmcIncidentTypes.NETWORK);

        // step - validate incident fields
        requestPage.validatingIncidentFields();
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
        page.driver.get(page.config.getProperty("snowEmployerUrl"));

        // step - login via SSO
        EmployerCenterHomePage homePage = new EmployerCenterHomePage();
        homePage.loginOnEmcHomePage();
    }

    /**
     * <b>[Test Method]</b> - Opening Home Page<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to open Home PAge<br>
     * BeforeMethod - annotation
     */
    @BeforeMethod
    public void openingHomePage() {
        Page page = new Page();
        ImpersonatePage impersonatePage = new ImpersonatePage();

        // step - login to Employee Center
        page.driver.get(page.config.getProperty("snowEmployerUrl"));

        // step - perform Impersonate
        impersonatePage.flowImpersonationOfUser(impersonateUser);
    }

}
