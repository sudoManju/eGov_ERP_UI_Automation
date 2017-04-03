package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetServiceResource {

    public Response getSearchAssetService(String json, String auth_token) {

        new APILogger().log("Search Asset Service Request is started  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token", auth_token)
                .body(json)
                .when()
                .post(Properties.searchAssetServiceUrl);

        return response;
    }

    public Response getCreateAssetService(String jsonString, String auth_token) {

        new APILogger().log("Create Asset Service Request is started  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token", auth_token)
                .body(jsonString)
                .when()
                .post(Properties.createAssetServiceUrl);

        return response;
    }
}
