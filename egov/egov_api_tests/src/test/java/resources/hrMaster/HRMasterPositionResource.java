package resources.hrMaster;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class HRMasterPositionResource extends Resource {

    public Response createPositionResource(String json) {
        new APILogger().log("Create Position Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisCreatePositionUrl);

        new APILogger().log("Create Position Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response searchPositionResource(String json, String positionName) {
        new APILogger().log("Search Position Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchPositionUrl + "&name=" + positionName);

        new APILogger().log("Search Position Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePositionResource(String json, int positionId) {
        new APILogger().log("Update Position Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisUpdatePositionUrl + positionId + "/_update");

        new APILogger().log("Update Position Test Response is Generated as  --" + response.asString());
        return response;
    }
}
