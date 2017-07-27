package resources.asset;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetServiceResource extends Resource {

    public Response getSearchAssetServiceResource(String json, String endPoints) {
        new APILogger().log("Search Asset Service Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.searchAssetServiceUrl + endPoints);

        new APILogger().log("Search Asset Service Response is Generated as --" + response.asString());
        return response;
    }

    public Response createAssetService(String jsonString) {
        new APILogger().log("Create Asset Service Request is Started with --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.createAssetServiceUrl);
        new APILogger().log("Create Asset Service Response is Generated as --" + response.asString());
        return response;
    }
}
