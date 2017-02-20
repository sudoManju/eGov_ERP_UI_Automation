package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import builders.UpdateServiceBuilder;

import entities.UpdateServiceRequest;

import resources.UpdateServiceResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class UpdateService extends BaseAPITest {

    @Test
    public void UpdateServiceShouldRespondWithSucessStatus() throws IOException{
        UpdateServiceRequest request = new UpdateServiceBuilder()
                .build();

        Response response = new UpdateServiceResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

 } 
