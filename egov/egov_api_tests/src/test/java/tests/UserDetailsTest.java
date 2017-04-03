package tests;

import builders.userDetails.UserDetailsForSearchRequestBuilder;
import builders.userDetails.CreateUserRequestBuilder;
import builders.userDetails.RequestInfoBuilder;
import builders.userDetails.UserBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.userDetails.User;
import entities.requests.userDetails.UserDetailsForSearchRequest;
import entities.requests.userDetails.CreateUserRequest;
import entities.requests.userDetails.RequestInfo;
import entities.responses.login.LoginResponse;
import entities.responses.userDetails.UserDetailsResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.Test;
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
        LoginResponse loginResponse = loginTestMethod("narasappa");

        // Create a user
        UserDetailsResponse userDetailsResponse = CreateAUserTest(loginResponse);

        // User Details Test
        userDetailsTestMethod(loginResponse, userDetailsResponse);
    }

    private void userDetailsTestMethod(LoginResponse loginResponse, UserDetailsResponse userDetailsResponse) throws IOException {
        int a[] = {userDetailsResponse.getUser()[0].getId()};

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        UserDetailsForSearchRequest userDetailsForSearchRequest = new
                UserDetailsForSearchRequestBuilder().withId(a).withRequestInfo(requestInfo).build();

        String jsonString = RequestHelper.getJsonString(userDetailsForSearchRequest);

        Response response = new UserDetailsResource().getUserDetails(jsonString);

        UserDetailsResponse userDetailsResponse1 = (UserDetailsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserDetailsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(userDetailsResponse1.getUser()[0].getUserName(), userDetailsResponse.getUser()[0].getUserName());

        new APILogger().log("User Details Request For Search is Completed --");
    }


    public UserDetailsResponse CreateAUserTest(LoginResponse loginResponse) throws IOException{

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder().withUsername("Test"+ RandomStringUtils.randomAlphanumeric(5)).build();

        CreateUserRequest request = new CreateUserRequestBuilder().withRequestInfo(requestInfo).withUser(user).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new UserDetailsResource().createUser(jsonString);

        Assert.assertEquals(response.getStatusCode(),200);

        UserDetailsResponse userDetailsResponse = (UserDetailsResponse)
                ResponseHelper.getResponseAsObject(response.asString(),UserDetailsResponse.class);

        Assert.assertEquals(request.getUser().getName(),userDetailsResponse.getUser()[0].getName());

        new APILogger().log("User Details Request For Create is Completed --");

        return userDetailsResponse;
    }

}
