package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.propertyType.CreatePropertyTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.propertyType.CreatePropertyTypeRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.propertyTypes.create.PropertyTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.PropertyTypeMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class PropertyTypeVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void propertyTypeTest() throws IOException{

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        PropertyTypesResponse create = createPropertyTypeMaster(loginResponse);   //Create

        SearchHelper helper = new SearchHelper(loginResponse);

        helper.searchPropertyTypeMaster(create);    //Search

        LoginAndLogoutHelper.logout(loginResponse);  //Logout
    }

    private PropertyTypesResponse createPropertyTypeMaster(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create PropertyType Master is started --");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreatePropertyTypeRequest request = new CreatePropertyTypesRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PropertyTypeMasterResource().createPropertyType(RequestHelper.getJsonString(request));

        Assert.assertEquals(response.getStatusCode(),200);

        PropertyTypesResponse response1 = (PropertyTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),PropertyTypesResponse.class);

        Assert.assertEquals(response1.getPropertyTypes()[0].getName(),request.getPropertyTypes()[0].getName());
        Assert.assertEquals(response1.getPropertyTypes()[0].getCode(),request.getPropertyTypes()[0].getCode());
        Assert.assertEquals(response1.getPropertyTypes()[0].getNameLocal(),request.getPropertyTypes()[0].getNameLocal());
        Assert.assertEquals(response1.getPropertyTypes()[0].getOrderNumber(),request.getPropertyTypes()[0].getOrderNumber());

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        new APILogger().log("Create PropertyType Master is completed --");

        return response1;
    }
}
