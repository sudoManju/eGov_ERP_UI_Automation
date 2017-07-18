package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class WallTypesMasterResource extends Resource {

    public Response create(String json) {

        new APILogger().log("Create WallTypes Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.createWallTypeMasterUrl+tenantId);
        new APILogger().log("Create WallTypes Master response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search WallTypes Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchWallTypeMasterUrl+tenantId+s);
        new APILogger().log("Search WallTypes Master response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update WallTypes Master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updateWallTypeMasterUrl+tenantId);
        new APILogger().log("Update WallTypes Master response is generated as --" + response.asString());

        return response;
    }
}
