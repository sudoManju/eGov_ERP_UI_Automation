package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.woodTypes.WoodTypeMasterRequestBuilder;
import builders.propertyTax.masters.woodTypes.WoodTypesBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.woodType.WoodTypeMasterRequest;
import entities.requests.propertyTax.masters.woodType.WoodTypes;
import entities.responses.propertyTax.masters.woodTypes.create.WoodTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.WoodTypesResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class WoodTypesVerificationTest extends BaseAPITest {

    WoodTypes[] woodTypes;
    RequestInfo requestInfo;
    SearchHelper helper;

    public WoodTypesVerificationTest(){woodTypes = new WoodTypes[1];}

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void woodTypesTest()throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);    //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new SearchHelper();
        WoodTypesResponse create = createWoodTypesMaster();       //Create
        helper.searchWoodTypesMaster(create);    //Search

        WoodTypesResponse update = updateWoodTypesMaster(create.getWoodTypes()[0].getId());   //Update
        helper.searchWoodTypesMaster(update);   //Search
        LoginAndLogoutHelper.logout();  //Logout
    }

    private WoodTypesResponse createWoodTypesMaster() throws IOException {
        new APILogger().log("Create WoodType Master is Started");
        woodTypes[0] = new WoodTypesBuilder().withName("Test"+ get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test_"+ get3DigitRandomInt()).build();
        WoodTypeMasterRequest request = new WoodTypeMasterRequestBuilder().withRequestInfo(requestInfo)
                .withWoodType(woodTypes).build();

        Response response = new WoodTypesResource().create(RequestHelper.getJsonString(request));
        WoodTypesResponse responseObject = checkAsserts(request,response);
        new APILogger().log("Create WoodType Master is Completed");

        return responseObject;
    }

    private WoodTypesResponse checkAsserts(WoodTypeMasterRequest request,Response response) throws IOException {
        WoodTypesResponse responseObject = (WoodTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WoodTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getWoodTypes()[0].getName(),request.getWoodTypes()[0].getName());
        Assert.assertEquals(responseObject.getWoodTypes()[0].getCode(),request.getWoodTypes()[0].getCode());
        Assert.assertEquals(responseObject.getWoodTypes()[0].getNameLocal(),request.getWoodTypes()[0].getNameLocal());
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"SUCCESSFUL");

        return responseObject;
    }

    private WoodTypesResponse updateWoodTypesMaster(int id) throws IOException{
        new APILogger().log("Update WoodType Master is Started");
        woodTypes[0] = new WoodTypesBuilder().withName("Test"+ get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withId(id).withNameLocal("Test_"+ get3DigitRandomInt()).build();
        WoodTypeMasterRequest request = new WoodTypeMasterRequestBuilder().withRequestInfo(requestInfo)
                .withWoodType(woodTypes).build();

        Response response = new WoodTypesResource().update(RequestHelper.getJsonString(request));
        WoodTypesResponse responseObject = checkAsserts(request,response);
        Assert.assertEquals(responseObject.getWoodTypes()[0].getId(),id);
        new APILogger().log("Update WoodType Master is Completed");

        return responseObject;
    }
}
