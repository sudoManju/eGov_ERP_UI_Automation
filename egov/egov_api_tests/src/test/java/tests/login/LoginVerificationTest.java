package tests.login;

import builders.LoginRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.login.LoginRequest;
import entities.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LoginResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.Map;

public class LoginVerificationTest extends BaseAPITest {

    @Test(groups = Categories.LOGIN)
    public void shouldAllowLoginToAnExistingUser() throws IOException {
        LoginRequest request = new LoginRequestBuilder().build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().post(jsonString);

        Assert.assertEquals(response.getStatusCode(), 200);

        LoginResponse loginResponse = (LoginResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginResponse.class);

        Assert.assertEquals(loginResponse.getUserRequest().getUserName(), "narasappa");
    }

    @Test(groups = Categories.LOGIN)
    public void shouldNotAllowLoginWithInvalidCredentials() throws IOException {
        LoginRequest request = new LoginRequestBuilder().withPassword("").build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().post(jsonString);

        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
