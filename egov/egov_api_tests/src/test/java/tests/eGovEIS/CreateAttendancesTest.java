package tests.eGovEIS;

import builders.eGovEIS.AttendanceBuilder;
import builders.eGovEIS.CreateAttendanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.createAttendance.Attendance;
import entities.requests.eGovEIS.createAttendance.CreateAttendanceRequest;
import entities.responses.eGovEIS.createAttendance.CreateAttendanceResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class CreateAttendancesTest extends BaseAPITest {

    @Test(groups = Categories.HR)
    public void createAttendanceInEIS() throws IOException {

        Attendance attendance = new AttendanceBuilder().withAttendanceDate(getRandomDate()).build();

        CreateAttendanceRequest request = new CreateAttendanceRequestBuilder().withAttendance(attendance).build();

        String jsonData = RequestHelper.getJsonString(request);

        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        Response response = new EgovEISResource().createAttendance(jsonData, loginResponse.getAccess_token());

        Assert.assertEquals(response.getStatusCode(), 200);

        CreateAttendanceResponse createAttendanceResponse = (CreateAttendanceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAttendanceResponse.class);
        Assert.assertEquals(createAttendanceResponse.getResponseInfo().getStatus(),request.getRequestInfo().getStatus());
        System.out.println(createAttendanceResponse.getAttendance().length);
        System.out.println(createAttendanceResponse.getAttendance()[0].getAttendanceDate());
    }
}