package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.ReceivingCenterResponse;
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

public class ReceivingCenterTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.PGR})
    public void receivingCenter() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // Receiving Center Test
        receivingCenterTestMethod(loginResponse);
    }

    private void receivingCenterTestMethod(LoginResponse loginResponse) throws IOException {

        Response response = new PGRResource().getReceivingCenter(loginResponse);
        List<ReceivingCenterResponse> receivingCenterResponse = getResponseObjectArray(response);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(receivingCenterResponse.size(), 10);

        new APILogger().log("Receiving Centers for PGR is Completed  -- ");
    }

    private List<ReceivingCenterResponse> getResponseObjectArray(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response.asString(), new TypeReference<ArrayList<ReceivingCenterResponse>>() {
        });
    }
}
