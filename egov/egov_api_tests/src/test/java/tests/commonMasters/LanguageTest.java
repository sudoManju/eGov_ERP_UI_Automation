package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.language.LanguageResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class LanguageTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void languageTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchLanguage(sessionId);  // Search Language
    }

    private void searchLanguage(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new CommonMasterResource()
                .searchLanguageResource(RequestHelper.getJsonString(commonMasterRequest), sessionId);
        LanguageResponse languageResponse = (LanguageResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LanguageResponse.class);

        Assert.assertTrue(languageResponse.getLanguage().length > 0);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Language Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
