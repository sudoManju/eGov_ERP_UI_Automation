package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.pgrCollections.FrequentlyFilledComplaintResponse;
import entities.responses.pgrCollections.LocationNameResponse;
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

public class GetLocationNameTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY})
    public void getParticularLocationName() throws IOException {

        Response response = new PGRResource().getParticularLocationName("konda peta");

        LocationNameResponse[] locations = (LocationNameResponse[]) ResponseHelper
                .getResponseAsObject(response.asString(), LocationNameResponse[].class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(locations[0].getName().contains("Konda Peta"));

        new APILogger().log("Getting a location details test with name is Completed-- ");
    }

    @Test(groups = {Categories.PGR, Categories.SANITY})
    public void invalidLocationName() throws IOException {

        Response response = new PGRResource().getParticularLocationName("vinay");

        LocationNameResponse[] locations = (LocationNameResponse[]) ResponseHelper
                .getResponseAsObject(response.asString(), LocationNameResponse[].class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(locations.length, 0);

        new APILogger().log("Getting a invalid location details test with name is completed-- ");
    }

    @Test(groups = {Categories.PGR, Categories.SANITY})
    public void getAllLocationNames() throws IOException {

        Response response = new PGRResource().getAllLocationNames();

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Getting all location details test with name is Completed-- ");

    }
}
