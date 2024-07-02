package automation.de.dg.salesforce.endpoints.controllers;

import automation.utilities.Log;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.File;

public class AccountAPI {
    public static String addNewAccountInCIM(String ispType) {
        Log.info("addNewAccountInCIM method executing");

        File jsonFile = null;
        String endpointUrl = null;

        switch (ispType) {
            case "DGB" -> {
                // Endpoint URL
                endpointUrl = "https://acc-b2b1.dg-w.de/dgb-cim-rest-v3/rest/v3/customers/create_contract";

                // Read JSON payload from file
                jsonFile = new File("src\\test\\resources\\test-data\\inputDir\\newAccount" + ispType + ".json");
            }
            case "DGH" -> {
                // Endpoint URL
                endpointUrl = "https://acc-b2b1.dg-w.de/dgh-cim-rest-v3/rest/v3/customers/create_contract";

                // Read JSON payload from file
                jsonFile = new File("src\\test\\resources\\test-data\\inputDir\\newAccount" + ispType + ".json");
            }
            case "NEW" -> {
                // Endpoint URL
                endpointUrl = "https://acc-b2b1.dg-w.de/new-cim-rest-v3/rest/v3/customers/create_contract";

                // Read JSON payload from file
                jsonFile = new File("src\\test\\resources\\test-data\\inputDir\\newAccount" + ispType + ".json");
            }
        }


        String username = "cimrest";
        String password = "cimrest";

        Response response = RestAssured.given()
                .auth().preemptive().basic(username, password)
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .patch(endpointUrl);

        return response.jsonPath().getString("customerId");
    }

    public String getAccountIdInSalesforce(String token, String customerNumber) {

        RestAssured.baseURI = "https://dgvlocity--uat2.sandbox.my.salesforce.com/services/data/v59.0";

        String soqlQuery = "SELECT Id From ACCOUNT Where CimId__c='" + customerNumber + "'";

        String apiEndpoint = "/query/";

        String bearerToken = token;
        System.out.println("bearerToken" + bearerToken);
        System.out.println("customerNumber" + customerNumber);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .param("q", soqlQuery)
                .get(apiEndpoint);

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        JsonPath jsonPath = response.jsonPath();

        String accountId = jsonPath.getString("records[0].Id");

        System.out.println("Account Id: " + accountId);

        return accountId;
    }

    /*
    public String callSalesforce(String query) throws InterruptedException {
        int totalSize;
        this.context = new ContextFactory();

        if (isNull(context.getAccessToken())) {
            context.setAccessToken(StaticContext.grantSalesforceAccessToken());
        }

        do {
            Thread.sleep(2000);

            context.setResponse(RestAssured.given().relaxedHTTPSValidation().auth().oauth2(context.getAccessToken())
                    .baseUri("https://dgvlocity--uat2.sandbox.my.salesforce.com/services/data/v57.0/query").queryParam("q", query)
                    .contentType(ContentType.JSON).get().then().statusCode(200).extract().response());

            totalSize = Integer.parseInt(context.getResponse().getBody().path("totalSize").toString());
        } while (totalSize == 0);

        return context.getResponse().getBody().asString();
    }

     */
}
