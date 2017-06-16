package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class FloorTypesResource {

    public Response createFloorTypeMaster(String jsonString) {

        new APILogger().log("Create FloorTypes request is started as--"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.createFloorTypesUrl);

        new APILogger().log("Create FloorTypes response is generated as --"+response.asString());

        return response;
    }

    public Response searchFloorTypeMaster(String jsonString,String s){

        new APILogger().log("Search FloorTypes request is started as--"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.searchFloorTypesUrl+s);

        new APILogger().log("Search FloorTypes response is generated as --"+response.asString());

        return response;
    }
}
