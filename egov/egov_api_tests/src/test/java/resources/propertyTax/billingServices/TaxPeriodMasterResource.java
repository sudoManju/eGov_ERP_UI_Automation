package resources.propertyTax.billingServices;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;


public class TaxPeriodMasterResource extends Resource{

    public Response create(String json){

        new APILogger().log("Create TaxPeriod Master Request is Started as --"+json);
        Response response = getPOSTResponseFromDEV(json, Properties.createTaxPeriodMasterUrl);
        new APILogger().log("Create TaxPeriod Master Response is Generated as --"+response.asString());

        return response;
    }
}
