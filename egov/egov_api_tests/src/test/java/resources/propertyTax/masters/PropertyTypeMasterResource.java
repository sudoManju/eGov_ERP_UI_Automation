package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class PropertyTypeMasterResource {

    public Response createPropertyType(String json){

        new APILogger().log("Create PropertyType request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createPropertyTypeUrl);

        new APILogger().log("Create PropertyType Response is generated as --"+response.asString());

        return response;
    }
}
