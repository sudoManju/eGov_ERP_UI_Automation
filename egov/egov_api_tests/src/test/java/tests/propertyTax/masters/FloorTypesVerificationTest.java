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
      LoginAndLogoutHelper.login(NARASAPPA);  //Login
      requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
      helper = new SearchHelper();
      FloorTypesResponse create = createFloorTypesMaster();  //Create
      helper.searchFloorTypesMaster(create);      //Search

      FloorTypesResponse update =updateFloorTypesMaster(create.getFloorTypes()[0].getId()); //Update
      helper.searchFloorTypesMaster(update);      //Search
      LoginAndLogoutHelper.logout(); //Logout
  }

    private FloorTypesResponse updateFloorTypesMaster(int id) throws IOException {
        new APILogger().log("Update Floor Types Master is Started --");
        floorTypes[0] = new FloorTypesBuilder().withId(id).withName("Test_"+ get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test_"+ get3DigitRandomInt()).build();
        FloorTypesRequest request = new FloorTypesRequestBuilder().withRequestInfo(requestInfo).withFloorTypes(floorTypes).build();

        Response response = new FloorTypesResource().update(RequestHelper.getJsonString(request));
        FloorTypesResponse responseObject = checkAsserts(request,response);
        Assert.assertEquals(responseObject.getFloorTypes()[0].getId(),id);
        new APILogger().log("Update Floor Types Master is Completed --");
        return responseObject;
    }

    private FloorTypesResponse createFloorTypesMaster() throws IOException {
        new APILogger().log("Create Floor Types Master is Started --");
        floorTypes[0] = new FloorTypesBuilder().withName("Test_"+ get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test_"+ get3DigitRandomInt()).build();
        FloorTypesRequest request = new FloorTypesRequestBuilder().withRequestInfo(requestInfo).withFloorTypes(floorTypes).build();

        Response response = new FloorTypesResource().create(RequestHelper.getJsonString(request));
        FloorTypesResponse responseObject = checkAsserts(request,response);
        new APILogger().log("Create Floor Types Master is Completed --");
        return responseObject;
    }

    private FloorTypesResponse checkAsserts(FloorTypesRequest request,Response response) throws IOException {
        FloorTypesResponse responseObject = (FloorTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),FloorTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getFloorTypes()[0].getName(),request.getFloorTypes()[0].getName());
        Assert.assertEquals(responseObject.getFloorTypes()[0].getCode(),request.getFloorTypes()[0].getCode());
        Assert.assertEquals(responseObject.getFloorTypes()[0].getNameLocal(),request.getFloorTypes()[0].getNameLocal());
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"SUCCESSFUL");

        return responseObject;
    }

}
