package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.pgrCollections.FetchComplaintResponse;
import entities.responses.pgrCollections.LocationNameResponse;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRComplaintResource;
import tests.BaseAPITest;
import utils.Categories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchComplaintTest extends BaseAPITest {

    @Test(groups = {Categories.PGR , Categories.SANITY})
    public void fetchAllComplaint() throws IOException {

        Response response = new PGRComplaintResource().getFetchComplaint();

        List<FetchComplaintResponse> fetchComplaints = getResponseObjectArray(response);

        Assert.assertEquals(response.getStatusCode() , 200);
        Assert.assertTrue(fetchComplaints.get(0).getMetadata() , true);

    }

    private List<FetchComplaintResponse> getResponseObjectArray(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response.asString(), new TypeReference<ArrayList<FetchComplaintResponse>>() {
        });
    }
}
