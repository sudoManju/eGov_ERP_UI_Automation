package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static data.ConstantData.tenantId;

public class DemandServiceResource extends Resource{

    public Response create(String json){
        new APILogger().log("Create Demand Service Request is started as --"+json);
        Response response = getPOSTResponseFromDEV(json, Properties.createDemandUrl);
        new APILogger().log("Create Demand Service Response is generated as --"+response.asString());

        return response;
    }

    public Response search(String jsonString, String s) {
        new APILogger().log("Search Demand Service Request is started as --"+jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.searchDemandUrl+tenantId+s);
        new APILogger().log("Search Demand Service Response is generated as --"+response.asString());

        return response;
    }
}
