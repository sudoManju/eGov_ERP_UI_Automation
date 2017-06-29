package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class PropertyTypeMasterResource {

    public Response create(String json){

        new APILogger().log("Create PropertyType request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createPropertyTypeUrl);

        new APILogger().log("Create PropertyType Response is generated as --"+response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search PropertyType request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.searchPropertyTypeUrl+s);

        new APILogger().log("Search PropertyType Response is generated as --"+response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update PropertyType request is started as --"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.updatePropertyTypeUrl);

        new APILogger().log("Update PropertyType Response is generated as --"+response.asString());

        return response;
    }
}
