package tests.login;

import builders.login.LoginRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.login.LoginRequest;
import entities.responses.login.LoginErrorResponse;
import entities.responses.login.LoginResponse;
import entities.responses.logout.InvalidLogoutResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LoginResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.Map;

import static utils.LoginAndLogoutHelper.login;

public class LoginVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV})
    public void shouldAllowLoginAndLogoutToAnExistingUser() throws IOException {

        // Login Test
        LoginResponse loginResponse = login("narasappa");

        // Logout Test
        LoginAndLogoutHelper.logout(loginResponse);

    }

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV})
    public void shouldNotAllowLogoutWithInvalidCredentials() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Logout Test
        Response response1 = new LoginResource().inValidLogout(loginResponse.getAccess_token());
        InvalidLogoutResponse invalidLogoutResponse = (InvalidLogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), InvalidLogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 400);
        Assert.assertEquals(invalidLogoutResponse.getResponseInfo().getStatus(), "Logout failed");
        Assert.assertEquals(invalidLogoutResponse.getError().getDescription(), "Logout failed");

        new APILogger().log("Logout Failed is Completed -- ");
    }

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV})
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
