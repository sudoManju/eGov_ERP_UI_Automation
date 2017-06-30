package tests.userServices;

import builders.userServices.createNoValidate.CreateUserRequestBuilder;
import builders.userServices.createNoValidate.GetUserRequestBuilder;
import builders.userServices.createNoValidate.RequestInfoBuilder;
import builders.userServices.createNoValidate.UserBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.userServices.createNoValidate.CreateUserRequest;
import entities.requests.userServices.createNoValidate.GetUserRequest;
import entities.requests.userServices.createNoValidate.RequestInfo;
import entities.requests.userServices.createNoValidate.User;
import entities.responses.userServices.createUser.UserResponse;
import entities.responses.userServices.getUser.GetUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.UserServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class CreateUserWithoutValidationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.DEV, Categories.USER})
    public void createUserWithoutValidationTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA); //Login Test
        UserResponse create = createUserWithoutValidation(); // Create User
        searchCreatedUser(create, "id"); // Get User Details with Id
        searchCreatedUser(create, "userName"); // Get User Details with UserName

        UserResponse update = updateUser(create.getUser()[0].getId()); // Update User Details
        searchCreatedUser(update, "id"); // Get User Details with Id
        searchCreatedUser(update, "userName"); // Get User Details with UserName
        LoginAndLogoutHelper.logout(); // Logout
    }

    private UserResponse updateUser(int id) throws IOException {
        new APILogger().log("Update user test is started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        User user = new UserBuilder("").build();
        CreateUserRequest request = new CreateUserRequestBuilder().withRequestInfo(requestInfo).withUser(user).build();

        Response response = new UserServiceResource().updateUserResource(RequestHelper.getJsonString(request), id);
        UserResponse response1 = (UserResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response1.getUser()[0].getId(), id);
        Assert.assertEquals(request.getUser().getName(), response1.getUser()[0].getName());
        new APILogger().log("Update user test is completed ---");
        return response1;
    }

    private void searchCreatedUser(UserResponse create, String searchType) throws IOException {
        new APILogger().log("get user details with searchLeaveApplicationResource type " + searchType + " is started ---");
        RequestInfo requestInfo = new RequestInfoBuilder("").withAuthToken(scenarioContext.getAuthToken()).build();
        GetUserRequest request = new GetUserRequest();

        switch (searchType) {
            case "id":
                int[] ids = new int[1];
                int id = create.getUser()[0].getId();
                ids[0] = id;
                request = new GetUserRequestBuilder().withRequestInfo(requestInfo).withId(ids).build();
                break;

            case "userName":
                request = new GetUserRequestBuilder().withRequestInfo(requestInfo).withUserName(create.getUser()[0].getUserName()).build();
                break;
        }

        Response response = new UserServiceResource().searchCreatedUserResource(RequestHelper.getJsonString(request));
        GetUserResponse response1 = (GetUserResponse)
                ResponseHelper.getResponseAsObject(response.asString(), GetUserResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response1.getUser()[0].getUserName(), create.getUser()[0].getUserName());
        new APILogger().log("get user details with searchLeaveApplicationResource type " + searchType + " is completed ---");
    }

    private UserResponse createUserWithoutValidation() throws IOException {
        new APILogger().log("Create User Test is started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        User user = new UserBuilder().withUserName("Test_" + get3DigitRandomInt() + get3DigitRandomInt()).build();
        CreateUserRequest request = new CreateUserRequestBuilder().withRequestInfo(requestInfo).withUser(user).build();

        Response response = new UserServiceResource().createUserWithoutValidationResource(RequestHelper.getJsonString(request));
        UserResponse response1 = (UserResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(request.getUser().getUserName(), response1.getUser()[0].getUserName());
        new APILogger().log("Create user test is completed ---");
        return response1;
    }
}
