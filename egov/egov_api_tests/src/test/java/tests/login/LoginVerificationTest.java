package tests.login;

import builders.login.LoginRequestBuilder;
import builders.login.LoginRequestForPilotServiceBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.login.LoginRequest;
import entities.requests.login.LoginRequestForPilotService;
import entities.responses.login.LoginErrorResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LoginResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.Map;

import static data.usernames.narasappa;

public class LoginVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV})
    public void shouldAllowLoginAndLogoutToAnExistingUser() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(narasappa);

        // Logout Test
        LoginAndLogoutHelper.logout(loginResponse);
    }

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV})
    public void shouldNotAllowLogoutWithInvalidCredentials() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(narasappa);

        // Logout Test
        Response response1 = new LoginResource().inValidLogout(loginResponse.getAccess_token());

        Assert.assertEquals(response1.getStatusCode(), 500);

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
