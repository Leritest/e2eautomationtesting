package de.dg.salesforce.endpoints;


import app.getxray.xray.testng.annotations.XrayTest;
import automation.de.dg.contstants.RestApiUrls;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfGettingCustomerDetail;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI : Salesforce API Rest Suite</b> Getting Customer data<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover getting Customer flow on Salesforce side.
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteSfApiCustomerDetail {

    CommonTest common = new CommonTest();
    SuiteSfGettingCustomerDetail getCustomer = new SuiteSfGettingCustomerDetail();
    int customerId = 3938705;

    /**
     * <b>[Test Method]</b> - Test Case getting Customer Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Customer details<br>
     */

    @Test
    @XrayTest(key = "REQ-9359", summary = "Integration with Selenium")
    public void tcGetCustomerData() {
        getCustomer.flowGettingAccountData();
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
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.SALESFORCE);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.TOKEN);
        // initialize Token base URL
        CommonTest.setTokenUrl(RestApiUrls.SALESFORCE_OAUTH_TOKEN_URL);
        // initialize RestApi URL
        common.setup();
    }

    @BeforeTest
    public void initTestingResource() {
        TestingResource resource = new TestingResource();
        resource.setCustomerId(customerId);
    }

}
