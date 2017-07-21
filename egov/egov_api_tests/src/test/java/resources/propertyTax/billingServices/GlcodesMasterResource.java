package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class GlcodesMasterResource extends Resource {

    public Response create(String json){

        new APILogger().log("Create Glcodes Master request is started as --"+json);
        Response response = getPOSTResponseFromDEV(json,Properties.createGlcodesMasterUrl);
        new APILogger().log("Create Glcodes Master response is generated as --"+response.asString());

        return response;
    }

    public Response search(String jsonString, String s) {
        new APILogger().log("Search Glcodes Master Request is started as --"+jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.searchGlcodesMasterUrl+tenantId+"&service="+serviceName+s);
        new APILogger().log("Search Glcodes Master Response is generated as --"+response.asString());

        return response;
    }

    public Response update(String jsonString) {

       new APILogger().log("Update Glcodes Master Request is started as --"+jsonString);
       Response response = getPOSTResponseFromDEV(jsonString,Properties.updateGlcodesMasterUrl);
       new APILogger().log("Update Glcodes Master Response is generated as --"+response.asString());

        return response;
    }
}
