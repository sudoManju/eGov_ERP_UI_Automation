package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class WallTypeResource {

    public Response create(String json){

        new APILogger().log("Create WallTypes Master request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createWallTypeMasterUrl);

        new APILogger().log("Create WallTypes Master response is generated as --"+response.asString());

        return response;
    }

    public Response search(String json, String s){

        new APILogger().log("Search WallTypes Master request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.searchWallTypeMasterUrl+s);

        new APILogger().log("Search WallTypes Master response is generated as --"+response.asString());

        return response;
    }
}
