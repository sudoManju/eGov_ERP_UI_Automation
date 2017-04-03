package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class LAMSServiceResource {

    public Response lamsServiceSearch(String jsonString) {

        new APILogger().log("LAMS service search request is started -- ");

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.lAMSServiceSearchUrl);

        return response;
    }
}
