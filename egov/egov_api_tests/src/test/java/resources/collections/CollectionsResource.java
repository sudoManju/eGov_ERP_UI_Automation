package resources.collections;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

public class CollectionsResource extends Resource {

    public Response createBusinessCategoryResource(String json) {
        new APILogger().log("Create BusinessCategory Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.collectionsCreateBusinessCategoryUrl);
        new APILogger().log("Create BusinessCategory Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchBusinessCategoryResource(String json, String path) {
        new APILogger().log("Search BusinessCategory Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.collectionsSearchBusinessCategoryUrl + path);
        System.out.println(Properties.collectionsSearchBusinessCategoryUrl + path);
        new APILogger().log("Search BusinessCategory Test Request is Generated as  --" + response.asString());
        return response;
    }
}
