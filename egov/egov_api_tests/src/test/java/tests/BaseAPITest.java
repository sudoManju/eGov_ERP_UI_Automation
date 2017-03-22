package tests;

import builders.login.LoginRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.login.LoginRequest;
import entities.responses.login.LoginResponse;
import entities.responses.logout.LogoutResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import resources.LoginResource;
import utils.APILogger;
import utils.Categories;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseAPITest {

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method) {
        Reporter.log("Test Method Name -- " + method.getName(), true);
    }

    @BeforeGroups(groups = Categories.SANITY, alwaysRun = true)
    public void setUp() throws IOException {
    }

    protected LoginResponse loginTestMethod(String path,String username) throws IOException {
        LoginRequest request = new LoginRequestBuilder().withUsername(username).build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().login(jsonString , path);
        LoginResponse loginResponse = (LoginResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(loginResponse.getUserRequest().getUserName(), username);

        new APILogger().log("Login is Completed -- ");
        return loginResponse;
    }

    protected void logoutTestMethod(LoginResponse loginResponse,String path) throws IOException {
        Response response1 = new LoginResource().logout(loginResponse.getAccess_token(),path);
        LogoutResponse logoutResponse = (LogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), LogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);
        Assert.assertEquals(logoutResponse.getStatus(), "Logout successfully");

        new APILogger().log("Logout is Completed --");
    }
}
