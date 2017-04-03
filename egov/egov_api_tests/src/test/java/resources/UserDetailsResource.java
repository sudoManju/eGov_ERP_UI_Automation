package resources;

import com.jayway.restassured.response.Response;
import entities.responses.login.LoginResponse;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class UserDetailsResource {

    public Response getUserDetails(LoginResponse loginResponse, String json) {
        new APILogger().log("User Details Request is Started --");

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token", loginResponse.getAccess_token())
                .body(json)
                .when()
                .post(Properties.devServerUrl + Properties.userUrl);

        return response;
    }

    public Response createUser(String jsonString){

        Response response = given().request().with()
                      .header("Content-Type", "application/json")
                      .body(jsonString)
                      .when()
                      .post(Properties.devServerUrl + Properties.userCreateUrl);

        return response;
    }
}
