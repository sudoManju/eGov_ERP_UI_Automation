package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import builders.LoginAUserBuilder;
import entities.LoginAUserRequest;
import resources.LoginAUserResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class LoginAUser extends BaseAPITest {

    @Test
    public void LoginAUserShouldRespondWithSucessStatus() throws IOException{
        LoginAUserRequest request = new LoginAUserBuilder()
                .build();

        Response response = new LoginAUserResource().serviceTypeValidation(RequestHelper.getJsonString(request));
       Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

 }
