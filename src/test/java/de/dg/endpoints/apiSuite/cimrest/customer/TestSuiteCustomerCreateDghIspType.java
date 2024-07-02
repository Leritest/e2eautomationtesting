package de.dg.endpoints.apiSuite.cimrest.customer;

import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteCreatingCustomerDetail;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI Feature : API Suite</b> Create Customer
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteCustomerCreateDghIspType {

    CommonTest common = new CommonTest();
    SuiteCreatingCustomerDetail createCustomer = new SuiteCreatingCustomerDetail();

    Portfolios portfolio = Portfolios.Twentythree;
    int tariff = 1000;
    WaipuTypes waipu = WaipuTypes.Perfect;

    RouterTypes router = RouterTypes.Bundle;

    boolean porting = true;
    boolean greater23 = false;
    boolean fsecure = true;

    String email = "Test_Kundenkommunikation@deutsche-glasfaser.de";

    /**
     * <b>[Test Method]</b> - Test Case creating Customer<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs creating Customer<br>
     */

    @Test
    public void tcCreateCustomerDghIspType() {
        // perform customer's activation process
        createCustomer.flowCustomerCreation(portfolio, tariff, waipu, router);
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
        // set if F-Secure should be added
        PostCreateCustomer.setFsecure(fsecure);
        // set email address
        PostCreateCustomer.setEmail(email);
        // set porting status
        createCustomer.setPortingStatus(porting);
    }

}
