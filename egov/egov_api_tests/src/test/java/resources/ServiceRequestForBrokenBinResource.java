package resources;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by soumyaghosh on 22/02/17.
 */
public class ServiceRequestForBrokenBinResource {

    public Response serviceTypeValidation(String jsonString) {
        return given().request().with()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/pgr/requests?jurisdictionId=ap.kurnool");
    }
}
