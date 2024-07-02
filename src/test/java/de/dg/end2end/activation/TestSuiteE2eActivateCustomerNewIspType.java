package de.dg.end2end.activation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.activation.FlowActivationCustomer;
import automation.de.dg.database.dgh.customer.SuiteBackDays;
import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteSignVzf;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfCreatingCustomer;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.DatabaseNames;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>End2End Feature : New ISP Type Suite</b> Create Customer<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to define end2end test suite<br>
 *  regarding customer creation for New type
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteE2eActivateCustomerNewIspType {

    CommonTest common = new CommonTest();
    TestingResource resource = new TestingResource();
    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();
    FlowActivationCustomer activationCustomer = new FlowActivationCustomer();
    SuiteSignVzf signVzf = new SuiteSignVzf();

    Portfolios portfolio = Portfolios.Eighteen;
    int tariff = 1012;
    WaipuTypes waipu = WaipuTypes.Null;
    RouterTypes router = RouterTypes.Ker;

    /**
     * <b>[Test Method]</b> - Test Case End2End activation of DGH Customer<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs end-to-end<br>
     * activation of DGH customer
     */
    @Test
    public void tcE2eActivateCustomerNewIspType() throws InterruptedException {
        // perform customer's activation process
        /*createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);

        // insert wait element until all backend processes are finished
        System.out.println("Proceeding with 60sec waiting timer until all backend process are performed");
        Thread.sleep(1000*60);*/
        //Following Customer is created: 8029521
        //Following Customer is created: 8029522
        //Following Customer is created: 8029523
        resource.setCustomerId(8029523);
        resource.setContractId(28648);

        // perform customer activation
        activationCustomer.flowCustomerActivation(resource.getCustomerId());

        // prepare CIM API environment
        createCustomer.prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
        // perform signing VZF
        signVzf.flowSignVzf(resource.getCustomerId(), resource.getContractId());

        System.out.println(resource.getCustomerId() + " is successfully activated");
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
        resource.setCustomerType(CustomerTypes.NEW);
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.CIMREST);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.BASIC);
        // initialize RestApi URL
        common.setup();
        // define CIM Rest instance
        StaticContext.setCimInstanceUrl(common.getRestApiUrl(), resource.getCustomerType(), "v3");
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
        String firstName = StaticContext.generateRandomFirstNameBasedOnData(CustomerTypes.NEW, portfolio, tariff, router, waipu);
        PostCreateCustomer.setFirstName(firstName);
        // set if contract length is greater than 23 months
        PostCreateCustomer.setGreater23(false);
        // set email address
        PostCreateCustomer.setEmail("Test_Kundenkommunikation@deutsche-glasfaser.de");
        // set db name
        DataBaseConnection.setDatabaseName(DatabaseNames.NEW);
    }

}
