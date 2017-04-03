package tests.lams;

import builders.lams.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.lams.RequestInfo;
import entities.responses.lams.LamsServiceSearchResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LAMSServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class LAMSServiceTest extends BaseAPITest {

    @Test(groups = {Categories.LAMS, Categories.SANITY})
    public void LAMSServiceSearchTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // LAMS Service Search Test
        lamsServiceTestMethod(loginResponse);
    }

    private void lamsServiceTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        String jsonString = RequestHelper.getJsonString(requestInfo);

        System.out.println(jsonString);

        Response response = new LAMSServiceResource().lamsServiceSearch(jsonString, loginResponse.getAccess_token());

        Assert.assertEquals(response.getStatusCode(), 200);

        LamsServiceSearchResponse lamsServiceSearchResponse = (LamsServiceSearchResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LamsServiceSearchResponse.class);

        Assert.assertEquals(lamsServiceSearchResponse.getResposneInfo().getStatus(), "successful");

        new APILogger().log("LAMS service search request is Completed -- ");
    }
}
