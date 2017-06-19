package tests.lams;

import builders.lams.LamsServiceRequestBuilder;
import builders.lams.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.lams.LamsServiceRequest;
import entities.requests.lams.RequestInfo;
import entities.responses.lams.LamsServiceSearchResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LAMSServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class LAMSServiceTest extends BaseAPITest {

    @Test(groups = {Categories.LAMS, Categories.SANITY , Categories.PILOT})
    public void searchLAMSServiceTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchLAMSService(); // Search LAMS
        pilotLogoutService(); // Logout
    }

    private void searchLAMSService() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LamsServiceRequest request = new LamsServiceRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new LAMSServiceResource().searchLAMSServiceResource(RequestHelper.getJsonString(request));
        LamsServiceSearchResponse lamsServiceSearchResponse = (LamsServiceSearchResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LamsServiceSearchResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(lamsServiceSearchResponse.getResposneInfo().getStatus(), "successful");
        new APILogger().log("LAMS service searchLeaveApplicationResource request is Completed -- ");
    }
}
