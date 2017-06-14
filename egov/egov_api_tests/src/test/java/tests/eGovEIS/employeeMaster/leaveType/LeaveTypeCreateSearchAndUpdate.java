package tests.eGovEIS.employeeMaster.leaveType;

import builders.eGovEIS.employeeMaster.RequestInfoBuilder;
import builders.eGovEIS.employeeMaster.leaveType.create.LeaveTypeBuilder;
import builders.eGovEIS.employeeMaster.leaveType.create.LeaveTypeCreateRequestBuilder;
import builders.eGovEIS.employeeMaster.leaveType.search.LeaveTypeSearchRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.employeeMaster.RequestInfo;
import entities.requests.eGovEIS.employeeMaster.leaveType.create.LeaveType;
import entities.requests.eGovEIS.employeeMaster.leaveType.create.LeaveTypeCreateRequest;
import entities.requests.eGovEIS.employeeMaster.leaveType.search.LeaveTypeSearchRequest;
import entities.responses.eGovEIS.employeeMaster.create.LeaveTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.employeeMaster.LeaveTypeResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data.UserData.ADMIN;

public class LeaveTypeCreateSearchAndUpdate extends BaseAPITest {

    @Test
    public void leaveTypeCreateSearchAndUpdateTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        createLeaveType(sessionId); // Create A Leave Type
    }

    private void createLeaveType(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LeaveTypeCreateRequest leaveTypeCreateRequest = new LeaveTypeCreateRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new LeaveTypeResource()
                .createLeaveTypeResource(RequestHelper.getJsonString(leaveTypeCreateRequest), sessionId);
        LeaveTypeResponse leaveTypeCreateResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Leave Type Create Test is Completed --");
        searchLeaveType(leaveTypeCreateResponse.getLeaveType()[0].getName(), leaveTypeCreateResponse, sessionId); // Search Leave Type
    }

    private void searchLeaveType(String leaveName, LeaveTypeResponse leaveTypeResponse, String sessionId) throws IOException {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LeaveTypeSearchRequest leaveTypeSearchRequest = new LeaveTypeSearchRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new LeaveTypeResource()
                .searchLeaveTypeResource(RequestHelper.getJsonString(leaveTypeSearchRequest), sessionId);
        LeaveTypeResponse leaveTypeSearchResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        int flag = 0;
        int leaveId = 0;
        System.out.println("========Length========" + leaveTypeSearchResponse.getLeaveType().length);
        for (int i = 0; i < leaveTypeSearchResponse.getLeaveType().length; i++) {
            if (leaveTypeSearchResponse.getLeaveType()[i].getName().contains(leaveName)) {
                leaveId = leaveTypeSearchResponse.getLeaveType()[i].getId();
                flag++;
            }
        }
        if (flag > 0) System.out.println("Created Leave is Found");
        else throw new RuntimeException("No Leave is found with -- " + leaveName);
        new APILogger().log("Leave Type Search Test is Completed --");

        // Updating a leave
        updateLeaveType(leaveId, leaveTypeResponse, sessionId); // Update Leave Type
    }

    private void updateLeaveType(int leaveId, LeaveTypeResponse leaveTypeResponse1, String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();

        LeaveType leaveType1 = new LeaveTypeBuilder()
                .withName(leaveTypeResponse1.getLeaveType()[0].getName())
                .withDescription("Modified Description of Leave Type")
                .withHalfdayAllowed(leaveTypeResponse1.getLeaveType()[0].getHalfdayAllowed())
                .withPayEligible(leaveTypeResponse1.getLeaveType()[0].getPayEligible())
                .withAccumulative(leaveTypeResponse1.getLeaveType()[0].getAccumulative())
                .withEncashable(leaveTypeResponse1.getLeaveType()[0].getEncashable())
                .withActive(leaveTypeResponse1.getLeaveType()[0].getActive())
                .withCreatedBy(leaveTypeResponse1.getLeaveType()[0].getCreatedBy())
                .withCreatedDate(getCurrentDate())
                .withLastModifiedBy(leaveTypeResponse1.getLeaveType()[0].getLastModifiedBy())
                .withLastModifiedDate(getCurrentDate())
                .withTenantId(leaveTypeResponse1.getLeaveType()[0].getTenantId())
                .build();

        LeaveType[] leaveTypes = new LeaveType[1];
        leaveTypes[0] = leaveType1;

        LeaveTypeCreateRequest leaveTypeUpdateRequest = new LeaveTypeCreateRequestBuilder()
                .withRequestInfo(requestInfo)
                .withLeaveType(leaveTypes)
                .build();

        Response response = new LeaveTypeResource()
                .updateLeaveTypeResource(RequestHelper.getJsonString(leaveTypeUpdateRequest), leaveId, sessionId);
        LeaveTypeResponse leaveTypeUpdateResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(leaveTypeUpdateResponse.getLeaveType()[0].getDescription(), "Modified Description of Leave Type");
        new APILogger().log("Leave Type Update Test is Completed --");

        // Search a leave type after updating it
        searchLeaveTypeAfterUpdate(leaveId, sessionId); // Search Leave Type After Update
    }

    private void searchLeaveTypeAfterUpdate(int leaveId, String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LeaveTypeSearchRequest leaveTypeSearchRequest = new LeaveTypeSearchRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new LeaveTypeResource().searchLeaveTypeResource(RequestHelper.getJsonString(leaveTypeSearchRequest), sessionId);
        LeaveTypeResponse leaveTypeSearchResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        int flag = 0;
        for (int i = 0; i < leaveTypeSearchResponse.getLeaveType().length; i++) {
            if (leaveTypeSearchResponse.getLeaveType()[i].getId() == leaveId) {
                if (leaveTypeSearchResponse.getLeaveType()[i].getDescription().contains("Modified Description of Leave Type")) {
                    flag++;
                }
            }
        }
        if (flag > 0) System.out.println("Updates Leave Type is Found");
        else throw new RuntimeException("Updated Leave Type is not Found -- " + leaveId);
        new APILogger().log("Search Leave Type After Update Test is Completed --");
    }
}
