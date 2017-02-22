package tests.pgr_collection;


import builders.ServiceRequestForBrokenBinRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.Asset;
import entities.ServiceRequestForBrokenBinRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.ServiceRequestForBrokenBinResource;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;

public class ServiceRequestForBrokenBinTest extends BaseAPITest {

    @Test
    public void shouldRespondWithSucessStatus() throws IOException {
        ServiceRequestForBrokenBinRequest request = new ServiceRequestForBrokenBinRequestBuilder()
                .build();

        Response response = new ServiceRequestForBrokenBinResource().serviceTypeValidation(RequestHelper.getJsonString(request));

        if(isNotFound(response))
                System.out.println("Server is Unavailable");

        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());



    }

}
