package de.dg.end2end.creation;


import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.activation.FlowActivationCustomer;
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

/**
 * <b>End2End Feature : DGH ISP Type Suite</b> Create Customer<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to define end2end test suite<br>
 *  regarding customer creation for DGH type
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteE2ECreateCustomerDghIspType {

    CommonTest common = new CommonTest();
    TestingResource resource = new TestingResource();
    FlowActivationCustomer activationCustomer = new FlowActivationCustomer();
    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();
    SuiteSignVzf signVzf = new SuiteSignVzf();

    Portfolios portfolio = Portfolios.Seventeen;
    int tariff = 300;
    WaipuTypes waipu = WaipuTypes.Null;
    RouterTypes router = RouterTypes.Premium_2018;

    /**
     * <b>[Test Method]</b> - Test Case End2End creation of DGH Customer<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs end-to-end<br>
     * activation of DGH customer
     */
    @Test
    public void tcE2eCreateCustomerDghIspType() {
        // perform customer's activation process
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);

        // prepare CIM API environment
        createCustomer.prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
        // perform signing VZF
        signVzf.flowSignVzf(resource.getCustomerId(), resource.getContractId());

        System.out.println(resource.getCustomerId() + " is successfully created");
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
    }

}
