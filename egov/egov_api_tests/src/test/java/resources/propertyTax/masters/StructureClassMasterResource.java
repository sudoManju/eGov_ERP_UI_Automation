package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class StructureClassMasterResource extends Resource {


    public Response create(String json) {

        new APILogger().log("Create StructureClass Master request is started with --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.createStructureClassUrl+tenantId);
        new APILogger().log("Create StructureClass Master response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search StructureClass Master request is started with --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchStructureClassUrl+tenantId+s);
        new APILogger().log("Search StructureClass Master response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update StructureClass Master request is started with --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updateStructureClassUrl+tenantId);
        new APILogger().log("Update StructureClass Master response is generated as --" + response.asString());

        return response;
    }
}
