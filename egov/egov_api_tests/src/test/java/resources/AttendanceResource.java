package resources;

import com.jayway.restassured.response.Response;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AttendanceResource {

    public Response post(String jsonData,String auth_token)
    {
        Response response = given().request().with()
                            .urlEncodingEnabled(false)
                            .header("Content-Type", "application/json")
                            .header("auth-token", auth_token)
                            .body(jsonData)
                            .when()
                            .post(Properties.devServerUrl + Properties.searchAttendanceUrl);
        return response;
    }
}
