package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class UsageMasterResource extends Resource{

    public Response create(String json) {

        new APILogger().log("Create Usage Master started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.createUsageMasterUrl+tenantId);
        new APILogger().log("Create Usage Master response generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search Usage Master started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchUsageMasterUrl+tenantId+s);
        new APILogger().log("Search Usage master response generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update Usage Master started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updateUsageMasterUrl+tenantId);
        new APILogger().log("Update Usage Master response generated as --" + response.asString());

        return response;
    }
}
