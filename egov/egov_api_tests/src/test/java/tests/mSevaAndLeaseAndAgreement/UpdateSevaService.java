package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;


import builders.UpdateSevaServiceBuilder;

import entities.UpdateSevaServiceRequest;
import resources.UpdateSevaServiceResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class UpdateSevaService extends BaseAPITest {

    @Test
    public void UpdateSevaServiceShouldRespondWithSucessStatus() throws IOException{
        UpdateSevaServiceRequest request = new UpdateSevaServiceBuilder()
                .build();

        Response response = new UpdateSevaServiceResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

 } 
