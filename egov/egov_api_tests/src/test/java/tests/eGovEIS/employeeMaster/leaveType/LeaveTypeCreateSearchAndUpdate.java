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
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class LeaveTypeCreateSearchAndUpdate extends BaseAPITest {

    @Test(groups = {Categories.PILOT, Categories.HR, Categories.SANITY})
    public void leaveTypeCreateSearchAndUpdateTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        createLeaveType(); // Create Leave Type
        pilotLogoutService(); // Logout
    }

    private void createLeaveType() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LeaveTypeCreateRequest leaveTypeCreateRequest = new LeaveTypeCreateRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new LeaveTypeResource()
                .createLeaveTypeResource(RequestHelper.getJsonString(leaveTypeCreateRequest));
        LeaveTypeResponse leaveTypeCreateResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Leave Type Create Test is Completed --");
        searchLeaveType(leaveTypeCreateResponse.getLeaveType()[0].getName(), leaveTypeCreateResponse); // Search Leave Type
    }

    private void searchLeaveType(String leaveName, LeaveTypeResponse leaveTypeResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LeaveTypeSearchRequest leaveTypeSearchRequest = new LeaveTypeSearchRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new LeaveTypeResource()
                .searchLeaveTypeResource(RequestHelper.getJsonString(leaveTypeSearchRequest));
        LeaveTypeResponse leaveTypeSearchResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        int flag = 0;
        int leaveId = 0;
        for (int i = 0; i < leaveTypeSearchResponse.getLeaveType().length; i++) {
            if (leaveTypeSearchResponse.getLeaveType()[i].getName().contains(leaveName)) {
                leaveId = leaveTypeSearchResponse.getLeaveType()[i].getId();
                flag++;
            }
        }
        if (!(flag > 0)) throw new RuntimeException("No Leave Type is found with -- " + leaveName);
        new APILogger().log("Leave Type Search Test is Completed --");
        updateLeaveType(leaveId, leaveTypeResponse); // Update Leave Type
    }

    private void updateLeaveType(int leaveId, LeaveTypeResponse leaveTypeResponse1) throws IOException {
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
                .updateLeaveTypeResource(RequestHelper.getJsonString(leaveTypeUpdateRequest), leaveId);
        LeaveTypeResponse leaveTypeUpdateResponse = (LeaveTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LeaveTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(leaveTypeUpdateResponse.getLeaveType()[0].getDescription(), "Modified Description of Leave Type");
        new APILogger().log("Update Leave Type Test is Completed --");
        searchLeaveTypeAfterUpdate(leaveId); // Search Leave Type After Update
    }

    private void searchLeaveTypeAfterUpdate(int leaveId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        LeaveTypeSearchRequest leaveTypeSearchRequest = new LeaveTypeSearchRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new LeaveTypeResource().searchLeaveTypeResource(RequestHelper.getJsonString(leaveTypeSearchRequest));
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
        if (!(flag > 0)) throw new RuntimeException("Updated Leave Type is not Found -- " + leaveId);
        new APILogger().log("Search Leave Type After Update Test is Completed --");
    }
}
