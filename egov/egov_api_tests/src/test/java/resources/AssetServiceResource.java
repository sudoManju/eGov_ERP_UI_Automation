package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetServiceResource {

    public Response getSearchAssetService(String json, String sessionId, String assetCode) {
        String path = assetCode == null ? null : "&code=" + assetCode;

        new APILogger().log("Search Asset Service Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + sessionId)
                .body(json)
                .when()
                .post(Properties.searchAssetServiceUrl + path);

        new APILogger().log("Search Asset Service Response is Generated as --" + response.asString());
        return response;
    }

    public Response getCreateAssetService(String jsonString, String sessionId) {
        new APILogger().log("Create Asset Service Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + sessionId)
                .body(jsonString)
                .when()
                .post(Properties.createAssetServiceUrl);

        new APILogger().log("Create Asset Service Response is Generated as --" + response.asString());
        return response;
    }
}
