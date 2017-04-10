package tests.eGovEIS;

import builders.eGovEIS.Employee.*;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.Employee.*;
import entities.responses.eGovEIS.Employee.CreateEmployeeResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class EmployeeMasterTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.DEV})
    public void CreateEmployeeTest() throws IOException {

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        //Create Employee Test
        createEmployeeTestMethod(loginResponse);
    }

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.DEV})
    public void SearchEmployeeTest() throws IOException {

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        //Search Employee Test
        searchEmployeeTestMethod(loginResponse);
    }

    // Create Employee Test
    public void createEmployeeTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder().withUserName("TestUser" + get3DigitRandomInt()).build();

        Employee employee = new EmployeeBuilder().withPassportNo("IND12" + get3DigitRandomInt()).withGpfNo("12" + get3DigitRandomInt()).withUser(user).build();

        CreateEmployeeRequest request = new CreateEmployeeRequestBuilder().withRequestInfo(requestInfo).withEmployee(employee).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new EgovEISResource().createEmployee(jsonString);

        Assert.assertEquals(response.getStatusCode(), 200);

        CreateEmployeeResponse createEmployeeResponse = (CreateEmployeeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateEmployeeResponse.class);

        Assert.assertEquals(request.getEmployee().getPassportNo(), createEmployeeResponse.getEmployee().getPassportNo());
        Assert.assertEquals(request.getEmployee().getUser().getUserName(), createEmployeeResponse.getEmployee().getUser().getUserName());
    }

    // Search Employee Test
    public void searchEmployeeTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        SearchEmployeeRequest request = new SearchEmployeeRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new EgovEISResource().searchEmployee(json);

        Assert.assertEquals(response.getStatusCode(),200);
    }
}