package de.dg.database.dgh;

import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.activation.FlowActivationCustomer;
import automation.de.dg.database.dgh.customer.SuiteBackDays;
import automation.de.dg.database.dgh.customer.SuiteOneTimeProduct;
import automation.de.dg.database.dgh.customer.SuiteUpdatingCustomerData;
import automation.de.dg.enumation.BackDays;
import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.testsupport.testsuite.SuiteTestMachine;
import automation.de.dg.enumation.AddressStatuses;
import automation.de.dg.enumation.AddressTypes;
import automation.de.dg.enumation.CustomerStatuses;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import de.dg.endpoints.apiSuite.cimrest.testsupport.TestSuiteTimeMachine;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * <b>Database Feature : Database Suite</b> Customer Activation
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteCustomerActivationArrayList {

    SuiteBackDays backDays = new SuiteBackDays();
    SuiteOneTimeProduct oneTimeProduct = new SuiteOneTimeProduct();
    FlowActivationCustomer activationCustomer = new FlowActivationCustomer();
    SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
    TestingResource resource = new TestingResource();

    int[] cupool = {3942350, 3942352, 3942354, 3942356, 3942357};
    int[] copool = {1059228, 1059229};

    //int[] cupool = {3940581, 3940582};
    //int[] copool = {1058979, 1058980};

    BackDays days = BackDays.PAST_MORE_TWO_YEARS;

    /**
     * <b>[Test Method]</b> - Test Case Customer activation<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs Customer activation<br>
     */

    @Test(priority = 1)
    public void tcCustomerActivation() {
        try {
            int custId;
            int coId;

            System.out.println("#####################################################################################");
            System.out.println("## Step check UC Status");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step check until UC-Status become Unit found
                activationCustomer.checkUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.UNIT_found);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step update Project");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step change group ID to assign Project
                updatingCustomer.updateGroupId(custId);
            }

            // insert wait element until all backend processes are finished
            System.out.println("Proceeding with 60sec waiting timer until all process are performed");
            Thread.sleep(1000*60);

            System.out.println("#####################################################################################");
            System.out.println("## Step check Customer Status");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step check Customer status to become AEB verschickt (10003)
                activationCustomer.checkCustomerStatus(custId, CustomerStatuses.AEB_verschickt);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step update UC Status");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step update UC-Status to HOMES served
                updatingCustomer.updateUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.HOMES_served.option);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case Customer finishing activation<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs finishing Customer activation<br>
     */

    @Test(priority = 2)
    public void tcCustomerFinishingActivation() {
        try {
            int custId;
            int coId;

            System.out.println("#####################################################################################");
            System.out.println("## Step Executing procedure");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step execute procedure
                updatingCustomer.executeActivationProductProcedure(custId);
            }

            // insert wait element until all backend processes are finished
            System.out.println("Proceeding with 30sec waiting timer until all process are performed");
            Thread.sleep(1000*30);

            System.out.println("#####################################################################################");
            System.out.println("## Step updating customer status");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step update customer status to become active
                updatingCustomer.updateCustomerStatus(custId);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step updating Contract");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step update contract
                updatingCustomer.updateContract(custId);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step verifying mail");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step validate email address
                updatingCustomer.updateEmailValidation(custId, AddressTypes.Rechnungsanschrift);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step activating phone");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step activate phone
                updatingCustomer.activatePhone(custId);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step activating co product");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step activating CO Product
                updatingCustomer.activateCoProduct(custId);
            }

            System.out.println("#####################################################################################");
            System.out.println("## Step activating cov product");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step activating CO Voice Product
                updatingCustomer.activateCoVoiceProduct(custId);
            }
        } catch (RuntimeException re) {
            throw new RuntimeException(re);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case deactivate One time Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs deactivation of one-time Customer's Product<br>
     */

    @Test(priority = 3)
    public void tcCustomerDeactivateOneTimeProduct() {
        try {
            int custId;

            System.out.println("#####################################################################################");
            System.out.println("## Step deactivating One time Product");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                // step execute procedure
                oneTimeProduct.performDeactivateOnetimeProduct(custId);
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case Customer changing days<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs Customer changing days<br>
     */

    @Test(priority = 4)
    public void tcCustomerChangedays() throws NullPointerException {
        try {
            int custId;
            int coId;

            TestSuiteTimeMachine timeMachine = new TestSuiteTimeMachine();
            SuiteTestMachine changeDays = new SuiteTestMachine();

            System.out.println("#####################################################################################");
            System.out.println("## Step Changing Date");
            System.out.println("#####################################################################################");
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step execute procedure
                /*backDays.setBackdays(days);
                //createCustomer.flowCustomerCreation();
                backDays.performBackDays();*/

                timeMachine.setupApiName();
                changeDays.flowChangeDays(resource.getCustomerId(), days);
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }


    @Test(priority = 5)
    public void tcCheckUcStatus() {
        System.out.println("#####################################################################################");
        System.out.println("### Check if UC Status is Homes served");
        System.out.println("#####################################################################################");
        try {
            int custId;
            int coId;
            for (int i = 0; i < cupool.length; i++) {
                custId = cupool[i];
                coId = copool[i];
                System.out.println("Customer ID: " + custId);
                resource.setCustomerId(custId);
                resource.setContractId(coId);
                // step check until UC-Status become Unit found
                activationCustomer.checkUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.HOMES_served);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void tcWholeProcess() {
        tcCustomerActivation();
        tcCustomerFinishingActivation();
        tcCustomerDeactivateOneTimeProduct();
    }

    @BeforeTest
    public void findContractId() throws SQLException, ClassNotFoundException {
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        // set db name
        if  (DataBaseConnection.getDatabaseName()==null) {
            DataBaseConnection.setDatabaseName(DatabaseNames.DGH);
        }

        // get Contract ID
        copool = new int[cupool.length];
        for (int i=0; i<cupool.length; i++) {
            dghQueries.getCustomerValidContract(cupool[i]);
            copool[i] = resource.getContractId();
        }
    }

    public void setCustomerList(List<Integer> cupooList) {
        cupool = new int[cupooList.size()];

        for (int i = 0; i<cupooList.size(); i++) {
            cupool[i] = cupooList.get(i);
        }
        copool = new int[cupooList.size()];
        try {
            findContractId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
