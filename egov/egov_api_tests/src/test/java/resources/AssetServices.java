package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class AssetServices {

    public Response getSearchAssetService(String json,String auth_token){

        new APILogger().log("Searching the Asset Service  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token",auth_token)
                .body(json)
                .when()
                .post(Properties.devServerUrl + Properties.searchAssetServiceUrl);

        return response;
    }

    public Response getCreateAssetService(String jsonString, String auth_token) {

        new APILogger().log("Creating the Asset Service  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token",auth_token)
                .body(jsonString)
                .when()
                .post(Properties.devServerUrl + Properties.createAssetServiceUrl);

        return response;
    }
}
