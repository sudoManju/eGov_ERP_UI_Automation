package tests.pgrCollection;

import builders.ComplaintRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.login.LoginResponse;
import entities.pgrCollection.createComplaint.ComplaintResponse;
import entities.pgrCollection.getComplaint.GetPGRComplaintResponse;
import entities.pgrCollection.createComplaint.ComplaintRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRComplaintResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class CreateAndGetComplaintTest extends BaseAPITest {

    @Test(groups = Categories.PGR)
    public void createAndGetComplaintInPGR() throws IOException {

        //Login Test
        LoginResponse loginResponse = loginTestMethod();

        // Create A Complaint
        ComplaintRequest request = new ComplaintRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);
        Response response = new PGRComplaintResource().createComplaint(jsonString);

        ComplaintResponse complaintResponse = (ComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(complaintResponse.getService_requests()[0].getDescription(),
                request.getServiceRequest().getDescription());

        new APILogger().log("Created a PGR Complaint Request -- ");

        // Get Complaint
        Response response1 = new PGRComplaintResource().
                getPGRComplaint(complaintResponse.getService_requests()[0].getService_request_id());
        GetPGRComplaintResponse getPgrComplaintResponse = (GetPGRComplaintResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), GetPGRComplaintResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);
        Assert.assertEquals(getPgrComplaintResponse.getResponse_info().getStatus(), "successful");

        new APILogger().log("Got a PGR Complaint Request-- ");

        // Logout Test
        logoutTestMethod(loginResponse);
    }
}
