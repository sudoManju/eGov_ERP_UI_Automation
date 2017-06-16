package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.woodTypes.CreateWoodTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.woodType.CreateWoodTypeRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.woodTypes.create.WoodTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.WoodTypesResource;
import tests.BaseAPITest;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class WoodTypesVerificationTest extends BaseAPITest {

    @Test
    public void woodTypesTest()throws IOException{

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);    //Login

        WoodTypesResponse create = createWoodTypesMaster(loginResponse);       //Create

        SearchHelper helper = new SearchHelper(loginResponse);

        helper.searchWoodTypesMaster(create);    //Search
    }

    private WoodTypesResponse createWoodTypesMaster(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateWoodTypeRequest request = new CreateWoodTypesRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WoodTypesResource().createWoodTypes(RequestHelper.getJsonString(request));

        Assert.assertEquals(response.getStatusCode(),200);

        WoodTypesResponse response1 = (WoodTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WoodTypesResponse.class);

        Assert.assertEquals(response1.getWoodTypes()[0].getName(),request.getWoodTypes()[0].getName());
        Assert.assertEquals(response1.getWoodTypes()[0].getCode(),request.getWoodTypes()[0].getCode());
        Assert.assertEquals(response1.getWoodTypes()[0].getNameLocal(),request.getWoodTypes()[0].getNameLocal());

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        return response1;
    }

}
