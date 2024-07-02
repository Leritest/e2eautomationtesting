package automation.de.dg.endpoints.cimrest.testsupport.controllers;

import automation.de.dg.endpoints.constants.CimUriPath;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.endpoints.enumaration.RestApiMethodTypes;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import io.restassured.response.Response;

/**
 * <b>RestAPI : Cim Rest Suite/Timemachine</b> Patch Method Test Suite<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Patch method type<br>
 *  under Testsupport controller umbrella
 */

public class PatchTimemachine extends CommonTest {

    /**
     * <b>[Method]</b> - Add days<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality add days to product and contract
     */

    public Response addDays(int custId, int days) throws IllegalArgumentException {
        try {
            // initialize request's body
            RestApiPreparationData.setBody("");
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.PATCH, CommonTest.getRestApiUrl() + CimUriPath.REST_URL_no_v +
                    CimUriPath.TEST_SUPPORT + "add?customerId=" + custId + "&daysToTravel=" + days);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (IllegalArgumentException ie) {
            throw new RuntimeException(ie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Substract days<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality substract days to product and contract
     */

    public Response subtractDays(int custId, int days) throws IllegalArgumentException {
        try {
            // initialize request's body
            RestApiPreparationData.setBody("");
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.PATCH, CommonTest.getRestApiUrl() + CimUriPath.REST_URL_no_v +
                    CimUriPath.TEST_SUPPORT + "subtract?customerId=" + custId + "&daysToTravel=" + days);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (IllegalArgumentException ie) {
            throw new RuntimeException(ie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
