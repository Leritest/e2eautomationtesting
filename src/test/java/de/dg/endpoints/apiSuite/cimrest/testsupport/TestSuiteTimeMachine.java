package de.dg.endpoints.apiSuite.cimrest.testsupport;

import automation.de.dg.enumation.BackDays;
import automation.de.dg.endpoints.cimrest.testsupport.testsuite.SuiteTestMachine;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI : CIM Rest Suite</b> Changing Customer dates<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover changing Customer's Product and Contract dates.
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteTimeMachine {

    CommonTest common = new CommonTest();
    SuiteTestMachine changeDays = new SuiteTestMachine();

    static int custId = 3942869;
    static BackDays bd = BackDays.PAST_MORE_TWO_YEARS;

    int[] cupool = {3943014, 3943016, 3943017, 3943020, 3943021, 3943023};
    //int[] cupool = {3942971, 3942972};

    /**
     * <b>[Test Method]</b> - Test Case changing date<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs changing Product date<br>
     */

    @Test
    public void tcChangeDate() {
        changeDays.flowChangeDays(custId, bd);
    }

    @Test
    public void tcChangeDateList() {

        int custId;
        TestingResource resource = new TestingResource();
        try {
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                changeDays.flowChangeDays(custId, bd);
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
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.CIMREST);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.BASIC);
        // initialize RestApi URL
        common.setup();
    }

}
