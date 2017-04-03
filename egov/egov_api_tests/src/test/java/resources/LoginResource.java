package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class LoginResource {

    public Response login(Map json) {

        new APILogger().log("Login Test is started -- ");

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0")
                .params(json)
                .when()
                .post(Properties.loginUrl);
//                .post(path + Properties.loginUrl);

        return response;
    }

    public Response logout(String accessToken) {

        new APILogger().log("Logout request started-- ");

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("auth-token", accessToken)
                .when()
                .post(Properties.logoutUrl + accessToken);

        return response;
    }

    public Response inValidLogout(String accessToken) {

        new APILogger().log("In-Valid logout request started-- " + accessToken);

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("auth-token", accessToken)
                .when()
                .post(Properties.logoutUrl);

        return response;
    }
}
