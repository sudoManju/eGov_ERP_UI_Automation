package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;
import static data.ConstantData.tenantId;

public class PropertyTypeMasterResource extends Resource {

    public Response create(String json) {

        new APILogger().log("Create PropertyType request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.createPropertyTypeUrl+tenantId);
        new APILogger().log("Create PropertyType Response is generated as --" + response.asString());

        return response;
    }

    public Response search(String json, String s) {

        new APILogger().log("Search PropertyType request is started as --" + json);
        Response response = getPOSTResponseFromDEV(json,Properties.searchPropertyTypeUrl+tenantId+ s);
        new APILogger().log("Search PropertyType Response is generated as --" + response.asString());

        return response;
    }

    public Response update(String jsonString) {

        new APILogger().log("Update PropertyType request is started as --" + jsonString);
        Response response = getPOSTResponseFromDEV(jsonString,Properties.updatePropertyTypeUrl+tenantId);
        new APILogger().log("Update PropertyType Response is generated as --" + response.asString());

        return response;
    }
}
