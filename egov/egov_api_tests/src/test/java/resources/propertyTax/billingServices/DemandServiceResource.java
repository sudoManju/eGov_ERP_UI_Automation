package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

public class DemandServiceResource extends Resource{

    public Response create(String json){
        new APILogger().log("Create Demand Service Request is started as --"+json);
        Response response = getPOSTResponseFromDEV(json, Properties.createDemandUrl);
        new APILogger().log("Create Demand Service Response is generated as --"+response.asString());

        return response;
    }
}
