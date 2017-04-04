package tests.pgrCollection;

import builders.pgrCollection.ComplaintRequestBuilder;
import builders.pgrCollection.ServiceRequestBuilder;
import builders.pgrCollection.ValuesBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.ComplaintRequest;
import entities.requests.pgrCollections.ServiceRequest;
import entities.requests.pgrCollections.Values;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.createComplaint.ComplaintResponse;
import entities.responses.pgrCollections.getComplaint.GetPGRComplaintResponse;
import entities.responses.pgrCollections.updateAndCloseComplaint.UpdateAndCloseComplaintInPGRResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class ComplaintVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY , Categories.QA})
    public void createAndGetComplaintInPGR() throws IOException {

        //Login Test
        LoginResponse loginResponse = loginTestMethod("narasappa");

        // Create A Complaint
        ComplaintResponse complaintResponse = createComplaintInPGR();

        // Get Complaint
        GetPGRComplaintResponse getPgrComplaintResponse =
                getComplaintInPGR(complaintResponse.getService_requests()[0].getService_request_id());

        // Update Complaint
        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse =
                updateComplaintInPGR(complaintResponse.getService_requests()[0].getService_request_id());

        //Close Complaint
        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse1 =
                closeComplaintInPGR(complaintResponse.getService_requests()[0].getService_request_id());

        // Logout Test
        logoutTestMethod(loginResponse);
    }

    private ComplaintResponse createComplaintInPGR() throws IOException {
        ComplaintRequest request = new ComplaintRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);
        Response response = new PGRResource().createComplaint(jsonString);

        ComplaintResponse complaintResponse = (ComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(complaintResponse.getService_requests()[0].getDescription(),
                request.getServiceRequest().getDescription());

        new APILogger().log("Creating complaint test for PGR is completed  -- ");

        return complaintResponse;
    }

    private GetPGRComplaintResponse getComplaintInPGR(String id) throws IOException {
        Response response = new PGRResource().getPGRComplaint(id);

        GetPGRComplaintResponse getPgrComplaintResponse = (GetPGRComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), GetPGRComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(getPgrComplaintResponse.getResponse_info().getStatus(), "successful");

        new APILogger().log("Getting a PGR complaint test is Completed -- ");
        return getPgrComplaintResponse;
    }


    private UpdateAndCloseComplaintInPGRResponse updateComplaintInPGR(String id) throws IOException {
        ServiceRequest serviceRequest = new ServiceRequestBuilder("update").withService_request_id(id).build();

        ComplaintRequest request = new ComplaintRequestBuilder("update").withServiceRequest(serviceRequest).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRResource().updateAndClosePGRComplaint(jsonString);

        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse = (UpdateAndCloseComplaintInPGRResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UpdateAndCloseComplaintInPGRResponse.class);

        Assert.assertEquals(updateAndCloseComplaintInPGRResponse.getService_requests()[0].getValues().getStatus(), request.getServiceRequest().getValues().getStatus());
        new APILogger().log("Update complaint for PGR test is Completed  -- ");

        return updateAndCloseComplaintInPGRResponse;
    }


    private UpdateAndCloseComplaintInPGRResponse closeComplaintInPGR(String id) throws IOException {
        Values values = new ValuesBuilder("close").withStatus("COMPLETED").build();

        ServiceRequest serviceRequest = new ServiceRequestBuilder("update").withService_request_id(id).withValues(values).build();

        ComplaintRequest request = new ComplaintRequestBuilder("update").withServiceRequest(serviceRequest).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRResource().updateAndClosePGRComplaint(jsonString);

        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse = (UpdateAndCloseComplaintInPGRResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UpdateAndCloseComplaintInPGRResponse.class);

        Assert.assertEquals(updateAndCloseComplaintInPGRResponse.getService_requests()[0].getValues().getStatus(), request.getServiceRequest().getValues().getStatus());
        new APILogger().log("Close complaint for PGR test is Completed  -- ");

        return updateAndCloseComplaintInPGRResponse;
    }
}
