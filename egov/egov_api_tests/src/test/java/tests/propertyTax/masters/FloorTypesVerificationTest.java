package tests.propertyTax.masters;


import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.floorTypes.CreateFloorTypesRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.floorType.CreateFloorTypesRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.floorTypes.create.FloorTypesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.FloorTypesResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class FloorTypesVerificationTest extends BaseAPITest {

  @Test
  public void floorTypesTest() throws IOException {

      LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);  //Login

      FloorTypesResponse create = createFloorTypes(loginResponse);  //Create

      SearchHelper helper = new SearchHelper(loginResponse);

      helper.searchFloorTypesMaster(create);      //Search
  }

    private FloorTypesResponse createFloorTypes(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create Floor Types Master is started --");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateFloorTypesRequest request = new CreateFloorTypesRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new FloorTypesResource().createFloorTypeMaster(RequestHelper.getJsonString(request));

        Assert.assertEquals(response.getStatusCode(),200);

        FloorTypesResponse response1 = (FloorTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),FloorTypesResponse.class);

        Assert.assertEquals(response1.getFloorTypes()[0].getName(),request.getFloorTypes()[0].getName());
        Assert.assertEquals(response1.getFloorTypes()[0].getCode(),request.getFloorTypes()[0].getCode());
        Assert.assertEquals(response1.getFloorTypes()[0].getNameLocal(),request.getFloorTypes()[0].getNameLocal());

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        new APILogger().log("Create Floor Types Master is completed --");

        return response1;
    }

}
