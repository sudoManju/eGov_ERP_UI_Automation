package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.propertyType.PropertyTypesBuilder;
import builders.propertyTax.masters.propertyType.PropertyTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.propertyType.PropertyTypeRequest;
import entities.requests.propertyTax.masters.propertyType.PropertyTypes;
import entities.responses.propertyTax.masters.propertyTypes.create.PropertyTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.PropertyTypeMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class PropertyTypeMasterVerificationTest extends BaseAPITest {

    PropertyTypes[] propertyTypes;
    RequestInfo requestInfo;
    PTISMasterSearchHelper helper;

    public PropertyTypeMasterVerificationTest(){propertyTypes = new PropertyTypes[1];}

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void propertyTypeTest() throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);    //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new PTISMasterSearchHelper();
        PropertyTypesResponse create = createPropertyTypeMaster();   //Create
        helper.searchPropertyTypeMaster(create);    //Search

        PropertyTypesResponse update = updatePropertyTypeMaster(create.getPropertyTypes()[0].getId());   //Update
        helper.searchPropertyTypeMaster(update);   //Search
        LoginAndLogoutHelper.logout();  //Logout
    }

    private PropertyTypesResponse createPropertyTypeMaster() throws IOException {
        new APILogger().log("Create PropertyType Master is Started --");
        propertyTypes[0] = new PropertyTypesBuilder().withName("Test_"+ get5DigitRandomInt()).withCode(get5DigitRandomInt())
                .withNameLocal("Test_"+ get5DigitRandomInt()).withOrderNum(Integer.parseInt(get5DigitRandomInt())).build();
        PropertyTypeRequest request = new PropertyTypesRequestBuilder().withRequestInfo(requestInfo)
                          .withPropertyTypes(propertyTypes).build();

        Response response = new PropertyTypeMasterResource().create(RequestHelper.getJsonString(request));
        PropertyTypesResponse responseObject = checkAsserts(request,response);
        new APILogger().log("Create PropertyType Master is Completed --");

        return responseObject;
    }

    private PropertyTypesResponse checkAsserts(PropertyTypeRequest request, Response response) throws IOException{
        PropertyTypesResponse responseObject = (PropertyTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),PropertyTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getName(),request.getPropertyTypes()[0].getName());
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getCode(),request.getPropertyTypes()[0].getCode());
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getNameLocal(),request.getPropertyTypes()[0].getNameLocal());
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getOrderNumber(),request.getPropertyTypes()[0].getOrderNumber());
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"SUCCESSFUL");

        return responseObject;
    }

    private PropertyTypesResponse updatePropertyTypeMaster(int id)throws IOException{
        new APILogger().log("Update PropertyType Master is Started --");
        propertyTypes[0] = new PropertyTypesBuilder().withId(id)
                .withName("Test_"+ get5DigitRandomInt()).withCode(get5DigitRandomInt())
                .withNameLocal("Test_"+ get5DigitRandomInt()).withOrderNum(Integer.parseInt(get5DigitRandomInt())).build();
        PropertyTypeRequest request = new PropertyTypesRequestBuilder().withRequestInfo(requestInfo)
                .withPropertyTypes(propertyTypes).build();

        Response response = new PropertyTypeMasterResource().update(RequestHelper.getJsonString(request));
        PropertyTypesResponse responseObject = checkAsserts(request,response);
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getId(),id);
        new APILogger().log("Update PropertyType Master is Completed --");

        return responseObject;
    }
}
