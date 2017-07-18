package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class OccupancyMasterResource extends Resource {


    public Response create(String jsonString) {

        new APILogger().log("Create Occupancy master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.createOccupancyMasterUrl+tenantId);
        new APILogger().log("Create Occupancy master response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search Occupancy master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchOccupancyMasterUrl+tenantId+s);
        new APILogger().log("Search Occupancy master response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update Occupancy master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updateOccupancyMasterUrl+tenantId);
        new APILogger().log("Update Occupancy master response is generated as --" + response.asString());

        return response;
    }
}
