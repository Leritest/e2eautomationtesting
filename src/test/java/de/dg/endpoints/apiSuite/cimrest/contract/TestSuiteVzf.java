package de.dg.endpoints.apiSuite.cimrest.contract;

import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteSignVzf;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * <b>RestAPI Feature : API Suite</b> Sign VZF
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteVzf {

    CommonTest common = new CommonTest();
    SuiteSignVzf signVzf = new SuiteSignVzf();

    static int contId = 1059407;
    static int custId = 3940998;

    int[] cupool = {3942046};

    /**
     * <b>[Test Method]</b> - Test Case Sign VZFt<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs signing VZF<br>
     */

    @Test
    public void tcSignVzf() {
        // perform signing VZF
        signVzf.flowSignVzf(custId, contId);
    }

    @Test
    public void tcSignVzfList() {
        int custId;
        TestingResource resource = new TestingResource();
        DghQueryManipulation dghQueries = new DghQueryManipulation();

        try {
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);

                dghQueries.getCustomerValidContract(custId);

                // perform signing VZF
                signVzf.flowSignVzf(custId, resource.getContractId());
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
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

    public void setCustomerList(List<Integer> cupooList) {
        cupool = new int[cupooList.size()];

        for (int i = 0; i<cupooList.size(); i++) {
            System.out.println("Customer is " + cupooList.get(i));
            cupool[i] = cupooList.get(i);
        }
    }

}
