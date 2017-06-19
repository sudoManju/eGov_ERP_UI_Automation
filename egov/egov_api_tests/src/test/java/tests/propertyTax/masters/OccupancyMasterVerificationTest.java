package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.occupancy.CreateOccupancyMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.occupancy.CreateOccupancyMasterRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.occupancy.create.CreateOccupancyMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.OccupancyMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class OccupancyMasterVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void OccupancyMasterTest()throws IOException{

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        CreateOccupancyMasterResponse create = createOccupancyMaster(loginResponse);   //Create

        SearchHelper helper = new SearchHelper(loginResponse);

        helper.searchOccupancyMaster(create);     //Search

        LoginAndLogoutHelper.logout(loginResponse); //Logout
    }

    private CreateOccupancyMasterResponse createOccupancyMaster(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create Occupancy Master is started --");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateOccupancyMasterRequest request = new CreateOccupancyMasterRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new OccupancyMasterResource().createOccupancyMaster(RequestHelper.getJsonString(request));

        Assert.assertEquals(response.getStatusCode(),200);

        CreateOccupancyMasterResponse response1 = (CreateOccupancyMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateOccupancyMasterResponse.class);

        Assert.assertEquals(response1.getOccuapancyMasters()[0].getName(),request.getOccuapancyMasters()[0].getName());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getNameLocal(),request.getOccuapancyMasters()[0].getNameLocal());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getCode(),request.getOccuapancyMasters()[0].getCode());

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        return response1;
    }
}
