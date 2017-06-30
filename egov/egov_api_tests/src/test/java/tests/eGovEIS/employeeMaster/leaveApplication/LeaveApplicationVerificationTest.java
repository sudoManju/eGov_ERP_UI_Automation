package tests.eGovEIS.employeeMaster.leaveApplication;

import builders.eGovEIS.employeeMaster.leaveApplication.CreateLeaveApplicationRequestBuilder;
import builders.eGovEIS.employeeMaster.leaveApplication.SearchLeaveApplicationRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.employeeMaster.leaveApplication.CreateLeaveApplicationRequest;
import entities.requests.eGovEIS.employeeMaster.leaveApplication.SearchLeaveApplicationRequest;
import entities.responses.eGovEIS.employeeMaster.LeaveApplication.CreateLeaveApplicationResponse;
import entities.responses.eGovEIS.searchEmployeeLeave.SearchLeaveApplicationsResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.employeeMaster.leaveApplication.LeaveApplicationResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data.UserData.ADMIN;

public class LeaveApplicationVerificationTest extends BaseAPITest {

    @Test
    public void leaveApplicationTest() throws IOException, InterruptedException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        String applicationNum = createLeaveApplication(); // Create Leave Application
        searchLeaveApplication(applicationNum); // Search Leave Application
        pilotLogoutService(); // Logout
    }

    private String createLeaveApplication() throws IOException {
        CreateLeaveApplicationRequest request = new CreateLeaveApplicationRequestBuilder().build();

        Response response = new LeaveApplicationResource()
                .createLeaveApplicationResource(RequestHelper.getJsonString(request));
        CreateLeaveApplicationResponse response1 = (CreateLeaveApplicationResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateLeaveApplicationResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(request.getLeaveApplication()[0].getReason(), response1.getLeaveApplication()[0].getReason());
        new APILogger().log("Create Leave Application Test is Completed --");
        return response1.getLeaveApplication()[0].getApplicationNumber();
    }

    private void searchLeaveApplication(String applicationNumber) throws IOException {

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SearchLeaveApplicationRequest request = new SearchLeaveApplicationRequestBuilder().build();
        Response response = new LeaveApplicationResource()
                .searchLeaveApplicationResource(RequestHelper.getJsonString(request), applicationNumber);
        SearchLeaveApplicationsResponse searchLeaveApplicationsResponse = (SearchLeaveApplicationsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchLeaveApplicationsResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(searchLeaveApplicationsResponse.getLeaveApplication()[0].getApplicationNumber(), applicationNumber);
        new APILogger().log("Search Leave Application Test is Completed --");
    }
}



