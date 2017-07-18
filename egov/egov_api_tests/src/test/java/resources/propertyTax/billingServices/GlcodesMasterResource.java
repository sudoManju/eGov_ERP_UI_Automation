package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class GlcodesMasterResource {

    public Response create(String json){

        new APILogger().log("Create Glcodes Master request is started as --"+json);

        Response response = given(). request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.createGlcodesMasterUrl);

        new APILogger().log("Create Glcodes Master response is generated as --"+response.asString());

        return response;
    }
}
