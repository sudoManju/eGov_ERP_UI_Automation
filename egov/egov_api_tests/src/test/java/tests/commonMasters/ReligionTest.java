package tests.commonMasters;

import builders.commonMasters.CommonMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.responses.commonMaster.religion.ReligionResponse;
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

public class ReligionTest extends BaseAPITest {

    @Test
    public void religionTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // Language Search Test
        religionTestMethod(loginResponse);
    }

    private void religionTestMethod(LoginResponse loginResponse) throws IOException {
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchReligionTest(jsonString, loginResponse.getAccess_token());

        ReligionResponse religionResponse = (ReligionResponse)
                ResponseHelper.getResponseAsObject(response.asString(), ReligionResponse.class);

        Assert.assertEquals(religionResponse.getReligion().length, 3);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Religion Test is Completed --");
    }

}
