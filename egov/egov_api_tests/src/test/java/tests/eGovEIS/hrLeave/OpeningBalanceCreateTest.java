package tests.eGovEIS.hrLeave;

import builders.eGovEIS.hrLeave.LeaveOpeningBalanceBuilder;
import builders.eGovEIS.hrLeave.LeaveTypeBuilder;
import builders.eGovEIS.hrLeave.OpeningBalanceCreateRequestBuilder;
import builders.eGovEIS.hrLeave.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.hrLeave.openingBalance.LeaveOpeningBalance;
import entities.requests.eGovEIS.hrLeave.openingBalance.LeaveType;
import entities.requests.eGovEIS.hrLeave.openingBalance.OpeningBalanceCreateRequest;
import entities.requests.eGovEIS.hrLeave.openingBalance.RequestInfo;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

public class OpeningBalanceCreateTest extends BaseAPITest{

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.DEV})
    public void openingBalanceCreateTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create attendances Test
        openingBalanceCreateTestMethod(loginResponse);
    }

    private void openingBalanceCreateTestMethod(LoginResponse loginResponse) {

        LeaveType leaveType = new LeaveTypeBuilder().build();
        LeaveOpeningBalance leaveOpeningBalance = new LeaveOpeningBalanceBuilder()
                .withLeaveType(leaveType)
                .withNoOfDays(Integer.parseInt(get3DigitRandomInt()))
                .build();

        int noOfDays = leaveOpeningBalance.getNoOfDays();

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withAuthToken(loginResponse.getAccess_token())
                .build();

        OpeningBalanceCreateRequest openingBalanceCreateRequest = new OpeningBalanceCreateRequestBuilder(leaveOpeningBalance)
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EgovEISResource().hrLeaveCreateOpeningBalance(RequestHelper.getJsonString(openingBalanceCreateRequest));
        Assert.assertEquals(response.getStatusCode() , 200 );
    }
}
