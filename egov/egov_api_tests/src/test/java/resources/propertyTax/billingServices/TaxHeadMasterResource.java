package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class TaxHeadMasterResource extends Resource {

    public Response create(String json){

        new APILogger().log("Create TaxHead Master request is started as --"+json);
        Response response = getPOSTResponseFromDEV(json,Properties.createTaxHeadMasterUrl);
        new APILogger().log("Create TaxHead Master response is generated as --"+response.asString());

        return response;
    }

    public Response search(String jsonString,String s) {

        new APILogger().log("Search TaxHead Master request is started as --"+jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.searchTaxHeadMasterUrl+tenantId+"&service="+serviceName+s);
        new APILogger().log("Search TaxHead Master response is generated as --"+response.asString());

        return response;
    }
}
