package tests.pgrCollection;

import builders.pgrCollection.receivingCenters.ReceivingCentersRequestBuilder;
import builders.pgrCollection.receivingCenters.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.receivingCenters.ReceivingCentersRequest;
import entities.requests.pgrCollections.receivingCenters.RequestInfo;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

public class ReceivingCentersAndModesTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void receivingCentersTest()throws IOException{

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        //AllReceivingCenters
        getAllReceivingCentersTest(loginResponse);

        //ReceivingCenterById
        getReceivingCenterByIdTest(loginResponse);

        //ReceivingMode
        getAllReceivingModesTest(loginResponse);
    }

    private void getAllReceivingModesTest(LoginResponse loginResponse) {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        ReceivingCentersRequest request = new ReceivingCentersRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getReceivingModes(json);

        Assert.assertEquals(response.getStatusCode(),200);
    }

    private void getAllReceivingCentersTest(LoginResponse loginResponse){

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        ReceivingCentersRequest request = new ReceivingCentersRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getReceivingCenter(json);

        Assert.assertEquals(response.getStatusCode(),200);

    }

    private void getReceivingCenterByIdTest(LoginResponse loginResponse){

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        ReceivingCentersRequest request = new ReceivingCentersRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getReceivingCenterById(json);

        Assert.assertEquals(response.getStatusCode(),200);
    }

}
