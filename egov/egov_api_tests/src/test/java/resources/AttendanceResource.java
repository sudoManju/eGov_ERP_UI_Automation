package resources;

import com.jayway.restassured.response.Response;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AttendanceResource {

    public Response post(String jsonData)
    {
        Response response = given().request().with()
                            .urlEncodingEnabled(false)
                            .header("Content-Type", "application/json")
                            .header("auth-token", "bedb20fb-7d74-445e-94cc-6a64e825d509")
                            .body(jsonData)
                            .when()
                            .post(Properties.devServerUrl + Properties.searchAttendanceUrl);
        return response;
    }
}
