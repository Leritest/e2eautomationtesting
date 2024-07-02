package automation.de.dg.database.dgh.activation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.customer.SuiteBackDays;
import automation.de.dg.database.manipulation.queries.DghUpdateQueries;
import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.database.dgh.customer.SuiteUpdatingCustomerData;
import automation.de.dg.database.manipulation.queryManipulation.DghUpdateQueryManipulation;
import automation.de.dg.enumation.AddressStatuses;
import automation.de.dg.enumation.AddressTypes;
import automation.de.dg.enumation.CustomerStatuses;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import automation.utilities.Log;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>DE.DG : Database/DGH</b> Activation Customer Data<br>
 * <i>Class functionality:</i><br>
 *  Class is used for activating customers<br>
 *  whole process is performed on DGH db
 */

public class FlowActivationCustomer {

    /**
     * <b>[Test Method]</b> - Updating Customer data<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of updating Customer data<br>
     * Updating is performed via database update actions
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Verifying Unit Check status is UNIT Found (200)
     * 2. Update Project ID
     * 3. Verifying Customer Status is AEB Verschickt (10003)
     * 4. Updating Unit Check status to Homes Served (700)
     * 5. Executing Procedure to activate Customer's Products
     * 6. Update Contract fields
     * 7. Update Customer Status to Active (10021)
     * 8. Verifying email address
     * 9. Activate Phone number
     * (Optional)10. Activate Customer's Router
     * @param custId int
     */
    public void flowCustomerActivation(int custId) throws InterruptedException {
        // initialize classes from interest
        SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
        SuiteBackDays backDays = new SuiteBackDays();
        TestingResource resource = new TestingResource();
        try {
            Log.info("Step - Process of Customer Activation on CIM");

            // step check until UC-Status become Unit found
            if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.DGH)) {
                checkUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.UNIT_found);
            }

            // step change group ID to assign Project
            updatingCustomer.updateGroupId(custId);

            // insert wait element until all backend processes are finished
            System.out.println("Proceeding with 30sec waiting timer until all backend process are performed");
            Thread.sleep(1000*30);

            // step to update customer status
            if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.DGH)) {
                // step check Customer status to become AEB verschickt (10003)
                checkCustomerStatus(custId, CustomerStatuses.AEB_verschickt);
            } else if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
                DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
                dghUpdateQueries.updateCustomerStatusUpToAbVerschickt(custId);
            }

            // step update UC-Status to HOMES served
            if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.DGH)) {
                updatingCustomer.updateUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.HOMES_served.option);
            }

            // step execute procedure
            updatingCustomer.executeActivationProductProcedure(custId);

            // insert wait element until all backend processes are finished
            System.out.println("Proceeding with 60sec waiting timer until all backend process are performed");
            Thread.sleep(1000*60);

            // step update contract
            updatingCustomer.updateContract(custId);

            // step update customer status to become active
            updatingCustomer.updateCustomerStatus(custId);

            // step validate email address
            updatingCustomer.updateEmailValidation(custId, AddressTypes.Rechnungsanschrift);

            // step activate phone
            updatingCustomer.activatePhone(custId);

            if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.DGH)) {
                // insert wait element until all backend processes are finished
                System.out.println("Proceeding with 2min waiting timer until all backend process are performed");
                Thread.sleep(1000*60*2);

                // step activating CO Product
                updatingCustomer.activateCoProduct(custId);

                // step activating CO Voice Product
                updatingCustomer.activateCoVoiceProduct(custId);
            }

            // step getting activated products and listed it
            backDays.getProducts(resource.getCustomerId());
            HashMap<String, Integer> product = resource.getCoProductIdList();
            System.out.println("Activated Products for Customer ID " + resource.getCustomerId());
            for ( Map.Entry<String, Integer> entry : product.entrySet()) {
                String desc = entry.getKey();
                Integer coId = entry.getValue();
                System.out.println("Product Description " + desc + " and Product ID " + coId);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Check UC Status ID<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs check of UC Status ID<br>
     * If status id is different from expected, process is aborted<br>
     * Timer is also implemented due to OMI processing time of changing status<br>
     * UC-Status ID is taken from Customer_address table
     * @param custId int
     * @param addType AddressTypes
     * @param status AddressStatuses
     */

    public void checkUcStatus(int custId, AddressTypes addType, AddressStatuses status) {
        System.out.println("Step checking UC-Status");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            TestingResource resource = new TestingResource();
            // take address status
            dghQueries.getCustomerAddress(custId, addType);
            // counter to prevent infinitive loop
            int counter = 0;
            // wait until UC-Status become desired value
            while (resource.getCustomerAddressStatusId() != status.option && counter < 15) {
                // take address status
                dghQueries.getCustomerAddress(custId, addType);
                int ucStatus = resource.getCustomerAddressStatusId();
                System.out.println("Current UC-Status is " + ucStatus);
                if (ucStatus == AddressStatuses.TZIP_search.option || ucStatus == AddressStatuses.TZIP_found.option) {
                    Thread.sleep(1000*60);
                } else if (ucStatus == AddressStatuses.CAMEL_denied.option) {
                    SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
                    updatingCustomer.updateUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.HOMES_served.option);
                    break;
                } else if (ucStatus == AddressStatuses.HOMES_served.option) {
                    break;
                } else if (ucStatus != AddressStatuses.UNIT_no_free_unit.option) {
                    //Thread.sleep(15000);
                    SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
                    updatingCustomer.updateUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.UNIT_found.option);
                } else {
                    SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
                    updatingCustomer.updateUcStatus(custId, AddressTypes.Installationsanschrift, AddressStatuses.UNIT_found.option);
                    //Assert.fail("UC-Status is UNIT no free unit (" + resource.getCustomerAddressStatusId() + ") and activation flow is aborted");
                }
                // increment counter to prevent infinitive loop
                counter++;
            }
            // stop activation process since UC status is not expected
            if ((resource.getCustomerAddressStatusId() != status.option) && (resource.getCustomerAddressStatusId() != AddressStatuses.HOMES_served.option)) {
                Assert.fail("UC-Status is " + resource.getCustomerAddressStatusId() + " and activation flow is aborted");
            } else {
                System.out.println("Current UC-Status is " + resource.getCustomerAddressStatusId());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Check Customer Status ID<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs check of Customer Status ID<br>
     * If status id is different from expected, process is aborted<br>
     * Timer is also implemented due to OMI processing time of changing status<br>
     * Customer Status ID is taken from Customer table
     * @param custId int
     * @param status CustomerStatuses
     */

    public void checkCustomerStatus(int custId, CustomerStatuses status) {
        System.out.println("Step checking customer's status");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            TestingResource resource = new TestingResource();
            // take customer's data
            dghQueries.getCustomer(custId);

            // counter to prevent infinitive loop
            int counter = 0;
            int countNachbearbeitung = 0;
            // wait until Customer Status become desired value
            while (resource.getCustomerStatusId() != status.option && counter < 10) {
                System.out.println("Current Customer Status is " + resource.getCustomerStatusId());
                counter++;
                // check if customer status is Nachbearbeitung
                if (resource.getCustomerStatusId() == CustomerStatuses.Nachbearbeitung.option) {
                    countNachbearbeitung++;
                    // break the loop if counter is reached
                    if (countNachbearbeitung == 3) {
                        break;
                    }
                }
                Thread.sleep(15000);
                // take customer's data
                dghQueries.getCustomer(custId);
            }

            // update status to 10003 if status is 10001
            if (resource.getCustomerStatusId() == CustomerStatuses.Nachbearbeitung.option) {
                /*int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AEB_zu_verschicken.option));
                Thread.sleep(500);
                System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AEB_zu_verschicken);
                count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AEB_verschickt.option));
                Thread.sleep(500);
                System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AEB_verschickt);*/
                DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
                dghUpdateQueries.updateCustomerStatusUpToAbVerschickt(custId);
            } else if (resource.getCustomerStatusId() != status.option) {
                // stop activation flow if customer status is not expected
                Assert.fail("Customer Status is " + resource.getCustomerStatusId() + " and activation flow is aborted");
            } else {
                System.out.println("Current Customer Status is " + resource.getCustomerStatusId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
