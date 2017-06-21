package tests.eGovEIS.employeeMaster;

import builders.eGovEIS.emp.AssignmentsBuilder;
import builders.eGovEIS.emp.CreateEmployeeRequestBuilder;
import builders.eGovEIS.emp.EmployeeBuilder;
import builders.eGovEIS.emp.UserBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.emp.*;
import entities.responses.eGovEIS.createEmp.CreateEmployeeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EmployeeMasterTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void EmployeeTest() throws IOException {

        LoginAndLogoutHelper.loginFromPilotService(ADMIN);   //Login

        createEmployeeTestMethod(scenarioContext.getSessionId());  //Create

        LoginAndLogoutHelper.logoutFromPilotService();  //Logout
    }

    public void createEmployeeTestMethod(String sessionId) throws IOException {
        String date = getRandomDate();
        Assignments assignments1 = new AssignmentsBuilder().withFromDate(date).withToDate(date).build();
        Assignments[] assignments = {assignments1};
        User user1 = new UserBuilder().withUserName("Test_"+get3DigitRandomInt()).build();
        Employee employee = new EmployeeBuilder().withCode("EMP_"+get3DigitRandomInt()).withAssignments(assignments).withUser(user1).build();
        CreateEmployeeRequest employeeRequest = new CreateEmployeeRequestBuilder().withEmployee(employee).build();

        Response response = new EgovEISResource().createEmployee(RequestHelper.getJsonString(employeeRequest),sessionId);
        CreateEmployeeResponse employeeResponse = (CreateEmployeeResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateEmployeeResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
    }

}