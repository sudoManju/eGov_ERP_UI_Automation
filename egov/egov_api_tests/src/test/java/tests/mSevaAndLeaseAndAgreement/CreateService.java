package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import builders.CreateServiceBuilder;

import entities.CreateServiceRequest;

import resources.CreateServiceResource;

import tests.BaseAPITest;
import utils.RequestHelper;

public class CreateService extends BaseAPITest {

    @Test
    public void CreateServiceShouldRespondWithSucessStatus() throws IOException{
        CreateServiceRequest request = new CreateServiceBuilder()
                .build();

        Response response = new CreateServiceResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        org.junit.Assert.assertTrue("Actual response code " + response.getStatusCode(), isGoodResponse(response));

    }

 } 


