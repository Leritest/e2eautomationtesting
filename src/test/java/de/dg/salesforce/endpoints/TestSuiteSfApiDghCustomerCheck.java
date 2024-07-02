package de.dg.salesforce.endpoints;

import automation.de.dg.contstants.RestApiUrls;
import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteCreatingCustomerDetail;
import automation.de.dg.enumation.*;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfCreatingCustomer;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfGettingCustomerDetail;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.*;

/**
 * <b>RestAPI : Salesforce API Rest Suite</b> Check DGH Customer data on SF<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover flow of checking DGH Customer details on Salesforce side.
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteSfApiDghCustomerCheck {

    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();
    SuiteSfGettingCustomerDetail getCustomerDetail = new SuiteSfGettingCustomerDetail();

    /**
     * <b>[Test Method]</b> - Test Case check Phone Number on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     * and check if Phone number is visible on Salesforce
     */
    @Test
    public void tcSfCheckAccountPhone() {
        // Create new Customer
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
        // Check if data is stored in SF
        getCustomerDetail.flowGettingSubscriptionDetail();
    }

    /**
     * <b>[Test Method]</b> - Test Case check Bank Holder on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     * and check if Bank Holder is visible on Salesforce
     */
    //@Test
    public void tcSfCheckAccountBankHolder() {
        // Create new Customer
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
        // Check if data is stored in SF
        getCustomerDetail.flowGettingBankHolderDetail();
    }

    /**
     * <b>[Test Method]</b> - Test Case check Contract on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     * and check if Contract is visible on Salesforce
     */
    //@Test
    public void tcSfCheckAccountContract() {
        // Create new Customer
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
        // Check if data is stored in SF
        getCustomerDetail.flowGettingContractDetail();
    }

    /**
     * <b>[Test Method]</b> - Test Case check Optin on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     * and check if Optin is visible on Salesforce
     */
    //@Test
    public void tcSfCheckAccountOptin() {
        // Create new Customer
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
        // Check if data is stored in SF
        getCustomerDetail.flowGettingOptinDetail(AddressTypesSalesforce.BILLING);
    }

    /**
     * <b>[Test Method]</b> - Test Case check Billing Address on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     * and check if Billing Address is visible on Salesforce
     */
    //@Test
    public void tcSfCheckAccountBillingAddress() {
        // Create new Customer
        createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
        // Check if data is stored in SF
        getCustomerDetail.flowGettingAddressDetails(AddressTypesSalesforce.BILLING);
    }

    /**
     * <b>[Test Method]</b> - Set Up for RestApi<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize API name used for testing
     * Next step will be setup action in CommonTest class under RestAPI package
     * which allows us to prepare base url and<br>
     * BeforeSuite - annotation
     */

    Portfolios portfolio = Portfolios.Twentythree;
    int tariff = 1000;
    WaipuTypes waipu = WaipuTypes.Null;
    RouterTypes router = RouterTypes.Ker;
    String firstName = "MCCAA";
    boolean porting = true;
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
        // set first name
        PostCreateCustomer.setFirstName(firstName);
        // set if contract length is greater than 23 months
        PostCreateCustomer.setGreater23(false);
        // set if F-Secure should be added
        PostCreateCustomer.setFsecure(fsecure);
        // set email address
        PostCreateCustomer.setEmail(email);
        // set Porting status
        SuiteCreatingCustomerDetail createCustomer = new SuiteCreatingCustomerDetail();
        createCustomer.setPortingStatus(porting);
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
        CommonTest common = new CommonTest();
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
    public void setCustomerAndAccount() {
        TestingResource resource = new TestingResource();
        resource.setCustomerType(CustomerTypes.DGH);
        resource.setCustomerId(3938705);
        resource.setContractId(1056960);
        resource.setSfAccountId("0019X00000OF751QAD");
    }
}
