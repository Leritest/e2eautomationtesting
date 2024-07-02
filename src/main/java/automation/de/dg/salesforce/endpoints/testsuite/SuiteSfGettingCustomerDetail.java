package automation.de.dg.salesforce.endpoints.testsuite;

import automation.de.dg.database.sf.queries.SfQueries;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteGettingCustomerDetail;
import automation.de.dg.enumation.AddressTypesSalesforce;
import automation.de.dg.salesforce.contstants.SalesforceUriPath;
import automation.de.dg.salesforce.endpoints.controllers.SfAccountValidator;
import automation.de.dg.salesforce.endpoints.controllers.SfGetCustomer;
import automation.de.dg.salesforce.enumation.RecordTypes;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import automation.utilities.Log;
import io.restassured.response.Response;
import org.testng.Assert;

import static org.testng.AssertJUnit.fail;

/**
 * <b>SF/API/TestSuite: Get Account Detail Suite</b> Getting Account Detail Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover getting Customer details from Salesforce.
 */

public class SuiteSfGettingCustomerDetail {

    /**
     * <b>[Test Method]</b> - Test Case getting Customer data<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer data<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Get  Account Number<br>
     * 2. Validating Account Number exists on Salesforce
     * 3. Call Account endpoint to verify existing data
     */

    public void flowGettingAccountData() {
        // initialize classes
        TestingResource resource = new TestingResource();
        SfGetCustomer getCustomer = new SfGetCustomer();
        SfAccountValidator sfAccountValidator = new SfAccountValidator();

        try {
            // Step 1 - get Account ID based on CIM Customer Number
            Log.info("Get Account ID from Customer Number");
            // get response from Query
            Response response = getDataFromQuery(SfQueries.getCustomerId(String.valueOf(resource.getCustomerId())));
            // storing Account Number
            resource.setSfAccountId(sfAccountValidator.validatingProperResponseFields(response, RecordTypes.ACCOUNT.option));
            Log.info("Salesforce Account number: " + resource.getSfAccountId());

            // Step 2 - get account data
            Log.info("Get Account Detail");
            response = getCustomer.getAccountData(SalesforceUriPath.ACCOUNT_PATH_URL, resource.getSfAccountId());

            // Step 3 - verify Customer and Account Numbers are in response
            Log.info("Step - Verify expected Customer Number and Account ID are returned");
            sfAccountValidator.validatingAccountIdIsCreated(response);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Customer's Phone Number<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer's Phone Number<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Get Phone from CIM
     * 2. Get  Account Number<br>
     * 3. Validating Account Number exists on Salesforce
     * 4. Call Account endpoint to verify existing data
     */

    public void flowGettingSubscriptionDetail() {
        // initialize classes
        TestingResource resource = new TestingResource();
        SfGetCustomer getCustomer = new SfGetCustomer();
        SfAccountValidator sfAccountValidator = new SfAccountValidator();
        SuiteGettingCustomerDetail getCimCustomer = new SuiteGettingCustomerDetail();

        try {
            // prepare CIM API environment
            prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
            // step 1 - Get Phone Number details from CIM
            getCimCustomer.flowGetPhoneNumberDetailFromCustomer();

            // prepare SF API environment
            prepareApiEnvironment(RestApiNames.SALESFORCE, RestApiAuth.TOKEN);

            // step 2 - check Phone is deployed on Salesforce
            System.out.println("#####################################################################################");
            System.out.println("### Get Subscription ID");
            System.out.println("#####################################################################################");
            // get response from Query
            Response response = getDataFromQuery(SfQueries.getPhoneNumber(String.valueOf(resource.getSfAccountId())));
            // storing Subscription Data
            resource.setSfPhoneId(sfAccountValidator.validatingProperResponseFields(response, RecordTypes.SUBSCRIPTION.option));
            System.out.println("Salesforce Subscription for phone number: " + resource.getSfPhoneId());

            // step 3 - get subscription data
            System.out.println("#####################################################################################");
            System.out.println("### Get Phone Number data");
            System.out.println("#####################################################################################");
            response = getCustomer.getAccountData(SalesforceUriPath.SUBSCRIPTION_PATH_URL, resource.getSfPhoneId());

            // Step 4 - verify Customer and Account Numbers are in response
            System.out.println("#####################################################################################");
            System.out.println("### Verify expected Customer Number and Account ID are returned");
            System.out.println("#####################################################################################");
            String phone = response.jsonPath().getJsonObject("Phone1__c");
            Assert.assertEquals(phone, resource.getSPhoneNumberDetail(), "Phone Number from API response is different from expected");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Customer's Bank Holder<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer's Bank Holder<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Get Bank details from CIM
     * 2. Get  Account Number<br>
     * 3. Validating Account Number exists on Salesforce
     * 4. Call Account endpoint to verify existing data
     */

    public void flowGettingBankHolderDetail() {
        // initialize classes
        TestingResource resource = new TestingResource();
        SfGetCustomer getCustomer = new SfGetCustomer();
        SfAccountValidator sfAccountValidator = new SfAccountValidator();
        SuiteGettingCustomerDetail getCimCustomer = new SuiteGettingCustomerDetail();

        try {
            // prepare CIM API environment
            prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
            // step 1 - Get Bank Account details from CIM
            getCimCustomer.flowGetBankAccountDetailFromCustomer();

            // prepare SF API environment
            prepareApiEnvironment(RestApiNames.SALESFORCE, RestApiAuth.TOKEN);

            // step 2 - Check Bank Account is deployed on Salesforce
            System.out.println("#####################################################################################");
            System.out.println("### Get Bank Account ID");
            System.out.println("#####################################################################################");
            // get response from Query
            Response response = getDataFromQuery(SfQueries.getBankAccount(String.valueOf(resource.getSfAccountId())));
            // storing Bank Account Data
            resource.setSfBankAccountId(sfAccountValidator.validatingProperResponseFields(response, RecordTypes.BANK_ACCOUNT.option));
            System.out.println("Salesforce Bank Account ID: " + resource.getSfPBankAccountId());

            // step 3 - get subscription data
            System.out.println("#####################################################################################");
            System.out.println("### Get Bank Account data");
            System.out.println("#####################################################################################");
            response = getCustomer.getAccountData(SalesforceUriPath.BANK_ACCOUNT_PATH_URL, resource.getSfPBankAccountId());

            // Step 4 - verify Customer and Account Numbers are in response
            System.out.println("#####################################################################################");
            System.out.println("### Verify expected Bank Holder and IBAN are returned");
            System.out.println("#####################################################################################");
            sfAccountValidator.validatingBankAccountIsCreated(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Customer's Contract<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer's Contract<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Get Phone from CIM
     * 2. Get  Account Number<br>
     * 3. Validating Account Number exists on Salesforce
     * 4. Call Account endpoint to verify existing data
     */

    public void flowGettingContractDetail() {
        // initialize classes
        TestingResource resource = new TestingResource();
        SfAccountValidator sfAccountValidator = new SfAccountValidator();
        SfGetCustomer getCustomer = new SfGetCustomer();

        try {
            // step 1 - Check Contract is deployed on Salesforce
            System.out.println("#####################################################################################");
            System.out.println("### Get Account ID");
            System.out.println("#####################################################################################");
            // get response from Query
            Response response = getDataFromQuery(SfQueries.getContract(String.valueOf(resource.getSfAccountId())));

            // storing Contract ID
            resource.setSfContractId(sfAccountValidator.validatingProperResponseFields(response, RecordTypes.ACCOUNT.option));
            response = getCustomer.getAccountData(SalesforceUriPath.ACCOUNT_PATH_URL, resource.getSfContractId());

            // Step 4 - verify Customer and Account Numbers are in response
            System.out.println("#####################################################################################");
            System.out.println("### Verify expected Bank Holder and IBAN are returned");
            System.out.println("#####################################################################################");
            sfAccountValidator.validatingContractIsCreated(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * <b>[Test Method]</b> - Test Case getting Customer's Optin<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer's Optin<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Get  Account Number<br>
     * 2. Validating Account Number exists on Salesforce
     * 3. Call Account endpoint to verify existing data
     */

    public void flowGettingOptinDetail(AddressTypesSalesforce addressType) {
        // initialize classes
        TestingResource resource = new TestingResource();
        SfGetCustomer getCustomer = new SfGetCustomer();
        SuiteGettingCustomerDetail getCimCustomer = new SuiteGettingCustomerDetail();

        try {
            // prepare CIM API environment
            prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
            // step 1 - Get Bank Account details from CIM
            getCimCustomer.flowGetOptinDetailFromCustomer();

            // prepare SF API environment
            prepareApiEnvironment(RestApiNames.SALESFORCE, RestApiAuth.TOKEN);

            // step 2 - Check Bank Account is deployed on Salesforce
            System.out.println("#####################################################################################");
            System.out.println("### Get Optin ID");
            System.out.println("#####################################################################################");
            // get response from Query
            Response response = getDataFromQuery(SfQueries.getOptin(String.valueOf(resource.getSfAccountId()), addressType));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Customer's Address<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer's Address<br>
     *
     * <i>Steps of this scenario:</i><br>
     * 1. Get Phone from CIM
     * 2. Get  Account Number<br>
     * 3. Validating Account Number exists on Salesforce
     * 4. Call Account endpoint to verify existing data
     */

    public void flowGettingAddressDetails(AddressTypesSalesforce addressType) {
        // initialize classes
        TestingResource resource = new TestingResource();
        SfGetCustomer getCustomer = new SfGetCustomer();
        SfAccountValidator sfAccountValidator = new SfAccountValidator();
        SuiteGettingCustomerDetail getCimCustomer = new SuiteGettingCustomerDetail();

        try {
            // prepare CIM API environment
            prepareApiEnvironment(RestApiNames.CIMREST, RestApiAuth.BASIC);
            // step 1 - Get Bank Account details from CIM
            getCimCustomer.flowGetAddressDetailFromCustomer(addressType);

            // prepare SF API environment
            prepareApiEnvironment(RestApiNames.SALESFORCE, RestApiAuth.TOKEN);

            System.out.println("#####################################################################################");
            System.out.println("### Get Address ID");
            System.out.println("#####################################################################################");
            // get response from Query
            Response response = getDataFromQuery(SfQueries.getAddressBasedOnType(String.valueOf(resource.getSfAccountId()), addressType));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Timed out Method for waiting Backed process be finished<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality generate timeout<br>
     * so backend process finish deploying data
     * @param response Response
     */
    public int timeoutMethodUntilDataIsGenerated(Response response, int count) {
        int totalSize = 0;
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            totalSize = response.jsonPath().getJsonObject("totalSize");
            if (totalSize < 1) {
                if (count < 2) {
                    Thread.sleep(60 * 1000);
                } else if (count < 6) {
                    Thread.sleep(30 * 1000);
                } else if (count < 10) {
                    Thread.sleep(10 * 1000);
                } else if (count < 20) {
                    Thread.sleep(5 * 1000);
                } else if (count < 40) {
                    Thread.sleep(2 * 1000);
                } else {
                    fail("For Customer ID " + resource.getCustomerId() + ", Account ID cannot be found on Salesforce");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return totalSize;
    }

    /**
     * <b>[Method]</b> - Method for getting Response based on Request's query<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality generate REST API Response<br>
     * based on Query in Request's URL
     * @param url String
     */

    public Response getDataFromQuery(String url) {
        // initialize classes
        SfGetCustomer getCustomer = new SfGetCustomer();

        Response response;
        try {
            int totalSize;
            int count = 0;
            // repeat steps until Backend process is finished
            do {
                response = getCustomer.getDataBasedOnQuery(url);
                totalSize = timeoutMethodUntilDataIsGenerated(response, count);
                count++;
            } while (totalSize < 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return response;
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
