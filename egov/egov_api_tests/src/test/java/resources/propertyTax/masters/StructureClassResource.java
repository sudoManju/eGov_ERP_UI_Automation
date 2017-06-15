package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class StructureClassResource {


    public Response createStructureClass(String json) {

        new APILogger().log("Create StructureClass Master request is started with --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createStructureClassUrl);

        new APILogger().log("Create StructureClass Master response is generated as --"+response.asString());

        return response;
    }
}
