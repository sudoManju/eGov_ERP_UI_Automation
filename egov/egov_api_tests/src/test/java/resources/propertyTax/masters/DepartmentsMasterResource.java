package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class DepartmentsMasterResource extends Resource {

    public Response create(String jsonString) {

        new APILogger().log("Create Department Master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.createDepartmentsMasterUrl+tenantId);
        new APILogger().log("Create Department Master response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search Department Master request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchDepartmentsMasterUrl+tenantId+s);
        new APILogger().log("Search Department Master response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update Department Master request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updateDepartmentsMasterUrl+tenantId);
        new APILogger().log("Update Department Master response is generated as --" + response.asString());

        return response;
    }
}
