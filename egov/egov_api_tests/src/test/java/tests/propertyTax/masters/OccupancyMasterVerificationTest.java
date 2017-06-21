package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.occupancy.CreateOccupancyMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.occupancy.CreateOccupancyMasterRequest;
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
        LoginAndLogoutHelper.login(NARASAPPA);   //Login

        CreateOccupancyMasterResponse create = createOccupancyMaster();   //Create

        SearchHelper helper = new SearchHelper();

        helper.searchOccupancyMaster(create);     //Search

        LoginAndLogoutHelper.logout1(); //Logout
    }

    private CreateOccupancyMasterResponse createOccupancyMaster() throws IOException {
        new APILogger().log("Create Occupancy Master is Started");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateOccupancyMasterRequest request = new CreateOccupancyMasterRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new OccupancyMasterResource().create(RequestHelper.getJsonString(request));
        CreateOccupancyMasterResponse response1 = (CreateOccupancyMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateOccupancyMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getName(),request.getOccuapancyMasters()[0].getName());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getNameLocal(),request.getOccuapancyMasters()[0].getNameLocal());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getCode(),request.getOccuapancyMasters()[0].getCode());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        new APILogger().log("Create Occupancy Master is Completed");

        return response1;
    }
}
