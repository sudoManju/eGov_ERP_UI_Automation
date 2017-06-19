package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetServiceResource extends Resource{

    public Response getSearchAssetService(String json, String assetCode) {
        String path = assetCode == null ? null : "&code=" + assetCode;

        new APILogger().log("Search Asset Service Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.searchAssetServiceUrl + path);

        new APILogger().log("Search Asset Service Response is Generated as --" + response.asString());
        return response;
    }

    public Response getCreateAssetService(String jsonString) {
        new APILogger().log("Create Asset Service Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(jsonString)
                .when()
                .post(Properties.createAssetServiceUrl);

        new APILogger().log("Create Asset Service Response is Generated as --" + response.asString());
        return response;
    }
}
