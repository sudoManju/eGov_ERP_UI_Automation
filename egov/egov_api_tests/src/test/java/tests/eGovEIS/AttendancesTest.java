package tests.eGovEIS;

import builders.eGovEIS.attendances.AttendanceBuilder;
import builders.eGovEIS.attendances.CreateAttendanceRequestBuilder;
import builders.eGovEIS.attendances.RequestInfoBuilder;
import builders.eGovEIS.attendances.SearchAttendanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.attendances.Attendance;
import entities.requests.eGovEIS.attendances.CreateAttendanceRequest;
import entities.requests.eGovEIS.attendances.RequestInfo;
import entities.requests.eGovEIS.attendances.SearchAttendanceRequest;
import entities.responses.eGovEIS.SearchAttendanceResponse;
import entities.responses.eGovEIS.createAttendance.CreateAttendanceResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import resources.EGovEISResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class AttendancesTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void createAttendanceTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        createAttendance(); // Create Attendance
        pilotLogoutService(); // Logout
    }

    @Test(groups = {Categories.HR, Categories.SANITY , Categories.PILOT})
    public void searchAttendanceTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchAttendance(); // Search Attendance
        pilotLogoutService(); // Logout
    }

    public void createAttendance() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        Attendance attendance = new AttendanceBuilder().withAttendanceDate(getRandomDate()).build();
        CreateAttendanceRequest request = new CreateAttendanceRequestBuilder().withRequestInfo(requestInfo)
                .withAttendance(attendance).build();

        Response response = new EGovEISResource().createAttendanceResource(RequestHelper.getJsonString(request));
        CreateAttendanceResponse createAttendanceResponse = (CreateAttendanceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAttendanceResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createAttendanceResponse.getResponseInfo().getStatus(), request.getRequestInfo().getStatus());
        new APILogger().log("Create Attendance Test is completed --");
    }

    public void searchAttendance() throws IOException {
        entities.requests.eGovEIS.RequestInfo requestInfo = new RequestInfoBuilder("Search").build1();
        SearchAttendanceRequest request = new SearchAttendanceRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new EGovEISResource().searchAttendanceResource(RequestHelper.getJsonString(request));
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

        new APILogger().log("Search attendances Test is completed --");
    }
}