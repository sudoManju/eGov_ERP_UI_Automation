package tests.mSevaAndLeaseAndAgreement;


import builders.CreateUserRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.CreateUserRequest;
import org.testng.annotations.Test;

import resources.ServiceRequestForAnonymousResource;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;


public class CreatesAUserTest extends BaseAPITest {

    @Test
    public void CreateUserShouldRespondWithSucessStatus() throws IOException{
        CreateUserRequest request = new CreateUserRequestBuilder()
                .build();

        Response response = new ServiceRequestForAnonymousResource().serviceTypeValidation(RequestHelper.getJsonString(request));

        org.junit.Assert.assertTrue("Actual response code " + response.getStatusCode(), isGoodResponse(response));

    }

 }

