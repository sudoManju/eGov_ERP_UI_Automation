package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.community.CommunityResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.usernames.narasappa;

public class CommunityTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void communityTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(narasappa);

        // Search Department Test
        communityTestMethod(loginResponse);
    }

    private void communityTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchCommunityTest(jsonString);
        CommunityResponse communityResponse = (CommunityResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CommunityResponse.class);

        Assert.assertEquals(communityResponse.getCommunity().length, 3);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Community Test is Completed --");
    }
}
