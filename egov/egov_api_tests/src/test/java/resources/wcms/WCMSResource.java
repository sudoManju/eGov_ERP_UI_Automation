package resources.wcms;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;
import utils.ResourceHelper;

import static com.jayway.restassured.RestAssured.given;

public class WCMSResource {

    public Response createCategoryTypeResource(String json) {
        new APILogger().log("Create CategoryType Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsCreateCategoryTypeUrl);

        new APILogger().log("Create CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchCategoryTypeResource(String jsonString, String name, String path) {
        new APILogger().log("Search CategoryType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchCategoryTypeUrl + path + name);

        new APILogger().log("Search CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateCategoryTypeResource(String jsonString, String code) {
        new APILogger().log("Update CategoryType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/category/" + code + "/_update");
        new APILogger().log("Update CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createPipeSizeResource(String jsonString) {
        new APILogger().log("Create PipeSize Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreatePipeSizeUrl);
        new APILogger().log("Create PipeSize Test Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchPipeSizeResource(String jsonString, String millimeterSize) {
        new APILogger().log("Search PipeSize Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchPipeSizeUrl + "&sizeInMilimeter=" + millimeterSize);
        new APILogger().log("Search PipeSize Test Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePipeSizeResource(String jsonString, String code) {
        new APILogger().log("Update PipeSize Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/pipesize/" + code + "/_update");
        new APILogger().log("Update PipeSize Test Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createDocumentTypeResource(String jsonString) {
        new APILogger().log("Create DocumentType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreateDocumentTypeUrl);
        new APILogger().log("Create DocumentType Test Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchDocumentTypeResource(String jsonString, String documentName) {
        new APILogger().log("Search DocumentType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchDocumentTypeUrl + "&name=" + documentName);
        new APILogger().log("Search DocumentType Test Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateDocumentTypeResource(String jsonString, String code) {
        new APILogger().log("Update DocumentType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/documenttype/" + code + "/_update");
        new APILogger().log("Update DocumentType Test Test Request is Generated as  --" + response.asString());
        return response;
    }
}