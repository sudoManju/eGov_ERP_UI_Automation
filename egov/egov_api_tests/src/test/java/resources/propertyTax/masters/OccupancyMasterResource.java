package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class OccupancyMasterResource {


    public Response create(String jsonString) {

        new APILogger().log("Create Occupancy master request is started as --"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.createOccupancyMasterUrl);

        new APILogger().log("Create Occupancy master response is generated as --"+response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search Occupancy master request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.searchOccupancyMasterUrl+s);

        new APILogger().log("Search Occupancy master response is generated as --"+response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update Occupancy master request is started as --"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.updateOccupancyMasterUrl);

        new APILogger().log("Update Occupancy master response is generated as --"+response.asString());

        return response;
    }
}
