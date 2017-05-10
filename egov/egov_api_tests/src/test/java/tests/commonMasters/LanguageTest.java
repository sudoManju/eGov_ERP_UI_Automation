package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.language.LanguageResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class LanguageTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void languageTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Language Search Test
        languageTestMethod(loginResponse);
    }

    private void languageTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchLanguageTest(jsonString);

        LanguageResponse languageResponse = (LanguageResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LanguageResponse.class);

        Assert.assertEquals(languageResponse.getLanguage().length, 4);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Language Test is Completed --");
    }
}
