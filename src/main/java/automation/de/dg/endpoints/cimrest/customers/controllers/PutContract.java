package automation.de.dg.endpoints.cimrest.customers.controllers;


import automation.de.dg.endpoints.constants.CimUriPath;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.endpoints.enumaration.RestApiMethodTypes;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import automation.utilities.TestDataGenerator;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * <b>RestAPI : Cim Rest Suite/Customer</b> Put Method Test Suite<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Put method type<br>
 *  under Customers controller umbrella
 */

public class PutContract extends CommonTest {

    /**
     * <b>[Method]</b> - Sign VZF<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sign VZF
     */

    public Response signVzf(String url, int custId, int coId, String body) {
        try {
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.PUT, url +
                    CimUriPath.CUSTOMER_INSTANCE + custId + "/" + CimUriPath.CONTRACT_INSTANCE + coId);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <b>[Method]</b> - Body for Sign VZFbr>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality create Body for signing VZF
     */

    public JSONObject bodySignVzf() {
        JSONObject body = new JSONObject();
        JSONObject vzf = new JSONObject();
        // fulfill vzf data
        vzf.put("confirmedDate", TestDataGenerator.getCurrentDate());
        vzf.put("confirmed", "YES");
        vzf.put("channel", "EMAIL");
        vzf.put("created", "true");

        body.put("vzf", vzf);
        return body;
    }

}
