package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;


import builders.UpdateAgreementBuilder;

import entities.UpdateAgreementRequest;

import resources.UpdateAgreementResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class UpdateAgreement extends BaseAPITest {
	@Test
    public void UpdateAgreementShouldRespondWithSucessStatus() throws IOException{
        UpdateAgreementRequest request = new UpdateAgreementBuilder()
                .build();

        Response response = new UpdateAgreementResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

 }


