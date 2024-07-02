package automation.de.dg.endpoints.cimrest.customers.testsuite;

import automation.de.dg.endpoints.cimrest.customers.controllers.GetCustomer;
import automation.de.dg.endpoints.cimrest.customers.controllers.PatchContract;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * <b>RestAPI : CIM Rest Suite</b> Save Product Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover flow of adding Product to Customer.
 */

public class SuiteSaveProduct {

    /**
     * <b>[Test Method]</b> - Test Case adding Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of adding Product<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Find Main Product ID<br>
     * 2. Prepare Request's Body
     * 3. Execute Request<br>
     *
     * @param custId int
     * @param prodId int
     * @throws IllegalArgumentException
     */

    public void flowAddProduct(int custId, int coId, int prodId) {
        try {
            // initialize classes
            GetCustomer getCustomer = new GetCustomer();
            PatchContract patchContract = new PatchContract();

            // step 1 - get Main Product
            Response response = getCustomer.getMainProduct(custId, coId);
            int mainProductId = response.jsonPath().getJsonObject("id");

            // step 2 - prepare body
            JSONObject body = patchContract.bodyAddProduct(prodId);

            // step 3 - execute request
            patchContract.addProduct(custId, coId, mainProductId, String.valueOf(body));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
