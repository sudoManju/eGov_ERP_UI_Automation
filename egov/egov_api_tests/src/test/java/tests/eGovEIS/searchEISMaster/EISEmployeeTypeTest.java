package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.employeeType.SearchEmployeeTypeResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISEmployeeTypeTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchEmployeeTypeTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchEmployeeType(); // Search Employee Type
        pilotLogoutService(); // Logout
    }

    private void searchEmployeeType() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EISMasterResource().
                searchEmployeeTypeResource(RequestHelper.getJsonString(searchEmployeeMasterRequest));
        SearchEmployeeTypeResponse searchEmployeeTypeResponse = (SearchEmployeeTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchEmployeeTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchEmployeeTypeResponse.getEmployeeType().length > 0);
        new APILogger().log("Search createEmployee Test is Completed--");
    }
}
