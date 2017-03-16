package tests.pgrCollection;

import builders.ComplaintRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.pgrCollection.getComplaint.GetPGRComplaintResponse;
import entities.pgrCollection.createComplaint.ComplaintRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRComplaintResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class CreateAndGetComplaintTest extends BaseAPITest {

    @Test(groups = Categories.PGR)
    public void createAndGetComplaintInPGR() throws IOException {

        // Get Complaint
        Response response = new PGRComplaintResource().getPGRComplaint("00166-2017-GV");
        GetPGRComplaintResponse getPgrComplaintResponse = (GetPGRComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), GetPGRComplaintResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(getPgrComplaintResponse.getResponse_info().getStatus(), "successful");

    }

    @Test
    public void createComplaintInPGR() throws IOException {
        ComplaintRequest request = new ComplaintRequestBuilder().build();

        String requestDesc = request.getServiceRequest().getDescription();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new PGRComplaintResource().createComplaint(jsonString);

        Assert.assertEquals(response.getStatusCode(), 201);

    }
}
