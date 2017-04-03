package tests;

import builders.UserDetailsRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.UserDetailsRequest;
import entities.responses.login.LoginResponse;
import entities.responses.userDetails.UserDetailsResponse;
import org.junit.Assert;
import org.junit.Test;
import resources.UserDetailsResource;
import utils.APILogger;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class UserDetailsTest extends BaseAPITest {

    @Test
    public void userDetails() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // User Details Test
        userDetailsTestMethod(loginResponse);
    }

    private void userDetailsTestMethod(LoginResponse loginResponse) throws IOException {
        UserDetailsRequest userDetailsRequest = new UserDetailsRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(userDetailsRequest);


        Response response = new UserDetailsResource().getUserDetails(loginResponse, jsonString);

        UserDetailsResponse userDetailsResponse = (UserDetailsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserDetailsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(userDetailsResponse.getUser()[0].getUserName(), "egovernments");

        new APILogger().log("User Details Request is Completed --");
    }

}
