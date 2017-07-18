package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class RoofTypesMasterResource extends Resource {

    public Response create(String json) {

        new APILogger().log("Create RoofType Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.createRoofTypeMasterUrl+tenantId);
        new APILogger().log("Create RoofType Master Response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search RoofType Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchRoofTypeMasterUrl+tenantId+s);
        new APILogger().log("Search RoofType Master Response is generated as --" + response.asString());

        return response;
    }

    public Response update(String json) {

        new APILogger().log("Update RoofType Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.updateRoofTypeMasterUrl+tenantId);
        new APILogger().log("Update RoofType Master Response is generated as --" + response.asString());

        return response;
    }
}
