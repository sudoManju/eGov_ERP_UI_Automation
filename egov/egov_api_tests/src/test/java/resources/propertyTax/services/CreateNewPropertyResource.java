package resources.propertyTax.services;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class CreateNewPropertyResource {

    public Response create(String json){

        new APILogger().log("Create new Property request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createNewPropertyUrl);

        new APILogger().log("Create new Property response is generated as --"+response.asString());

        return response;
    }
}
