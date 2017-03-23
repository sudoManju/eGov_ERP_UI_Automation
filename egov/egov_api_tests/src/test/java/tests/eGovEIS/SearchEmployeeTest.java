package tests.eGovEIS;

import builders.eGovEIS.SearchEmployeeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.SearchEmployeeRequest;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.EgovEISResource;
import tests.BaseAPITest;
import utils.Properties;
import utils.RequestHelper;

import java.io.IOException;
import java.util.Map;

public class SearchEmployeeTest extends BaseAPITest {

    @Test
    public void searchEmployeeInEIS() throws IOException {
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        SearchEmployeeRequest searchEmployeeRequest = new SearchEmployeeRequestBuilder().build();
        Map jsonData = RequestHelper.asMap(searchEmployeeRequest);

        Response response = new EgovEISResource().searchEmployee(jsonData, loginResponse.getAccess_token());
        Assert.assertEquals(response.getStatusCode(), "200");
    }
}
