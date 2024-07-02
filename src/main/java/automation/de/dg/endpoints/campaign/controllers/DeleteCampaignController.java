package automation.de.dg.endpoints.campaign.controllers;

import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.de.dg.endpoints.constants.CampaignUriPath;
import automation.endpoints.enumaration.RestApiMethodTypes;
import io.restassured.response.Response;

/**
 * <b>RestAPI : Campaign Suite</b> Delete Method Test Suite
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Delete method type<br>
 */

public class DeleteCampaignController extends CommonTest  {

    /**
     * <b>[Method]</b> - Delete All Campaigns<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality deletes all Campaigns
     */

    public void deleteAllCampaigns(String houseID, int expectedCode) {
        try {
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.DELETE, CommonTest.getRestApiUrl() + CampaignUriPath.REST_URL + CampaignUriPath.HOUSE_INSTANCE + "/" + houseID);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), expectedCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Delete Campaign Instance<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality deletes Campaign Instance
     */

    public void deleteCampaignInstance(String instance, int expectedCode) {
        try {
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.DELETE, CommonTest.getRestApiUrl() + CampaignUriPath.REST_URL + CampaignUriPath.CAMPAIGN_INSTANCES + "/" + instance);
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), expectedCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
