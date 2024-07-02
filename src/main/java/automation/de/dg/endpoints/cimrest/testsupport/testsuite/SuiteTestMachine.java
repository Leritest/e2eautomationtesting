package automation.de.dg.endpoints.cimrest.testsupport.testsuite;

import automation.de.dg.enumation.BackDays;
import automation.de.dg.endpoints.cimrest.testsupport.controllers.PatchTimemachine;
import io.restassured.response.Response;

/**
 * <b>RestAPI : CIM Rest Suite</b> Test Machine Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover flow of changing days to the customer.
 */

public class SuiteTestMachine {

    /**
     * <b>[Test Method]</b> - Test Case changing date of Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of changing Product's date<br>
     *
     * @param custId int
     * @param bd BackDays
     */

    public void flowChangeDays(int custId, BackDays bd) {
        try {
            // initialize classes
            PatchTimemachine patchTime = new PatchTimemachine();
            Response response;
            // step - change date
            if (bd.option > 0) {
                response = patchTime.addDays(custId, Math.abs(bd.option));
            } else {
                response = patchTime.subtractDays(custId, Math.abs(bd.option));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
