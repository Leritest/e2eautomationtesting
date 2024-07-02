package de.dg.endpoints.apiSuite.campaign;

import automation.endpoints.CommonTest;
import automation.de.dg.endpoints.campaign.testsuite.SuiteDeletingCampaign;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI Feature : API Suite</b> Delete Campaign
 */

@Listeners(automation.listeners.ExtentApiListener.class)
public class TestSuiteApiCampaignDelete {

    CommonTest common = new CommonTest();
    SuiteDeletingCampaign deleteCampaign = new SuiteDeletingCampaign();

    /**
     * <b>[Test Method]</b> - Test Case deleting Campaign Instance<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs deleting Campaign Instance<br>
     */

    @Test
    public void tcDeleteCampaignInstance() {
        deleteCampaign.flowDeleteCampaignInstance();
    }

    /**
     * <b>[Test Method]</b> - Set Up for RestApi<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize API name used for testing
     * Next step will be setup action in CommonTest class under RestAPI package
     * which allows us to prepare base url and<br>
     * BeforeSuite - annotation
     */

    @BeforeSuite
    public void setupApiName() {
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.CAMPAIGN);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.BASIC);
        // initialize RestApi URL
        common.setup();
    }

}
