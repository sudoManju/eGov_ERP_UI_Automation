package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class CommonMasterResource {

    public Response searchLanguageTest(String json, String auth_token) {
        new APILogger().log("Search Language Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token", auth_token)
                .body(json)
                .when()
                .post(Properties.devServerUrl + Properties.cmLanguageUrl);

        return response;
    }

    public Response searchDepartmentTest(String jsonString, String access_token) {
        new APILogger().log("Search Department Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token", access_token)
                .body(jsonString)
                .when()
                .post(Properties.devServerUrl + Properties.cmDepartmentUrl);

        return response;
    }

    public Response searchCommunityTest(String jsonString, String access_token) {

        new APILogger().log("Search Community Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token", access_token)
                .body(jsonString)
                .when()
                .post(Properties.devServerUrl + Properties.cmCommunityUrl);

        return response;
    }
}
