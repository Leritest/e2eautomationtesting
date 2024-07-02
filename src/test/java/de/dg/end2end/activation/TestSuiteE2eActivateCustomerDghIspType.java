package de.dg.end2end.activation;


import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.activation.FlowActivationCustomer;
import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteSignVzf;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.*;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfCreatingCustomer;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.DatabaseNames;
import automation.enumaration.RestApiNames;
import automation.utilities.Log;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;

/**
 * <b>End2End Feature : DGH ISP Type Suite</b> Create Customer<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to define end2end test suite<br>
 *  regarding customer creation for DGH type
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteE2eActivateCustomerDghIspType {

    CommonTest common = new CommonTest();
    TestingResource resource = new TestingResource();
    FlowActivationCustomer activationCustomer = new FlowActivationCustomer();
    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();
    SuiteSignVzf signVzf = new SuiteSignVzf();

    Portfolios portfolio = Portfolios.Twentythree;
    int tariff = 500;
    WaipuTypes waipu = WaipuTypes.Null;
    RouterTypes router = RouterTypes.Wlanplus;

    /**
     * <b>[Test Method]</b> - Test Case End2End activation of DGH Customer<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs end-to-end<br>
     * activation of DGH customer
     */
    @Test
    public void tcE2eActivateCustomerDghIspType() throws InterruptedException {
        // perform customer's activation process
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);

        // insert wait element until all backend processes are finished
        System.out.println("Proceeding with 60sec waiting timer until all backend process are performed");
        Thread.sleep(1000*60);

        // perform customer activation
        activationCustomer.flowCustomerActivation(resource.getCustomerId());

        // prepare CIM API environment
        createCustomer.prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
        // perform signing VZF
        signVzf.flowSignVzf(resource.getCustomerId(), resource.getContractId());

        Log.info(resource.getCustomerId() + " is successfully activated");
        Log.info(resource.getSfAccountId() + " is Account ID on Salesforce");
    }

    /**
     * <b>[Test Method]</b> - Set Up for RestApi<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize API name used for testing
     * Next step will be setup action in CommonTest class under RestAPI package
     * which allows us to prepare base url and<br>
     * BeforeSuite - annotation
     */

    @BeforeSuite
    public void setupApiName() {
        // set Customer Type
        TestingResource resource = new TestingResource();
        resource.setCustomerType(CustomerTypes.DGH);
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.CIMREST);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.BASIC);
        // initialize RestApi URL
        common.setup();
        // define CIM Rest instance
        StaticContext.setCimInstanceUrl(common.getRestApiUrl(), resource.getCustomerType(), "v4");

        System.out.println(resource.getCustomerId() + " is successfully activated");
    }

    /**
     * <b>[Test Method]</b> - Set Up testing Resource<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize testing resource<br>
     * used for POST Customer Creation CIM Rest API Request
     * BeforeTest - annotation
     */

    @BeforeTest
    public void initTestingResource() {
        // set first name
        String firstName = StaticContext.generateRandomFirstNameBasedOnData(CustomerTypes.DGH, portfolio, tariff, router, waipu);
        PostCreateCustomer.setFirstName(firstName);
        // set if contract length is greater than 23 months
        PostCreateCustomer.setGreater23(false);
        // set email address
        PostCreateCustomer.setEmail("Test_Kundenkommunikation@deutsche-glasfaser.de");
        // set db name
        DataBaseConnection.setDatabaseName(DatabaseNames.DGH);
    }

}
