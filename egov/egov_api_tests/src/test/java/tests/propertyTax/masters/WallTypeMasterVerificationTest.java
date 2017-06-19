package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.wallTypes.CreateWallTypeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.wallType.CreateWallTypeMasterRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.wallTypes.create.WallTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.WallTypeResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class WallTypeMasterVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void wallTypeMasterTest() throws IOException {

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);   //Create

        WallTypesResponse create = createWallTypeMasterTest(loginResponse);

        SearchHelper helper = new SearchHelper(loginResponse);

        helper.searchWallTypeMaster(create);   //Search

        LoginAndLogoutHelper.logout(loginResponse); //Logout
    }

    private WallTypesResponse createWallTypeMasterTest(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create WallType Master is started as --");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateWallTypeMasterRequest request = new CreateWallTypeMasterRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WallTypeResource().createWallTypeMaster(RequestHelper.getJsonString(request));

        Assert.assertEquals(response.getStatusCode(),200);

        WallTypesResponse response1 = (WallTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WallTypesResponse.class);

        Assert.assertEquals(response1.getWallTypes()[0].getName(),request.getWallTypes()[0].getName());
        Assert.assertEquals(response1.getWallTypes()[0].getCode(),request.getWallTypes()[0].getCode());
        Assert.assertEquals(response1.getWallTypes()[0].getNameLocal(),request.getWallTypes()[0].getNameLocal());

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        return response1;
    }
}
