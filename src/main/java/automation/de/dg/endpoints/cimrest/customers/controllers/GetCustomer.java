package automation.de.dg.endpoints.cimrest.customers.controllers;

import automation.de.dg.endpoints.constants.CimUriPath;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.endpoints.enumaration.RestApiMethodTypes;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import io.restassured.response.Response;

import static org.testng.AssertJUnit.fail;

/**
 * <b>RestAPI : Cim Rest Suite/Customer</b> Get Method Test Suite<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Get method type<br>
 *  under Customers controller umbrella
 */

public class GetCustomer extends CommonTest {

    /**
     * <b>[Method]</b> - Get Customer<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get customer's data
     */

    public Response getCustomer(String url, String instance) {
        try {
            // check if customer ID is empty or invalid
            if (instance.equals("") || instance.isEmpty()) {
                fail("Customer ID " + instance + " cannot be empty");
            }
            String body = null;
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, url);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Customer's Main Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get customer's main Product
     */

    public Response getMainProduct(int cuId, int coId) {
        try {
            String body = null;
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_v3 +
                    CimUriPath.CUSTOMER_INSTANCE + cuId + "/" + CimUriPath.CONTRACT_INSTANCE + coId + "/main_product?embed=productInstances");
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
