package de.dg.endpoints.apiSuite.cimrest.customer;

import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteGettingCustomerDetail;
import automation.de.dg.enumation.AddressTypesSalesforce;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI : CIM Rest Suite</b> Getting Customer data<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover getting Customer flow.
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteApiCustomerGetDetail {

    CommonTest common = new CommonTest();
    SuiteGettingCustomerDetail getCustomer = new SuiteGettingCustomerDetail();

    // 3938327  3938552
    // 1056736  1056960
    int customerId = 3938556;
    int contractId = 1056964;

    /**
     * <b>[Test Method]</b> - Test Case getting Customer Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Customer details<br>
     */

    @Test
    public void tcGetCustomerDetail() {
        getCustomer.flowGetCustomerDetail();
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Contract Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Contract details<br>
     */

    @Test
    public void tcGetCustomerContract() {
        getCustomer.flowGetContractDetailFromCustomer();
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Contract's Connection Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Contract's Connection details<br>
     */

    @Test
    public void tcGetCustomerContractConnection() {
        getCustomer.flowGetContractConnectionDetailFromCustomer();
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Bank Account Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Bank Account details<br>
     */

    @Test
    public void tcGetCustomerBankAccount() {
        getCustomer.flowGetBankAccountDetailFromCustomer();
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Address Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Address details<br>
     */

    @Test
    public void tcGetCustomerBillingAddress() {
        getCustomer.flowGetAddressDetailFromCustomer(AddressTypesSalesforce.BILLING);
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Phone Number Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Phone Number details<br>
     */

    @Test
    public void tcGetCustomerPhoneNumber() {
        getCustomer.flowGetPhoneNumberDetailFromCustomer();
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Optin Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Optin details<br>
     */

    @Test
    public void tcGetCustomerOptin() {
        getCustomer.flowGetOptinDetailFromCustomer();
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
        CommonTest.setRestApiName(RestApiNames.CIMREST);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.BASIC);
        // initialize RestApi URL
        common.setup();

        TestingResource resource = new TestingResource();
        resource.setCustomerId(customerId);
        resource.setContractId(contractId);
    }

}
