package tests.propertyTax.masters;


import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.floorTypes.FloorTypesBuilder;
import builders.propertyTax.masters.floorTypes.FloorTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.floorType.FloorTypes;
import entities.requests.propertyTax.masters.floorType.FloorTypesRequest;
import entities.responses.propertyTax.masters.floorTypes.create.FloorTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.FloorTypesResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.Arrays;

import static data.UserData.NARASAPPA;

public class FloorTypesVerificationTest extends BaseAPITest {

    FloorTypes[] floorTypes;
    RequestInfo requestInfo;
    SearchHelper helper;

    public FloorTypesVerificationTest(){
        floorTypes = new FloorTypes[1];
    }

  @Test(groups = {Categories.PTIS, Categories.SANITY})
  public void floorTypesTest() throws IOException {

      LoginAndLogoutHelper.login1(NARASAPPA);  //Login
      requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
      helper = new SearchHelper();
      FloorTypesResponse create = createFloorTypesMaster();  //Create
      helper.searchFloorTypesMaster(create);      //Search

      Arrays.fill(floorTypes,null);
      FloorTypesResponse update =updateFloorTypesMaster(create.getFloorTypes()[0].getId()); //Update
      helper.searchFloorTypesMaster(update);      //Search
      LoginAndLogoutHelper.logout1(); //Logout
  }

    private FloorTypesResponse updateFloorTypesMaster(int id) throws IOException {
        new APILogger().log("Update Floor Types Master is Started --");
        floorTypes[0] = new FloorTypesBuilder().withId(id).withName("Test_"+get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test_"+get3DigitRandomInt()).build();
        FloorTypesRequest request = new FloorTypesRequestBuilder().withRequestInfo(requestInfo).withFloorTypes(floorTypes).build();

        Response response = new FloorTypesResource().update(RequestHelper.getJsonString(request));
        FloorTypesResponse response1 = checkAsserts(request,response);
        new APILogger().log("Update Floor Types Master is Completed --");
        return response1;
    }

    private FloorTypesResponse createFloorTypesMaster() throws IOException {
        new APILogger().log("Create Floor Types Master is Started --");
        floorTypes[0] = new FloorTypesBuilder().withName("Test_"+get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test_"+get3DigitRandomInt()).build();
        FloorTypesRequest request = new FloorTypesRequestBuilder().withRequestInfo(requestInfo).withFloorTypes(floorTypes).build();

        Response response = new FloorTypesResource().create(RequestHelper.getJsonString(request));
        FloorTypesResponse response1 = checkAsserts(request,response);
        new APILogger().log("Create Floor Types Master is Completed --");
        return response1;
    }

    private FloorTypesResponse checkAsserts(FloorTypesRequest request,Response response) throws IOException {
        FloorTypesResponse response1 = (FloorTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),FloorTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getFloorTypes()[0].getName(),request.getFloorTypes()[0].getName());
        Assert.assertEquals(response1.getFloorTypes()[0].getCode(),request.getFloorTypes()[0].getCode());
        Assert.assertEquals(response1.getFloorTypes()[0].getNameLocal(),request.getFloorTypes()[0].getNameLocal());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        return response1;
    }

}
