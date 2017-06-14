package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.religion.ReligionResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class ReligionTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void religionTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchReligion(sessionId);  // Search Religion
    }

    private void searchReligion(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new CommonMasterResource()
                .searchReligionResource(RequestHelper.getJsonString(commonMasterRequest), sessionId);
        ReligionResponse religionResponse = (ReligionResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ReligionResponse.class);

        Assert.assertTrue(religionResponse.getReligion().length > 0);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Religion Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
