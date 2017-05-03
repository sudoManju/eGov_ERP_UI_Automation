package tests.eGovEIS.leaveManagement;

import builders.eGovEIS.leaveManagement.create.LeaveOpeningBalanceBuilder;
import builders.eGovEIS.leaveManagement.create.LeaveTypeBuilder;
import builders.eGovEIS.leaveManagement.create.OpeningBalanceCreateRequestBuilder;
import builders.eGovEIS.leaveManagement.create.RequestInfoBuilder;
import builders.eGovEIS.leaveManagement.search.SearchOpeningBalanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.leaveManagement.RequestInfo;
import entities.requests.eGovEIS.leaveManagement.create.LeaveOpeningBalance;
import entities.requests.eGovEIS.leaveManagement.create.LeaveType;
import entities.requests.eGovEIS.leaveManagement.create.OpeningBalanceCreateRequest;
import entities.requests.eGovEIS.leaveManagement.search.SearchOpeningBalanceRequest;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.RequestHelper;

import java.io.IOException;

public class OpeningBalanceCreateTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.DEV})
    public void openingBalanceCreateTest() throws IOException {

        // Login Test
//        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create attendances Test
//        openingBalanceCreateTestMethod(loginResponse);
        openingBalanceCreateTestMethod();
    }

    private void openingBalanceCreateTestMethod() {

        LeaveType leaveType = new LeaveTypeBuilder().build();
        LeaveOpeningBalance leaveOpeningBalance = new LeaveOpeningBalanceBuilder()
                .withLeaveType(leaveType)
                .withNoOfDays(Integer.parseInt(get3DigitRandomInt()))
                .build();

        int noOfDays = leaveOpeningBalance.getNoOfDays();

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withAuthToken("2708de2c-8eb1-4871-9237-8f301f808b37")
                .withTs("01-01-2017 01:01:01")
                .build();

        OpeningBalanceCreateRequest openingBalanceCreateRequest = new OpeningBalanceCreateRequestBuilder(leaveOpeningBalance)
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EgovEISResource().hrLeaveCreateOpeningBalance(RequestHelper.getJsonString(openingBalanceCreateRequest));
        Assert.assertEquals(response.getStatusCode(), 200);

        searchOpeningBalance(noOfDays);
    }

    private void searchOpeningBalance(int noOfDays) {

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withAuthToken("2708de2c-8eb1-4871-9237-8f301f808b37")
                .withTs("01-01-2017 01:01:01")
                .build();

        SearchOpeningBalanceRequest searchOpeningBalanceRequest = new SearchOpeningBalanceRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EgovEISResource().hrLeaveSearchOpeningBalance(RequestHelper.getJsonString(searchOpeningBalanceRequest), noOfDays);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
