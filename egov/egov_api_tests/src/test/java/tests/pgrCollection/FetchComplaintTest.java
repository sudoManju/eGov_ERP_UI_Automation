package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.pgrCollections.FetchComplaintResponse;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchComplaintTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY})
    public void fetchAllComplaint() throws IOException {

        Response response = new PGRResource().getFetchComplaint();

        FetchComplaintResponse[] fetchComplaints = (FetchComplaintResponse[]) ResponseHelper
                .getResponseAsObject(response.asString(), FetchComplaintResponse[].class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(fetchComplaints[0].getMetadata(), true);
        new APILogger().log("Fetch all Complaints test is Completed -- ");
    }
}
