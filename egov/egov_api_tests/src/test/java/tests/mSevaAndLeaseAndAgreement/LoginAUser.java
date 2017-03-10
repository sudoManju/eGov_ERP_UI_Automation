package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import entities.Login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import builders.login.LoginUserBuilder;
import entities.login.LoginUserRequest;
import resources.LoginAUserResource;
import tests.BaseAPITest;
import utils.RequestHelper;
import utils.ResponseHelper;

public class LoginAUser extends BaseAPITest {

    @Test
    public void LoginAUserShouldRespondWithSucessStatus() throws IOException{
        LoginUserRequest request = new LoginUserBuilder()
                .build();

        Response response = new LoginAUserResource().serviceTypeValidation(RequestHelper.getJsonString(request));
       Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

    @Test
    public void LoginUser() throws IOException{
        LoginAUserRequest request = new LoginAUserBuilder().build();

        Response response = new LoginAUserResource().post(RequestHelper.getJsonString(request));
        System.out.println("Response ----"+ response.asString());
        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

        LoginResponse loginResponse = (LoginResponse) ResponseHelper.getResponseAsObject(response.asString(), LoginResponse.class);
        System.out.println(loginResponse);
         Assert.assertEquals(loginResponse.getUser().getUser_name(), "aeiou");

    }

 }
