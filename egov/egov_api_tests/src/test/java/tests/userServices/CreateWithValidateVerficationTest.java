package tests.userServices;

import builders.userServices.createWithValidate.*;
import com.jayway.restassured.response.Response;
import entities.requests.userServices.createWithValidate.*;
import entities.responses.login.LoginResponse;
import entities.responses.userServices.createUser.CreateUserResponse;
import entities.responses.userServices.createUserWithValidation.OtpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.UserServiceResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class CreateWithValidateVerficationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY,Categories.DEV})
    public void createWithValidateAndGetTest() throws IOException{

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        //Create OTP
        OtpResponse otp = createOtp(loginResponse);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Validate OTP
        OtpResponse validatedOtp = validateOtp(loginResponse,otp);

        //Create User With Validation
        CreateUserResponse user =  createUser(loginResponse,validatedOtp);
    }

    private CreateUserResponse createUser(LoginResponse loginResponse, OtpResponse validatedOtp) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        User user = new UserBuilder()
                .withUserName("Test_"+get3DigitRandomInt()+get3DigitRandomInt())
                .withMobileNumber(validatedOtp.getOtp().getIdentity())
                .withOtpReference(validatedOtp.getOtp().getUUID())
                .build();

        CreateUserValidateRequest request = new CreateUserValidateRequestBuilder().withRequestInfo(requestInfo)
                                                 .withUser(user).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new UserServiceResource().createCitizenUser(json);

        Assert.assertEquals(response.getStatusCode(),200);

        CreateUserResponse response1 = (CreateUserResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateUserResponse.class);

        Assert.assertEquals(response1.getUser()[0].getMobileNumber(),validatedOtp.getOtp().getIdentity());
        Assert.assertEquals(request.getUser().getUserName(),response1.getUser()[0].getUserName());

        return response1;
    }

    private OtpResponse validateOtp(LoginResponse loginResponse, OtpResponse otpgenerated) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        Otp otp = new OtpBuilder().withIdentity(otpgenerated.getOtp().getIdentity()).withOtp(otpgenerated.getOtp().getOtp()).build();

        ValidateOtpRequest request = new ValidateOtpRequestBuilder().withRequestInfo(requestInfo).withOtp(otp).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new UserServiceResource().validateOtp(json);

        System.out.println(response.getHeaders().get("x-correlation-id"));

        Assert.assertEquals(response.getStatusCode(),200);

        OtpResponse response1 = (OtpResponse)
                ResponseHelper.getResponseAsObject(response.asString(),OtpResponse.class);

        Assert.assertEquals(otpgenerated.getOtp().getIdentity(),response1.getOtp().getIdentity());

        Assert.assertTrue(response1.getOtp().getIsValidationSuccessful());

        return response1;
    }

    private OtpResponse createOtp(LoginResponse loginResponse) throws IOException {

        String phoneNo = "9"+get3DigitRandomInt()+get3DigitRandomInt()+get3DigitRandomInt();

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        Otp otp = new OtpBuilder().withIdentity(phoneNo).build();

        CreateOtpRequest request = new CreateOtpRequestBuilder().withRequestInfo(requestInfo).withOtp(otp).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new UserServiceResource().createOtp(json);

        Assert.assertEquals(response.getStatusCode(),201);

        OtpResponse response1 = (OtpResponse)
                ResponseHelper.getResponseAsObject(response.asString(),OtpResponse.class);

        Assert.assertEquals(phoneNo,response1.getOtp().getIdentity());

        return response1;
    }
}
