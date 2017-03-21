package tests.eGovEIS;


import builders.eGovEIS.SearchAttendanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.SearchAttendanceRequest;
import entities.responses.eGovEIS.SearchAttendanceResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import resources.AttendanceResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class SearchAttendancesTest extends BaseAPITest{

    @Test(groups = Categories.EIS)
    public void searchAttendanceInEIS() throws IOException
    {
        SearchAttendanceRequest request = new SearchAttendanceRequestBuilder().build();
        String jsonData = RequestHelper.getJsonString(request);

        Response response = new AttendanceResource().post(jsonData);
        Assert.assertEquals(response.getStatusCode(), 200);

        SearchAttendanceResponse searchAttendanceResponse = (SearchAttendanceResponse) ResponseHelper.getResponseAsObject(response.asString(), SearchAttendanceResponse.class);
        System.out.println("Attendance Mark list: "+searchAttendanceResponse.getAttendance().length);

        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "120");
        System.out.println("Employee Attendance based on ID: "+searchAttendanceResponse.getAttendance()[0].getId());
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getType().getCode(), "P", "Assert attendance type code");
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[0].getAttendanceDate(), "2017-01-01", "Assert");

        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "140");
        System.out.println("Employee Attendance based on ID: "+searchAttendanceResponse.getAttendance()[1].getId());
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getAttendanceDate(), "2017-01-01");
        Assert.assertEquals(searchAttendanceResponse.getAttendance()[1].getType().getCode(), "P");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(searchAttendanceResponse.getAttendance()[0].getEmployee(), "120");
        softAssert.assertEquals(searchAttendanceResponse.getAttendance()[1].getEmployee(), "140");

        try{
            softAssert.assertAll();
        }catch (AssertionError error){
            System.out.println("Assertion Failed because of: "+error.getMessage());
        }
    }
}