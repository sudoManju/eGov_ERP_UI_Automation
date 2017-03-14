package tests.login;

import builders.LoginRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.login.LoginErrorResponse;
import entities.login.LoginRequest;
import entities.login.LoginResponse;
import entities.logout.InvalidLogoutResponse;
import entities.logout.LogoutResponse;
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
    public void shouldAllowLoginAndLogoutToAnExistingUser() throws IOException {

        // Login
        LoginRequest request = new LoginRequestBuilder().build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().login(jsonString);
        LoginResponse loginResponse = (LoginResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(loginResponse.getUserRequest().getUserName(), "narasappa");

        // Logout
        Response response1 = new LoginResource().logout(loginResponse.getAccess_token());
        LogoutResponse logoutResponse = (LogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), LogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode() , 200);
        Assert.assertEquals(logoutResponse.getStatus() , "Logout successfully");
    }

    @Test(groups = Categories.LOGIN)
    public void shouldNotAllowLogoutWithInvalidCredentials() throws IOException {

        // Login
        LoginRequest request = new LoginRequestBuilder().build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().login(jsonString);
        LoginResponse loginResponse = (LoginResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(loginResponse.getUserRequest().getUserName(), "narasappa");

        // Logout
        Response response1 = new LoginResource().logout(loginResponse.getAccess_token().substring(1));
        InvalidLogoutResponse invalidLogoutResponse = (InvalidLogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), InvalidLogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode() , 400);
        Assert.assertEquals(invalidLogoutResponse.getResponseInfo().getStatus(), "Logout failed");
        Assert.assertEquals(invalidLogoutResponse.getError().getDescription(), "Logout failed");
    }

    @Test(groups = Categories.LOGIN)
    public void shouldNotAllowLoginWithInvalidCredentials() throws IOException {
        LoginRequest request = new LoginRequestBuilder().withPassword("").build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().login(jsonString);
        LoginErrorResponse loginErrorResponse = (LoginErrorResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginErrorResponse.class);

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(loginErrorResponse.getError_description(), "Invalid login credentials");
    }
}
