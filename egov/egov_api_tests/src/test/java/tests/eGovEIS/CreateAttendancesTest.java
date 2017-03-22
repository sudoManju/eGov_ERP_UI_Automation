package tests.eGovEIS;

import builders.eGovEIS.CreateAttendanceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.createAttendance.CreateAttendanceRequest;
import entities.responses.eGovEIS.createAttendance.CreateAttendanceResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.AttendanceResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class CreateAttendancesTest extends BaseAPITest {

    @Test(groups = Categories.HR)
    public void createAttendanceInEIS() throws IOException
    {
        CreateAttendanceRequest request = new CreateAttendanceRequestBuilder().build();
        String jsonData = RequestHelper.getJsonString(request);
        LoginResponse loginResponse = loginTestMethod(Properties.serverUrl,"narasappa");

        Response response = new AttendanceResource().post(jsonData, loginResponse.getAccess_token());
        Assert.assertEquals(response.getStatusCode(), 200);

        CreateAttendanceResponse createAttendanceResponse = (CreateAttendanceResponse) ResponseHelper.getResponseAsObject(response.asString(), CreateAttendanceResponse.class);
        System.out.println(createAttendanceResponse.getResponseInfo().getStatus());
    }
}