package tests.userServices;

import builders.userServices.createNoValidate.GetUserRequestBuilder;
import builders.userServices.createWithValidate.*;
import com.jayway.restassured.response.Response;
import entities.requests.userServices.createNoValidate.GetUserRequest;
import entities.requests.userServices.createWithValidate.*;
import entities.responses.userServices.createUser.UserResponse;
import entities.responses.userServices.createUserWithValidation.OtpResponse;
import entities.responses.userServices.getUser.GetUserResponse;
import entities.responses.userServices.searchOtp.SearchOtpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.UserServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class CreateUserWithValidationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.DEV, Categories.USER})
    public void createUserWithValidationTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA); // Login
        OtpResponse otp = createOtp(); // Create OTP
        searchOtp(otp); // Search OTP

        OtpResponse validatedOtp = validateOtp(otp); // Validate OTP
        searchOtp(otp); // Search OTP

        UserResponse user = createCitizenUser(validatedOtp); // Create User With Validation
        searchCreatedUser(user, "id"); // Search User Details
        searchCreatedUser(user, "userName"); // // Search User Details
        LoginAndLogoutHelper.logout(); // Logout
    }

    private void searchOtp(OtpResponse otpGene) throws IOException {
        new APILogger().log("Search OTP Test is started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        Otp otp = new OtpBuilder().withUuid(otpGene.getOtp().getUUID()).build();
        SearchOtpRequest request = new SearchOtpRequestBuilder().withRequestInfo(requestInfo).withOtp(otp).build();

        Response response = new UserServiceResource().searchOtpResource(RequestHelper.getJsonString(request));
        SearchOtpResponse response1 = (SearchOtpResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchOtpResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Is OTP Validated :" + response1.getOtp().getIsValidationSuccessful());
        new APILogger().log("Search OTP Test is completed ---");
    }

    private UserResponse createCitizenUser(OtpResponse validatedOtp) throws IOException {
        new APILogger().log("Create User Test with OTP is started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        User user = new UserBuilder()
                .withUserName("Test_" + get3DigitRandomInt() + get3DigitRandomInt())
                .withMobileNumber(validatedOtp.getOtp().getIdentity())
                .withOtpReference(validatedOtp.getOtp().getUUID())
                .build();
        CreateUserValidateRequest request = new CreateUserValidateRequestBuilder().withRequestInfo(requestInfo)
                .withUser(user).build();

        Response response = new UserServiceResource().createCitizenUserResource(RequestHelper.getJsonString(request));
        UserResponse response1 = (UserResponse)
                ResponseHelper.getResponseAsObject(response.asString(), UserResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response1.getUser()[0].getMobileNumber(), validatedOtp.getOtp().getIdentity());
        Assert.assertEquals(request.getUser().getUserName(), response1.getUser()[0].getUserName());
        new APILogger().log("Create User Test with OTP is completed ---");
        return response1;
    }

    private OtpResponse validateOtp(OtpResponse otpGenerated) throws IOException {
        new APILogger().log("Validate OTP Test is started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        Otp otp = new OtpBuilder().withIdentity(otpGenerated.getOtp().getIdentity()).withOtp(otpGenerated.getOtp().getOtp()).build();
        ValidateOtpRequest request = new ValidateOtpRequestBuilder().withRequestInfo(requestInfo).withOtp(otp).build();

        Response response = new UserServiceResource().validateOtp(RequestHelper.getJsonString(request));
        OtpResponse response1 = (OtpResponse)
                ResponseHelper.getResponseAsObject(response.asString(), OtpResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(otpGenerated.getOtp().getIdentity(), response1.getOtp().getIdentity());
        Assert.assertTrue(response1.getOtp().getIsValidationSuccessful());
        new APILogger().log("Validate OTP Test is Completed ---");
        return response1;
    }

    private OtpResponse createOtp() throws IOException {
        new APILogger().log("Create OTP Test is started ---");
        String phoneNo = "9" + get3DigitRandomInt() + get3DigitRandomInt() + get3DigitRandomInt();
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        Otp otp = new OtpBuilder().withIdentity(phoneNo).build();
        CreateOtpRequest request = new CreateOtpRequestBuilder().withRequestInfo(requestInfo).withOtp(otp).build();

        Response response = new UserServiceResource().createOtpResource(RequestHelper.getJsonString(request));
        OtpResponse response1 = (OtpResponse)
                ResponseHelper.getResponseAsObject(response.asString(), OtpResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(phoneNo, response1.getOtp().getIdentity());
        new APILogger().log("Create OTP Test is completed ---");
        return response1;
    }

    private void searchCreatedUser(UserResponse create, String searchType) throws IOException {
        new APILogger().log("Get User details with type " + searchType + " is Started ---");

        entities.requests.userServices.createNoValidate.RequestInfo requestInfo = new builders.userServices.createNoValidate.RequestInfoBuilder("").
                withAuthToken(scenarioContext.getAuthToken()).build();
        GetUserRequest request = new GetUserRequest();

        switch (searchType) {
            case "id":
                int[] ids = new int[1];
                int id = create.getUser()[0].getId();
                ids[0] = id;
                request = new GetUserRequestBuilder().withRequestInfo(requestInfo).withId(ids).build();
                break;

            case "userName":
                request = new GetUserRequestBuilder().withRequestInfo(requestInfo).withUserName(create.getUser()[0].getUserName()).build();
                break;
        }

        Response response = new UserServiceResource().searchCreatedUserResource(RequestHelper.getJsonString(request));
        GetUserResponse response1 = (GetUserResponse)
                ResponseHelper.getResponseAsObject(response.asString(), GetUserResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response1.getUser()[0].getUserName(), create.getUser()[0].getUserName());
        new APILogger().log("Get User details with type " + searchType + " is Completed ---");
    }
}