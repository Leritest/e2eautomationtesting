package automation.de.dg.endpoints.campaign.testsuite;

import automation.de.dg.database.manipulation.queryManipulation.PortfolioQueryManipulation;
import automation.de.dg.endpoints.campaign.controllers.DeleteCampaignController;
import automation.de.dg.endpoints.campaign.controllers.GetCampaignController;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import org.testng.Assert;

/**
 * <b>RestAPI : Campaign Suite</b> Deleting Campaign Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover delete Campaign(s) scenarios.
 */

public class SuiteDeletingCampaign {

    /**
     * <b>[Method]</b> - Flow of deleting Campaign Instance<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality delete Campaign Instance<br>
     ** <i>Steps of this scenario:</i><br>
     * Pre-requirement steps<br>
     * 1. Find Campaign Instance from DB<br>
     *
     * Executing steps<br>
     * 2. Delete Campaign Instance<br>
     * 3. Verify Campaign Instance is not returned in Get response<br>
     * 4. Verify Campaign Instance is deleted from DB<br>
     */
    public void flowDeleteCampaignInstance() {
        try {
            // step 1 - find Campaign Instance
            System.out.println("----------------------------------------------");
            System.out.println("Step 1 - find Campaign Instance");
            PortfolioQueryManipulation portfolioQueries = new PortfolioQueryManipulation();
            String id = portfolioQueries.getCampaignInstanceForOneHouse();
            System.out.println("Selected Campaign Instance: " + id);

            // step 2 - delete Campaign Instance
            System.out.println("----------------------------------------------");
            System.out.println("Step 2 - delete Campaign Instance");
            DeleteCampaignController deleteCampaign = new DeleteCampaignController();
            deleteCampaign.deleteCampaignInstance(id, RestApiHttpStatusCodes.NO_CONTENT.option);

            // step 3 - check if campaign instance is not found
            System.out.println("----------------------------------------------");
            System.out.println("Step 3 - check if campaign instance is not found using get instance endpoint");
            GetCampaignController getCampaign = new GetCampaignController();
            getCampaign.getCampaignInstance(id, RestApiHttpStatusCodes.NOT_FOUND.option);

            // step 4 - check record is deleted from database
            System.out.println("----------------------------------------------");
            System.out.println("Step 4 - check record is deleted from database");
            if (portfolioQueries.getCampaignInstance("uuid", id)) {
                Assert.fail("Campaign Instance is not deleted from campaign_instance table");
            } else {
                System.out.println("Campaign Instance " + id + " is deleted from campaign_instance table");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
