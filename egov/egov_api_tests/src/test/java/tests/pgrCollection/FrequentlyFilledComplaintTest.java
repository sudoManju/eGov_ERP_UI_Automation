package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.pgrCollections.FrequentlyFilledComplaintResponse;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRComplaintResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrequentlyFilledComplaintTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY})
    public void getFrequentlyFilledComplaints() throws IOException {

        Response response = new PGRComplaintResource().getFrequentlyFilledComplaints(3);

        List<FrequentlyFilledComplaintResponse> fetchComplaints = getResponseObjectArray(response);

        Assert.assertEquals(fetchComplaints.size(), 3);
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Get Frequently filled Complaints test is completed -- ");

    }

    private List<FrequentlyFilledComplaintResponse> getResponseObjectArray(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response.asString(), new TypeReference<ArrayList<FrequentlyFilledComplaintResponse>>() {
        });
    }

}
