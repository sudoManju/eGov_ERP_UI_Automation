package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.religion.ReligionResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class ReligionTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void religionTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        // Language Search Test
        religionTestMethod(loginResponse);
    }

    private void religionTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchReligionTest(jsonString);

        ReligionResponse religionResponse = (ReligionResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ReligionResponse.class);

        Assert.assertEquals(religionResponse.getReligion().length, 3);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Religion Test is Completed --");
    }

}
