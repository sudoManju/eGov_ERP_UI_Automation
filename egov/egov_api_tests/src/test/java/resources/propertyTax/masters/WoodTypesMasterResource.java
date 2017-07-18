package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class WoodTypesMasterResource extends Resource {


    public Response create(String jsonString) {

        new APILogger().log("Create WoodTypes Master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.createWoodTypeUrl+tenantId);
        new APILogger().log("Create WoodTypes Master response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search WoodTypes Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchWoodTypeUrl+tenantId+s);
        new APILogger().log("Search WoodTypes Master Response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update WoodTypes Master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updateWoodTypeUrl+tenantId);
        new APILogger().log("Update WoodTypes Master response is generated as --" + response.asString());

        return response;
    }
}
