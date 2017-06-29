package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.wallTypes.WallTypeMasterRequestBuilder;
import builders.propertyTax.masters.wallTypes.WallTypesBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.wallType.WallTypeMasterRequest;
import entities.requests.propertyTax.masters.wallType.WallTypes;
import entities.responses.propertyTax.masters.wallTypes.create.WallTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.WallTypeResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.Arrays;

import static data.UserData.NARASAPPA;

public class WallTypeMasterVerificationTest extends BaseAPITest {

    WallTypes[] wallTypes;
    RequestInfo requestInfo;
    SearchHelper helper;

    public WallTypeMasterVerificationTest(){
        wallTypes = new WallTypes[1];
    }

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void wallTypeMasterTest() throws IOException {
        LoginAndLogoutHelper.login1(NARASAPPA);   //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new SearchHelper();
        WallTypesResponse create = createWallTypeMasterTest();  //Create
        helper.searchWallTypeMaster(create);   //Search

        WallTypesResponse update = updateWallTypeMasterTest(create.getWallTypes()[0].getId()); //Update
        helper.searchWallTypeMaster(update);  //Search
        LoginAndLogoutHelper.logout1(); //Logout
    }

    private WallTypesResponse createWallTypeMasterTest() throws IOException {
        new APILogger().log("Create WallType Master is Started --");
        wallTypes[0] = new WallTypesBuilder().withCode(get3DigitRandomInt())
                .withName("Test_"+get3DigitRandomInt()).withNameLocal("Test_"+get3DigitRandomInt()).build();
        WallTypeMasterRequest request = new WallTypeMasterRequestBuilder().withRequestInfo(requestInfo)
                .withWallTypes(wallTypes).build();

        Response response = new WallTypeResource().create(RequestHelper.getJsonString(request));
        WallTypesResponse responseObject = checkAsserts(request,response);
        new APILogger().log("Create WallType Master is Completed --");
        return responseObject;
    }

    private WallTypesResponse updateWallTypeMasterTest(int id) throws IOException{
        new APILogger().log("Update WallType Master is Started --");
        wallTypes[0] = new WallTypesBuilder().withId(id).withName("Test_"+get3DigitRandomInt())
                .withCode(get3DigitRandomInt()).withNameLocal("Test_"+get3DigitRandomInt()).build();
        WallTypeMasterRequest request = new WallTypeMasterRequestBuilder()
                .withRequestInfo(requestInfo).withWallTypes(wallTypes).build();

        Response response = new WallTypeResource().update(RequestHelper.getJsonString(request));
        WallTypesResponse responseObject = checkAsserts(request,response);
        Assert.assertEquals(responseObject.getWallTypes()[0].getId(),id);
        new APILogger().log("Update WallType Master is Completed --");
        return responseObject;
    }

    private WallTypesResponse checkAsserts(WallTypeMasterRequest request,Response response) throws IOException {
        WallTypesResponse responseObject = (WallTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WallTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getWallTypes()[0].getName(),request.getWallTypes()[0].getName());
        Assert.assertEquals(responseObject.getWallTypes()[0].getCode(),request.getWallTypes()[0].getCode());
        Assert.assertEquals(responseObject.getWallTypes()[0].getNameLocal(),request.getWallTypes()[0].getNameLocal());
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"SUCCESSFUL");
        return responseObject;
    }
}
