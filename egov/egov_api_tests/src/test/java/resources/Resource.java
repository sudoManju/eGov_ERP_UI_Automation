package resources;

import com.jayway.restassured.response.Response;
import tests.BaseAPITest;

import static com.jayway.restassured.RestAssured.given;

public class Resource extends BaseAPITest {

    protected Response getPOSTResponseFromDEV(String json, String path) {
        return given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(path);
    }
}
