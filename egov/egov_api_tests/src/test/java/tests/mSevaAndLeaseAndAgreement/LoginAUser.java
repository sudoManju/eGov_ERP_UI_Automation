package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import builders.login.LoginUserBuilder;
import entities.login.LoginUserRequest;
import resources.LoginAUserResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class LoginAUser extends BaseAPITest {

    @Test
    public void LoginAUserShouldRespondWithSucessStatus() throws IOException{
        LoginUserRequest request = new LoginUserBuilder()
                .build();

        Response response = new LoginAUserResource().serviceTypeValidation(RequestHelper.getJsonString(request));
       Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

 }
