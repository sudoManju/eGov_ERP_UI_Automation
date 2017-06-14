package tests.eGovEIS.employeeLeave;

import builders.eGovEIS.employeeLeave.RequestInfoBuilder;
import builders.eGovEIS.employeeLeave.SearchEmployeeLeaveRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.employeeLeave.RequestInfo;
import entities.requests.eGovEIS.employeeLeave.SearchEmployeeLeaveRequest;
import entities.responses.eGovEIS.searchEmployeeLeave.SearchEmployeeLeaveResponse;
import entities.responses.eGovEIS.searchEmployeeLeave.SearchLeaveApplicationsResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EmployeeLeaveTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void employeeLeaveTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchEmployeeLeaveTypes(sessionId);
        searchEmployeeLeaveApplications(sessionId);
        pilotLogoutService(sessionId); // Logout
    }

    private void searchEmployeeLeaveTypes(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeLeaveRequest searchEmployeeLeaveRequest = new SearchEmployeeLeaveRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EgovEISResource()
                .searchEmployeeLeaveTypesResource(RequestHelper.getJsonString(searchEmployeeLeaveRequest), sessionId);
        SearchEmployeeLeaveResponse employeeLeaveResponse = (SearchEmployeeLeaveResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchEmployeeLeaveResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(employeeLeaveResponse.getLeaveType().length > 0);
        new APILogger().log("Search Employee Leave Types Test is Completed --");
    }

    private void searchEmployeeLeaveApplications(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeLeaveRequest searchEmployeeLeaveRequest = new SearchEmployeeLeaveRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EgovEISResource()
                .searchEmployeeLeaveApplicationsResource(RequestHelper.getJsonString(searchEmployeeLeaveRequest), sessionId);
        SearchLeaveApplicationsResponse searchLeaveApplicationsResponse = (SearchLeaveApplicationsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchLeaveApplicationsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchLeaveApplicationsResponse.getLeaveApplication().length > 0);
        new APILogger().log("Search Employee Leave Applications Test is Completed --");
    }
}
