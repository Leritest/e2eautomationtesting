package de.dg.endpoints.apiSuite.campaign;

import automation.endpoints.CommonTest;
import automation.de.dg.endpoints.campaign.testsuite.SuiteGettingCampaignDetail;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * <b>RestAPI Feature : API Suite</b> Get Campaign
 */

@Listeners(automation.listeners.ExtentApiListener.class)
public class TestSuiteApiCampaignGet {

    CommonTest common = new CommonTest();
    SuiteGettingCampaignDetail getCampaign = new SuiteGettingCampaignDetail();

    /**
     * <b>[Test Method]</b> - Test Case getting Campaign Detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Campaign details<br>
     */

    @Test
    public void tcGetCampaignInstance() {
        getCampaign.flowGetCampaingInstanceInfo();
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
