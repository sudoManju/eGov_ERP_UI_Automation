package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class GlcodesMasterResource extends Resource {

    public Response create(String json){

        new APILogger().log("Create Glcodes Master request is started as --"+json);
        Response response = getPOSTResponseFromDEV(json,Properties.createGlcodesMasterUrl);
        new APILogger().log("Create Glcodes Master response is generated as --"+response.asString());

        return response;
    }
}
