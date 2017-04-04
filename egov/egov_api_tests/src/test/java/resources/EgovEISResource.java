package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class EgovEISResource {

    public Response searchAttendance(String jsonData, String auth_token) {
        new APILogger().log("Search Attendance is started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .header("auth-token", auth_token)
                .body(jsonData)
                .when()
                .post(Properties.searchAttendanceUrl);
        return response;
    }

    public Response createAttendance(String jsonData, String access_token) {

        new APILogger().log("Create Attendance is started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .header("auth-token", access_token)
                .body(jsonData)
                .when()
                .post(Properties.createAttendanceURL);
        return response;

    }

    public Response searchEmployee(String jsonData) {
        new APILogger().log("Search Employee is started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .body(jsonData)
                .when()
                .post(Properties.searchEmployeeURL);
        return response;
    }

    public Response createEmployee(String jsonData) {
        new APILogger().log("Create Employee Test is started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .body(jsonData).when()
                .post(Properties.createEmployeeUrl);
        return response;
    }
}
