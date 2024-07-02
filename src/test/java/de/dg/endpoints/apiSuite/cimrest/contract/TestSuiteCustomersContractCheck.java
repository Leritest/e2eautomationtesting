package de.dg.endpoints.apiSuite.cimrest.contract;

import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteGetContractDetail;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI Feature : API Suite</b> Check Contract
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover flow of checking Customer's Contract detail.
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteCustomersContractCheck {

    CommonTest common = new CommonTest();
    SuiteGetContractDetail getContract = new SuiteGetContractDetail();

    static int custId = 3938705;

    /**
     * <b>[Test Method]</b> - Test Case Check Contract<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs checking Contract<br>
     * for newly created Customer
     */

    @Test
    public void tcCheckContractForNewCustomer() {
        getContract.flowGetContractDetailNewCustomer(StaticContext.getCimInstanceUrl(), custId);
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
        // define CIM Rest instance
        TestingResource resource = new TestingResource();
        resource.setCustomerType(CustomerTypes.DGH);
        StaticContext.setCimInstanceUrl(common.getRestApiUrl(), resource.getCustomerType(), "v4");
    }

    @BeforeTest
    public void findContractId() {
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        dghQueries.getCustomerValidContract(custId);
    }

}
