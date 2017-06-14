package tests.login;

import builders.login.LoginRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.login.LoginRequest;
import entities.responses.login.LoginErrorResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LoginResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.Map;

import static data.UserData.ADMIN;
import static data.UserData.NARASAPPA;

public class LoginVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV, Categories.PGR})
    public void shouldAllowLoginAndLogoutToAnExistingUser() throws IOException {
        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA); // Login
        LoginAndLogoutHelper.logout(loginResponse); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV, Categories.PGR})
    public void shouldNotAllowLogoutWithInvalidCredentials() throws IOException {
        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA); // Login Test
        Response response1 = new LoginResource().inValidLogout(loginResponse.getAccess_token()); // Invalid Logout
        Assert.assertEquals(response1.getStatusCode(), 500);
        new APILogger().log("Logout Test Failed is Completed -- ");
    }

    @Test(groups = {Categories.SANITY, Categories.QA, Categories.DEV, Categories.PGR})
    public void shouldNotAllowLoginWithInvalidCredentials() throws IOException {
        LoginRequest request = new LoginRequestBuilder().withPassword("").build(); // Invalid Login
        Response response = new LoginResource().login(RequestHelper.asMap(request));
        LoginErrorResponse loginErrorResponse = (LoginErrorResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginErrorResponse.class);

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(loginErrorResponse.getError_description(), "Invalid login credentials");
        new APILogger().log("Login Failed is Completed -- ");
    }

    @Test(groups = {Categories.PILOT})
    public void shouldAllowLoginAndLogoutInPilotService() {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        LoginAndLogoutHelper.logoutFromPilotService(sessionId); // Logout
    }
}
