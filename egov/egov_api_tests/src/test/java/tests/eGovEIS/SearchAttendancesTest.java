package tests.eGovEIS;


import builders.eGovEIS.RequestInfoBuilder;
import entities.requests.eGovEIS.RequestInfo;
import entities.responses.login.LoginResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginHelper;

import java.io.IOException;

public class SearchAttendancesTest extends BaseAPITest {

    @Test(groups = {Categories.EIS, Categories.SANITY, Categories.DEV})
    public void searchAttendanceInEIS() throws IOException {
        LoginResponse loginResponse = LoginHelper.loginTestMethod("narasappa");

        RequestInfo requestInfo = new RequestInfoBuilder("search").withAuthToken(loginResponse.getAccess_token()).build1();

//        Response response = new EgovEISResource().searchAttendance(jsonData, loginResponse.getAccess_token());
//        SearchAttendanceResponse searchAttendanceResponse = (SearchAttendanceResponse) ResponseHelper.getResponseAsObject(response.asString(), SearchAttendanceResponse.class);
//        System.out.println("Attendance Mark list: " + searchAttendanceResponse.getAttendance().length);
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "1");
//        System.out.println("Employee Attendance based on ID: " + searchAttendanceResponse.getAttendance()[0].getId());
//        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getType().getCode(), "P", "Assert attendance type code");
//        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getAttendanceDate(), "2010-03-31", "Assert on Attendance Date");
//
//        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "1");
//        System.out.println("Employee Attendance based on ID: " + searchAttendanceResponse.getAttendance()[1].getId());
//        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getAttendanceDate(), "2010-05-24");
//        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getType().getCode(), "P");
//
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "1");
//        softAssert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "1");
//
//        try {
//            softAssert.assertAll();
//        } catch (AssertionError error) {
//            System.out.println("Assertion Failed because of: " + error.getMessage());
//        }
    }
}