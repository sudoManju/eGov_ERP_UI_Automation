package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.PGRComplaintResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRComplaintResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.ResponseHelper;

import java.io.IOException;

public class CreateAndGetComplaintTest extends BaseAPITest {

    @Test(groups = Categories.PGR)
    public void createAndGetComplaintInPGR() throws IOException {

        // Get Complaint
        Response response = new PGRComplaintResource().getPGRComplaint("00166-2017-GV");
        PGRComplaintResponse pgrComplaintResponse = (PGRComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), PGRComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(pgrComplaintResponse.getResponse_info().getStatus(), "successful");
    }
}
