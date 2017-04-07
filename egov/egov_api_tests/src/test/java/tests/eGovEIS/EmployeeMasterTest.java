package tests.eGovEIS;

import builders.eGovEIS.CreateEmployee.CreateEmployeeRequestBuilder;
import builders.eGovEIS.CreateEmployee.EmployeeBuilder;
import builders.eGovEIS.CreateEmployee.RequestInfoBuilder;
import builders.eGovEIS.CreateEmployee.UserBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.Employee.CreateEmployeeRequest;
import entities.requests.eGovEIS.Employee.Employee;
import entities.requests.eGovEIS.Employee.RequestInfo;
import entities.requests.eGovEIS.Employee.User;
import entities.responses.login.LoginResponse;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

public class EmployeeMasterTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.WIP})
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

        User user = new UserBuilder().withUserName("TestUser"+get3DigitRandomInt()).build();

        Employee employee = new EmployeeBuilder().withPassportNo("IND12"+get3DigitRandomInt()).withGpfNo("12"+get3DigitRandomInt()).withUser(user).build();

        CreateEmployeeRequest request = new CreateEmployeeRequestBuilder().withRequestInfo(requestInfo).withEmployee(employee).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new EgovEISResource().createEmployee(jsonString);

        System.out.println(response.getStatusCode());

    }

    // Search Employee Test
    public void searchEmployeeTestMethod(LoginResponse loginResponse) throws IOException {

//        entities.requests.eGovEIS.RequestInfo requestInfo = new RequestInfoBuilder("Search")
//                .withAuthToken1(loginResponse.getAccess_token()).build1();
//        SearchEmployeeRequest request = new SearchEmployeeRequestBuilder().withRequestInfo(requestInfo).build();
//        String jsonData = RequestHelper.getJsonString(request);
//        Response response = new EgovEISResource().searchEmployee(jsonData);
//        SearchEmployeeResponse searchEmployeeResponse = (SearchEmployeeResponse) ResponseHelper
//                .getResponseAsObject(response.asString(), SearchEmployeeResponse.class);
//
////        ##Assertions for Employee 1##
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getUserName(), "egovernments");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getCode(), "A9090");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getPosition(), "4");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getDesignation(), "2");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getDepartment(), "1");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getFromDate(), "2015-01-01");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getToDate(), "2020-12-31");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getIsPrimary(), "true");
////        ##Assertions for Employee 2##
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getUserName(), "elzan");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getCode(), "1001");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getPosition(), "1");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getDesignation(), "2");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getDepartment(), "1");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getFromDate(), "2016-01-01");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getToDate(), "2017-12-31");
//        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getIsPrimary(), "true");
    }
}