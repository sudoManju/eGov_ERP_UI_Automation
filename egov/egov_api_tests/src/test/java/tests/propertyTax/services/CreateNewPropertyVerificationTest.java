package tests.propertyTax.services;

import builders.propertyTax.services.create.CreateNewPropertyRequestBuilder;
import builders.propertyTax.services.create.OwnersBuilder;
import builders.propertyTax.services.create.PropertiesBuilder;
import builders.propertyTax.services.create.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.services.create.CreateNewPropertyRequest;
import entities.requests.propertyTax.services.create.Owners;
import entities.requests.propertyTax.services.create.Properties;
import entities.requests.propertyTax.services.create.RequestInfo;
import entities.responses.propertyTax.services.create.CreateNewPropertyResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.services.CreateNewPropertyResource;
import tests.BaseAPITest;
import tests.propertyTax.masters.PTISMasterSearchHelper;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class CreateNewPropertyVerificationTest extends BaseAPITest {

    Owners[] owners;
    String get10DigitNum;
    Properties[] properties;
    PTISMasterSearchHelper helper;

    public CreateNewPropertyVerificationTest(){
        owners = new Owners[1];
        get10DigitNum = get5DigitRandomInt()+get5DigitRandomInt();
        properties = new Properties[1];
    }

    @Test
    public void createNewPropertyTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);     //Login
        helper = new PTISMasterSearchHelper();

        createNewProperty();   //Create
    }

    private void createNewProperty() throws IOException {

        owners[0] = new OwnersBuilder().withUserName("Test_"+get5DigitRandomInt())
                .withMobileNumber("9"+get10DigitNum.substring(0,9))
                .withAadharNumber(get10DigitNum+get3DigitRandomInt().substring(0,2)).build();
        properties[0] = new PropertiesBuilder().withOwners(owners).build();
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateNewPropertyRequest request = new CreateNewPropertyRequestBuilder().withRequestInfo(requestInfo)
                .withProperties(properties).build();

        Response response = new CreateNewPropertyResource().create(RequestHelper.getJsonString(request));
        checkAsserts(request,response);

    }

    private void checkAsserts(CreateNewPropertyRequest request, Response response) throws IOException {
        CreateNewPropertyResponse responseObject = (CreateNewPropertyResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateNewPropertyResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(request.getProperties()[0].getOwners()[0].getUserName(),responseObject.getProperties()[0].getOwners()[0].getUserName());
        Assert.assertEquals(request.getProperties()[0].getOwners()[0].getMobileNumber(),responseObject.getProperties()[0].getOwners()[0].getMobileNumber());
        Assert.assertEquals(request.getProperties()[0].getOwners()[0].getAadhaarNumber(),responseObject.getProperties()[0].getOwners()[0].getAadhaarNumber());
        Assert.assertEquals(request.getProperties()[0].getOwners()[0].getGender(),responseObject.getProperties()[0].getOwners()[0].getGender());
        Assert.assertEquals(request.getProperties()[0].getOwners()[0].getEmailId(),responseObject.getProperties()[0].getOwners()[0].getEmailId());
        Assert.assertEquals(request.getProperties()[0].getOwners()[0].getType(),responseObject.getProperties()[0].getOwners()[0].getType());
        Assert.assertTrue(request.getProperties()[0].getOwners()[0].getActive());

        Assert.assertEquals(request.getProperties()[0].getAddress().getAddressNumber(),responseObject.getProperties()[0].getAddress().getAddressNumber());
        Assert.assertEquals(request.getProperties()[0].getAddress().getCity(),responseObject.getProperties()[0].getAddress().getCity());
        Assert.assertEquals(request.getProperties()[0].getAddress().getPincode(),responseObject.getProperties()[0].getAddress().getPincode());
        Assert.assertEquals(request.getProperties()[0].getAddress().getAddressLine1(),responseObject.getProperties()[0].getAddress().getAddressLine1());
        Assert.assertEquals(request.getProperties()[0].getAddress().getAddressLine2(),responseObject.getProperties()[0].getAddress().getAddressLine2());

        Assert.assertEquals(request.getProperties()[0].getBoundary().getAdminBoundary().getId(),responseObject.getProperties()[0].getBoundary().getAdminBoundary().getId());
        Assert.assertEquals(request.getProperties()[0].getBoundary().getLocationBoundary().getId(),responseObject.getProperties()[0].getBoundary().getLocationBoundary().getId());
        Assert.assertEquals(request.getProperties()[0].getBoundary().getRevenueBoundary().getId(),responseObject.getProperties()[0].getBoundary().getRevenueBoundary().getId());

        Assert.assertEquals(request.getProperties()[0].getPropertyDetail().getFloorType(),responseObject.getProperties()[0].getPropertyDetail().getFloorType());
        Assert.assertTrue(helper.checkFloorType(request.getProperties()[0].getPropertyDetail().getFloorType()));
        Assert.assertEquals(request.getProperties()[0].getPropertyDetail().getWoodType(),responseObject.getProperties()[0].getPropertyDetail().getWoodType());
        Assert.assertTrue(helper.checkWoodType(request.getProperties()[0].getPropertyDetail().getWoodType()));
        Assert.assertEquals(request.getProperties()[0].getPropertyDetail().getWallType(),responseObject.getProperties()[0].getPropertyDetail().getWallType());
        Assert.assertTrue(helper.checkWallType(request.getProperties()[0].getPropertyDetail().getWallType()));
        Assert.assertEquals(request.getProperties()[0].getPropertyDetail().getRoofType(),responseObject.getProperties()[0].getPropertyDetail().getRoofType());
        Assert.assertTrue(helper.checkRoofType(request.getProperties()[0].getPropertyDetail().getRoofType()));
        Assert.assertEquals(request.getProperties()[0].getPropertyDetail().getPropertyType(),responseObject.getProperties()[0].getPropertyDetail().getPropertyType());

        Assert.assertEquals(request.getProperties()[0].getPropertyDetail().getFloors()[0].getFloorNo(),responseObject.getProperties()[0].getPropertyDetail().getFloors()[0].getFloorNo());
    }
}
