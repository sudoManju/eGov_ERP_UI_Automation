package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import builders.CreateAgreementBuilder;
import entities.CreateAgreementRequest;
import resources.CreateAgreementResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class CreateAgreement extends BaseAPITest {
@Test
		    public void CreateAgreementShouldRespondWithSucessStatus() throws IOException{
		        CreateAgreementRequest request = new CreateAgreementBuilder()
		                .build();
		        Response response = new CreateAgreementResource().serviceTypeValidation(RequestHelper.getJsonString(request));
		        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

		    }

		 }


