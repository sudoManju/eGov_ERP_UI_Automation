package tests.eGovEIS;

import builders.eGovEIS.SearchEmployeeRequestBuilder;
import builders.eGovEIS.createEmployee.CreateEmployeeRequestBuilder;
import builders.eGovEIS.createEmployee.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.SearchEmployeeRequest;
import entities.requests.eGovEIS.createEmployee.CreateEmployeeRequest;
import entities.requests.eGovEIS.createEmployee.RequestInfo;
import entities.responses.eGovEIS.employeeMasters.CreateEmployeeResponse;
import entities.responses.eGovEIS.employeeMasters.SearchEmployeeResponse;
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
        CreateEmployeeRequest request = new CreateEmployeeRequestBuilder().withRequestInfo(requestInfo).build();

        String jsonData = RequestHelper.getJsonString(request);
        Response response = new EgovEISResource().createEmployee(jsonData);

        CreateEmployeeResponse createEmployeeResponse = (CreateEmployeeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateEmployeeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // Search Employee Test
    public void searchEmployeeTestMethod(LoginResponse loginResponse) throws IOException {

        entities.requests.eGovEIS.RequestInfo requestInfo = new RequestInfoBuilder("Search")
                .withAuthToken1(loginResponse.getAccess_token()).build1();
        SearchEmployeeRequest request = new SearchEmployeeRequestBuilder().withRequestInfo(requestInfo).build();
        String jsonData = RequestHelper.getJsonString(request);
        Response response = new EgovEISResource().searchEmployee(jsonData);
        SearchEmployeeResponse searchEmployeeResponse = (SearchEmployeeResponse) ResponseHelper
                .getResponseAsObject(response.asString(), SearchEmployeeResponse.class);

//        ##Assertions for Employee 1##
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getUserName(), "egovernments");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getCode(), "A9090");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getPosition(), "4");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getDesignation(), "2");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getDepartment(), "1");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getFromDate(), "2015-01-01");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getToDate(), "2020-12-31");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getIsPrimary(), "true");
//        ##Assertions for Employee 2##
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getUserName(), "elzan");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getCode(), "1001");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getPosition(), "1");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getDesignation(), "2");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getDepartment(), "1");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getFromDate(), "2016-01-01");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getToDate(), "2017-12-31");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getIsPrimary(), "true");
    }
}