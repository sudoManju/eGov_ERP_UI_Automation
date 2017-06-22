package resources.wcms;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;
import utils.ResourceHelper;

import static com.jayway.restassured.RestAssured.given;

public class WCMSResource {

    public Response createCategoryTypeResource(String json) {
        new APILogger().log("Create Category Type Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsCreateCategoryTypeUrl);

        new APILogger().log("Create Category Type Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchCategoryTypeResource(String jsonString, String name, String path) {
        new APILogger().log("Search Category Type Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchCategoryTypeUrl + path + name);

        new APILogger().log("Search Category Type Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateCategoryTypeResource(String jsonString, String code) {
        new APILogger().log("Update Category Type Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/category/" + code + "/_update");
        new APILogger().log("Update Category Type Test Request is Generated as  --" + response.asString());
        return response;
    }
}
