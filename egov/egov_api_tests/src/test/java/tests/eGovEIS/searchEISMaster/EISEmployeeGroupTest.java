package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.employeeGroup.SearchEmployeeGroupResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISEmployeeGroupTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY , Categories.PILOT})
    public void searchEmployeeGroupTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchEmployeeGroup(); // Search Employee Group
        pilotLogoutService(); // Logout
    }

    private void searchEmployeeGroup() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EISMasterResource().
                searchEmployeeGroupResource(RequestHelper.getJsonString(searchEmployeeMasterRequest));
        SearchEmployeeGroupResponse searchEmployeeGroupResponse = (SearchEmployeeGroupResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchEmployeeGroupResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchEmployeeGroupResponse.getGroup().length > 0);
        new APILogger().log("Search createEmployee Group Test is Completed--");
    }
}
