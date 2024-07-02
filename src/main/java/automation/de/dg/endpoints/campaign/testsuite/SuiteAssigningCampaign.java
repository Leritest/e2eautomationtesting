package automation.de.dg.endpoints.campaign.testsuite;

import automation.de.dg.database.manipulation.queryManipulation.PlmQueryManipulation;
import automation.de.dg.database.manipulation.queryManipulation.PortfolioQueryManipulation;
import automation.de.dg.endpoints.campaign.controllers.GetCampaignController;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import automation.de.dg.domain.enums.DgfChannels;
import automation.de.dg.domain.enums.DgfMarketingSegment;
import automation.endpoints.CommonTest;
import automation.de.dg.endpoints.campaign.controllers.DeleteCampaignController;
import automation.de.dg.endpoints.campaign.controllers.PostCampaignController;
import automation.utilities.TestDataGenerator;
import org.json.JSONObject;
import org.testng.Assert;

/**
 * <b>RestAPI : Campaign Suite</b> Assigning Campaign Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover assign Campaign to House ID scenarios.
 */

public class SuiteAssigningCampaign extends CommonTest {

    /**
     * <b>[Method]</b> - Flow of assigning Campaign Instance to House ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality assign Campaign Instance to House ID<br>
     ** <i>Steps of this scenario:</i><br>
     * Pre-requirement steps<br>
     * 1. Find some active Campaign from DB<br>
     * 2. Find some House ID<br>
     *
     * Executing steps<br>
     * 3. Delete Campaign(s) assigned to House ID<br>
     * 4. Assign Campaign to House ID<br>
     * 5. Verify proper Campaign is returned as valid<br>
     */

    public void flowAssignCampaignInstance() {
        try {
            // step 1 - find Campaign
            PlmQueryManipulation plmQueries = new PlmQueryManipulation();
            String[] value = plmQueries.getAvailableCampaigns(DgfMarketingSegment.DGH);
            System.out.println("Chosen Campaign: " + value[1]);

            // step 2 - find House ID
            //AcQueryManipulation acQueries = new AcQueryManipulation();
            //acQueries.getHouseId();
            String houseId = TestDataGenerator.houseIds[TestDataGenerator.generateRandomNumber(0, TestDataGenerator.houseIds.length-1)];
            System.out.println("Selected House ID:" + houseId);

            // step 3 - delete existing Campaigns linked to House ID
            PortfolioQueryManipulation portfolioQueries = new PortfolioQueryManipulation();
            DeleteCampaignController deleteCampaign = new DeleteCampaignController();
            // check if House ID exists in Address table
            boolean hasRecord = portfolioQueries.getHouseId(houseId);
            // delete all records from Address table
            if (hasRecord) {
                deleteCampaign.deleteAllCampaigns(houseId, RestApiHttpStatusCodes.NO_CONTENT.option);
            } else {
                deleteCampaign.deleteAllCampaigns(houseId, RestApiHttpStatusCodes.NOT_FOUND.option);
            }

            // step 4 - add Campaign to House ID
            PostCampaignController postCampaing = new PostCampaignController();
            // generate body
            JSONObject campaign = postCampaing.setCampaignInstance(Integer.parseInt(value[0]), value[1], DgfMarketingSegment.DGH.option.toUpperCase(), 1, DgfChannels.ALL.option.toUpperCase());
            JSONObject body = postCampaing.setPostCampaign(houseId, campaign);
            // execute Post endpoint
            postCampaing.assignValidCampaign(String.valueOf(body));

            // step 5 - check if Campaign becomes valid
            GetCampaignController getCampaign = new GetCampaignController();
            String returnedCampaign = getCampaign.getValidCampaign(houseId);
            Assert.assertEquals(value[1], returnedCampaign, "Returned Campaign is not same as expected");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
