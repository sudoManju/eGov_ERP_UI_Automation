package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class EgovEISResource {

    public Response searchAttendance(String jsonData, String auth_token) {
        new APILogger().log("Search Attendance Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .header("auth-token", auth_token)
                .body(jsonData)
                .when()
                .post(Properties.searchAttendanceUrl);

        new APILogger().log("Search Attendance Response is Generated as -- " + response.asString());
        return response;
    }

    public Response createAttendance(String jsonData, String access_token) {

        new APILogger().log("Create Attendance Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
//                .header("auth-token", access_token)
                .body(jsonData)
                .when()
                .post(Properties.createAttendanceURL);

        new APILogger().log("Create Attendance Response is Generated as -- " + response.asString());
        return response;

    }

    public Response searchEmployee(String jsonData) {
        new APILogger().log("Search Employee Request is started with -- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .body(jsonData)
                .when()
                .post(Properties.searchEmployeeURL);

        new APILogger().log("Search Employee Response is started with -- " + response.asString());
        return response;
    }

    public Response createEmployee(String jsonData) {
        new APILogger().log("Create Employee Request Test is started with-- " + jsonData);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .body(jsonData).when()
                .post(Properties.createEmployeeUrl);

        new APILogger().log("Create Employee Response Test is started with-- " + response.asString());
        return response;
    }
}
