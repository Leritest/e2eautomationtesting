package automation.de.dg.endpoints.cimrest.customers.controllers;

import automation.de.dg.endpoints.constants.CimUriPath;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.endpoints.enumaration.RestApiMethodTypes;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import automation.utilities.TestDataGenerator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class PatchContract extends CommonTest {

    //##################################################################################################################
    //###                                            Cancel Contract
    //##################################################################################################################

    /**
     * <b>[Method]</b> - Cancel Contract<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality cancel contract
     */

    public Response cancelContract(int customerId, int contractId, String body) {
        try {
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.PATCH, CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_V4 +
                    CimUriPath.CUSTOMER_INSTANCE + customerId + "/" + CimUriPath.CONTRACT_INSTANCE +  contractId + "/cancel");
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Body for Cancel Contract<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality create Body for Cancel Contract
     */
    public JSONObject bodyDeactivation() {
        JSONObject body = new JSONObject();
        body.put("cancelType", "TERMINATION");
        body.put("reason", "INTERNAL_CORRECTION");
        body.put("terminationDate", TestDataGenerator.getCurrentDate());
        body.put("note", "string");

        return body;
    }


    //##################################################################################################################
    //###                                            Add Product
    //##################################################################################################################

    /**
     * <b>[Method]</b> - Add Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality add Product
     */

    public Response addProduct(int customerId, int contractId, int mainId, String body) {
        try {
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.PATCH, CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_V4 +
                    CimUriPath.CUSTOMER_INSTANCE + customerId + "/" + CimUriPath.CONTRACT_INSTANCE +  contractId + "/main_product/" + mainId + "/save_product_changes");
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Body for Add Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality create Body for adding Product
     */
    public JSONObject bodyAddProduct(int prodId) {
        JSONObject body = new JSONObject();
        JSONArray attr = new JSONArray();
        body.put("dateActive", TestDataGenerator.getCurrentDate());
        body.put("categoryId", 10000);
        body.put("documentType", 5);
        body.put("contractProducts", attr);
        body.put("terminationProducts", attr);

        JSONObject newProducts = new JSONObject();
        newProducts.put("id", prodId);
        newProducts.put("instanceCount", 1);
        newProducts.put("attributes", attr);

        attr = new JSONArray();
        attr.put(newProducts);
        body.put("newProducts", attr);

        return body;
    }

    //##################################################################################################################
    //###                                            Create Contract
    //##################################################################################################################

    /**
     * <b>[Method]</b> - Create Customer<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality create customer<br>
     * and check if response code is 200 OK
     * @return response Response
     */

    public Response createCustomer(String url, String body) {
        try {
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.PATCH, url +
                    CimUriPath.CUSTOMER_INSTANCE + "create_contract");
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
