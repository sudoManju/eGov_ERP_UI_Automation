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

    public Response searchCategoryTypeResource(String jsonString, String path) {
        new APILogger().log("Search CategoryType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchCategoryTypeUrl + path);

        new APILogger().log("Search CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateCategoryTypeResource(String jsonString, String code) {
        new APILogger().log("Update CategoryType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/categorytype/" + code + "/_update");
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
        new APILogger().log("Create PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchPipeSizeResource(String jsonString, String path) {
        new APILogger().log("Search PipeSize Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchPipeSizeUrl + path);
        new APILogger().log("Search PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePipeSizeResource(String jsonString, String code) {
        new APILogger().log("Update PipeSize Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/pipesize/" + code + "/_update");
        new APILogger().log("Update PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createDocumentTypeResource(String jsonString) {
        new APILogger().log("Create DocumentType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreateDocumentTypeUrl);
        new APILogger().log("Create DocumentType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchDocumentTypeResource(String jsonString, String path) {
        new APILogger().log("Search DocumentType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchDocumentTypeUrl + path);
        new APILogger().log("Search DocumentType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateDocumentTypeResource(String jsonString, String code) {
        new APILogger().log("Update DocumentType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/documenttype/" + code + "/_update");
        new APILogger().log("Update DocumentType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createSourceTypeResource(String jsonString) {
        new APILogger().log("Create SourceType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreateSourceTypeUrl);
        new APILogger().log("Create SourceType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchSourceTypeResource(String jsonString, String path) {
        new APILogger().log("Create SourceType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchSourceTypeUrl + path);
        new APILogger().log("Create SourceType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateSourceTypeResource(String jsonString, String code) {
        new APILogger().log("Update SourceType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/sourcetype/" + code + "/_update");
        new APILogger().log("Update SourceType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createSupplyTypeResource(String jsonString) {
        new APILogger().log("Create SupplyType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreateSupplyTypeUrl);
        new APILogger().log("Create SupplyType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchSupplyTypeResource(String jsonString, String path) {
        new APILogger().log("Search SupplyType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchSupplyTypeUrl + path);
        new APILogger().log("Search SupplyType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateSupplyTypeResource(String jsonString, String code) {
        new APILogger().log("Update SupplyType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/supplytype/" + code + "/_update");
        new APILogger().log("Update SupplyType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createDocumentTypeApplicationTypeResource(String jsonString) {
        new APILogger().log("Create DocumentType - ApplicationType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreateDocumentTypeApplicationTypeUrl);
        new APILogger().log("Create DocumentType - ApplicationType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchDocumentTypeApplicationTypeResource(String jsonString) {
        new APILogger().log("Search DocumentType - ApplicationType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchDocumentTypeApplicationTypeUrl);
        new APILogger().log("Search DocumentType - ApplicationType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createPropertyTypeUsageType(String jsonString) {
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsCreatePropertyTypeUsageTypeUrl);
        return response;
    }

    public Response searchPropertyTypeUsageTypeResource(String jsonString, String path) {
        new APILogger().log("Search PropertyType - UsageType Test Request is Started with --" + jsonString);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.wcmsSearchPropertyTypeUsageTypeUrl + path);

        new APILogger().log("Search PropertyType - UsageType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePropertyTypeUsageType(String jsonString, int propertyTypeUsageTypeId) {
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/propertytype-usagetype/" + propertyTypeUsageTypeId + "/_update");

        return response;
    }
}
