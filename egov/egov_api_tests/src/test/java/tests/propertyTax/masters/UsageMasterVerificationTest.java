package tests.propertyTax.masters;

import builders.propertyTax.masters.usage.CreateUsageMasterRequestBuilder;
import builders.propertyTax.masters.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.usage.CreateUsageMasterRequest;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.usage.create.UsageMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.UsageMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class  UsageMasterVerificationTest extends BaseAPITest {


    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void usageMasterTest() throws IOException {

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);     //Login

        SearchHelper helper = new SearchHelper(loginResponse);

        UsageMasterResponse response1 = createUsageMaster(loginResponse);   //Create

        helper.searchForUsageMaster(response1); //Search

        LoginAndLogoutHelper.logout(loginResponse);   //Logout
    }

    private UsageMasterResponse createUsageMaster(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create Usage Master Test Started");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateUsageMasterRequest request = new CreateUsageMasterRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new UsageMasterResource().createUsageMaster(json);

        Assert.assertEquals(response.getStatusCode(),200);

        UsageMasterResponse response1 = (UsageMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),UsageMasterResponse.class);

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        Assert.assertEquals(response1.getUsageMasters()[0].getDescription(),request.getUsageMasters()[0].getDescription());

        new APILogger().log("Create Usage Master Test Completed");

        return response1;
    }
}
