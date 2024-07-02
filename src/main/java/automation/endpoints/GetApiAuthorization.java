package automation.endpoints;

import io.restassured.response.Response;
import org.apache.http.client.utils.URIBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <b>RESTASSUREAPI</b> [Rest Assure API]: Getting Token for Rest Assure API
 */

public class GetApiAuthorization {

    /**
     * <b>[Method]</b> Generating authorization token<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality generating Base64 token<br>
     * used for Basic Authorization<br>
     * and sent via request's header<br>
     *
     * @param username     String
     * @param password     String
     * @return String token
     */

    public static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    /**
     * <b>[Method]</b> Generating access token with api<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality generating a login url with URIBuilder method<br>
     * with given parameters.<br>
     * Then decoding the generated url to UTF-8 <br>
     * and sending it with post request by using postWithoutHeader method<br>
     * @param userName String
     * @param password String
     * @param clientId String
     * @param clientSecret String
     * @return String token
     */
    public static String getTokenViaAPI(String host, String userName, String password,String clientId, String clientSecret) {
        URIBuilder builder = new URIBuilder();
        String loginRequestUrl = builder.setScheme("https")
                .setHost(host)
                .addParameter("username", userName)
                .addParameter("password", password)
                .addParameter("grant_type", "password")
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .toString();
        String decodedURL = URLDecoder.decode(loginRequestUrl, StandardCharsets.UTF_8);
        Response postResponse = CommonTest.postWithoutHeader("",decodedURL);

        postResponse.then()
                .statusCode(200);

        return postResponse.jsonPath().getJsonObject("token_type") +" " +postResponse.jsonPath()
                .getJsonObject("access_token");

    }

}
