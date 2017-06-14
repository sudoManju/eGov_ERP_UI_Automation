package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISPositionHierarchyTest extends BaseAPITest {

    @Test(groups = Categories.WIP)
//    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchPositionHierarchyTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchPositionHierarchy(sessionId); // Search Position Hierarchy
    }

    private void searchPositionHierarchy(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EISMasterResource().
                searchPositionHierarchyResource(RequestHelper.getJsonString(searchEmployeeMasterRequest), sessionId);

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Search Position Test is Completed--");
        pilotLogoutService(sessionId); // Logout
    }
}
