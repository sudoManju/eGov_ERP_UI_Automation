package tests.eGovEIS;

import builders.eGovEIS.AttendanceBuilder;
import builders.eGovEIS.CreateAttendanceRequestBuilder;
import builders.eGovEIS.RequestInfoBuilder;
import builders.eGovEIS.SearchAttendanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.SearchAttendanceRequest;
import entities.requests.eGovEIS.SearchEmployeeRequest;
import entities.requests.eGovEIS.createAttendance.Attendance;
import entities.requests.eGovEIS.createAttendance.CreateAttendanceRequest;
import entities.requests.eGovEIS.createAttendance.RequestInfo;
import entities.responses.eGovEIS.SearchAttendanceResponse;
import entities.responses.eGovEIS.createAttendance.CreateAttendanceResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class AttendancesTest extends BaseAPITest {

    @Test(groups = Categories.HR)
    public void CreateAttendancesTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginHelper.loginTestMethod("narasappa");

        // Create Attendances Test
        createAttendancesTestMethod(loginResponse);
    }

    @Test(groups = Categories.HR)
    public void SearchAttendancesTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginHelper.loginTestMethod("narasappa");

        // Search Attendances Test
        searchAttendancesTestMethod(loginResponse);
    }

    public void createAttendancesTestMethod(LoginResponse loginResponse) throws IOException
    {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        Attendance attendance = new AttendanceBuilder().withAttendanceDate(getRandomDate()).build();
        CreateAttendanceRequest request = new CreateAttendanceRequestBuilder().withRequestInfo(requestInfo).withAttendance(attendance).build();

        String jsonData = RequestHelper.getJsonString(request);
        Response response = new EgovEISResource().createAttendance(jsonData, loginResponse.getAccess_token());
        CreateAttendanceResponse createAttendanceResponse = (CreateAttendanceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAttendanceResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createAttendanceResponse.getResponseInfo().getStatus(), request.getRequestInfo().getStatus());

        new APILogger().log("Create Attendance Test is completed --");
    }

    public void searchAttendancesTestMethod(LoginResponse loginResponse) throws IOException
    {
        entities.requests.eGovEIS.RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build1();
        SearchAttendanceRequest request = new SearchAttendanceRequestBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        String jsonData = RequestHelper.getJsonString(request);
//        System.out.println(jsonData);

        Response response = new EgovEISResource().searchAttendance(jsonData, loginResponse.getAccess_token());
        SearchAttendanceResponse searchAttendanceResponse = (SearchAttendanceResponse) ResponseHelper.getResponseAsObject(response.asString(), SearchAttendanceResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "1");
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getType().getCode(), "P", "Assert attendance type code");
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getAttendanceDate(), "2010-03-31", "Assert on Attendance Date");

        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "1");
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

        new APILogger().log("Search Attendances Test is completed --");
    }
}