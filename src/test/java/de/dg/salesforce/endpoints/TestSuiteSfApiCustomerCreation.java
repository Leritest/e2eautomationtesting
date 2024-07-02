package de.dg.salesforce.endpoints;

import automation.de.dg.contstants.RestApiUrls;
import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfCreatingCustomer;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.*;

/**
 * <b>RestAPI : Salesforce API Rest Suite</b> Creating Customer on SF<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover creating Customer flow on Salesforce side.
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteSfApiCustomerCreation {

    CommonTest common = new CommonTest();
    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();

    /**
     * <b>[Test Method]</b> - Test Case creating Customer on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     * and check if customer is visible on Salesforce
     */

    @Test
    public void tcCreateSfCustomer() {
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
    }


    Portfolios portfolio = Portfolios.Twentythree;
    int tariff = 1000;
    WaipuTypes waipu = WaipuTypes.Null;
    RouterTypes router = RouterTypes.Ker;
    String firstName = "MCCAA";
    boolean porting = false;
    boolean greater23 = false;
    boolean fsecure = false;
    String email = "Test_Kundenkommunikation@deutsche-glasfaser.de";

    /**
     * <b>[Test Method]</b> - Set Up Create Customer Resource<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to set customer resource used for customer creation<br>
     * BeforeClass - annotation
     */

    @BeforeClass
    public void initTestingResource() {
        // set Customer Type
        TestingResource resource = new TestingResource();
        resource.setCustomerType(CustomerTypes.DGH);
        // prepare CIM API environment
        createCustomer.prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
        // define CIM Rest instance
        StaticContext.setCimInstanceUrl(common.getRestApiUrl(), resource.getCustomerType(), "v4");
        // set first name
        PostCreateCustomer.setFirstName(firstName);
        // set if contract length is greater than 23 months
        PostCreateCustomer.setGreater23(false);
        // set if F-Secure should be added
        PostCreateCustomer.setFsecure(fsecure);
        // set email address
        PostCreateCustomer.setEmail(email);
    }

    /**
     * <b>[Test Method]</b> - Set Up for RestApi<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize API name used for testing
     * Next step will be setup action in CommonTest class under RestAPI package
     * which allows us to prepare base url and<br>
     * BeforeSuite - annotation
     */

    //@BeforeSuite
    public void setupApiName() {
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.SALESFORCE);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.TOKEN);
        // initialize Token base URL
        CommonTest.setTokenUrl(RestApiUrls.SALESFORCE_OAUTH_TOKEN_URL);
        // initialize RestApi URL
        common.setup();
    }

}
