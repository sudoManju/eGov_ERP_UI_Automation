package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class WoodTypesResource {


    public Response create(String jsonString) {

        new APILogger().log("Create WoodTypes Master request is started as --"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.createWoodTypeUrl);

        new APILogger().log("Create WoodTypes Master response is generated as --"+response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search WoodTypes Master request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.searchWoodTypeUrl+s);

        new APILogger().log("Search WoodTypes Master Response is generated as --"+response.asString());

        return response;
    }
}
