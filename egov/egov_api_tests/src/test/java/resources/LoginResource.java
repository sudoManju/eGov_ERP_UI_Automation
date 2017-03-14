package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class LoginResource {

    public Response post(Map json) {
        new APILogger().log("login request -- " + json);

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0")
                .params(json)
                .when()
                .post("http://egov-micro-qa.egovernments.org/user/_login?jurisdiction_id=ap.public");

        return response;
    }
}
