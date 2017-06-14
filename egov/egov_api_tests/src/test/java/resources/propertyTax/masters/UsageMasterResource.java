package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class UsageMasterResource {


    public Response createUsageMaster(String json) {

        new APILogger().log("Create Usage Master started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createUsageMasterUrl);

        new APILogger().log("Create Usage Master response generated as --"+response.asString());

        return response;
    }
}
