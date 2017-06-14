package tests.propertyTax.masters;

import builders.propertyTax.masters.CreateUsageMasterRequestBuilder;
import builders.propertyTax.masters.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.CreateUsageMasterRequest;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.CreateUsageMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.UsageMasterResource;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class UsageMasterVerificationTest {

    @Test
    public void usageMasterTest() throws IOException {

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        createUsageMaster(loginResponse);
    }

    private void createUsageMaster(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create Usage Master Test Started");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateUsageMasterRequest request = new CreateUsageMasterRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new UsageMasterResource().createUsageMaster(json);

        Assert.assertEquals(response.getStatusCode(),200);

        CreateUsageMasterResponse response1 = (CreateUsageMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateUsageMasterResponse.class);

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        Assert.assertEquals(response1.getUsageMasters()[0].getDescription(),request.getUsageMasters()[0].getDescription());

        new APILogger().log("Create Usage Master Test Completed");
    }
}
