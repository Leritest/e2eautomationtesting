package automation.de.dg.endpoints.cimrest.customers.testsuite;

import automation.de.dg.endpoints.cimrest.customers.controllers.PutContract;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.utilities.Log;
import org.json.JSONObject;

/**
 * <b>RestAPI : CIM Rest Suite</b> Signing VZF Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover flow of signing VZF.
 */

public class SuiteSignVzf {


    /**
     * <b>[Test Method]</b> - Test Case signing VZF<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of signing VZF<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Prepare Body for Request
     * 2. Execute Request
     *
     * @param custId int
     * @param coId int
     * @throws IllegalArgumentException
     */

    public void flowSignVzf(int custId, int coId) {
        try {
            Log.info("Step - Signing VZF");

            // initialize classes
            PutContract putContract = new PutContract();

            // step 1 - prepare body
            JSONObject body = putContract.bodySignVzf();

            // step 2 - execute request
            putContract.signVzf(StaticContext.getCimInstanceUrl(), custId, coId, String.valueOf(body));
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

}
