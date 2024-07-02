package automation.de.dg.endpoints.cimrest.customers.testsuite;


import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.controllers.GetCustomer;
import automation.de.dg.endpoints.constants.CimUriPath;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.utilities.Log;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <b>RestAPI : Campaign Suite</b> Getting Contract detail Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover getting Contract details.
 */

public class SuiteGetContractDetail {

    /**
     * <b>[Test Method]</b> - Test Case getting Contract detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Contract detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  Execute Request
     */

    public void flowGetContractDetailNewCustomer(String url, int customerId) {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();
            DghQueryManipulation dghQueries = new DghQueryManipulation();

            // step 1 - execute request
            Log.info("Step - Getting and Verifying Contract Details");
            Response response = getCustomer.getCustomer(url + CimUriPath.CUSTOMER_INSTANCE + customerId + "/" +  CimUriPath.CONTRACT_INSTANCE, String.valueOf(resource.getContractId()));

            // get Contract ID from database
            dghQueries.getCustomerValidContract(customerId);
            // step 2 - checking expected data from response
            dghQueries.getCustomerValidContract(customerId);
            ArrayList array = response.jsonPath().get();
            HashMap<String, ?> object = (HashMap<String, String>) array.get(0);

            // verifying proper data are written
            int id = (int) object.get("id");
            Assert.assertEquals(id, resource.getContractId(), "Actual customer's contract id is not " + resource.getContractId() + ", but " + id);
            String channel = (String) object.get("channel");
            Assert.assertEquals(channel.toUpperCase(), "ONLINE", "Actual customer's channel is not ONLINE, but " + channel);
            String evnType = (String) object.get("evnType");
            Assert.assertEquals(evnType.toUpperCase(), "ANONYMOUS", "Actual customer's evnType is not ANONYMOUS, but " + channel);
            String status = (String) object.get("status");
            Assert.assertEquals(status.toUpperCase(), "ORDER_ENTRY", "Actual customer's evnType is not ORDER_ENTRY, but " + status);
            String statusCategory = (String) object.get("statusCategory");
            Assert.assertEquals(statusCategory.toUpperCase(), "ORDER_RECEIVED", "Actual customer's evnType is not ORDER_RECEIVED, but " + statusCategory);
            String processState = (String) object.get("processState");
            Assert.assertEquals(processState.toUpperCase(), "NEW", "Actual customer's evnType is not NEW, but " + processState);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
