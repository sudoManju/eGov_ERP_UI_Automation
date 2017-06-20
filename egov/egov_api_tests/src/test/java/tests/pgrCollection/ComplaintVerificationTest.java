package tests.pgrCollection;

import builders.pgrCollection.createComplaint.ComplaintRequestBuilder;
import builders.pgrCollection.createComplaint.RequestInfoBuilder;
import builders.pgrCollection.createComplaint.ServiceRequestBuilder;
import builders.pgrCollection.searchComplaint.SearchComplaintRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.createComplaint.ComplaintRequest;
import entities.requests.pgrCollections.createComplaint.RequestInfo;
import entities.requests.pgrCollections.createComplaint.ServiceRequest;
import entities.requests.pgrCollections.searchComplaint.SearchComplaintRequest;
import entities.responses.pgrCollections.CreateComplaint.ComplaintResponse;
import entities.responses.pgrCollections.SearchComplaint.SearchComplaintResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data.UserData.NARASAPPA;

public class ComplaintVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void createAndGetComplaintTest() throws IOException {
        LoginAndLogoutHelper.login1(NARASAPPA); //Login Test
        ComplaintResponse create = createComplaint(); // Create Complaint
        getComplaint(create); // Get Complaint
        ComplaintResponse update = updateComplaint(create); // Update Complaint
//        getComplaint(updateLeaveTypeResource,loginResponse);
        LoginAndLogoutHelper.logout1(); // Logout
    }

    private ComplaintResponse createComplaint() throws IOException {
        new APILogger().log("Creating Complaint test for PGR is Started  -- ");
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        ComplaintRequest request = new ComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().createComplaintResource(RequestHelper.getJsonString(request));
        ComplaintResponse response1 = (ComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(request.getServiceRequest().getDescription(), response1.getServiceRequests()[0].getDescription());
        new APILogger().log("Creating Complaint test for PGR is Completed  -- ");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response1;
    }

    private void getComplaint(ComplaintResponse complaint) throws IOException {
        new APILogger().log("Getting a PGR complaint test is Started -- ");

        entities.requests.pgrCollections.searchComplaint.RequestInfo requestInfo = new
                builders.pgrCollection.searchComplaint.RequestInfoBuilder()
                .withAuthToken(scenarioContext.getAuthToken()).build();

        SearchComplaintRequest request = new SearchComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource()
                .getComplaintResource(complaint.getServiceRequests()[0].getServiceRequestId(), RequestHelper.getJsonString(request));
        SearchComplaintResponse response1 = (SearchComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(complaint.getServiceRequests()[0].getServiceRequestId(), response1.getServiceRequests()[0].getServiceRequestId());

        String status = null;

        for (int i = 0; i < complaint.getServiceRequests()[0].getAttribValues().length; i++) {
            if (complaint.getServiceRequests()[0].getAttribValues()[i].getKey().equals("status")) {
                status = complaint.getServiceRequests()[0].getAttribValues()[i].getName();
            }
        }

        String getStatus = null;

        for (int i = 0; i < response1.getServiceRequests()[0].getAttribValues().length; i++) {
            if (response1.getServiceRequests()[0].getAttribValues()[i].getKey().equals("status")) {
                getStatus = response1.getServiceRequests()[0].getAttribValues()[i].getName();
            }
        }

        Assert.assertEquals(getStatus, status);
        new APILogger().log("Getting a PGR complaint test is Completed -- ");
    }


    private ComplaintResponse updateComplaint(ComplaintResponse complaint) throws IOException {
        new APILogger().log("Update Complaint for PGR test is Started  -- ");
        RequestInfo requestInfo = new RequestInfoBuilder("").withAuth_token(scenarioContext.getAuthToken()).build();
        ServiceRequest serviceRequest = new ServiceRequestBuilder("").withServiceRequestId(complaint.getServiceRequests()[0].getServiceRequestId()).build();
        ComplaintRequest request = new ComplaintRequestBuilder("").withServiceRequest(serviceRequest)
                .withRequestInfo(requestInfo).build();

        Response response = new PGRResource().updateAndClosePGRComplaintResource(RequestHelper.getJsonString(request));
        ComplaintResponse response1 = (ComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(complaint.getServiceRequests()[0].getServiceRequestId(), response1.getServiceRequests()[0].getServiceRequestId());
        Assert.assertEquals(request.getServiceRequest().getDescription(), response1.getServiceRequests()[0].getDescription());
        new APILogger().log("Update complaint for PGR test is Completed  -- ");
        return response1;
    }
}
