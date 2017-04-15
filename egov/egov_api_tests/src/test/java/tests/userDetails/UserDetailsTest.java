package tests.userDetails;

import builders.userDetails.CreateUserRequestBuilder;
import builders.userDetails.RequestInfoBuilder;
import builders.userDetails.UserBuilder;
import builders.userDetails.UserDetailsForSearchRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.userDetails.CreateUserRequest;
import entities.requests.userDetails.RequestInfo;
import entities.requests.userDetails.User;
import entities.requests.userDetails.UserDetailsForSearchRequest;
import entities.responses.login.LoginResponse;
import entities.responses.userDetails.UserDetailsResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.UserDetailsResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class UserDetailsTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.DEV})
    public void userDetails() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create a user
        UserDetailsResponse userDetailsResponse = CreateAUserTest(loginResponse);

        // Get User Details Test with Id
        searchUserTestMethod(loginResponse, userDetailsResponse, "Id");

        //Get User Details Test with UserName
        searchUserTestMethod(loginResponse, userDetailsResponse, "UserName");
    }

    private void searchUserTestMethod(LoginResponse loginResponse, UserDetailsResponse userDetailsResponse, String searchType) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        UserDetailsForSearchRequest userDetailsForSearchRequest = new UserDetailsForSearchRequest();

        switch (searchType) {
            case "Id":
                int a[] = {userDetailsResponse.getUser()[0].getId()};
                userDetailsForSearchRequest = new
                        UserDetailsForSearchRequestBuilder().withId(a).withRequestInfo(requestInfo).build();

                break;
            case "UserName":
                userDetailsForSearchRequest = new
                        UserDetailsForSearchRequestBuilder().withUserName(userDetailsResponse.getUser()[0].getUserName())
                        .withRequestInfo(requestInfo).build();

                break;
        }


        String jsonString = RequestHelper.getJsonString(userDetailsForSearchRequest);

        Response response = new UserDetailsResource().getUserDetails(jsonString);

        UserDetailsResponse userDetailsResponse1 = (UserDetailsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserDetailsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);

        boolean isSame = false;

        for (int i = 0; i < userDetailsResponse1.getUser().length; i++) {
            if (userDetailsResponse.getUser()[0].getUserName().equals(userDetailsResponse1.getUser()[i].getUserName())) {
                isSame = true;
            }
        }

        Assert.assertTrue(isSame);

        new APILogger().log("User Details Request For Search with " + searchType + " is Completed --");
    }


    private UserDetailsResponse CreateAUserTest(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder().withUsername("Test" + RandomStringUtils.randomAlphanumeric(5)).build();

        CreateUserRequest request = new CreateUserRequestBuilder().withRequestInfo(requestInfo).withUser(user).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new UserDetailsResource().createUser(jsonString);

//        Assert.assertEquals(response.getStatusCode(), 200);

        UserDetailsResponse userDetailsResponse = (UserDetailsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserDetailsResponse.class);

        Assert.assertEquals(request.getUser().getName(), userDetailsResponse.getUser()[0].getName());

        new APILogger().log("User Details Request For Create is Completed --");

        return userDetailsResponse;
    }

}
