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
import entities.responses.login.LoginResponse;
import entities.responses.userServices.createUser.CreateUserResponse;
import entities.responses.userServices.getUser.GetUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.UserServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class CreatenovalidateVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY,Categories.DEV})
    public void userDetails() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create a user
        CreateUserResponse create = CreateAUserTest(loginResponse);

        //Get User Details with Id
        getTheNewlyCreatedUser(loginResponse,create,"id");

        //Get User Details with UserName
        getTheNewlyCreatedUser(loginResponse,create,"userName");
    }

    private void getTheNewlyCreatedUser(LoginResponse loginResponse,CreateUserResponse create,String searchType) throws IOException {

      RequestInfo requestInfo = new RequestInfoBuilder("").withAuthToken(loginResponse.getAccess_token()).build();

      GetUserRequest request = new GetUserRequest();

      switch (searchType){
          case "id" :
              int[] ids = new int[1];
              int id = create.getUser()[0].getId();
              ids[0] = id;
              request  = new GetUserRequestBuilder().withRequestInfo(requestInfo).withId(ids).build();
              break;

          case "userName" :
              request  = new GetUserRequestBuilder().withRequestInfo(requestInfo).withUserName(create.getUser()[0].getUserName()).build();
              break;
      }

        String json = RequestHelper.getJsonString(request);

        Response response = new UserServiceResource().getUserDetails(json);

        Assert.assertEquals(response.getStatusCode(),200);

        GetUserResponse response1 = (GetUserResponse)
                ResponseHelper.getResponseAsObject(response.asString(),GetUserResponse.class);

        Assert.assertEquals(response1.getUser()[0].getUserName(),create.getUser()[0].getUserName());

    }

    private CreateUserResponse CreateAUserTest(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder().withUserName("Test_"+get3DigitRandomInt()+get3DigitRandomInt()).build();

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
