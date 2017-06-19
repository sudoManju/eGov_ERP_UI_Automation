package resources.hrMaster;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class HRMasterDesignationResource extends Resource {

    public Response createDesignationResource(String json) {

        new APILogger().log("Create Designation Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisCreateDesignationTypeUrl);

        new APILogger().log("Create Designation Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response searchDesignationResource(String json, String name) {
        new APILogger().log("Search Designation Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchDesignationTypeUrl + "&name=" + name);

        new APILogger().log("Search Designation Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response updateDesignationResource(String json, int id) {

        new APILogger().log("Update Designation Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisUpdateDesignationTypeUrl + id + "/_update");

        new APILogger().log("Update Designation Test Response is Generated as  --" + response.asString());
        return response;

    }
}
