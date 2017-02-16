package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import builders.CreateAndSendOtpBuilder;
import entities.CreateAndSendOTPRequest;
import resources.CreateAndSendOTPResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class CreatesAndSendsOTPForVerification  extends BaseAPITest{
	  @Test
	    public void CreateAndSendOTPShouldRespondWithSucessStatus() throws IOException{
	        CreateAndSendOTPRequest request = new CreateAndSendOtpBuilder()
	                .build();

	        Response response = new CreateAndSendOTPResource().serviceTypeValidation(RequestHelper.getJsonString(request));
	        org.junit.Assert.assertTrue("Actual response code " + response.getStatusCode(), isGoodResponse(response));

	    }

	 }

