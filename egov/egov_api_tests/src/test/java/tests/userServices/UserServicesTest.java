package tests.userServices;

import builders.userServices.CreateUserRequestBuilder;
import builders.userServices.RequestInfoBuilder;
import builders.userServices.UserBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.userServices.CreateUserRequest;
import entities.requests.userServices.RequestInfo;
import entities.requests.userServices.User;
import entities.responses.login.LoginResponse;
import entities.responses.userServices.CreateUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.UserServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class UserServicesTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY})
    public void userDetails() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create a user
        CreateUserResponse create = CreateAUserTest(loginResponse);
    }

    private CreateUserResponse CreateAUserTest(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder().withUserName("Test_"+get3DigitRandomInt()).build();

        CreateUserRequest request = new CreateUserRequestBuilder().withRequestInfo(requestInfo).withUser(user).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new UserServiceResource().createUser(json);

        Assert.assertEquals(response.getStatusCode(),200);

        CreateUserResponse response1 = (CreateUserResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateUserResponse.class);

        Assert.assertEquals(request.getUser().getUserName(),response1.getUser()[0].getUserName());

        return response1;
    }
}
