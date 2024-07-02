package automation.de.dg.endpoints.cimrest.customers.controllers;

import automation.de.dg.database.manipulation.queryManipulation.AcQueryManipulation;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteCreatingCustomerDetail;
import automation.de.dg.endpoints.constants.Attachment;
import automation.de.dg.endpoints.constants.CimUriPath;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.utils.config.CustomerCreationTestingData;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.RestApiPreparationData;
import automation.endpoints.enumaration.RestApiMethodTypes;
import automation.endpoints.enumaration.RestApiHttpStatusCodes;
import automation.utilities.TestDataGenerator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

/**
 * <b>RestAPI : Cim Rest Suite/Customer</b> Post Method Test Suite<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to execute endpoint with Post method type<br>
 *  under Customers controller umbrella
 */

public class PostCreateCustomer extends CommonTest {

    /**
     * <b>[Method]</b> - Create Customer<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality create customer<br>
     * and check if response code is 200 OK
     * @return response Response
     */

    public Response createCustomer(String url, String body) {
        try {
            // initialize request's body
            RestApiPreparationData.setBody(body);
            // get response value
            Response response = RestApiPreparationData.returnResponse(RestApiMethodTypes.POST, url +
                    CimUriPath.CUSTOMER_INSTANCE + "create_contract");
            // check proper HTTP response code is in return
            checkStatusCode(response.getStatusCode(), RestApiHttpStatusCodes.OK.option);
            // return response
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Body preparing<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality prepare body for customer creation
     * @return body JSONObject
     */
    public JSONObject body() {
        String firstName;
        // generate first name
        if (getFirstName() == null) {
            firstName = TestDataGenerator.getRandomFirstName();
        } else if (getFirstName().isEmpty() || getFirstName().isBlank()) {
            firstName = TestDataGenerator.getRandomFirstName();
        } else {
            firstName = getFirstName();
        }
        // generate last name
        String lastName = TestDataGenerator.getRandomLastName();
        String fullName = firstName + " " + lastName;

        // generate random address
        AcQueryManipulation query = new AcQueryManipulation();
        query.getAddressWithAvailableUnit(query.getCityWithAvailableUnit());

        JSONObject body = new JSONObject();
        // check if VZF should be signed
        if (getSignedVzf()) {
            body.put("attachments", attachmentPart());
        }
        body.put("bankAccount", bankHolderPart(fullName));
        body.put("billingAddress", billingAddressPart(firstName, lastName));
        body.put("contract", contractPart());
        body.put("customer", customerPart(firstName, lastName));
        body.put("optin", optinPart());
        body.put("orderType", "SALE");
        body.put("remark", "");
        body.put("shippingAddress", shippingAddressPart(firstName, lastName));

        return body;
    }

    /**
     * <b>[Method]</b> - Set Attachment Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare attachment part of Body Request<br>
     * @return attachment JSONArray
     */

    public JSONArray attachmentPart() {
        JSONArray attachment = new JSONArray();
        JSONObject requestParams = new JSONObject();
        requestParams.put("content", Attachment.ATTACHMENT);
        requestParams.put("mimeType", "image/jpeg");
        requestParams.put("name", "page_1");
        requestParams.put("type", "VZF_ONLINE");

        attachment.put(requestParams);
        return attachment;
    }

    /**
     * <b>[Method]</b> - Set Bank Holder Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Bank Holder part of Body Request<br>
     * @return requestParams JSONObject
     */

    public JSONObject bankHolderPart(String fullName) {
        JSONObject requestParams = new JSONObject();

        requestParams.put("accountHolder", fullName);
        requestParams.put("iban", "DE75512108001245126199");
        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Billing Address Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Billing Address part of Body Request<br>
     * @return requestParams JSONObject
     */

    public JSONObject billingAddressPart(String firstName, String lastName) {
        JSONObject requestParams = new JSONObject();
        // call address method to get data
        JSONObject address = address();
        // store address data in billing address part
        address.keySet().forEach(keyStr ->
        {
            Object keyvalue = address.get(keyStr);
            requestParams.put(keyStr, keyvalue);
        });
        requestParams.put("individual", individualPart(firstName, lastName));
        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Billing Individual Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Individual part<br>
     * for Billing Address and Customer which is part of Body Request<br>
     * @return requestParams JSONObject
     */
    public JSONObject individualPart(String firstName, String lastName) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("birthday", "1976-05-30");
        requestParams.put("companyName", getCompanyName());
        requestParams.put("firstname", firstName);
        requestParams.put("lastname", lastName);
        //requestParams.put("salutation", "MR");
        //requestParams.put("salutationId", "100001");
        if (getFirmaCheck()) {
            requestParams.put("salutation", "Firma");
            requestParams.put("salutationId", "100003");
        } else {
            requestParams.put("salutation", "MR");
            requestParams.put("salutationId", "100001");
        }
        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Customer Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Customer part of Body Request<br>
     * @return requestParams JSONObject
     */

    public JSONObject customerPart(String firstName, String lastName) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("customerType", "PK");
        requestParams.put("email", getEmail());
        JSONArray individuale = new JSONArray();
        individuale.put(individualPart(firstName, lastName));

        requestParams.put("individuals", individuale);
        requestParams.put("mobile", "");
        requestParams.put("paymentType", "SEPA");
        requestParams.put("phone", "+49 8541 854557878");
        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Contract Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Contract part of Body Request<br>
     * @return requestParams JSONObject
     */

    public JSONObject contractPart() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("bookingId", "10009784861692291171");
        requestParams.put("connection", address());
        requestParams.put("creationDate", TestDataGenerator.getCurrentDate());
        requestParams.put("evnType", "ANONYMOUS");
        requestParams.put("mainProduct", MainProduct.mainProduct());
        requestParams.put("marketingChannel", "ONLINE");
        requestParams.put("partnerId", 10004);
        requestParams.put("saleChannel", "Consumer Website");
        requestParams.put("signatureDate", TestDataGenerator.getCurrentDate());
        requestParams.put("telephoneNumbers", telephone());

        TestingResource resource = new TestingResource();
        if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
            requestParams.put("signatureType", "ONLINE");
        }

        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Optin Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Optin part of Body Request<br>
     * @return requestParams JSONObject
     */

    public JSONObject optinPart() {
        Random rd = new Random();
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", rd.nextBoolean());
        requestParams.put("optinProvidedByCustomer", rd.nextBoolean());
        requestParams.put("phone", rd.nextBoolean());
        requestParams.put("post", rd.nextBoolean());
        requestParams.put("trafficDataUsage", rd.nextBoolean());

        // storing address data
        CustomerCreationTestingData.setOptinPart(requestParams);
        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Shipping Address Body Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Shipping Address part of Body Request<br>
     * @return requestParams JSONObject
     */

    public JSONObject shippingAddressPart(String firstName, String lastName) {
        JSONObject requestParams = new JSONObject();
        // call address method to get data
        JSONObject address = address();
        // store address data in billing address part
        address.keySet().forEach(keyStr ->
        {
            Object keyvalue = address.get(keyStr);
            requestParams.put(keyStr, keyvalue);
        });
        requestParams.put("individual", individualPart(firstName, lastName));
        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Telephone Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Telephone number<br>
     * based on porting status
     * @return JSONArray telephone
     */

    private JSONArray telephone() {
        SuiteCreatingCustomerDetail suiteCreate = new SuiteCreatingCustomerDetail();

        JSONArray telephone = new JSONArray();
        JSONObject porting = new JSONObject();

        // chekc if Phone number is Ported In
        if (suiteCreate.getPortingStatus()) {
            porting.put("type", "PORTING");
            //porting.put("number", "+49 211 361701");
            porting.put("porting", porting());
            // generate random number
            setPhoneNumber();
            // get random number
            String number = getPhoneNumber();
            porting.put("number", number);
        } else {
            porting.put("type", "NEW");
            // generate random number
            setPhoneNumber();
            // get random number
            String number = getPhoneNumber();
            porting.put("number", number);
        }

        telephone.put(porting);
        return telephone;
    }

    /**
     * <b>[Method]</b> - Set Porting Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Porting<br>
     * @return JSONObject porting
     */
    private JSONObject porting() {
        TestingResource resource = new TestingResource();

        JSONObject porting = new JSONObject();
        porting.put("individual", individualPart(TestDataGenerator.getRandomFirstName(), TestDataGenerator.getRandomLastName()));
        if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
            porting.put("ekpId", 10491);
            porting.put("contractAddress", address());
        } else if (resource.getCustomerType().equals(CustomerTypes.DGB)) {
            porting.put("carrierId", 10002);
            porting.put("address", address());
        } else {
            porting.put("carrierId", 10397);
            porting.put("address", address());
        }

        return porting;
    }

    /**
     * <b>[Method]</b> - Set Address Part<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Address<br>
     * @return JSONObject address
     */
    private JSONObject address() {
        JSONObject address = new JSONObject();
        /*address.put("city", "Spiekeroog");
        address.put("country", "DE");
        address.put("housenumber", 3);
        address.put("street", "Bi d'Utkiek");
        address.put("zipCode", "26474");*/

        TestingResource resource = new TestingResource();
        String[] list = resource.getCustomerAddress();

        address.put("city", list[1]);
        address.put("country", list[0]);
        address.put("housenumber", Integer.parseInt(list[4]));
        address.put("street", list[3]);
        address.put("zipCode", Integer.parseInt(list[2]));

        // storing address data
        CustomerCreationTestingData.setAddress(address);
        return address;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                             Initialization resource part
    //----------------------------------------------------------------------------------------------------------------//


    static String firstName;
    public static void setFirstName(String name) {
        firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    static boolean greater23;
    public static void setGreater23(boolean value) {
        greater23 = value;
    }

    public static boolean getGreater23() {
        return greater23;
    }

    static boolean fsecure;
    public static void setFsecure(boolean value) {
        fsecure = value;
    }

    public static boolean getFsecure() {
        return fsecure;
    }

    static String email;
    public static void setEmail(String mail) {
        email = mail;
    }
    public static String getEmail() {
        return email;
    }

    static boolean signedVzf;

    public static void setSignedVzf(boolean vzf) {
        signedVzf = vzf;
    }

    public static boolean getSignedVzf() {
        return signedVzf;
    }

    static String phoneNumber;

    public static void setPhoneNumber() {
        String number = String.valueOf(TestDataGenerator.generateRandomNumber(2, 9));
        for (int i=0; i<7; i++) {
            int no = TestDataGenerator.generateRandomNumber(0, 9);
            number = number + no;
        }
        phoneNumber = "+49 211 " + number;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    static String companyName = "";
    public static void setCompanyName(String name) {
        //companyName = TestDataGenerator.getRandomFirstName() + " " + TestDataGenerator.getRandomLastName() + " DGB Company";
        companyName = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    static boolean firma;
    public static void setFirmaCheck() {
        firma = true;
    }

    public boolean getFirmaCheck() {
        return firma;
    }
}
