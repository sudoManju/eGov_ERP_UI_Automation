package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class StructureClassResource {


    public Response create(String json) {

        new APILogger().log("Create StructureClass Master request is started with --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createStructureClassUrl);

        new APILogger().log("Create StructureClass Master response is generated as --"+response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search StructureClass Master request is started with --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.searchStructureClassUrl+s);

        new APILogger().log("Search StructureClass Master response is generated as --"+response.asString());

        return response;
    }
}
