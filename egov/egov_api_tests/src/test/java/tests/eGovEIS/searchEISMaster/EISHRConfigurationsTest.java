package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.hrConfigurations.SearchHRConfigurationsResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISHRConfigurationsTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchHRConfigurationsTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchHRConfigurations(sessionId); // Search HR Configurations
    }

    private void searchHRConfigurations(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EISMasterResource().
                searchHRConfigurationsResource(RequestHelper.getJsonString(searchEmployeeMasterRequest), sessionId);
        SearchHRConfigurationsResponse searchHRConfigurationsResponse = (SearchHRConfigurationsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchHRConfigurationsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchHRConfigurationsResponse.getHRConfiguration().getWeekly_holidays()[0].contains("5-day week"));
        new APILogger().log("Search HR Configurations Test is Completed--");
        pilotLogoutService(sessionId); // Logout
    }
}
