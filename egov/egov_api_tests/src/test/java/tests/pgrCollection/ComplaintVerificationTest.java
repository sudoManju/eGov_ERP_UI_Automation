package tests.pgrCollection;

import builders.pgrCollection.ComplaintRequestBuilder;
import builders.pgrCollection.ServiceRequestBuilder;
import builders.pgrCollection.ValuesBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.ServiceRequest;
import entities.requests.pgrCollections.Values;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.createComplaint.ComplaintResponse;
import entities.responses.pgrCollections.getComplaint.GetPGRComplaintResponse;
import entities.requests.pgrCollections.ComplaintRequest;
import entities.responses.pgrCollections.updateAndCloseComplaint.UpdateAndCloseComplaintInPGRResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRComplaintResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class ComplaintVerificationTest extends BaseAPITest {

    @Test(groups = { Categories.PGR , Categories.SANITY })
    public void createAndGetComplaintInPGR() throws IOException {

        //Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.serverUrl,"narasappa");

        // Create A Complaint
        ComplaintResponse complaintResponse =  createComplaintInPGR();
        new APILogger().log("Created a PGR Complaint Request -- ");

        // Get Complaint
        GetPGRComplaintResponse getPgrComplaintResponse =
                getComplaintInPGR(complaintResponse.getService_requests()[0].getService_request_id());
        new APILogger().log("Got a PGR Complaint Request-- ");

        // Update Complaint
        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse =
                  updateComplaintInPGR(complaintResponse.getService_requests()[0].getService_request_id());
        new APILogger().log("Updated a PGR complaint request");

        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse1 =
                updateComplaintInPGR(complaintResponse.getService_requests()[0].getService_request_id());
        new APILogger().log("Close a PGR complaint request");


        // Logout Test
        logoutTestMethod(loginResponse,Properties.serverUrl);
    }

    public ComplaintResponse createComplaintInPGR() throws IOException{
        ComplaintRequest request = new ComplaintRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);
        Response response = new PGRComplaintResource().createComplaint(jsonString);

        ComplaintResponse complaintResponse = (ComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(complaintResponse.getService_requests()[0].getDescription(),
                request.getServiceRequest().getDescription());

        return complaintResponse;
    }

    public GetPGRComplaintResponse getComplaintInPGR(String id) throws IOException{
        Response response = new PGRComplaintResource().
                getPGRComplaint(id);
        GetPGRComplaintResponse getPgrComplaintResponse = (GetPGRComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), GetPGRComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(getPgrComplaintResponse.getResponse_info().getStatus(), "successful");
        return getPgrComplaintResponse;
    }


    public UpdateAndCloseComplaintInPGRResponse updateComplaintInPGR(String id) throws IOException{
        ServiceRequest serviceRequest = new ServiceRequestBuilder("update").withService_request_id(id).build();

        ComplaintRequest request = new ComplaintRequestBuilder("update").withServiceRequest(serviceRequest).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRComplaintResource().updateAndClosePGRComplaint(jsonString);

        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse = (UpdateAndCloseComplaintInPGRResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UpdateAndCloseComplaintInPGRResponse.class);

        Assert.assertEquals(updateAndCloseComplaintInPGRResponse.getService_requests()[0].getValues().getStatus(), request.getServiceRequest().getValues().getStatus());

        return updateAndCloseComplaintInPGRResponse;
    }


    public UpdateAndCloseComplaintInPGRResponse closeComplaintInPGR(String id) throws  IOException{
        Values values = new ValuesBuilder("close").withStatus("COMPLETED").build();

        ServiceRequest serviceRequest = new ServiceRequestBuilder("update").withService_request_id(id).withValues(values).build();

        ComplaintRequest request = new ComplaintRequestBuilder("update").withServiceRequest(serviceRequest).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRComplaintResource().updateAndClosePGRComplaint(jsonString);

        UpdateAndCloseComplaintInPGRResponse updateAndCloseComplaintInPGRResponse = (UpdateAndCloseComplaintInPGRResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UpdateAndCloseComplaintInPGRResponse.class);

        Assert.assertEquals(updateAndCloseComplaintInPGRResponse.getService_requests()[0].getValues().getStatus(), request.getServiceRequest().getValues().getStatus());

        return updateAndCloseComplaintInPGRResponse;
    }
}
