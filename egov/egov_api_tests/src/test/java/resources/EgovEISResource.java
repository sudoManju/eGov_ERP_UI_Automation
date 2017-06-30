package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class EgovEISResource extends Resource {

    public Response searchAttendanceResource(String jsonData) {
        new APILogger().log("Search Attendance Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(jsonData)
                .when()
                .post(Properties.searchAttendanceUrl);

        new APILogger().log("Search Attendance Response is Generated as -- " + response.asString());
        return response;
    }

    public Response createAttendanceResource(String jsonData) {

        new APILogger().log("Create Attendance Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(jsonData)
                .when()
                .post(Properties.createAttendanceURL);

        new APILogger().log("Create Attendance Response is Generated as -- " + response.asString());
        return response;

    }

    public Response searchEmployee(String jsonData, String criteria) {
        new APILogger().log("Search createEmployee Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .header("cookie","SESSIONID="+scenarioContext.getSessionId())
                .body(jsonData)
                .when()
                .post(Properties.searchEmployeeURL + criteria);

        System.out.println(Properties.searchEmployeeURL + criteria);

        new APILogger().log("Search createEmployee Response is generated as -- " + response.asString());
        return response;
    }

    public Response searchEmployee(String jsonData) {
        new APILogger().log("Search createEmployee Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .body(jsonData)
                .when()
                .post("http://10.0.0.151:32656/hr-employee/employees/_search?tenantId=ap.kurnool");

        new APILogger().log("Search createEmployee Response is generated as -- " + response.asString());
        return response;
    }

    public Response createEmployee(String jsonData,String s) {
        new APILogger().log("Create createEmployee Request Test is started with -- " + jsonData);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(jsonData)
                .when()
                .post(Properties.createEmployeeUrl);

        new APILogger().log("Create createEmployee Response Test is generated as -- " + response.asString());
        return response;
    }

    public Response searchEmployeeLeaveTypesResource(String jsonData) {
        new APILogger().log("Search Employee Leave Types Test Request is Started with -- " + jsonData);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(jsonData)
                .when()
                .post(Properties.searchLeaveTypeUrl);

        new APILogger().log("Search Employee Leave Types Test Response is Generated as -- " + response.asString());
        return response;
    }

    public Response searchEmployeeLeaveApplicationsResource(String jsonData) {
        new APILogger().log("Search Employee Leave Applications Test Request is Started with -- " + jsonData);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(jsonData).when()
                .post(Properties.eisSearchLeaveApplicationUrl);

        new APILogger().log("Search Employee Leave Applications Test Response is Generated with -- " + response.asString());
        return response;
    }

    public Response hrLeaveCreateOpeningBalance(String jsonData) {

        new APILogger().log("Create HR Leave Opening Balance Test is started with-- " + jsonData);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonData)
                .when()
                .post("http://10.0.0.151:32656/hr-leave/leaveopeningbalances/_create");
//                .post(Properties.createOpeningBalanceUrlUrl);

        new APILogger().log("Create HR Leave Opening Balance Test is completed with-- " + response.asString());
        return response;
    }

    public Response hrLeaveSearchOpeningBalance(String jsonData, String path) {

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonData)
                .when()
                .post("http://10.0.0.151:32656/hr-leave/leaveopeningbalances/_search?tenantId=ap.kurnool" + path);

        return response;
    }

    public Response hrLeaveUpdateOpeningBalance(String json, int id) {

        new APILogger().log("Update HR Leave Opening Balance Test is started with-- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("http://10.0.0.151:32656/hr-leave/leaveopeningbalances/" + id + "/_update");

        new APILogger().log("Update HR Leave Opening Balance Test is completed with-- " + response.asString());

        return response;

    }
}