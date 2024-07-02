package automation.de.dg.salesforce.endpoints.controllers;

import automation.de.dg.database.sf.queries.SfQueries;
import automation.de.dg.salesforce.contstants.SalesforceUriPath;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.endpoints.enumaration.RestApiMethodTypes;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import automation.utilities.Log;
import io.restassured.response.Response;

import static org.testng.AssertJUnit.fail;

/**
 * <b>SF/API/AccountAPI: Salesforce API Rest Suite/Customer</b> Get Method Test Suite<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Get method type<br>
 *  under SF Account controller umbrella
 */

public class SfGetCustomer extends CommonTest {

    /**
     * <b>[Method]</b> - Get Account ID from Customer Number<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get Account's ID from CIM Customer Number
     * @param query String
     */

    public Response getDataBasedOnQuery(String query) {
        try {
            String body = null;
            // initialize request's body
            RestApiPreparationData.setBody(body);

            // define request's URL
            String url = CommonTest.getRestApiUrl() + SalesforceUriPath.QUERY_PATH_URL + query;

            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, url);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cannot find instance for query " + query);
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Account's Phone<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get Account's Phone
     * @param instance String
     */

    public Response getPhoneFromAccountId(String instance) {
        try {
            // check if customer ID is empty or invalid
            if (instance.equals("") || instance.isEmpty()) {
                fail("Account ID " + instance + " cannot be empty");
            }
            String body = null;
            // initialize request's body
            RestApiPreparationData.setBody(body);

            // define request's URL
            String url = CommonTest.getRestApiUrl() + SalesforceUriPath.QUERY_PATH_URL + SfQueries.getPhoneNumber(instance);

            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, url);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cannot find instance for Customer ID " +instance);
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Customer Details<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get customer's data
     * @param instance String
     */

    public Response getAccountData(String path, String instance) {
        try {
            // check if customer ID is empty or invalid
            if (instance.equals("") || instance.isEmpty()) {
                fail("Account ID " + instance + " cannot be empty");
            }
            String body = null;
            // initialize request's body
            RestApiPreparationData.setBody(body);

            // define request's URL
            String url = CommonTest.getRestApiUrl() + path + instance;

            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, url);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            fail("Cannot find instance for Customer ID " + instance);
            throw new RuntimeException(e);
        }
    }

}
