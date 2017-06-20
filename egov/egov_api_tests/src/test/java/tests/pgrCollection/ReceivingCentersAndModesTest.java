package tests.pgrCollection;

import builders.pgrCollection.receivingCenters.ReceivingCentersRequestBuilder;
import builders.pgrCollection.receivingCenters.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.receivingCenters.ReceivingCentersRequest;
import entities.requests.pgrCollections.receivingCenters.RequestInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class ReceivingCentersAndModesTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void receivingCentersTest() throws IOException {
        LoginAndLogoutHelper.login1(NARASAPPA); //Login Test
        getAllReceivingCentersTest(); // Get All Receiving Centers
        getReceivingCenterByIdTest(); // Get Receiving Center By ID
        getAllReceivingModes(); // Get All Receiving Modes
    }

    private void getAllReceivingModes() {
        new APILogger().log("Receiving Modes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        ReceivingCentersRequest request = new ReceivingCentersRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().getReceivingModesResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Receiving Modes Test is Completed ---");
    }

    private void getAllReceivingCentersTest() {
        new APILogger().log("All Receiving Centers Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        ReceivingCentersRequest request = new ReceivingCentersRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().getReceivingCenterResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("All Receiving Centers test is Completed ---");
    }

    private void getReceivingCenterByIdTest() {
        new APILogger().log("Receiving Centers by Id Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        ReceivingCentersRequest request = new ReceivingCentersRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().getReceivingCenterByIdResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Receiving Centers by Id Test is Completed ---");
    }
}
