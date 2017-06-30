package tests.eGovEIS.employeeMaster;

import builders.eGovEIS.emp.*;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.emp.*;
import entities.responses.eGovEIS.createEmp.CreateEmployeeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EGovEISResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.CODE;
import static data.SearchParameterData.CODE1;
import static data.UserData.ADMIN;

public class EmployeeMasterTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void EmployeeTest() throws IOException {

        LoginAndLogoutHelper.loginFromPilotService(ADMIN);   //Login

        CreateEmployeeResponse create = createEmployeeTestMethod(scenarioContext.getSessionId());  //Create

        searchEmployeeTestMethod(create);

        LoginAndLogoutHelper.logoutFromPilotService();  //Logout
    }

    public CreateEmployeeResponse createEmployeeTestMethod(String sessionId) throws IOException {
        String date = getRandomDate();
        Assignments assignments1 = new AssignmentsBuilder().withFromDate(date).withToDate(date).build();
        Assignments[] assignments = {assignments1};
        User user1 = new UserBuilder().withUserName("Test_"+ get3DigitRandomInt()).build();
        Employee employee = new EmployeeBuilder().withCode("EMP_"+ get3DigitRandomInt()).withAssignments(assignments).withUser(user1).build();
        CreateEmployeeRequest employeeRequest = new CreateEmployeeRequestBuilder().withEmployee(employee).build();

        Response response = new EGovEISResource().createEmployee(RequestHelper.getJsonString(employeeRequest),sessionId);
        CreateEmployeeResponse employeeResponse = (CreateEmployeeResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateEmployeeResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);

        return employeeResponse;
    }

    public void searchEmployeeTestMethod(CreateEmployeeResponse create){
        SearchEmployeeRequest request = new SearchEmployeeRequestBuilder().build();

        Response response = new EGovEISResource().searchEmployee(RequestHelper.getJsonString(request),CODE1+create.getEmployee().getCode());

        Assert.assertEquals(response.getStatusCode(),200);
    }

}