package automation.de.dg.salesforce.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class StaticContext {

    public static String token = null;

    public static String grantSalesforceAccessToken() {
        Map<String, String> credentials = new HashMap<>();

        credentials.put("grant_type", "password");
        credentials.put("username", "auto.admin@extern.deutsche-glasfaser.de");
        credentials.put("password", "Aut0Adm!n!!");
        credentials.put("client_id", "3MVG9uAc45HBYUriLzgLaqctzgLyvPusZiFzxObBC8bfmCsB93trJ_dp.SELm3EDN7zpeGn70mZZ87RocPp79");
        credentials.put("client_secret", "F2154077FA7798E271748F7298789E49EB8266629938898DA83A6FA5AF88C1E8");

        RestAssured.baseURI = "https://test.salesforce.com/services";
        String token = RestAssured.given()
            .with()
            .contentType(ContentType.URLENC)
            .relaxedHTTPSValidation()
            .formParams(credentials)
            .post("/oauth2/token")
            .then().assertThat().statusCode(200)
            .and()
            .extract()
            .path("access_token").toString();

        return token;
    }
}
