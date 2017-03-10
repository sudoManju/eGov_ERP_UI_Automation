package tests.pgr_collection;


import builders.LoginAUserBuilder;
import builders.ServiceTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.Login.LoginResponse;
import entities.LoginAUserRequest;
import entities.ServiceRequestForAnonymousRequest;
import org.junit.Assert;
import org.testng.annotations.Test;

import resources.LoginAUserResource;
import resources.ServiceRequestForAnonymousResource;
import tests.BaseAPITest;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;


public class ServiceRequestForAnonymousTest extends BaseAPITest {

    @Test
    public void shouldRespondWithSucessStatus() throws IOException{
        ServiceRequestForAnonymousRequest request = new ServiceTypeRequestBuilder()
                                    .build();

        Response response = new ServiceRequestForAnonymousResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        Assert.assertTrue("Actual response code " + response.getStatusCode(), isGoodResponse(response));

    }

}
