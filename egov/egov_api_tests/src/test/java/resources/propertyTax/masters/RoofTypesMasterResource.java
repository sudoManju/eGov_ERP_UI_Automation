package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class RoofTypesMasterResource {

    public Response create(String json) {

        new APILogger().log("Create RoofType Master request is started as --" + json);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.createRoofTypeMasterUrl);

        new APILogger().log("Create RoofType Master Response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search RoofType Master request is started as --" + json);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.searchRoofTypeMasterUrl + s);

        new APILogger().log("Search RoofType Master Response is generated as --" + response.asString());

        return response;
    }

    public Response update(String json) {

        new APILogger().log("Update RoofType Master request is started as --" + json);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.updateRoofTypeMasterUrl);

        new APILogger().log("Update RoofType Master Response is generated as --" + response.asString());

        return response;
    }
}
