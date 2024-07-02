package automation.de.dg.endpoints.campaign.testsuite;

import automation.de.dg.database.manipulation.queryManipulation.PortfolioQueryManipulation;
import automation.de.dg.endpoints.campaign.controllers.GetCampaignController;
import automation.de.dg.domain.CampaignInstances;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;

/**
 * <b>RestAPI : Campaign Suite</b> Getting Campaign detail Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover getting Campaign details.
 */

public class SuiteGettingCampaignDetail {

    /**
     * <b>[Method]</b> - Flow of getting Campaign detail<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality getting Campaign detail<br>
     ** <i>Steps of this scenario:</i><br>
     * Pre-requirement steps<br>
     * 1. Find Campaign Instance from DB<br>
     *
     * Executing steps<br>
     * 2. Delete Campaign Instance<br>
     * 3. Verify Campaign Instance is not returned in Get response<br>
     * 4. Verify Campaign Instance is deleted from DB<br>
     */

    public void flowGetCampaingInstanceInfo() {
        try {
            // step 1 - find Campaign Instance
            System.out.println("----------------------------------------------");
            System.out.println("Step 1 - find Campaign Instance");
            PortfolioQueryManipulation portfolioQueries = new PortfolioQueryManipulation();
            CampaignInstances campaign = new CampaignInstances();
            portfolioQueries.getCampaignInstance("uuid", "");
            String id = campaign.getUuid();
            System.out.println("Selected Campaign Instance: " + id);

            // step 2 - get Campaign Instance
            System.out.println("----------------------------------------------");
            System.out.println("Step 2 - call Campaign Instance endpoint");
            GetCampaignController getCampaign = new GetCampaignController();
            Response response = getCampaign.getCampaignInstance(id, RestApiHttpStatusCodes.OK.option);

            // step 3 - validate Campaign Instance with Database records
            System.out.println("----------------------------------------------");
            System.out.println("Step 3 - validating Campaign Instance response with db value using assertion");
            checkCampaignResponse(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void checkCampaignResponse(Response response) {
        try {
            String uuid = response.jsonPath().getJsonObject("campaignInstanceId");
            int id = response.jsonPath().getJsonObject("campaignId");
            String name = response.jsonPath().getJsonObject("campaignName");
            String segment = response.jsonPath().getJsonObject("marketingSegment");
            String channel = response.jsonPath().getJsonObject("channel");
            int ranking = response.jsonPath().getJsonObject("ranking");

            CampaignInstances campaign = new CampaignInstances();
            // verify uuid is same
            Assert.assertEquals(uuid, campaign.getUuid(), "Campaign Instance " + campaign.getUuid() + " is not displayed in Get Campaign Instance response");
            // verify campaign id is same
            Assert.assertEquals(id, campaign.getCampaignId(), "Campaign ID " + campaign.getCampaignId() + " is not displayed in Get Campaign Instance response");
            // verify campaign name is same
            Assert.assertEquals(name, campaign.getCampaignName(), "Campaign Name " + campaign.getCampaignName() + " is not displayed in Get Campaign Instance response");
            // verify marketing segment is same
            Assert.assertEquals(segment, campaign.getSegment().option.toUpperCase(), "Campaign Marketing Segment " + campaign.getSegment() + " is not displayed in Get Campaign Instance response");
            // verify channel is same
            Assert.assertEquals(channel, campaign.getChannel().option.toUpperCase(), "Campaign Channel " + campaign.getChannel() + " is not displayed in Get Campaign Instance response");
            // verify ranking is same
            Assert.assertEquals(ranking, campaign.getRanking(), "Campaign Ranking " + campaign.getRanking() + " is not displayed in Get Campaign Instance response");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
