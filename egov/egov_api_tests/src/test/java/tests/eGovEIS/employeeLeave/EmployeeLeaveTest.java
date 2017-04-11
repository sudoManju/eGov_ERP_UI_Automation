package tests.eGovEIS.employeeLeave;

import builders.eGovEIS.employeeLeave.RequestInfoBuilder;
import builders.eGovEIS.employeeLeave.SearchEmployeeLeaveRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.employeeLeave.RequestInfo;
import entities.requests.eGovEIS.employeeLeave.SearchEmployeeLeaveRequest;
import entities.responses.eGovEIS.searchEmployeeLeave.SearchEmployeeLeaveResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;


public class EmployeeLeaveTest extends BaseAPITest {

    @Test
    public void employeeLeaveSearch() throws IOException{

        //Login
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        employeeLeave(loginResponse);
    }

    private void employeeLeave(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        SearchEmployeeLeaveRequest searchEmployeeLeaveRequest = new SearchEmployeeLeaveRequestBuilder().withRequestInfo(requestInfo).build();

        String jsonString = RequestHelper.getJsonString(searchEmployeeLeaveRequest);

        Response response = new EgovEISResource().searchEmployeeLeave(jsonString);

        Assert.assertEquals(response.getStatusCode(),200);

        SearchEmployeeLeaveResponse employeeLeaveResponse = (SearchEmployeeLeaveResponse) ResponseHelper.getResponseAsObject(response.asString(),SearchEmployeeLeaveResponse.class);

        System.out.println(employeeLeaveResponse.getLeaveType().length);
    }
}
