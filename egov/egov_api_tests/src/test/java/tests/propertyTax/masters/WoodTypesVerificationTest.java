package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.woodTypes.CreateWoodTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.woodType.CreateWoodTypeRequest;
import entities.responses.propertyTax.masters.woodTypes.create.WoodTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.WoodTypesResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class WoodTypesVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void woodTypesTest()throws IOException{

        LoginAndLogoutHelper.login(NARASAPPA);    //Login

        WoodTypesResponse create = createWoodTypesMaster();       //Create

        SearchHelper helper = new SearchHelper();

        helper.searchWoodTypesMaster(create);    //Search

        LoginAndLogoutHelper.logout1();  //Logout
    }

    private WoodTypesResponse createWoodTypesMaster() throws IOException {
        new APILogger().log("Create WoodType Master is Started");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateWoodTypeRequest request = new CreateWoodTypesRequestBuilder().withRequestInfo(requestInfo).build();


        Response response = new WoodTypesResource().create(RequestHelper.getJsonString(request));
        WoodTypesResponse response1 = (WoodTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),WoodTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getWoodTypes()[0].getName(),request.getWoodTypes()[0].getName());
        Assert.assertEquals(response1.getWoodTypes()[0].getCode(),request.getWoodTypes()[0].getCode());
        Assert.assertEquals(response1.getWoodTypes()[0].getNameLocal(),request.getWoodTypes()[0].getNameLocal());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        new APILogger().log("Create WoodType Master is Completed");

        return response1;
    }

}
