package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import builders.AgreementNoticeBuilder;

import entities.AgreementNoticeRequest;

import resources.AgreementNoticeResource;

import tests.BaseAPITest;
import utils.RequestHelper;

public class AgreementNotice extends BaseAPITest {
	@Test
    public void AgreementNoticeShouldRespondWithSucessStatus() throws IOException{
        AgreementNoticeRequest request = new AgreementNoticeBuilder()
                .build();

        Response response = new AgreementNoticeResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

    }

 }