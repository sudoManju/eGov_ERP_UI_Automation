package tests.serviceType;


import builders.ServiceTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.ServiceRequestForAnonymousRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.ServiceRequestForAnonymousResource;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;
import java.util.ArrayList;


public class ServiceRequestForAnonymousTest extends BaseAPITest {

    @Test
    public void shouldRespondWithSucessStatus() throws IOException{
        ServiceRequestForAnonymousRequest request = new ServiceTypeRequestBuilder()
                                    .build();

        Response response = new ServiceRequestForAnonymousResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        org.junit.Assert.assertTrue("Actual response code " + response.getStatusCode(), isGoodResponse(response));

    }
}
