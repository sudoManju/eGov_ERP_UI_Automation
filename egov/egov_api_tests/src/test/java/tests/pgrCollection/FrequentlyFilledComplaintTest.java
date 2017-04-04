package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.pgrCollections.FetchComplaintResponse;
import entities.responses.pgrCollections.FrequentlyFilledComplaintResponse;
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

public class FrequentlyFilledComplaintTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.QA})
    public void getFrequentlyFilledComplaints() throws IOException {

        Response response = new PGRResource().getFrequentlyFilledComplaints(3);

        FrequentlyFilledComplaintResponse[] fetchComplaints = (FrequentlyFilledComplaintResponse[]) ResponseHelper
                .getResponseAsObject(response.asString(), FrequentlyFilledComplaintResponse[].class);

        Assert.assertEquals(fetchComplaints.length, 3);
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Get Frequently filled Complaints test is completed -- ");
    }
}
