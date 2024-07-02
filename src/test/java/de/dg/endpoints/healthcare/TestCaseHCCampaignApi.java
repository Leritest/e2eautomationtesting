package de.dg.endpoints.healthcare;

import automation.de.dg.endpoints.campaign.testsuite.HealthCheckSuite;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import automation.endpoints.CommonTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

/**
 * <b>RestAPI Feature : Health Care Suite</b> Campaign API
 */

@Listeners(automation.listeners.ExtentListeners.class)
public class TestCaseHCCampaignApi {

    HealthCheckSuite healthCheck = new HealthCheckSuite();
    CommonTest common = new CommonTest();

    /**
     * <b>[Test Method]</b> - Verify that Get Campaign Instance Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Get Campaign Instance Endpoint is up<br>
     */

    @Test
    public void tcVerifyGetCampaignInstanceUp() {
        try {
            healthCheck.checkGetCampaignInstanceUp();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Verify that Get Campaign All Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Get Campaign All Endpoint is up<br>
     */

    @Test
    public void tcVerifyGetCampaignAllUp() {
        try {
            healthCheck.checkGetCampaignAllUp();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Verify that Get Valid Campaign Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Get valid Campaign Endpoint is up<br>
     */

    @Test
    public void tcVerifyGetValidCampaignUp() {
        try {
            healthCheck.checkGetValidCampaignUp();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Verify that Post Campaign Instance Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Post Campaign Instance Endpoint is up<br>
     */

    @Test
    public void tcVerifyPostCampaignInstanceUp() {
        try {
            healthCheck.checkPostCampaignInstanceUp();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Verify that Put Campaign Instance Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Put Campaign Instance Endpoint is up<br>
     */

    @Test
    public void tcVerifyPutCampaignInstanceUp() {
        try {
            healthCheck.checkPutCampaignInstanceUp();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Verify that Delete Campaign Instance Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Delete Campaign Instance Endpoint is up<br>
     */

    @Test
    public void tcVerifyDeleteCampaignInstanceUp() {
        try {
            healthCheck.checkDeleteCampaignInstanceUp();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Verify that Delete All Campaigns Endpoint is up<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality verifies that Delete Campaign Instance Endpoint is up<br>
     */

    @Test
    public void tcVerifyDeleteAllCampaignsUp() {
        try {
            healthCheck.checkDeleteAllCampaigns();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
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
