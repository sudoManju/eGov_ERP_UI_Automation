package tests.lams;

import builders.lams.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.lams.RequestInfo;
import entities.responses.lams.LamsServiceSearchResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LamsServiceResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class LamsServiceTest extends BaseAPITest {

    @Test(groups = {Categories.LAMS, Categories.SANITY})
    public void LamsServiceSearchTest() throws IOException{

        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl,"narasappa");

        RequestInfo requestInfo = new RequestInfoBuilder().build();

        String jsonString = RequestHelper.getJsonString(requestInfo);

        Response response = new LamsServiceResource().LamsServiceSearch(jsonString,loginResponse.getAccess_token());

        Assert.assertEquals(response.getStatusCode(),200);

        LamsServiceSearchResponse lamsServiceSearchResponse = (LamsServiceSearchResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LamsServiceSearchResponse.class);

        Assert.assertEquals(lamsServiceSearchResponse.getResposneInfo().getStatus(),"successful");
    }
}
