package tests.eGovEIS;

import builders.eGovEIS.SearchEmployeeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.SearchEmployeeRequest;
import entities.responses.eGovEIS.SearchAttendanceResponse;
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

        SearchEmployeeRequest searchEmployeeRequest = new SearchEmployeeRequestBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        Map jsonData = RequestHelper.asMap(searchEmployeeRequest);

        Response response = new EgovEISResource().searchEmployee(jsonData, loginResponse.getAccess_token());
        Assert.assertEquals(response.getStatusCode(), 200);

        SearchEmployeeResponse searchEmployeeResponse = (SearchEmployeeResponse) ResponseHelper.getResponseAsObject(response.asString(), SearchEmployeeResponse.class);
        System.out.println("Number of Employees: "+searchEmployeeResponse.getEmployee().length);
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getUserName(), "egovernments");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getCode(), "A9090");
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getPosition(), 1);
        Assert.assertEquals(searchEmployeeResponse.getEmployee()[0].getAssignments()[0].getIsPrimary(), true);
    }
}