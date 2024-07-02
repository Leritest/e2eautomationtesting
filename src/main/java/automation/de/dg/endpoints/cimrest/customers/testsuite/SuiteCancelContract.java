package automation.de.dg.endpoints.cimrest.customers.testsuite;

import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.controllers.PatchContract;
import automation.de.dg.enumation.CustomerStatuses;
import automation.de.dg.utils.config.TestingResource;
import org.json.JSONObject;
import org.testng.Assert;

/**
 * <b>RestAPI : CIM Rest Suite</b> Cancel Contract Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover cancelling contract flow.
 */

public class SuiteCancelContract {

    /**
     * <b>[Test Method]</b> - Test Case cancelling Contract<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of cancelling Contract<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Cancelling Contract<br>
     * 2. Validating Proper Customer's status from DB
     *
     * @param custId int
     * @param coId int
     * @throws IllegalArgumentException
     */

    public void flowCancellingContract(int custId, int coId) throws IllegalArgumentException {
        try {
            // step 1 - cancelling contract
            PatchContract patchContract = new PatchContract();
            // preparing body
            JSONObject body = patchContract.bodyDeactivation();
            // executing request
            patchContract.cancelContract(custId, coId, String.valueOf(body));

            // step 2 - validating proper customer status
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            TestingResource resource = new TestingResource();
            // take customer's data
            dghQueries.getCustomer(custId);
            // validating customer's status
            if (resource.getCustomerStatusId() == CustomerStatuses.Kündigung_in_Bearbeitung.option) {
                System.out.println("Customer ID " + custId + " is in process of cancelling contract and status is " + CustomerStatuses.Kündigung_in_Bearbeitung);
            } else {
                Assert.fail("For customer ID " + custId + " status is " + resource.getCustomerStatusId() + " instead of " + CustomerStatuses.Kündigung_in_Bearbeitung);
            }
        } catch(IllegalArgumentException ie) {
            throw new RuntimeException(ie);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
