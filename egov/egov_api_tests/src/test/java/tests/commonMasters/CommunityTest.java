package tests.commonMasters;

import builders.commonMasters.CommonMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.responses.commonMaster.community.CommunityResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class CommunityTest extends BaseAPITest {

    @Test
    public void communityTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // Search Department Test
        communityTestMethod(loginResponse);
    }

    private void communityTestMethod(LoginResponse loginResponse) throws IOException {
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchCommunityTest(jsonString, loginResponse.getAccess_token());

        CommunityResponse communityResponse = (CommunityResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CommunityResponse.class);

        Assert.assertEquals(communityResponse.getCommunity().length, 3);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Community Test is Completed --");
    }
}
