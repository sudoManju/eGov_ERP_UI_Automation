package tests.eGovEIS;

import builders.eGovEIS.RequestInfoBuilder;
import builders.eGovEIS.SearchEmployeeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.RequestInfo;
import entities.requests.eGovEIS.SearchEmployeeRequest;
import entities.responses.eGovEIS.employeeMasters.SearchEmployeeResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.Map;

public class SearchEmployeeTest extends BaseAPITest {

    @Test
    public void searchEmployeeInEIS() throws IOException {
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        RequestInfo requestInfo = new RequestInfoBuilder("search").withAuthToken(loginResponse.getAccess_token()).build1();

        SearchEmployeeRequest searchEmployeeRequest = new SearchEmployeeRequestBuilder().withRequestInfo(requestInfo).build();
        String jsonData = RequestHelper.getJsonString(searchEmployeeRequest);

        System.out.println(jsonData);
        Response response = new EgovEISResource().searchEmployee(jsonData);
        SearchEmployeeResponse searchEmployeeResponse = (SearchEmployeeResponse) ResponseHelper.getResponseAsObject(response.asString(), SearchEmployeeResponse.class);

        System.out.println(response.asString());
//        ###Assertions for various use cases###
        System.out.println("Number of Employees: " + searchEmployeeResponse.getEmployee().length);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getUserName(), "egovernments");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getCode(), "A9090");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getPosition(), "4");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getDesignation(), "2");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getDepartment(), "1");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getFromDate(), "2015-01-01");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getToDate(), "2020-12-31");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getIsPrimary(), "true");

        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getUserName(), "tara123");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getCode(), "EMP120");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getPosition(), "1");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getDesignation(), "2");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getDepartment(), "1");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getFromDate(), "2016-01-01");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getToDate(), "2017-12-31");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[1].getAssignments()[0].getIsPrimary(), "true");
    }
}