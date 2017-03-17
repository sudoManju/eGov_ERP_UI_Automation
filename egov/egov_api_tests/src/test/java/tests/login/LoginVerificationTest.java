package tests.login;

import builders.LoginRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.responses.login.LoginErrorResponse;
import entities.requests.login.LoginRequest;
import entities.responses.login.LoginResponse;
import entities.responses.logout.InvalidLogoutResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LoginResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.util.Map;

public class LoginVerificationTest extends BaseAPITest {

    @Test(groups = Categories.LOGIN)
    public void shouldAllowLoginAndLogoutToAnExistingUser() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod();

        // Logout Test
        logoutTestMethod(loginResponse);
    }

    @Test(groups = Categories.LOGIN)
    public void shouldNotAllowLogoutWithInvalidCredentials() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod();

        // Logout Test
        Response response1 = new LoginResource().logout(loginResponse.getAccess_token().substring(1));
        InvalidLogoutResponse invalidLogoutResponse = (InvalidLogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), InvalidLogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 400);
        Assert.assertEquals(invalidLogoutResponse.getResponseInfo().getStatus(), "Logout failed");
        Assert.assertEquals(invalidLogoutResponse.getError().getDescription(), "Logout failed");

        new APILogger().log("Logout Failed is Completed -- ");
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

        new APILogger().log("Login Failed is Completed -- ");
    }
}
