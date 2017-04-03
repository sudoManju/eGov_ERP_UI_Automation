package tests.login;

import builders.login.LoginRequestBuilder;
import builders.userDetails.CreateUserRequestBuilder;
import builders.userDetails.RequestInfoBuilder;
import builders.userDetails.UserBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.login.LoginRequest;
import entities.requests.userDetails.CreateUserRequest;
import entities.requests.userDetails.RequestInfo;
import entities.requests.userDetails.User;
import entities.responses.login.LoginErrorResponse;
import entities.responses.login.LoginResponse;
import entities.responses.logout.InvalidLogoutResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.LoginResource;
import resources.UserDetailsResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.Map;

public class LoginVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY})
    public void shouldAllowLoginAndLogoutToAnExistingUser() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.serverUrl, "narasappa");

        // Logout Test
        logoutTestMethod(loginResponse, Properties.serverUrl);

    }

    @Test(groups = {Categories.SANITY})
    public void shouldNotAllowLogoutWithInvalidCredentials() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.serverUrl, "narasappa");

        // Logout Test
        Response response1 = new LoginResource().inValidLogout(loginResponse.getAccess_token(), Properties.serverUrl);
        InvalidLogoutResponse invalidLogoutResponse = (InvalidLogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), InvalidLogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 400);
        Assert.assertEquals(invalidLogoutResponse.getResponseInfo().getStatus(), "Logout failed");
        Assert.assertEquals(invalidLogoutResponse.getError().getDescription(), "Logout failed");

        new APILogger().log("Logout Failed is Completed -- ");
    }

    @Test(groups = {Categories.SANITY})
    public void shouldNotAllowLoginWithInvalidCredentials() throws IOException {
        LoginRequest request = new LoginRequestBuilder().withPassword("").build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().login(jsonString, Properties.serverUrl);
        LoginErrorResponse loginErrorResponse = (LoginErrorResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginErrorResponse.class);

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(loginErrorResponse.getError_description(), "Invalid login credentials");

        new APILogger().log("Login Failed is Completed -- ");
    }


    @Test
    public void CreateAUserTest() throws IOException{
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder().withUsername("Test"+ RandomStringUtils.randomAlphanumeric(5)).build();

        CreateUserRequest request = new CreateUserRequestBuilder().withRequestInfo(requestInfo).withUser(user).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new UserDetailsResource().createUser(jsonString);

        Assert.assertEquals(response.getStatusCode(),200);

    }
}
