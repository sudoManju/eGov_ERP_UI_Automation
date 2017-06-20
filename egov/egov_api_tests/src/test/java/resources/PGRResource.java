package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class PGRResource {

    public Response createComplaintResource(String json) {
        new APILogger().log("Creating complaint Request for PGR is started  with-- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.complaintUrl);

        new APILogger().log("Creating complaint Response for PGR is generated as-- " + response.asString());

        return response;
    }

    public Response getComplaintResource(String serviceRequestId, String json) {
        new APILogger().log("Getting a PGR complaint Request is started for-- " + json);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .when()
                .body(json)
                .post(Properties.getPGRComplaintUrl + serviceRequestId);

        new APILogger().log("Getting a PGR complaint Response is generated as-- " + response.asString());
        return response;
    }

    public Response fetchAllComplaintsResource(String json) {
        new APILogger().log("Fetch all Complaints Request is started -- " + json);
        Response response = given().request().with()
                .urlEncodingEnabled(true)
                .header("Content-Type", "application/json")
                .when()
                .body(json)
                .post(Properties.fetchComplaintsUrl);

        new APILogger().log("Fetch all Complaints Response is generated as-- " + response.asString());

        return response;
    }

    public Response fetchComplaintByIdResource(String json) {
        new APILogger().log("Fetch Complaints By Id Request is started  for-- " + json);
        Response response = given().request().with()
                .urlEncodingEnabled(true)
                .header("Content-Type", "application/json")
                .when()
                .body(json)
                .post(Properties.fetchComplaintsByIdUrl);

        new APILogger().log("Get Frequently filled Complaints Response is generated as-- " + response.asString());

        return response;
    }

    public Response updateAndClosePGRComplaintResource(String json) {
        new APILogger().log("Update/Close complaint Request for PGR is started  with-- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.updateComplaintUrl);

        new APILogger().log("Update/Close complaint Response for PGR is started  with-- " + response.asString());
        return response;
    }

    public Response getReceivingCenterResource(String json) {
        new APILogger().log("All Receiving Centers Request for PGR is started  -- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.pgrReceivingCenterUrl);

        new APILogger().log("All Receiving Centers Response for PGR is generated as-- " + response.asString());

        return response;
    }

    public Response getReceivingCenterByIdResource(String json) {
        new APILogger().log("Receiving Centers By Id Request for PGR is started  -- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.pgrReceivingCenterUrl + "&id=1");

        new APILogger().log("Receiving Centers By Id Response for PGR is generated as-- " + response.asString());
        return response;
    }

    public Response getReceivingModesResource(String json) {
        new APILogger().log("Get All Receiving Modes For PGR is started-- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.pgrReceivingModesUrl);

        new APILogger().log("All Receiving Modes For PGR is Response is generated-- " + response.asString());
        return response;
    }

    public Response complaintTypeByServiceCodeResource(String json) {
        new APILogger().log("Get Complaint Type By Service Code For PGR is started-- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.getComplaintTypeByServiceCodeUrl);


        new APILogger().log("Complaint Type By Service Code For PGR is Response is generated-- " + response.asString());
        return response;
    }

    public Response complaintTypeCategoriesResource(String json) {
        new APILogger().log("Get Complaint Type Categories For PGR is started-- " + json);
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.getComplaintTypeCategoriesUrl);

        new APILogger().log("Complaint Type Categories For PGR is Response is generated-- " + response.asString());
        return response;
    }
}
