package resources.employeeMaster;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class LeaveTypeResource extends Resource {

    public Response createLeaveTypeResource(String json) {

        new APILogger().log("Create Leave Type Test Request is Started with--" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.createLeaveTypeUrl);

        new APILogger().log("Create Leave Type Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response searchLeaveTypeResource(String json) {
        new APILogger().log("Search Leave Type Test Request is Started with--" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.searchLeaveTypeUrl);

        new APILogger().log("Search Leave Type Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response updateLeaveTypeResource(String json, int leaveId) {
        new APILogger().log("Update Leave Type Test Request is Started with--" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post("http://kurnool-pilot-services.egovernments.org/hr-leave/leavetypes/" + leaveId + "/_update?tenantId=ap.kurnool");

        new APILogger().log("Update Leave Type Test Response is Generated as  --" + response.asString());
        return response;
    }
}
