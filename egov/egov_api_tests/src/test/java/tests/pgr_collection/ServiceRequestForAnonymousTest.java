package tests.pgr_collection;


import builders.login.LoginUserBuilder;
import builders.login.UserBuilder;
import builders.ServiceTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.login.LoginUserRequest;
import entities.ServiceRequestForAnonymousRequest;
import entities.login.LoginUserResponse;
import org.codehaus.groovy.runtime.powerassert.SourceText;
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

    @Test
    public void CheckingUserName() throws IOException{

        LoginUserRequest request = new LoginUserBuilder().build();

        Response response = new LoginAUserResource().serviceTypeValidation(RequestHelper.getJsonString(request));

        LoginUserResponse loginUserResponse = (LoginUserResponse)
                ResponseHelper.getResponseAsObject(response.asString(), LoginUserResponse.class);

        Assert.assertEquals("abc" , loginUserResponse.getAccess_token());
        Assert.assertEquals(200 , response.getStatusCode());
    }
}
