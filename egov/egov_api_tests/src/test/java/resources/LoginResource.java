package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class LoginResource {

    public Response login(Map json) {

        new APILogger().log("Login Request is started with-- "+json.toString());

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0")
                .params(json)
                .when()
                .post(Properties.loginUrl);

        new APILogger().log("Login Response is generated as-- "+response.asString());

        return response;
    }

    public Response logout(String accessToken) {

        new APILogger().log("Logout request started for-- "+accessToken);

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("auth-token", accessToken)
                .when()
                .post(Properties.logoutUrl + accessToken);

        new APILogger().log("Logout response generated as-- "+response.asString());
        return response;
    }

    public Response inValidLogout(String accessToken) {

        new APILogger().log("In-Valid logout request started for-- " + accessToken);

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("auth-token", accessToken)
                .when()
                .post(Properties.logoutUrl);

        new APILogger().log("In-Valid logout response generated as-- " + response.asString());

        return response;
    }
}
