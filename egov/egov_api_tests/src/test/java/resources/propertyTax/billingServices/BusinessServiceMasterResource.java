package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

public class BusinessServiceMasterResource extends Resource {

    public Response create(String json){

        new APILogger().log("Create Business Service Master Request is Started as --"+json);
        Response response = getPOSTResponseFromDEV(json, Properties.createBusinessServiceMasterUrl);
        new APILogger().log("Create Business Service Master Response is generated as --"+response.asString());

        return response;
    }
}
