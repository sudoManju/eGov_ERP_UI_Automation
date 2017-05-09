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
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.CreateComplaint.CreateComplaintResponse;
import entities.responses.pgrCollections.SearchComplaint.SearchComplaintResponse;
import entities.responses.pgrCollections.UpdateComplaint.UpdateComplaintResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class ComplaintVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void createAndGetComplaintInPGR() throws IOException {

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create A Complaint
        CreateComplaintResponse create = createComplaintInPGR(loginResponse);

        // Update Complaint
        updateComplaintInPGR(create.getServiceRequests()[0].getServiceRequestId(),loginResponse);

        // Get Complaint
        getComplaintInPGR(create.getServiceRequests()[0].getServiceRequestId(),loginResponse);

//        //Close Complaint
//        closeComplaintInPGR(create.getServiceRequests()[0].getServiceRequestId(),loginResponse);

        // Logout Test
        LoginAndLogoutHelper.logout(loginResponse);
    }

    private CreateComplaintResponse createComplaintInPGR(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuth_token(loginResponse.getAccess_token()).build();

        ComplaintRequest request = new ComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRResource().createComplaint(jsonString);

        Assert.assertEquals(response.getStatusCode(), 201);

        CreateComplaintResponse response1 = (CreateComplaintResponse)
                                           ResponseHelper.getResponseAsObject(response.asString(),CreateComplaintResponse.class);

        Assert.assertEquals(request.getServiceRequest().getDescription(),response1.getServiceRequests()[0].getDescription());

        new APILogger().log("Creating complaint test for PGR is completed  -- ");

        return response1;
    }

    private void getComplaintInPGR(String id,LoginResponse loginResponse) throws IOException {

        entities.requests.pgrCollections.searchComplaint.RequestInfo requestInfo = new
                               builders.pgrCollection.searchComplaint.RequestInfoBuilder()
                              .withAuthToken(loginResponse.getAccess_token()).build();

        SearchComplaintRequest request = new SearchComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getPGRComplaint(id,jsonString);

        Assert.assertEquals(response.getStatusCode(), 200);

        SearchComplaintResponse response1 = (SearchComplaintResponse)
                               ResponseHelper.getResponseAsObject(response.asString(),SearchComplaintResponse.class);

        Assert.assertEquals(id,response1.getServiceRequests()[0].getServiceRequestId());

        Assert.assertEquals(response1.getServiceRequests()[0].getValues().getComplaintStatus(),"PROCESSING");

        new APILogger().log("Getting a PGR complaint test is Completed -- ");
    }


    private void updateComplaintInPGR(String id,LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder("").withAuth_token(loginResponse.getAccess_token()).build();

        ServiceRequest serviceRequest = new ServiceRequestBuilder("").withServiceRequestId(id).build();

        ComplaintRequest request = new ComplaintRequestBuilder("").withServiceRequest(serviceRequest)
                                       .withRequestInfo(requestInfo).build();

        String jsonString  = RequestHelper.getJsonString(request);

        Response response = new PGRResource().updateAndClosePGRComplaint(jsonString);

        Assert.assertEquals(response.getStatusCode(),200);

        UpdateComplaintResponse response1 = (UpdateComplaintResponse)
                                 ResponseHelper.getResponseAsObject(response.asString(),UpdateComplaintResponse.class);

        Assert.assertEquals(request.getServiceRequest().getDescription(),response1.getServiceRequests()[0].getDescription());

        new APILogger().log("Update complaint for PGR test is Completed  -- ");

    }


    private void closeComplaintInPGR(String id,LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder("").withAuth_token(loginResponse.getAccess_token()).build();

        ServiceRequest serviceRequest = new ServiceRequestBuilder("").withServiceRequestId(id).build();
    }
}
