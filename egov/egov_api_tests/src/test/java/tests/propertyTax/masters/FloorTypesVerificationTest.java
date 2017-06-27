package tests.propertyTax.masters;


import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.floorTypes.CreateFloorTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.floorType.CreateFloorTypesRequest;
import entities.responses.propertyTax.masters.floorTypes.create.FloorTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.FloorTypesResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class FloorTypesVerificationTest extends BaseAPITest {

  @Test(groups = {Categories.PTIS, Categories.SANITY})
  public void floorTypesTest() throws IOException {

     LoginAndLogoutHelper.login1(NARASAPPA);  //Login

      FloorTypesResponse create = createFloorTypes();  //Create

      SearchHelper helper = new SearchHelper();

      helper.searchFloorTypesMaster(create);      //Search

      LoginAndLogoutHelper.logout1(); //Logout
  }

    private FloorTypesResponse createFloorTypes() throws IOException {
        new APILogger().log("Create Floor Types Master is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateFloorTypesRequest request = new CreateFloorTypesRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new FloorTypesResource().create(RequestHelper.getJsonString(request));
        FloorTypesResponse response1 = (FloorTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),FloorTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getFloorTypes()[0].getName(),request.getFloorTypes()[0].getName());
        Assert.assertEquals(response1.getFloorTypes()[0].getCode(),request.getFloorTypes()[0].getCode());
        Assert.assertEquals(response1.getFloorTypes()[0].getNameLocal(),request.getFloorTypes()[0].getNameLocal());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        new APILogger().log("Create Floor Types Master is Completed --");

        return response1;
    }

}
