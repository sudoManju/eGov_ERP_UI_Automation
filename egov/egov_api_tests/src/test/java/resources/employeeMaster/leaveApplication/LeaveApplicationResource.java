package resources.employeeMaster.leaveApplication;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class LeaveApplicationResource extends Resource{

    public Response createLeaveApplicationResource(String json) {
        new APILogger().log("Create Leave Application Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .when()
                .body(json)
                .post(Properties.eisCreateLeaveApplicationUrl);

        new APILogger().log("Create Leave Application Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchLeaveApplicationResource(String json, String appNum) {
        new APILogger().log("Search Leave Application Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .when()
                .body(json)
                .post(Properties.eisSearchLeaveApplicationUrl + "&applicationNumber=" + appNum);

        new APILogger().log("Search Leave Application Test Response is generated as --" + response.asString());
        return response;
    }
}

