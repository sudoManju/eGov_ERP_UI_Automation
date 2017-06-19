package resources.searchEISMaster;

import com.jayway.restassured.response.Response;
import resources.Resource;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class EISMasterResource extends Resource{

    public Response searchEmployeeTypeResource(String json) {
        new APILogger().log("Search createEmployee Test Request is Started with -- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchEmployeeTypeUrl);

        new APILogger().log("Search createEmployee Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchDesignationResource(String json) {
        new APILogger().log("Search Designation Test Request is Started with -- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchDesignationTypeUrl);

        new APILogger().log("Search Designation Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchPositionResource(String json) {
        new APILogger().log("Search Position Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchPositionUrl);

        new APILogger().log("Search Position Test Response is Generated as --" + response.asString());
        return response;
    }


    public Response searchPositionHierarchyResource(String json) {
        new APILogger().log("Search Position Hierarchy Test Request is Started with --" + json);
        System.out.println(Properties.eisSearchPositionHierarchyUrl);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchPositionHierarchyUrl);

        new APILogger().log("Search Position Hierarchy Test Response is Generated as  --" + response.asString());
        return response;
    }

    public Response searchGradeResource(String json) {
        new APILogger().log("Search Grade Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchGradeUrl);

        new APILogger().log("Search Grade Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchEmployeeGroupResource(String json) {
        new APILogger().log("Search createEmployee Group Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchEmployeeGroupUrl);

        new APILogger().log("Search createEmployee Group Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchRecruitmentQuotaResource(String json) {
        new APILogger().log("Search Recruitment Quota Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchRecruitmentQuotaUrl);

        new APILogger().log("Search Recruitment Quota Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchRecruitmentModesResource(String json) {
        new APILogger().log("Search Recruitment Modes Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchRecruitmentModesUrl);

        new APILogger().log("Search Recruitment Modes Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchHRConfigurationsResource(String json) {
        new APILogger().log("Search HR Configurations Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchHrConfigurationsUrl);

        new APILogger().log("Search HR Configurations Test Response is Generated as --" + response.asString());
        return response;
    }

    public Response searchHRStatusesResource(String json) {
        new APILogger().log("Search HR Statuses Test Request is Started with --" + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("cookie", "SESSIONID=" + scenarioContext.getSessionId())
                .body(json)
                .when()
                .post(Properties.eisSearchHrStatusesUrl);

        new APILogger().log("Search HR Statuses Test Response is Generated as --" + response.asString());
        return response;
    }
}
