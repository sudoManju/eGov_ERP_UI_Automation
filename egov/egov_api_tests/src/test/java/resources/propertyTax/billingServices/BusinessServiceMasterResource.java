package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class BusinessServiceMasterResource extends Resource {

    public Response create(String json){

        new APILogger().log("Create Business Service Master Request is Started as --"+json);
        Response response = getPOSTResponseFromDEV(json, Properties.createBusinessServiceMasterUrl);
        new APILogger().log("Create Business Service Master Response is generated as --"+response.asString());

        return response;
    }

    public Response search(String json,String s){

        new APILogger().log("Create Business Service Master Request is Started as --"+json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchBusinessServiceMasterUrl+tenantId+"&businessService="+serviceName+s);
        new APILogger().log("Create Business Service Master Response is generated as --"+response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update Business Service Master Request is Started as --"+jsonString);
        Response response = getPOSTResponseFromDEV(jsonString, Properties.updateBusinessServiceMasterUrl);
        new APILogger().log("Update Business Service Master Response is generated as --"+response.asString());

        return response;
    }
}
