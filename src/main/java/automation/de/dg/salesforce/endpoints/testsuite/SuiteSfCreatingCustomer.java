package automation.de.dg.salesforce.endpoints.testsuite;

import automation.de.dg.contstants.RestApiUrls;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteCreatingCustomerDetail;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import automation.utilities.Log;

/**
 * <b>SF/API/TestSuite: Create SF Account Suite</b> Creating Account Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover creating Customer on Salesforce.
 */

public class SuiteSfCreatingCustomer {

    /**
     * <b>[Test Method]</b> - Test Case creating Customer on Salesforce<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of creating Salesforce Customer<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  1. Execute POST Customer Creation Request<br>
     *  2. Check if Customer is visible on Salesforce<br>
     * @param portfolio Portfolios
     * @param tariff int
     * @param waipu WaipuTypes
     * @param router RouterTypes
     */

    public void flowSfCustomerCreation(Portfolios portfolio, int tariff, WaipuTypes waipu, RouterTypes router) {
        try {
            // initialize classes
            SuiteCreatingCustomerDetail createCustomer = new SuiteCreatingCustomerDetail();
            SuiteSfGettingCustomerDetail getCustomer = new SuiteSfGettingCustomerDetail();

            // prepare CIM API environment
            prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);

            // Step 1 - execute CIM request to create Customer
            Log.info("Step - Customer Creation on CIM");
            Log.info("Portfolio: " + portfolio + ", Tariff: " + tariff + ", Router Type: " + router + ", Waipu Type: " + waipu);
            createCustomer.flowCustomerCreation(portfolio, tariff, waipu, router);

            // prepare SF API environment
            prepareApiEnvironment(RestApiNames.SALESFORCE, RestApiAuth.TOKEN);
            // initialize Token base URL
            CommonTest.setTokenUrl(RestApiUrls.SALESFORCE_OAUTH_TOKEN_URL);

            // Step 2 - check if Salesforce Account is created
            Log.info("Step - Verifying Customer on Salesforce");
            getCustomer.flowGettingAccountData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Prepare API environment<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize API environment
     * @param api RestApiNames
     * @param auth RestApiAuth
     */
    public void prepareApiEnvironment(RestApiNames api, RestApiAuth auth) {
        CommonTest common = new CommonTest();

        // initialize API name
        CommonTest.setRestApiName(api);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(auth);
        // initialize RestApi URL
        common.setup();
    }
}
