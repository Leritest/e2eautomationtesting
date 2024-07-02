package de.dg.endpoints.apiSuite.cimrest.contract;

import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.customer.SuiteUpdatingCustomerData;
import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteSaveProduct;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteSignVzf;
import automation.de.dg.endpoints.cimrest.testsupport.testsuite.SuiteTestMachine;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.BackDays;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.dghIsp.DghCampaigns;
import automation.de.dg.enumation.dghIsp.DghLoyaltyBonus;
import automation.de.dg.enumation.dghIsp.DghTreueBonus;
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

/**
 * <b>RestAPI Feature : API Suite</b> Add Product
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteCustomerAddProduct {

    CommonTest common = new CommonTest();
    SuiteSaveProduct saveProduct = new SuiteSaveProduct();
    SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
    SuiteSignVzf signVzf = new SuiteSignVzf();
    SuiteTestMachine changeDays = new SuiteTestMachine();

    static int contId = 1059689;
    static int custId = 3942958;


//Downgrademöglichkeit PK2018 - 10525, PK2023 - 27002
    /**
     * <b>[Test Method]</b> - Test Case Add Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs adding Product<br>
     */

    @Test
    public void tcAddProduct() {
        updatingCustomer.updateCampaign(custId, DghCampaigns.Regular_Retention_2018.option);

        // perform signing VZF
        /*signVzf.flowSignVzf(custId, contId);

        // perform customer's activation process
        saveProduct.flowAddProduct(custId, contId, DghTreueBonus.Treuebonus_24_x_10_2018.option);

        // perform signing VZF
        signVzf.flowSignVzf(custId, contId);

        // perform Time Machine
        changeDays.flowChangeDays(custId, BackDays.PAST_ONE_DAY);

        updatingCustomer.updateCampaign(custId, DghCampaigns.Portout_Retention_2023.option);*/

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


    @BeforeTest
    public void findContractId() throws SQLException, ClassNotFoundException {
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        TestingResource resource = new TestingResource();
        // set db name
        DataBaseConnection.setDatabaseName(DatabaseNames.DGH);
        // find contract ID
        dghQueries.getCustomerValidContract(custId);
        contId = resource.getContractId();
    }

}
