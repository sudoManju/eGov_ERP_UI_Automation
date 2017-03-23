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
import java.util.GregorianCalendar;
import java.util.Map;

import static java.lang.String.format;

public class BaseAPITest {

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method) {
        Reporter.log("Test Method Name -- " + method.getName(), true);
    }

    @BeforeGroups(groups = Categories.SANITY, alwaysRun = true)
    public void setUp() throws IOException {

    }

    public String getRandomDate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2010, 2017);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        String finalDate = format(gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH) + 1) + "/" + gc.get(gc.YEAR));

        return finalDate;
    }

    protected LoginResponse loginTestMethod(String path, String username) throws IOException {
        LoginRequest request = new LoginRequestBuilder().withUsername(username).build();

        Map jsonString = RequestHelper.asMap(request);

        Response response = new LoginResource().login(jsonString, path);
        LoginResponse loginResponse = (LoginResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(loginResponse.getUserRequest().getUserName(), username);

        new APILogger().log("Login Test is Completed -- ");
        return loginResponse;
    }

    protected void logoutTestMethod(LoginResponse loginResponse, String path) throws IOException {
        Response response1 = new LoginResource().logout(loginResponse.getAccess_token(), path);
        LogoutResponse logoutResponse = (LogoutResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), LogoutResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);
        Assert.assertEquals(logoutResponse.getStatus(), "Logout successfully");

        new APILogger().log("Logout Test is Completed --");
    }
}
