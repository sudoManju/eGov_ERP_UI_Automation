package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class UserDetailsResource {

    public Response getUserDetails(String json) {
        new APILogger().log("User Details Request For Search is Started with--" + json);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.userUrl);

        new APILogger().log("User Details Response For Search is Generated as--" + response.asString());

        return response;
    }

    public Response createUser(String jsonString) {

        new APILogger().log("User Details Request For Create is Started with--" + jsonString);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.userCreateUrl);

        new APILogger().log("User Details Response For Create is Generated as--" + response.asString());

        return response;
    }
}
