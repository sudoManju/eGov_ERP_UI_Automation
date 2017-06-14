package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.position.SearchPositionResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISPositionTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY , Categories.PILOT})
    public void searchPositionTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchPosition(sessionId); // Search Positions
    }

    private void searchPosition(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EISMasterResource().
                searchPositionResource(RequestHelper.getJsonString(searchEmployeeMasterRequest), sessionId);
        SearchPositionResponse searchPositionResponse = (SearchPositionResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchPositionResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchPositionResponse.getPosition().length > 0);
        new APILogger().log("Search Position Test is Completed--");
        pilotLogoutService(sessionId); // Logout
    }
}
