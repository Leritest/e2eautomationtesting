package automation.endpoints;

import automation.de.dg.endpoints.constants.RestAPIAuthConstants;
import automation.de.dg.contstants.RestApiUrls;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import automation.utilities.ExtentManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

/**
 * <b>RESTASSUREAPI</b> [Rest Assure API]: Common Actions for Rest Assure API
 */

public class CommonTest {

    public static RequestSpecification spec;
    public static String url;
    private static RestApiNames restApi;
    private static RestApiAuth restAuth;
    private static String token_url;
    private static Object ldapValue;
    private static String ldapParameter;

    /**
     * <b>[Test Method]</b> - Set Up for RestApi if it is used in UI cases<br>
     * <br><i>Test Method functionality:</i><br>
     * This method helps to construct set up method to avoid spec comes as null<br>
     *
     * @return spec RequestSpecification
     */
    public static RequestSpecification getSpec() {
        if (Objects.isNull(spec)) {
            return new RequestSpecBuilder()
                    .setBaseUri(getRestApiUrl())
                    .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                    .build();
        }
        return spec;
    }

    /**
     * <b>[Method]</b> Prepare Authorized Header<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality generates a header map which contains<br>
     * Content-Type and Authorization token
     *
     * @return headerMap map
     */

    public static Map<String, Object> getAuthorizedHeader() {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        //headerMap.put(headerParameter, headerValue);
        switch (getRestApiAuth()) {
            case BASIC -> {
                String encodeString = GetApiAuthorization.getBasicAuthenticationHeader(getApiUsername(), getApiPassword());
                headerMap.put("Authorization", encodeString);
            }
            case HEADER -> {
                headerMap.put(ldapParameter, ldapValue);
            }
            case TOKEN -> {
                String token = GetApiAuthorization.getTokenViaAPI(getTokenUrl(), getApiUsername(), getApiPassword(), RestAPIAuthConstants.SF_CLIENT_ID, RestAPIAuthConstants.SF_CLIENT_SECRET);
                headerMap.put("Authorization", token);
            }
        }

        return headerMap;
    }

    /**
     * <b>[Method]</b> Post request without header<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends post request without header and
     * with given body and url<br>
     *
     * @param body String
     * @param url  String
     * @return post request response as Response object
     */
    public static Response postWithoutHeader(String body, String url) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(url);
    }

    /**
     * <b>[Method]</b> Post request with header<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends post request with authorized header and
     * with given body and url<br>
     *
     * @param body String
     * @param url  String
     * @return post request response as Response object
     */
    public static Response post(String body, String url) {
        RequestSpecification requestSpecification = given(getSpec())
                .headers(getAuthorizedHeader())
                .when()
                .body(body);
        Response response = requestSpecification.post(url);
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    /**
     * <b>[Method]</b> Put request<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends put request with authorized header and
     * with given body and url<br>
     *
     * @param body String
     * @param url  String
     * @return put request response as Response object
     */
    public static Response put(String body, String url) throws UnsupportedEncodingException {
        RequestSpecification requestSpecification = given(getSpec())
                .headers(getAuthorizedHeader())
                .when()
                .body(body);
        Response response = requestSpecification.put(url);
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    /**
     * <b>[Method]</b> Patch request<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends patch request with authorized header and
     * with given body and url<br>
     *
     * @param body String
     * @param url  String
     * @return patch request response as Response object
     */
    public static Response patch(String body, String url) {
        RequestSpecification requestSpecification = given(getSpec())
                .headers(getAuthorizedHeader())
                .when()
                .body(body);
        Response response = requestSpecification.patch(url);
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    /**
     * <b>[Method]</b> Delete request<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends put request with authorized header and
     * with given body and url<br>
     *
     * @param url String
     * @return delete request response as Response object
     */
    public static Response delete(String url) throws UnsupportedEncodingException {
        RequestSpecification requestSpecification = given(getSpec())
                .headers(getAuthorizedHeader());
        Response response = requestSpecification.delete(url);
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    /**
     * <b>[Method]</b> Get request<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends put request with authorized header and
     * with given body and url<br>
     *
     * @param url String
     * @return get request response as Response object
     */
    public static Response get(String url) throws UnsupportedEncodingException {
        RequestSpecification requestSpecification = given(getSpec())
                .headers(getAuthorizedHeader());
        Response response = requestSpecification.get(url);
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    /**
     * <b>[Method]</b> Print Request log in report<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Prints request Base Uri, Base Path, Method, Headers and request body of request to report
     *
     * @param requestSpecification requestSpecification
     */

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentManager.logInfoDetails("Endpoint is: " + queryableRequestSpecification.getBaseUri());
        ExtentManager.logInfoDetails("Base Path is: " + queryableRequestSpecification.getDerivedPath());
        ExtentManager.logInfoDetails("Method is: " + queryableRequestSpecification.getMethod());
        ExtentManager.logInfoDetails("Headers are: ");
        ExtentManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        if (queryableRequestSpecification.getMethod().equals("POST") || queryableRequestSpecification.getMethod().equals("PUT") || queryableRequestSpecification.getMethod().equals("PATCH")) {
            ExtentManager.logInfoDetails("Request Body is: ");
            ExtentManager.logJSON(queryableRequestSpecification.getBody());
        }
    }

    /**
     * <b>[Method]</b> Print Response log in report<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Prints response status code, headers and body to report
     *
     * @param response Response
     */
    private static void printResponseLogInReport(Response response) {
        ExtentManager.logPassDetails("Response status is " + response.getStatusCode());
        ExtentManager.logInfoDetails("Response headers are: ");
        ExtentManager.logHeaders(response.getHeaders().asList());
        if (!(response.getContentType()).isBlank()) {
            String jsonBody = response.getBody().asPrettyString();
            jsonBody = jsonBody.replaceAll("\n", "<br>");
            String formattedBody = "<details>\n" +
                    " <summary style=\"color:rgb(33,150,243);\"><b> Click here to see Json Body Response </b></summary>\n" +
                    " " + jsonBody + "\n" +
                    "</details>\n";
            ExtentManager.logInfoDetailsWithoutLabelColor(formattedBody);
        }

    }


    //----------------------------------------------------------------------------------------------------------------//
    //                                             Initialization resource part
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Set Up for RestApi<br>
     * <br><i>Test Method functionality:</i><br>
     * The RequestSpecBuilder is a special class of RestAssured
     * which allows us to prepare base url and
     * add logging for request e and response of api calls and more, so we can use the same
     * logic for all requests in order to not repeat them again and again<br>
     * BeforeTest - annotation
     */
    @BeforeTest
    public void setup() {
        String apiUrl;
        // initialize RestAPI URL
        switch (getRestApiName()) {
            case CAMPAIGN -> {
                apiUrl = RestApiUrls.CAMPAIGN_URL;
            }
            case CIMREST -> {
                apiUrl = RestApiUrls.CIMREST_URL;
            }
            case SALESFORCE -> {
                apiUrl = RestApiUrls.SALESFORCE_URL;
            }
            default -> {
                apiUrl = "";
            }
        }
        spec = new RequestSpecBuilder()
                .setBaseUri(apiUrl)
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();

        // set RestAPI URL
        setRestApiUrl(apiUrl);
    }

    /**
     * <b>[Method]</b> - Check Status Code<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality checks API status code<br>
     * compare expected HTTP status code with actual response's status code
     *
     * @param expected Expected HTTP status code
     * @param actual HTTP status code in response
     */

    protected void checkStatusCode(int expected, int actual) {
        Assert.assertEquals(expected, actual, "Response HTTP status code is not in expected status");
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                              Setting Methods
    //----------------------------------------------------------------------------------------------------------------//


    /**
     * <b>[Test Method]</b> - Setting Rest API Name<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Rest API Name initialized BeforeSuite inside executing testing Class
     */

    public static void setRestApiName(RestApiNames rest) {
        restApi = rest;
    }

    /**
     * <b>[Test Method]</b> - Setting Rest API Authorization type<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Rest API Authorization type initialized BeforeSuite inside executing testing Class
     */

    public static void setRestApiAuthType(RestApiAuth rest) {
        restAuth = rest;
    }

    /**
     * <b>[Test Method]</b> - Setting Header Value<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Rest API Header Value
     * initialized either during BeforeSuite
     * or as pre-required test case step
     * inside executing testing Class
     */

    public static void setHeaderValue(Object ob) {
        ldapValue = ob;
    }

    /**
     * <b>[Test Method]</b> - Setting Header Value<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Rest API Header Parameter
     * initialized either during BeforeSuite
     * or as pre-required test case step
     * inside executing testing Class
     */

    public static void setHeaderParameter(String par) {
        ldapParameter = par;
    }

    /**
     * <b>[Test Method]</b> - Setting Token URL<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Rest API URL address used for Token creation
     * initialized either during BeforeSuite
     * or as pre-required test case step
     * inside executing testing Class
     */

    public static void setTokenUrl(String url) {
        token_url = url;
    }

    /**
     * <b>[Test Method]</b> - Setting Rest API URL<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Rest API Name initialized BeforeSuite inside executing testing Class
     */

    public static void setRestApiUrl(String apiUrl) {
        url = apiUrl;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                              Getting Methods
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Getting Rest API Name<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Rest API Name
     */

    public static RestApiNames getRestApiName() {
        return restApi;
    }

    /**
     * <b>[Test Method]</b> - Getting Rest API Authorization<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Rest API Auth
     */

    public static RestApiAuth getRestApiAuth() {
        return restAuth;
    }

    /**
     * <b>[Test Method]</b> - Getting Rest API URL<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Rest API URL
     */

    public static String getRestApiUrl() {
        return url;
    }

    /**
     * <b>[Test Method]</b> - Getting Base URL<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Base URL used for Token creation
     */

    public static String getTokenUrl() {
        return token_url;
    }

    /**
     * <b>[Test Method]</b> - Getting Rest API Username<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Rest API Username used for Basic auth<br>
     * Username is taken from Rest API Constant class
     * @return username API username
     */
    private static String getApiUsername() {
        String username = null;
        switch (getRestApiName()) {
            case CAMPAIGN -> {
                username = RestAPIAuthConstants.CAMPAIGN_USERNAME;
            }
            case CIMREST -> {
                username = RestAPIAuthConstants.CIM_USERNAME;
            }
            case SALESFORCE -> {
                username = RestAPIAuthConstants.SF_USERNAME;
            }
        }
        return username;
    }

    /**
     * <b>[Test Method]</b> - Getting Rest API Password<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Rest API Password used for Basic auth<br>
     * Username is taken from Rest API Constant class
     * @return username API password
     */
    private static String getApiPassword() {
        String password = null;
        switch (getRestApiName()) {
            case CAMPAIGN -> {
                password = RestAPIAuthConstants.CAMPAIGN_PASSWORD;
            }
            case CIMREST -> {
                password = RestAPIAuthConstants.CIM_PASSWORD;
            }
            case SALESFORCE -> {
                password = RestAPIAuthConstants.SF_PASSWORD;
            }
        }
        return password;
    }

}
