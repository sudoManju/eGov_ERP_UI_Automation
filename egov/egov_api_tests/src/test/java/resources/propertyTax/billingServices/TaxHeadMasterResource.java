package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class TaxHeadMasterResource extends Resource {

    public Response create(String json){

        new APILogger().log("Create TaxHead Master request is started as --"+json);
        Response response = getPOSTResponseFromDEV(json,Properties.createTaxHeadMasterUrl);
        new APILogger().log("Create TaxHead Master response is generated as --"+response.asString());

        return response;
    }
}
