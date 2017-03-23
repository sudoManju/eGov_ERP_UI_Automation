package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class PGRComplaintResource {

    public Response createComplaint(String json) {

        new APILogger().log("Creating complaint test for PGR is started  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.serverUrl + Properties.complaintUrl);

        return response;
    }

    public Response getPGRComplaint(String serviceRequestId) {

        new APILogger().log("Getting a PGR complaint test is started -- ");

        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("api_id", "org.egov.pgr")
                .header("ver", "1.0")
                .header("ts", "28-03-2016 10:22:33")
                .header("action", "GET")
                .header("did", "4354648646")
                .header("msg_id", "654654")
                .header("requester_id", "61")
                .header("auth_token", "null")
                .when()
                .get(Properties.serverUrl + Properties.getPGRComplaintUrl + serviceRequestId);

        return response;
    }

    public Response getParticularLocationName(String locationName) {

        new APILogger().log("Getting a location details test with name is started-- ");

        Response response = given().request().with()
                .urlEncodingEnabled(true)
                .when()
                .get(Properties.serverUrl + Properties.locationNameUrl + locationName);

        return response;
    }

    public Response getAllLocationNames() {

        new APILogger().log("Getting all location details test with name is started-- ");

        Response response = given().request().with()
                .urlEncodingEnabled(true)
                .when()
                .get(Properties.serverUrl + Properties.locationNameUrl);

        return response;
    }

    public Response getFetchComplaint() {

        new APILogger().log("Fetch all Complaints test is started -- ");

        Response response = given().request().with()
                .urlEncodingEnabled(true)
                .when()
                .get(Properties.serverUrl + Properties.fetchComplaintsUrl);

        return response;
    }

    public Response getFrequentlyFilledComplaints(int count) {

        new APILogger().log("Get Frequently filled Complaints test is started -- ");

        Response response = given().request().with()
                .urlEncodingEnabled(true)
                .when()
                .get(Properties.serverUrl + Properties.frequentlyFilledComplaintsUrl + count + "&tenantId=ap.public");

        return response;
    }

    public Response updateAndClosePGRComplaint(String json) {

        new APILogger().log("Update/Close complaint for PGR is started  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .put(Properties.serverUrl + Properties.complaintUrl);

        return response;
    }
}
