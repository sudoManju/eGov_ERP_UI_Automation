package tests.eGovEIS.employeeMaster.leaveType;

import builders.eGovEIS.employeeMaster.RequestInfoBuilder;
import builders.eGovEIS.employeeMaster.leaveType.create.LeaveTypeCreateRequestBuilder;
import builders.eGovEIS.employeeMaster.leaveType.search.LeaveTypeSearchRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.employeeMaster.RequestInfo;
import entities.requests.eGovEIS.employeeMaster.leaveType.create.LeaveTypeCreateRequest;
import entities.requests.eGovEIS.employeeMaster.leaveType.search.LeaveTypeSearchRequest;
import entities.responses.eGovEIS.employeeMaster.create.LeaveTypeCreateResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.employeeMaster.LeaveTypeResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class LeaveTypeCreateSearchAndUpdate extends BaseAPITest {

    @Test
    public void leaveTypeCreateSearchAndUpdateTest() throws IOException {

        //Login Test
//        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Creating a Leave Type
        leaveTypeCreateTestMethod();
    }

    private void leaveTypeCreateTestMethod() throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().build();

        LeaveTypeCreateRequest leaveTypeCreateRequest = new LeaveTypeCreateRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new LeaveTypeResource().create(RequestHelper.getJsonString(leaveTypeCreateRequest));

        LeaveTypeCreateResponse leaveTypeCreateResponse = (LeaveTypeCreateResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeCreateResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Leave Type Create Test is Completed --");

        // Searching a leave
        leaveTypeSearchTestMethod(leaveTypeCreateResponse.getLeaveType()[0].getName());
    }

    private void leaveTypeSearchTestMethod(String leaveName) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();

        LeaveTypeSearchRequest leaveTypeSearchRequest = new LeaveTypeSearchRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new LeaveTypeResource().search(RequestHelper.getJsonString(leaveTypeSearchRequest));

        LeaveTypeCreateResponse leaveTypeCreateResponse = (LeaveTypeCreateResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeCreateResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        int flag = 0;
        for (int i = 0; i < leaveTypeCreateResponse.getLeaveType().length; i++) {
            if (leaveTypeCreateResponse.getLeaveType()[i].getName().contains(leaveName)) {
                flag++;
            }
        }
        if (flag > 0) System.out.println("Created Leave is Found");
        else throw new RuntimeException("No Leave is found with -- " + leaveName);
        new APILogger().log("Leave Type Search Test is Completed --");

        // Updating a leave
        leaveTypeUpdateTestMethod(leaveName);
    }

    private void leaveTypeUpdateTestMethod(String leaveName) {
        
    }

}
