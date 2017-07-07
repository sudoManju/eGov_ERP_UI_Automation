package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class FloorTypesMasterResource {

    public Response create(String jsonString) {

        new APILogger().log("Create FloorTypes request is started as--" + jsonString);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.createFloorTypesUrl);

        new APILogger().log("Create FloorTypes response is generated as --" + response.asString());

        return response;
    }

    public Response search(String jsonString, String s) {

        new APILogger().log("Search FloorTypes request is started as--" + jsonString);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.searchFloorTypesUrl + s);

        new APILogger().log("Search FloorTypes response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update FloorTypes request is started as--" + jsonString);

        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.updateFloorTypesUrl);

        new APILogger().log("Update FloorTypes response is generated as --" + response.asString());

        return response;
    }
}
