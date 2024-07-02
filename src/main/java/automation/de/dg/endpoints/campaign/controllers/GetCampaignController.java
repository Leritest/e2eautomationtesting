package automation.de.dg.endpoints.campaign.controllers;

import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.de.dg.endpoints.constants.CampaignUriPath;
import automation.endpoints.enumaration.RestApiMethodTypes;
import io.restassured.response.Response;

import java.util.ArrayList;

/**
 * <b>RestAPI : Campaign Suite</b> Get Method Test Suite
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Get method type<br>
 */

public class GetCampaignController extends CommonTest  {

    /**
     * <b>[Method]</b> - Get Valid Campaign(s)<br>
     * <i>Method functionality:</i><br>
     * This functionality returns valid Campaign(s)<br>
     * assigned to House ID
     */

    public String getValidCampaign(String houseId) {
        // get response value
        Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, CommonTest.getRestApiUrl() + CampaignUriPath.REST_URL + CampaignUriPath.HOUSE_INSTANCE + "/" + houseId);
        // check proper HTTP response code is in return
        checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
        //
        ArrayList array = response.jsonPath().getJsonObject("campaignName");
        String campaign = (String) array.get(0);
        return campaign;
    }

    /**
     * <b>[Method]</b> - Get Campaign Instance<br>
     * <i>Method functionality:</i><br>
     * This functionality returns Campaign Instance
     */

    public Response  getCampaignInstance(String instance, int expectedCode) {
        // get response value
        Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.GET, CommonTest.getRestApiUrl() + CampaignUriPath.REST_URL + CampaignUriPath.CAMPAIGN_INSTANCES + "/" + instance);
        // check proper HTTP response code is in return
        checkStatusCode(response.getStatusCode(), expectedCode);
        // return response
        return response;
    }

}
