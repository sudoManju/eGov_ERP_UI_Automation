package resources.wcms;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;
import utils.ResourceHelper;

import static com.jayway.restassured.RestAssured.given;

public class WCMSResource extends Resource {

    public Response createCategoryTypeResource(String json) {
        new APILogger().log("Create CategoryType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsCreateCategoryTypeUrl);
        new APILogger().log("Create CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchCategoryTypeResource(String json, String queryParameter) {
        new APILogger().log("Search CategoryType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsSearchCategoryTypeUrl + queryParameter);
        new APILogger().log("Search CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateCategoryTypeResource(String json, String code) {
        new APILogger().log("Update CategoryType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, ResourceHelper.getBaseURL() + "/wcms-masters/categorytype/" + code + "/_update");
        new APILogger().log("Update CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createPipeSizeResource(String json) {
        new APILogger().log("Create PipeSize Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsCreatePipeSizeUrl);
        new APILogger().log("Create PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchPipeSizeResource(String json, String queryParameter) {
        new APILogger().log("Search PipeSize Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsSearchPipeSizeUrl + queryParameter);
        new APILogger().log("Search PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePipeSizeResource(String json, String code) {
        new APILogger().log("Update PipeSize Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, ResourceHelper.getBaseURL() + "/wcms-masters/pipesize/" + code + "/_update");
        new APILogger().log("Update PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createDocumentTypeResource(String json) {
        new APILogger().log("Create DocumentType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsCreateDocumentTypeUrl);
        new APILogger().log("Create DocumentType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchDocumentTypeResource(String json, String queryParameter) {
        new APILogger().log("Search DocumentType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsSearchDocumentTypeUrl + queryParameter);
        new APILogger().log("Search DocumentType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateDocumentTypeResource(String json, String code) {
        new APILogger().log("Update DocumentType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, ResourceHelper.getBaseURL() + "/wcms-masters/documenttype/" + code + "/_update");
        new APILogger().log("Update DocumentType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createSourceTypeResource(String json) {
        new APILogger().log("Create SourceType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsCreateSourceTypeUrl);
        new APILogger().log("Create SourceType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchSourceTypeResource(String json, String queryParameter) {
        new APILogger().log("Create SourceType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsSearchSourceTypeUrl + queryParameter);
        new APILogger().log("Create SourceType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateSourceTypeResource(String json, String code) {
        new APILogger().log("Update SourceType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, ResourceHelper.getBaseURL() + "/wcms-masters/sourcetype/" + code + "/_update");
        new APILogger().log("Update SourceType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createSupplyTypeResource(String json) {
        new APILogger().log("Create SupplyType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsCreateSupplyTypeUrl);
        new APILogger().log("Create SupplyType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchSupplyTypeResource(String json, String queryParameter) {
        new APILogger().log("Search SupplyType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsSearchSupplyTypeUrl + queryParameter);
        new APILogger().log("Search SupplyType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateSupplyTypeResource(String json, String code) {
        new APILogger().log("Update SupplyType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, ResourceHelper.getBaseURL() + "/wcms-masters/supplytype/" + code + "/_update");
        new APILogger().log("Update SupplyType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createDocumentTypeApplicationTypeResource(String json) {
        new APILogger().log("Create DocumentType - ApplicationType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsCreateDocumentTypeApplicationTypeUrl);
//        Response response = given().request().with()
//                .header("Content-Type", "application/json")
//                .body(json)
//                .when()
//                .post(Properties.wcmsCreateDocumentTypeApplicationTypeUrl);
        new APILogger().log("Create DocumentType - ApplicationType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchDocumentTypeApplicationTypeResource(String json, String queryParameter) {
        new APILogger().log("Search DocumentType - ApplicationType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, Properties.wcmsSearchDocumentTypeApplicationTypeUrl + queryParameter);
//        Response response = given().request().with()
//                .header("Content-Type", "application/json")
//                .body(json)
//                .when()
//                .post(Properties.wcmsSearchDocumentTypeApplicationTypeUrl + queryParameter);
        new APILogger().log("Search DocumentType - ApplicationType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updateDocumentTypeApplicationTypeResource(String json, int code) {
        new APILogger().log("Update DocumentType - ApplicationType Test Request is Started with --" + json);
        Response response = getPOSTResponseFromDEV(json, ResourceHelper.getBaseURL() + "/wcms-masters/documenttype-applicationtype/" + code + "/_update");
//        Response response = given().request().with()
//                .header("Content-Type", "application/json")
//                .body(json)
//                .when()
//                .post(ResourceHelper.getBaseURL() + "/wcms-masters/documenttype-applicationtype/" + code + "/_update");
        new APILogger().log("Update DocumentType - ApplicationType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createPropertyTypeUsageType(String json) {
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsCreatePropertyTypeUsageTypeUrl);
        return response;
    }

    public Response searchPropertyTypeUsageTypeResource(String json, String queryParameter) {
        new APILogger().log("Search PropertyType - UsageType Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsSearchPropertyTypeUsageTypeUrl + queryParameter);

        new APILogger().log("Search PropertyType - UsageType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePropertyTypeUsageType(String json, int propertyTypeUsageTypeId) {
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/propertytype-usagetype/" + propertyTypeUsageTypeId + "/_update");

        return response;
    }

    public Response createPropertyCategoryTypeResource(String json) {
        new APILogger().log("Create PropertyType - CategoryType Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsCreatePropertyCategoryTypeUrl);
        new APILogger().log("Create PropertyType - CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchPropertyCategoryTypeResource(String json, String queryParameter) {
        new APILogger().log("Search PropertyType - CategoryType Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsSearchPropertyCategoryTypeUrl + queryParameter);
        new APILogger().log("Search PropertyType - CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePropertyCategoryTypeResource(String json, String id) {
        new APILogger().log("Update PropertyType - CategoryType Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/propertytype-categorytype/" + id + "/_update");

        new APILogger().log("Update PropertyType - CategoryType Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response createPropertyPipeSize(String json) {
        new APILogger().log("Create PropertyType - PipeSize Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsCreatePropertyPipeSizeUrl);
        new APILogger().log("Create PropertyType - PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response searchPropertyPipeSizeResource(String json, String queryParameter) {
        new APILogger().log("Search PropertyType - PipeSize Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.wcmsSearchPropertyPipeSizeUrl + queryParameter);
        new APILogger().log("Search PropertyType - PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }

    public Response updatePropertyPipeSizeResource(String json, String id) {
        new APILogger().log("Update PropertyType - PipeSize Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(ResourceHelper.getBaseURL() + "/wcms-masters/propertytype-pipesize/" + id + "/_update");
        new APILogger().log("Update PropertyType - PipeSize Test Request is Generated as  --" + response.asString());
        return response;
    }
}
