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
import resources.EGovEISResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EmployeeLeaveTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void employeeLeaveTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchEmployeeLeaveTypes(); // Search Employee Leave Types
        searchEmployeeLeaveApplications(); // Search Employee Leave Applications
        pilotLogoutService(); // Logout
    }

    private void searchEmployeeLeaveTypes() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeLeaveRequest searchEmployeeLeaveRequest = new SearchEmployeeLeaveRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EGovEISResource()
                .searchEmployeeLeaveTypesResource(RequestHelper.getJsonString(searchEmployeeLeaveRequest));
        SearchEmployeeLeaveResponse employeeLeaveResponse = (SearchEmployeeLeaveResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchEmployeeLeaveResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(employeeLeaveResponse.getLeaveType().length > 0);
        new APILogger().log("Search Employee Leave Types Test is Completed --");
    }

    private void searchEmployeeLeaveApplications() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeLeaveRequest searchEmployeeLeaveRequest = new SearchEmployeeLeaveRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EGovEISResource()
                .searchEmployeeLeaveApplicationsResource(RequestHelper.getJsonString(searchEmployeeLeaveRequest));
        SearchLeaveApplicationsResponse searchLeaveApplicationsResponse = (SearchLeaveApplicationsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchLeaveApplicationsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchLeaveApplicationsResponse.getLeaveApplication().length > 0);
        new APILogger().log("Search Employee Leave Applications Test is Completed --");
    }
}
