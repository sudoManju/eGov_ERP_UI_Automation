package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.wallTypes.CreateWallTypeMasterRequestBuilder;
import builders.propertyTax.masters.wallTypes.UpdateWallTypeMasterRequestBuilder;
import builders.propertyTax.masters.wallTypes.WallTypesBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.wallType.CreateWallTypeMasterRequest;
import entities.requests.propertyTax.masters.wallType.UpdateWallTypeMasterRequest;
import entities.requests.propertyTax.masters.wallType.WallTypes;
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

        LoginAndLogoutHelper.login1(NARASAPPA);   //Login
        WallTypesResponse create = createWallTypeMasterTest();  //Create
        SearchHelper helper = new SearchHelper();
        helper.searchWallTypeMaster(create);   //Search

        WallTypesResponse update = updateWallTypeMasterTest(create.getWallTypes()[0].getId()); //Update
        helper.searchWallTypeMaster(update);  //Search
        LoginAndLogoutHelper.logout1(); //Logout
    }

    private WallTypesResponse createWallTypeMasterTest() throws IOException {
        new APILogger().log("Create WallType Master is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateWallTypeMasterRequest request = new CreateWallTypeMasterRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WallTypeResource().create(RequestHelper.getJsonString(request));
        WallTypesResponse response1 = (WallTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WallTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getWallTypes()[0].getName(),request.getWallTypes()[0].getName());
        Assert.assertEquals(response1.getWallTypes()[0].getCode(),request.getWallTypes()[0].getCode());
        Assert.assertEquals(response1.getWallTypes()[0].getNameLocal(),request.getWallTypes()[0].getNameLocal());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        new APILogger().log("Create WallType Master is Completed --");

        return response1;
    }

    private WallTypesResponse updateWallTypeMasterTest(int id) throws IOException{
        new APILogger().log("Update WallType Master is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        WallTypes wallTypes = new WallTypesBuilder().withId(id).withName("Test_"+get3DigitRandomInt())
                .withCode(get3DigitRandomInt()).withNameLocal("Test_"+get3DigitRandomInt()).build();
        WallTypes[] wallTypes1 = new WallTypes[1];
        wallTypes1[0] = wallTypes;
        UpdateWallTypeMasterRequest request = new UpdateWallTypeMasterRequestBuilder()
                .withRequestInfo(requestInfo).withWallTypes(wallTypes1).build();

        Response response = new WallTypeResource().update(RequestHelper.getJsonString(request));
        WallTypesResponse response1 = (WallTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WallTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getWallTypes()[0].getName(),request.getWallTypes()[0].getName());
        Assert.assertEquals(response1.getWallTypes()[0].getCode(),request.getWallTypes()[0].getCode());
        Assert.assertEquals(response1.getWallTypes()[0].getNameLocal(),request.getWallTypes()[0].getNameLocal());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        new APILogger().log("Update WallType Master is Completed --");

        return response1;
    }
}
