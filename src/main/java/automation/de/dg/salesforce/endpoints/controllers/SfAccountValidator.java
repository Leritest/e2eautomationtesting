package automation.de.dg.salesforce.endpoints.controllers;

import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.utils.config.CustomerCreationTestingData;
import automation.de.dg.utils.config.TestingResource;
import automation.utilities.Log;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import static org.testng.AssertJUnit.fail;

/**
 * <b>SF/API/AccountAPI : Account Validator Suite</b> Validating Account detail Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover validating Account details on Salesforce.
 */

public class SfAccountValidator {

    /**
     * <b>[Test Method]</b> - Validating Account ID is created<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs validating of creating Account ID<br>
     */

    public void validatingAccountIdIsCreated(Response response) {
        // initialize classes
        TestingResource resource = new TestingResource();

        try {
            // take Account ID from Get Account Response
            String acc = response.jsonPath().getJsonObject("Id");
            // take CIM Customer ID from Get Account Response
            String cust = response.jsonPath().getJsonObject("CimId__c");

            // verifying returned values match values stored during customer creation
            Assert.assertEquals(acc, resource.getSfAccountId(), "Account ID from API response is different from expected");
            Assert.assertEquals(cust, String.valueOf(resource.getCustomerId()), "Customer Number from API response is different from expected");

            Log.info("Account ID " + acc + " exists on Salesforce");
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Validating Proper Response fields<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs validating of<br>
     * expected response body fields
     */

    public String validatingProperResponseFields(Response response, String expectedType) {
        String value = null;
        try {
            // get whole JSON Body response
            JSONObject responseJSONObject = new JSONObject(response.getBody().asString());

            // take Records part of JSON Object
            JSONArray array = responseJSONObject.getJSONArray("records");
            JSONObject object = (JSONObject) array.get(0);

            // take Attribute JSON Object inside Records part
            JSONObject attribute = object.getJSONObject("attributes");

            // take Type inside Attribute
            String type = String.valueOf(attribute.get("type"));
            Assert.assertEquals(type, expectedType, "Response body does not return expected type " + expectedType + ", but " + type);

            // take URL inside Attribute
            String url = String.valueOf(attribute.get("url"));
            // split url value
            String[] list = url.split("/");
            // take last part of URL
            value = list[list.length-1];
        } catch (JSONException je) {
            je.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * <b>[Test Method]</b> - Validating Bank Account is created<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs validating of creating Bank Account<br>
     */

    public void validatingBankAccountIsCreated(Response response) {
        // initialize classes
        TestingResource resource = new TestingResource();

        try {
            // take data from Get Bank Holder Response
            String iban = response.jsonPath().getJsonObject("vlocity_cmt__IBAN__c");
            String holderName = response.jsonPath().getJsonObject("vlocity_cmt__BankAccountHolderName__c");
            String customerType = response.jsonPath().getJsonObject("ISP__c");
            boolean isActive = response.jsonPath().getJsonObject("IsSEPAActive__c");

            String[] list = resource.getBankAccountDetail();

            // verifying returned values match values stored during customer creation
            Assert.assertEquals(isActive, Boolean.parseBoolean(list[2]), "Bank Account is not active");
            Assert.assertEquals(iban, list[1], "IBAN is not properly saved");
            Assert.assertEquals(customerType, String.valueOf(resource.getCustomerType()), "IBAN is not properly saved");
            Assert.assertEquals(holderName, list[0], "Bank Holder Name is not properly saved");

            System.out.println("Bank Account is created on Salesforce");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Validating Phone Number is created<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs validating of creating Phone Number<br>
     */

    public void validatingPhoneIsCreated(Response response) {
        // initialize classes
        TestingResource resource = new TestingResource();

        try {
            // take Phone Number from Get Subscription Response
            String phone = response.jsonPath().getJsonObject("Phone1__c");

            // verifying returned values match values stored during customer creation
            Assert.assertEquals(phone, PostCreateCustomer.getPhoneNumber(), "Phone Number from API response is different from expected");
            System.out.println("For Account ID " + resource.getSfAccountId() + ", Subscription " +  phone + " is created on Salesforce");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Validating Contract is created<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs validating of creating Contract<br>
     */

    public void validatingContractIsCreated(Response response) {
        // initialize classes
        TestingResource resource = new TestingResource();

        try {
            // take CIM Customer ID from Get Contract Response
            String customerId = response.jsonPath().getJsonObject("CimId__c");
            // verifying returned values match values stored during customer creation
            Assert.assertEquals(customerId, String.valueOf(resource.getCustomerId()), "CIM Customer ID from API response is different from expected");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
