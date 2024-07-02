package de.dg.endpoints.apiSuite.cimrest.customer;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteCancelContract;
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

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteCustomerDeactivation {

    CommonTest common = new CommonTest();
    SuiteCancelContract cancelContract = new SuiteCancelContract();

    static int contId = 1060250;
    static int custId = 3941841;

    int[] cupool = {3942957, 3942958};
    int[] copool = {1059063, 1059064};

    /**
     * <b>[Test Method]</b> - Test Case cancelling Contractl<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs cancelling Contract<br>
     */

    @Test
    public void tcCancelContract() {
        cancelContract.flowCancellingContract(custId, contId);
    }

    @Test
    public void tcCancelContractList() {
        int custId;
        int coId;
        TestingResource resource = new TestingResource();
        for (int i = 0; i < cupool.length; i++) {
            custId = cupool[i];
            coId = copool[i];
            System.out.println("Customer ID: " + custId);
            resource.setCustomerId(custId);
            resource.setContractId(coId);
            cancelContract.flowCancellingContract(custId, coId);
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
        // set db name
        DataBaseConnection.setDatabaseName(DatabaseNames.DGH);
    }

    @BeforeTest
    public void findContractId() throws SQLException, ClassNotFoundException {
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        TestingResource resource = new TestingResource();
        // set db name
        DataBaseConnection.setDatabaseName(DatabaseNames.DGH);

        // get Contract ID
        copool = new int[cupool.length];
        for (int i=0; i<cupool.length; i++) {
            dghQueries.getCustomerValidContract(cupool[i]);
            copool[i] = resource.getContractId();
        }
    }

}
