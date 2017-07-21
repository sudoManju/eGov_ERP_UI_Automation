package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;


public class TaxPeriodsMasterResource extends Resource{

    public Response create(String json){

        new APILogger().log("Create TaxPeriod Master Request is Started as --"+json);
        Response response = getPOSTResponseFromDEV(json, Properties.createTaxPeriodMasterUrl);
        new APILogger().log("Create TaxPeriod Master Response is Generated as --"+response.asString());

        return response;
    }

    public Response search(String json,String s){

        new APILogger().log("Search TaxPerid Master Request is Started as --"+json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchTaxPeriodMasterUrl+tenantId+"&service="+serviceName+s);
        new APILogger().log("Search TaxPeriod Master Response is generated as --"+response.asString());

        return response;
    }
}
