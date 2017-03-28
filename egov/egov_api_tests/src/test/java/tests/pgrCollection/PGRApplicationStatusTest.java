package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.PGRApplicationStatusResponse;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.Properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PGRApplicationStatusTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.PGR})
    public void pgrApplicationStatus() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // Get PGR Application Status
        pgrApplicationStatusTestMethod(loginResponse);
    }

    private void pgrApplicationStatusTestMethod(LoginResponse loginResponse) throws IOException {
        Response response = new PGRResource().getPGRApplicationStatus(loginResponse);

        List<PGRApplicationStatusResponse> pgrApplicationStatusResponse = getResponseObjectArray(response);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(pgrApplicationStatusResponse.size(), 9);

        new APILogger().log("Get All Application Status for PGR is Completed  -- ");
    }

    private List<PGRApplicationStatusResponse> getResponseObjectArray(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response.asString(), new TypeReference<ArrayList<PGRApplicationStatusResponse>>() {
        });
    }
}
