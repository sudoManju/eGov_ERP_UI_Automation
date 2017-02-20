package tests.mSevaAndLeaseAndAgreement;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import builders.CreateAndSendRecoverPasswordOTPBuilder;
import entities.CreateAndSendRecoverPasswordOTPRequest;
import resources.CreateAndSendRecoverPasswordOTPResource;
import tests.BaseAPITest;
import utils.RequestHelper;

public class CreateAndSendRecoverpasswordOTP   extends BaseAPITest{
		  @Test
		    public void CreateAndSendRecoverPasswordOTPShouldRespondWithSucessStatus() throws IOException{
		        CreateAndSendRecoverPasswordOTPRequest request = new CreateAndSendRecoverPasswordOTPBuilder()
		                .build();

		        Response response = new CreateAndSendRecoverPasswordOTPResource().serviceTypeValidation(RequestHelper.getJsonString(request));
		        Assert.assertTrue(isGoodResponse(response), "Actual response code " + response.getStatusCode());

		    }

		 }

