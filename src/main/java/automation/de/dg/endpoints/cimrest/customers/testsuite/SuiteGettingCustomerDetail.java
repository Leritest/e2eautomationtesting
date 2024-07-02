package automation.de.dg.endpoints.cimrest.customers.testsuite;

import automation.de.dg.endpoints.cimrest.customers.controllers.GetCustomer;
import automation.de.dg.endpoints.constants.CimUriPath;
import automation.de.dg.enumation.AddressTypesSalesforce;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <b>RestAPI : Campaign Suite</b> Getting Customer detail Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover getting Customer details.
 */

public class SuiteGettingCustomerDetail {

    /**
     * <b>[Test Method]</b> - Test Case getting Customer detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Customer detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  Execute Request
     */

    public void flowGetCustomerDetail() {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Customer Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_V4 + CimUriPath.CUSTOMER_INSTANCE + resource.getCustomerId();
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));

            String phone = response.jsonPath().getJsonObject("phone");
            System.out.println(phone);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Contract detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Contract detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  Execute Request
     */
    public void flowGetContractDetailFromCustomer() {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Contract Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_v3 + CimUriPath.CUSTOMER_INSTANCE  + resource.getCustomerId() + "/" + CimUriPath.CONTRACT_INSTANCE;
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Contract's Connection detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Contract's Connection detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  Execute Request
     */
    public void flowGetContractConnectionDetailFromCustomer() {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Contract's Connection Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_V4 + CimUriPath.CUSTOMER_INSTANCE  + resource.getCustomerId() + "/"
                    + CimUriPath.CONTRACT_INSTANCE + resource.getContractId() + "/" + CimUriPath.CONNECTION_INSTANCE;
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Optin detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Optin detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  1. Execute Request
     *  2. Store Optin values
     */
    public void flowGetOptinDetailFromCustomer() {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Optin Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_v3 + CimUriPath.CUSTOMER_INSTANCE  + resource.getCustomerId() + "/" + CimUriPath.OPTIN_INSTANCE;
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));

            // storing optin details
            HashMap<String, Boolean> optin = new HashMap<>();
            optin.put("phone", response.jsonPath().getJsonObject("phone"));
            optin.put("email", response.jsonPath().getJsonObject("email"));
            optin.put("trafficDataUsage", response.jsonPath().getJsonObject("trafficDataUsage"));

            System.out.println("Optin values are:");
            System.out.println("Phone: " + optin.get("phone"));
            System.out.println("Email: " + optin.get("email"));
            System.out.println("Traffic Data Usage: " + optin.get("trafficDataUsage"));
            resource.setOptinDetail(optin);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Bank Account detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Bank Account detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  1. Execute Request
     *  2. Save Bank Account Detail
     */

    public void flowGetBankAccountDetailFromCustomer() {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Bank Account Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_v3 + CimUriPath.CUSTOMER_INSTANCE  + resource.getCustomerId() + "/" + CimUriPath.BANK_ACCOUNTS_INSTANCE;
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));

            // storing bank account details
            ArrayList array = response.jsonPath().get();
            HashMap<String, String> object = (HashMap<String, String>) array.get(0);

            String[] list = new String[3];
            list[0] = object.get("accountHolder");
            list[1] = object.get("iban");
            list[2] = String.valueOf(object.get("isSepaActive"));
            System.out.println("Account Holder: " + list[0]);
            System.out.println("IBAN: " + list[1]);
            System.out.println("Is active account: " + list[2]);
            resource.setBankAccountDetail(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Address detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Address detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  Execute Request
     */

    public void flowGetAddressDetailFromCustomer(AddressTypesSalesforce addressType) {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Addresses Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_V4 + CimUriPath.CUSTOMER_INSTANCE  + resource.getCustomerId() + "/" + CimUriPath.ADDRESSES_INSTANCE;
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));

            System.out.println("#####################################################################################");
            System.out.println("### Store desired Address type");
            System.out.println("#####################################################################################");
            ArrayList array = response.jsonPath().get();
            String[] list = new String[5];
            for (int i=0; i<array.size(); i++) {
                HashMap<String, String> object = (HashMap<String, String>) array.get(i);
                if (object.get("type").equals(String.valueOf(addressType))) {
                    list[0] = object.get("country");
                    list[1] = object.get("city");
                    list[2] = object.get("zipCode");
                    list[3] = object.get("street");
                    list[4] = String.valueOf(object.get("housenumber"));
                    break;
                }
            }
            System.out.println("Customer address for type " + addressType + " is:");
            for (int i=0; i< list.length; i++) {
                System.out.println(list[i]);
            }
            resource.setAddressDetail(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Test Case getting Phone Number detail<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of getting Phone Number detail<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  1. Execute Request
     *  2. Store Phone Numbers
     */

    public void flowGetPhoneNumberDetailFromCustomer() {
        try {
            // initialize classes
            TestingResource resource = new TestingResource();
            GetCustomer getCustomer = new GetCustomer();

            // step - execute request
            System.out.println("#####################################################################################");
            System.out.println("### Getting Addresses Details");
            System.out.println("#####################################################################################");
            String url = CommonTest.getRestApiUrl() + CimUriPath.REST_URL_DGH_V4 + CimUriPath.CUSTOMER_INSTANCE  + resource.getCustomerId() + "/" + CimUriPath.CONTRACT_INSTANCE
                    + + resource.getContractId() + "/" + CimUriPath.TELEPHONE_NUMBERS_INSTANCE;
            Response response = getCustomer.getCustomer(url, String.valueOf(resource.getCustomerId()));

            // check if Customer's phone is type Porting
            ArrayList array = response.jsonPath().get();
            boolean porting = false;
            int row = 0;
            for (int i = 0; i< array.size(); i++) {
                HashMap<String, String> object = (HashMap<String, String>) array.get(i);
                if (object.get("status").equals("PORTING")) {
                    porting = true;
                    row = i;
                    break;
                }
            }

            // Take Phone Number
            String number;
            if (porting) {
                HashMap<String, String> object = (HashMap<String, String>) array.get(row);
                number = object.get("number");
            } else {
                HashMap<String, String> object = (HashMap<String, String>) array.get(0);
                number = object.get("number");
            }
            System.out.println("Customer's phone number: " + number);
            resource.setPhoneNumberDetail(number);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
