package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.hrStatuses.SearchHRStatusesResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISHRStatuses extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchHRStatusesTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchHRStatuses(); // Search HR Status
        pilotLogoutService(); // Logout
    }

    private void searchHRStatuses() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EISMasterResource().
                searchHRStatusesResource(RequestHelper.getJsonString(searchEmployeeMasterRequest));
        SearchHRStatusesResponse searchHRStatusesResponse = (SearchHRStatusesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchHRStatusesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchHRStatusesResponse.getHRStatus().length > 0);
        new APILogger().log("Search HR Statuses Test is Completed--");
    }
}
