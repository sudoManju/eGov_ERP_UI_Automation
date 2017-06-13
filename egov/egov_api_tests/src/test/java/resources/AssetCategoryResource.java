package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetCategoryResource {

    public Response create(String json, String sessionId) {

        new APILogger().log("Create Asset Category Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + sessionId)
                .body(json)
                .when()
                .post(Properties.assetCategoryCreateUrl);

        new APILogger().log("Create Asset Category Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response search(String json, String sessionId, String code) {
        String path = code != null ? "&code=" + code : null;

        new APILogger().log("Search Asset Category Request Test is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + sessionId)
                .body(json)
                .when()
                .post(Properties.assetCategorySearchUrl + path);

        new APILogger().log("Search Asset Category Response Test is Generated as --" + response.asString());
        return response;
    }
}
