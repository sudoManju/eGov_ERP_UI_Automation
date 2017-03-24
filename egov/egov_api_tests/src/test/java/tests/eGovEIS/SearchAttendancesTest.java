package tests.eGovEIS;


import builders.eGovEIS.SearchAttendanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.SearchAttendanceRequest;
import entities.responses.eGovEIS.SearchAttendanceResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.Map;

public class SearchAttendancesTest extends BaseAPITest {

    @Test(groups = {Categories.EIS, Categories.SANITY})
    public void searchAttendanceInEIS() throws IOException {
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        SearchAttendanceRequest request = new SearchAttendanceRequestBuilder().build();
        Map jsonData = RequestHelper.asMap(request);

        Response response = new EgovEISResource().searchAttendance(jsonData, loginResponse.getAccess_token());
        Assert.assertEquals(response.getStatusCode(), 200);

        SearchAttendanceResponse searchAttendanceResponse = (SearchAttendanceResponse) ResponseHelper.getResponseAsObject(response.asString(), SearchAttendanceResponse.class);
        System.out.println("Attendance Mark list: " + searchAttendanceResponse.getAttendance().length);

        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "1");
        System.out.println("Employee Attendance based on ID: " + searchAttendanceResponse.getAttendance()[0].getId());
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getType().getCode(), "P", "Assert attendance type code");
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getAttendanceDate(), "2010-03-31", "Assert on Attendance Date");

        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "1");
        System.out.println("Employee Attendance based on ID: " + searchAttendanceResponse.getAttendance()[1].getId());
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getAttendanceDate(), "2010-05-24");
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getType().getCode(), "P");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "1");
        softAssert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "1");

        try {
            softAssert.assertAll();
        } catch (AssertionError error) {
            System.out.println("Assertion Failed because of: " + error.getMessage());
        }
    }
}