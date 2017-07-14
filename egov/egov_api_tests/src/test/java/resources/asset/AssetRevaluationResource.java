package resources.asset;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetRevaluationResource extends Resource {

    public Response createRevaluationResource(String json) {
        new APILogger().log("Create Asset Revaluation Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.createAssetRevaluationUrl);

        new APILogger().log("Create Asset Revaluation Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response searchRevaluationResource(String json) {
        new APILogger().log("Search Asset Revaluation Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.searchAssetRevaluationUrl);

        new APILogger().log("Search Asset Revaluation Test Response is Generated as  --" + response.asString());
        return response;
    }

}
